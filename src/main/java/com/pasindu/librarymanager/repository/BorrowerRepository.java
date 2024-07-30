package com.pasindu.librarymanager.repository;

import com.pasindu.librarymanager.entity.Borrower;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Borrower> findById(int id);

    Optional<Borrower> findByEmail(String email);
}