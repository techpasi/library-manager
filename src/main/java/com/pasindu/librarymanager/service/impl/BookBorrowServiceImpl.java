package com.pasindu.librarymanager.service.impl;

import com.pasindu.librarymanager.dto.BookBorrowDTO;
import com.pasindu.librarymanager.entity.Book;
import com.pasindu.librarymanager.entity.BookBorrow;
import com.pasindu.librarymanager.entity.Borrower;
import com.pasindu.librarymanager.enumeration.BookStatusEnum;
import com.pasindu.librarymanager.exception.ValidationException;
import com.pasindu.librarymanager.repository.BookBorrowRepository;
import com.pasindu.librarymanager.repository.BookRepository;
import com.pasindu.librarymanager.repository.BorrowerRepository;
import com.pasindu.librarymanager.service.BookBorrowService;
import com.pasindu.librarymanager.service.BorrowerService;
import jakarta.persistence.LockModeType;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookBorrowServiceImpl implements BookBorrowService {

    private static final Logger LOG = LoggerFactory.getLogger(BookBorrowServiceImpl.class);

    @Autowired
    private BookBorrowRepository bookBorrowRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowerRepository borrowerRepository;



    public List<BookBorrow> getAll() {
        return bookBorrowRepository.findAll();
    }

    @Transactional
    public BookBorrow borrowBook(BookBorrowDTO bookBorrowDTO) {
        Optional<Borrower> borrower = borrowerRepository.findById(bookBorrowDTO.getBorrowerId());
        Optional<Book> book = bookRepository.findById(bookBorrowDTO.getBookId());

        //validation
        bookBorrowingValidation(bookBorrowDTO, borrower,book);

        BookBorrow bookBorrow = new BookBorrow();
        bookBorrow.setBook(book.get());
        bookBorrow.setBorrower(borrower.get());
        bookBorrow.setBorrowDate(LocalDateTime.now());
        bookBorrow = bookBorrowRepository.save(bookBorrow);

        book.get().setStatus(BookStatusEnum.BORROWED);
        bookRepository.save(book.get());

        LOG.info("Book borrowed, bookId: {} | borrowerId: {}",bookBorrowDTO.getBookId(), bookBorrowDTO.getBorrowerId());
        return bookBorrow;
    }

    @Transactional
    public BookBorrow returnBook(BookBorrowDTO bookBorrowDTO) {
        Optional<Borrower> borrower = borrowerRepository.findById(bookBorrowDTO.getBorrowerId());
        Optional<Book> book = bookRepository.findById(bookBorrowDTO.getBookId());

        //validation
        BookBorrow bookBorrow = bookBorrowReturnValidation(bookBorrowDTO, borrower,book);
        bookBorrow.setReturnDate((bookBorrowDTO.getReturnDate() != null ? bookBorrowDTO.getReturnDate() : LocalDateTime.now()));
        bookBorrowRepository.save(bookBorrow);

        book.get().setStatus(BookStatusEnum.AVAILABLE);
        bookRepository.save(book.get());
        LOG.info("Book borrow returned, bookId: {} | borrowerId: {}",bookBorrowDTO.getBookId(), bookBorrowDTO.getBorrowerId());
        return bookBorrow;
    }

    //check book borrow validation
    private void bookBorrowingValidation(BookBorrowDTO bookBorrowDTO, Optional<Borrower> borrower, Optional<Book> book){
        if(borrower.isEmpty()){
            LOG.error("Unable to find borrower, id: {}", bookBorrowDTO.getBorrowerId());
            throw new NoSuchElementException("Unable to find borrower");
        }
        if(book.isEmpty()){
            LOG.error("Unable to find book, id: {}", bookBorrowDTO.getBorrowerId());
            throw new NoSuchElementException("Unable to find book");

        }else if(!book.get().getStatus().equals(BookStatusEnum.AVAILABLE)){
            LOG.error("Book is not available, bookId: {}", bookBorrowDTO.getBookId());
            throw new IllegalStateException("Book is not available");
        }

        if(book.isPresent() && borrower.isPresent()){
            List<BookBorrow> borrowerBorrowedBooks = bookBorrowRepository.findByBorrowerIdAndBookDetailsId(bookBorrowDTO.getBorrowerId(), book.get().getBookDetails().getId());
            if(!borrowerBorrowedBooks.isEmpty()){
                LOG.error("Borrower already borrowed this book, bookId: {} | borrowerId: {} ", bookBorrowDTO.getBookId(), bookBorrowDTO.getBorrowerId());
                throw new IllegalStateException("Borrower already borrowed this book");
            }
        }
    }

    //check book borrow return validation
    private BookBorrow bookBorrowReturnValidation(BookBorrowDTO bookBorrowDTO, Optional<Borrower> borrower, Optional<Book> book){
        if(borrower.isEmpty()){
            LOG.error("Unable to find borrower, id: {}", bookBorrowDTO.getBorrowerId());
            throw new NoSuchElementException("Unable to find borrower");
        }
        if(book.isEmpty()){
            LOG.error("Unable to find book, id: {}", bookBorrowDTO.getBorrowerId());
            throw new NoSuchElementException("Unable to find book");

        }else if(!book.get().getStatus().equals(BookStatusEnum.BORROWED)){
            LOG.error("Book is not borrowed, bookId: {}", bookBorrowDTO.getBookId());
            throw new IllegalStateException("Book is not borrowed");
        }

        Optional<BookBorrow> bookBorrow = bookBorrowRepository.findByBookIdAndBorrowerIdAndReturnDateIsNull(bookBorrowDTO.getBookId(), bookBorrowDTO.getBorrowerId());
        if(bookBorrow.isEmpty()){
            LOG.error("Unable to find the borrowed record, bookId: {} | borrowerId", bookBorrowDTO.getBorrowerId(), bookBorrowDTO.getBorrowerId());
            throw new NoSuchElementException("Unable to find the borrowed record");
        }
        return bookBorrow.get();
    }
}
