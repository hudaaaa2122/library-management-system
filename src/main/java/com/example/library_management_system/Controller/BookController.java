package com.example.library_management_system.Controller;

import com.example.library_management_system.Entity.Book;
import com.example.library_management_system.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping ("/all")
    public List <Book> getAllBooks() {
        return bookRepository.findAll();
    }
    @DeleteMapping ("/delete/{id}")
    public String deleteBook(@PathVariable UUID id) {
        bookRepository.deleteById(id);
        return "Book deleted";
    }
    @GetMapping ("/{title}")
    public Book getBookByTitle(@PathVariable String title) {
        return bookRepository.findAll().stream().filter(book -> book.getTitle().equals(title)).findFirst()
                .orElseThrow(() -> new RuntimeException("Book not found with title: " + title));
    }
    @PostMapping ("/create")
    public String createBook(@RequestBody Book book) {
        bookRepository.save(book);
        return "Book created";
    }
    @PatchMapping ("/update/{id}/copies")
    public Book updateBookCopies(@PathVariable UUID id, @RequestBody UpdateCopiesRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        book.setAvailableCopies(request.getAvailableCopies());
        return bookRepository.save(book);
    }
}
