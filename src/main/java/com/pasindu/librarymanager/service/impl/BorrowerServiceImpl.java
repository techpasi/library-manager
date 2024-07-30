package com.pasindu.librarymanager.service.impl;

import com.pasindu.librarymanager.entity.Book;
import com.pasindu.librarymanager.entity.BookDetails;
import com.pasindu.librarymanager.entity.Borrower;
import com.pasindu.librarymanager.exception.ValidationException;
import com.pasindu.librarymanager.repository.BookDetailsRepository;
import com.pasindu.librarymanager.repository.BookRepository;
import com.pasindu.librarymanager.repository.BorrowerRepository;
import com.pasindu.librarymanager.service.BookService;
import com.pasindu.librarymanager.service.BorrowerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BorrowerServiceImpl implements BorrowerService {

    private static final Logger LOG = LoggerFactory.getLogger(BorrowerServiceImpl.class);

    @Autowired
    private BorrowerRepository borrowerRepository;

    public List<Borrower> getAll() {
        return borrowerRepository.findAll();
    }

    public Borrower create(Borrower borrower) {
        Optional<Borrower> checkBorrower = borrowerRepository.findByEmail(borrower.getEmail());
        if(checkBorrower.isPresent()){
            LOG.error("Already registered borrower email, email: {}", borrower.getEmail());
            throw new ValidationException("Already registered borrower email");
        }

        return borrowerRepository.save(borrower);
    }
}
