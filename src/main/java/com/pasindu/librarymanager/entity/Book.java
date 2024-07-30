package com.pasindu.librarymanager.entity;

import com.pasindu.librarymanager.enumeration.BookStatusEnum;
import com.pasindu.librarymanager.enumeration.RoleEnum;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_detail_id")
    private BookDetails bookDetails;

    @Column
    @Enumerated(EnumType.STRING)
    private BookStatusEnum status;
}
