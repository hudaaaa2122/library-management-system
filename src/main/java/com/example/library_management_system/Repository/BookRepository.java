package com.example.library_management_system.Repository;

import com.example.library_management_system.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

}

