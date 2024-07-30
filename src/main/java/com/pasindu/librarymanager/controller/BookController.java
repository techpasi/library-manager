package com.pasindu.librarymanager.controller;

import com.pasindu.librarymanager.dto.ApiResponseDTO;
import com.pasindu.librarymanager.entity.Book;
import com.pasindu.librarymanager.entity.BookDetails;
import com.pasindu.librarymanager.repository.BookRepository;
import com.pasindu.librarymanager.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @GetMapping
    public ApiResponseDTO getAllBooks(
            @RequestParam(value = "unique", defaultValue = "false") boolean unique
    ) {
        List<Book> books = bookService.getAllBooks(unique);
        return new ApiResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null, books);
    }

    @PostMapping("/create")
    public ApiResponseDTO createBook(@Valid @RequestBody BookDetails bookDetails) {
        Book newBook = bookService.create(bookDetails);
        return new ApiResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), Optional.of(newBook), null);
    }

}
