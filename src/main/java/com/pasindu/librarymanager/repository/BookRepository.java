package com.pasindu.librarymanager.repository;

import com.pasindu.librarymanager.entity.Book;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Book> findById(int id);
}