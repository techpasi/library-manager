package com.pasindu.librarymanager.service.impl;

import com.pasindu.librarymanager.entity.Book;
import com.pasindu.librarymanager.entity.BookDetails;
import com.pasindu.librarymanager.enumeration.BookStatusEnum;
import com.pasindu.librarymanager.exception.ValidationException;
import com.pasindu.librarymanager.repository.BookDetailsRepository;
import com.pasindu.librarymanager.repository.BookRepository;
import com.pasindu.librarymanager.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger LOG = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookDetailsRepository bookDetailsRepository;

    public List<Book> getAllBooks(boolean unique) {
        List<Book> books = bookRepository.findAll();
        if(unique){
            books = books.stream()
                .collect(Collectors.collectingAndThen(
                    Collectors.toMap(
                        book -> book.getBookDetails().getIsbn(),
                        book -> book,
                        (book1, book2) -> book1
                    ),
                    map -> new ArrayList<>(map.values())
                ));
        }
        return books;
    }



    public Book create(BookDetails bookDetails) {
        Optional<BookDetails> existingBookDetails = bookDetailsRepository.findByIsbn(bookDetails.getIsbn());

        if(existingBookDetails.isEmpty()){
            existingBookDetails = Optional.of(bookDetailsRepository.save(bookDetails));
        }else{
            if(!bookDetails.getAuthor().equals(existingBookDetails.get().getAuthor()) && !bookDetails.getTitle().equals(existingBookDetails.get().getTitle())){
                LOG.error("Already registered isbn with different author or title found, isbn: {}", bookDetails.getIsbn());
                throw new ValidationException("Already registered isbn with different author or title found");
            }
        }
        Book newBook = new Book();
        newBook.setBookDetails(existingBookDetails.get());
        newBook.setStatus(BookStatusEnum.AVAILABLE);
        return bookRepository.save(newBook);
    }
}
