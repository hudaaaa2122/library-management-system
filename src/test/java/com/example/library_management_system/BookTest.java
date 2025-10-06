package com.example.library_management_system;

import com.example.library_management_system.entity.Book;
import com.example.library_management_system.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class BookTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        bookRepository.deleteAll();
    }
    @Test
    void testCreateBook() throws Exception {
        Book book = new Book(null , "Book Title", "Author Name", "ISBN123456", 5, 5);
        mockMvc.perform(post("/books/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());
        assertThat(bookRepository.findAll()).hasSize(1);
    }
    @Test
    void testUpdateBookCopies() throws Exception {
        Book book = new Book(null , "Book Title", "Author Name", "ISBN123456", 5, 5);
        book = bookRepository.save(book);
        int newCopies = 10;
        mockMvc.perform(patch("/books/update/" + book.getId() + "/copies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"availableCopies\":" + newCopies + "}"))
                .andExpect(status().isOk());
        Book updatedBook = bookRepository.findById(book.getId()).orElseThrow();
        assertThat(updatedBook.getAvailableCopies()).isEqualTo(newCopies);
    }
    @Test
    void testDeleteBook() throws Exception {
        Book book = new Book(null , "Book Title", "Author Name", "ISBN123456", 5, 5);
        book = bookRepository.save(book);
        mockMvc.perform(delete("/books/delete/" + book.getId()))
                .andExpect(status().isOk());
        assertThat(bookRepository.findById(book.getId())).isEmpty();
    }
}
