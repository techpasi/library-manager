package com.pasindu.librarymanager.service;

import com.pasindu.librarymanager.entity.Book;
import com.pasindu.librarymanager.entity.BookDetails;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks(boolean unique);
    Book create(BookDetails bookDetails);
}
