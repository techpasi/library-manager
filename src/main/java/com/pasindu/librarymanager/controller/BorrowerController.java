package com.pasindu.librarymanager.controller;

import com.pasindu.librarymanager.dto.ApiResponseDTO;
import com.pasindu.librarymanager.entity.Book;
import com.pasindu.librarymanager.entity.Borrower;
import com.pasindu.librarymanager.service.BorrowerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/borrower")
public class BorrowerController {

    @Autowired
    private BorrowerService borrowerService;

    @GetMapping
    public ApiResponseDTO getAll() {
        List<Borrower> borrowers = borrowerService.getAll();
        return new ApiResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null, borrowers);
    }

    @PostMapping("/create")
    public ApiResponseDTO create(@Valid @RequestBody Borrower borrower) {
        Borrower newBorrower = borrowerService.create(borrower);
        return new ApiResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), Optional.of(newBorrower), null);
    }

}
