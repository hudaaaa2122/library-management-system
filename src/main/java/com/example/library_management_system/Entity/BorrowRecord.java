package com.example.library_management_system.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "borrow_records" , uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class BorrowRecord {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    @Column (unique = true, nullable = false)
    private String email;
    private LocalDate membershipDate;
}
