package com.pasindu.librarymanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Borrower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column
    private String name;

    @Size(max = 255)
    @NotNull
    @Email
    @Column
    private String email;

}
