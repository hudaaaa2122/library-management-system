package com.example.library_management_system.controller;

import com.example.library_management_system.entity.Book;
import com.example.library_management_system.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List <Book> getAllBooks() {
        return bookRepository.findAll();
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity <String> deleteBook(@PathVariable UUID id) {
        bookRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted");
    }
    @GetMapping ("/{title}")
    public Book getBookByTitle(@PathVariable String title) {
        return bookRepository.findAll().stream().filter(book -> book.getTitle().equals(title)).findFirst()
                .orElseThrow(() -> new RuntimeException("Book not found with title: " + title));
    }
    @PostMapping
    public ResponseEntity <String> createBook(@RequestBody Book book) {
        book.setId(UUID.randomUUID());
        bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.OK).body("Book created");
    }
    @PatchMapping ("/{id}/copies")
    public Book updateBookCopies(@PathVariable UUID id, @RequestBody UpdateCopiesRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        book.setAvailableCopies(request.getAvailableCopies());
        return bookRepository.save(book);
    }
}
