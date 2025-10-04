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
@Table (name = "borrow_records")
public class BorrowRecord {
    @Id
    private UUID id;
    @Column (nullable = false)
    private UUID bookId;
    @Column (nullable = false)
    private UUID memberId;
    private LocalDate borrowDate;
    @Column(nullable = true)
    private LocalDate returnDate;
}
