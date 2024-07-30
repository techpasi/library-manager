package com.pasindu.librarymanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Data
public class BookBorrowDTO {
    @NotNull(message = "book id is required")
    private int bookId;

    @NotNull(message = "borrower id is required")
    private int borrowerId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime borrowDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime returnDate;

    public BookBorrowDTO(int bookId, int borrowerId, LocalDateTime borrowDate, LocalDateTime returnDate) {
        this.bookId = bookId;
        this.borrowerId = borrowerId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }
}
