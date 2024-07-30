package com.pasindu.librarymanager.service;

import com.pasindu.librarymanager.entity.Borrower;

import java.util.List;

public interface BorrowerService {

    List<Borrower> getAll();
    Borrower create(Borrower borrower);
}
