package com.pasindu.librarymanager.service;


import com.pasindu.librarymanager.dto.BookBorrowDTO;
import com.pasindu.librarymanager.entity.Book;
import com.pasindu.librarymanager.entity.BookBorrow;
import com.pasindu.librarymanager.entity.BookDetails;
import com.pasindu.librarymanager.entity.Borrower;
import com.pasindu.librarymanager.enumeration.BookStatusEnum;
import com.pasindu.librarymanager.repository.BookBorrowRepository;
import com.pasindu.librarymanager.repository.BookDetailsRepository;
import com.pasindu.librarymanager.repository.BookRepository;
import com.pasindu.librarymanager.repository.BorrowerRepository;
import com.pasindu.librarymanager.service.impl.BookBorrowServiceImpl;
import com.pasindu.librarymanager.service.impl.BookServiceImpl;
import com.pasindu.librarymanager.service.impl.BorrowerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookDetailsRepository bookDetailsRepository;

    @Mock
    private BookBorrowRepository bookBorrowRepository;

    @Mock
    private BorrowerRepository borrowerRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @InjectMocks
    private BookBorrowServiceImpl bookBorrowService;

    @InjectMocks
    private BorrowerServiceImpl borrowerService;


    private Book book;
    private BookDetails bookDetails;
    private BookBorrowDTO bookBorrowDTO;
    private Borrower borrower;
    private BookBorrow bookBorrow;

    @BeforeEach
    void setUp() {
        bookDetails = new BookDetails();
        bookDetails.setId(1);
        bookDetails.setAuthor("Author Name");
        bookDetails.setIsbn("123-123-123");
        bookDetails.setTitle("Harry Potter");

        book = new Book();
        book.setId(1);
        book.setStatus(BookStatusEnum.AVAILABLE);
        book.setBookDetails(bookDetails);

        borrower = new Borrower();
        borrower.setId(1);
        borrower.setName("Pasindu");
        borrower.setEmail("nimotech.cs@gmail.com");

        bookBorrowDTO = new BookBorrowDTO(1,1,null,null);

        bookBorrow = new BookBorrow();
        bookBorrow.setBook(book);
        bookBorrow.setBorrower(borrower);
        bookBorrow.setBorrowDate(LocalDateTime.now());


    }

    @Test
    public void testCreateBook_Success() {
        // Arrange
        when(bookDetailsRepository.save(any(BookDetails.class))).thenReturn(bookDetails);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        // Act
        Book createdBook = bookService.create(bookDetails);

        // Assert
        Assertions.assertNotNull(createdBook);
        Assertions.assertEquals(book.getId(), createdBook.getId());
        Assertions.assertEquals(BookStatusEnum.AVAILABLE, createdBook.getStatus());
        Assertions.assertEquals(bookDetails, createdBook.getBookDetails());
    }

    @Test
    public void testGetAllBooks_Success() {
        // Arrange
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));

        // Act
        List<Book> books = bookService.getAllBooks(false);
        List<Book> uniqueBooks = bookService.getAllBooks(true);

        // Assert
        Assertions.assertEquals(1, books.size());
        Assertions.assertEquals(1, uniqueBooks.size());
    }

    @Test
    public void testBorrowBook_Success() {
        // Arrange
        when(borrowerRepository.findById(bookBorrowDTO.getBorrowerId())).thenReturn(Optional.of(borrower));
        when(bookRepository.findById(bookBorrowDTO.getBookId())).thenReturn(Optional.of(book));
        when(bookBorrowRepository.save(any(BookBorrow.class))).thenReturn(bookBorrow);

        // Act
        BookBorrow result = bookBorrowService.borrowBook(bookBorrowDTO);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(book, result.getBook());
        Assertions.assertEquals(borrower, result.getBorrower());
        Assertions.assertNotNull(result.getBorrowDate());
        Assertions.assertEquals(BookStatusEnum.BORROWED, book.getStatus());
    }
}
