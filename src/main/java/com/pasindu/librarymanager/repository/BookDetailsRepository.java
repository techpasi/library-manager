package com.pasindu.librarymanager.repository;

import com.pasindu.librarymanager.entity.Book;
import com.pasindu.librarymanager.entity.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetails, Integer> {
    Optional<BookDetails> findByIsbn(String isbn);
}