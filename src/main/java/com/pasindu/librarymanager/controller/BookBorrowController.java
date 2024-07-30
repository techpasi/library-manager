package com.pasindu.librarymanager.controller;

import com.pasindu.librarymanager.dto.ApiResponseDTO;
import com.pasindu.librarymanager.dto.BookBorrowDTO;
import com.pasindu.librarymanager.entity.BookBorrow;
import com.pasindu.librarymanager.service.BookBorrowService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book-borrow")
public class BookBorrowController {

    @Autowired
    private BookBorrowService bookBorrowService;

    @GetMapping
    public ApiResponseDTO getAll() {
        List<BookBorrow> bookBorrows = bookBorrowService.getAll();
        return new ApiResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null, bookBorrows);
    }

    @PostMapping("/borrow")
    public ApiResponseDTO borrowBook(@Valid @RequestBody BookBorrowDTO bookBorrowDTO) {
        BookBorrow newBookBorrower = bookBorrowService.borrowBook(bookBorrowDTO);
        return new ApiResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null, null);
    }

    @PostMapping("/return")
    public ApiResponseDTO returnBook(@Valid @RequestBody BookBorrowDTO bookBorrowDTO) {
        BookBorrow returnBookBorrower = bookBorrowService.returnBook(bookBorrowDTO);
        return new ApiResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null, null);
    }

}
