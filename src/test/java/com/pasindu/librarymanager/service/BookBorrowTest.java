package com.pasindu.librarymanager.service;


import com.pasindu.librarymanager.entity.Borrower;
import com.pasindu.librarymanager.repository.BorrowerRepository;
import com.pasindu.librarymanager.service.impl.BorrowerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookBorrowTest {
    @Mock
    private BorrowerRepository borrowerRepository;

    @InjectMocks
    private BorrowerServiceImpl borrowerService;

    private Borrower borrower;

    @BeforeEach
    void setUp() {
        borrower = new Borrower();
        borrower.setId(1);
        borrower.setName("Pasindu");
        borrower.setEmail("nimotech.cs@gmail.com");

    }

    @Test
    public void testRegisterBorrower_Success() {
        // Arrange
        when(borrowerRepository.save(any(Borrower.class))).thenReturn(borrower);

        // Act
        Borrower createdBorrower = borrowerService.create(borrower);

        // Assert
        Assertions.assertNotNull(createdBorrower);
        Assertions.assertEquals("Pasindu", createdBorrower.getName());
    }
}
