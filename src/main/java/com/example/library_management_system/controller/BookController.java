package com.example.library_management_system.controller;

import com.example.library_management_system.dto.UpdateCopiesRequest;
import com.example.library_management_system.entity.Book;
import com.example.library_management_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    public BookController() {
    }

    @GetMapping
    public List <Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @DeleteMapping ("/{id}")
    public String deleteBook(@PathVariable UUID id, BookService bookService) {
      bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted").toString();
    }

    @GetMapping ("/title/{title}")
    public Book getBookByTitle(@PathVariable String title) {
        return bookService. getBookByTitle(title);
    }

    @PostMapping
    public ResponseEntity <String> createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PatchMapping ("/{id}/copies")
    public Book updateBookCopies(@PathVariable UUID id, @RequestBody UpdateCopiesRequest request) {
        return bookService.updateBookCopies(id, request);
    }
}
