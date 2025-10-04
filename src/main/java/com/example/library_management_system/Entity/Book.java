package com.example.library_management_system.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "books" , uniqueConstraints = {@UniqueConstraint(columnNames = "isbn")})
public class Book {
    @Id
    private UUID id;
    @Column
    private String title;
    @Column
    private String author;
    @Column (unique = true, nullable = false)
    private String isbn;
    @Column
    private int publishedYear;
    @Column
    private int availableCopies;
}
