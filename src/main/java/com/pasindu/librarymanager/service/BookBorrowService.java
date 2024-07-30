package com.pasindu.librarymanager.service;

import com.pasindu.librarymanager.dto.BookBorrowDTO;
import com.pasindu.librarymanager.entity.BookBorrow;
import com.pasindu.librarymanager.entity.Borrower;

import java.util.List;

public interface BookBorrowService {

    List<BookBorrow> getAll();
    BookBorrow borrowBook(BookBorrowDTO bookBorrowDTO);
    BookBorrow returnBook(BookBorrowDTO bookBorrowDTO);
}
