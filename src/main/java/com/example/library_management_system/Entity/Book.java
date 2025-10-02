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
    @GeneratedValue
    private UUID id;
    private String title;
    private String author;
    @Column (unique = true, nullable = false)
    private String isbn;
    private int publishedYear;
    private int availableCopies;
}
