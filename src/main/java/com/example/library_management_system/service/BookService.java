package com.example.library_management_system.service;
import com.example.library_management_system.dto.UpdateCopiesRequest;
import com.example.library_management_system.entity.Book;
import com.example.library_management_system.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    public ResponseEntity<String> deleteBook(@PathVariable UUID id) {
        bookRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted");
    }

    public Book getBookByTitle(@PathVariable String title) {
        return bookRepository.findAll().stream().filter(book -> book.getTitle().equals(title)).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with title: " + title));
    }


    public ResponseEntity <String> createBook(@RequestBody Book book) {
        book.setId(UUID.randomUUID());
        bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.OK).body("Book created");
    }


    public Book updateBookCopies(@PathVariable UUID id, @RequestBody UpdateCopiesRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id: " + id));
        book.setAvailableCopies(request.getAvailableCopies());
        return bookRepository.save(book);



    }
}
