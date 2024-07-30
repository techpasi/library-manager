package com.pasindu.librarymanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class BookDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column
    private String isbn;

    @Size(max = 255)
    @NotNull
    @Column
    private String title;

    @Size(max = 255)
    @NotNull
    @Column
    private String author;
}
