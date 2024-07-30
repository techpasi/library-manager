package com.pasindu.librarymanager.repository;

import com.pasindu.librarymanager.entity.BookBorrow;
import com.pasindu.librarymanager.entity.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookBorrowRepository extends JpaRepository<BookBorrow, Integer> {

    @Query("SELECT bb FROM BookBorrow bb WHERE bb.returnDate IS NULL AND bb.borrower.id = :borrowerId AND bb.book.bookDetails.id = :bookDetailsId")
    List<BookBorrow> findByBorrowerIdAndBookDetailsId(@Param("borrowerId") int borrowerId, @Param("bookDetailsId") int bookDetailsId);

    Optional<BookBorrow> findByBookIdAndBorrowerIdAndReturnDateIsNull(int borrowerId,int bookDetailsId);
}