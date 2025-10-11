package com.example.library_management_system.service;

import com.example.library_management_system.entity.Book;
import com.example.library_management_system.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void testGetBookByTitle_UsingFake() {
        abstract class FakeBookRepository implements BookRepository {
            List<Book> books = new ArrayList<>();
            @Override
            public List<Book> findAll() { return books; }
            public void addBook(Book b) { books.add(b); }
        }

        FakeBookRepository fakeRepo = new FakeBookRepository();
        fakeRepo.addBook(new Book(UUID.randomUUID(), "Fake Title", "Fake Author", "321", 2022, 10));

        BookService fakeService = new BookService(fakeRepo);
        Book result = fakeService.getBookByTitle("Fake Title");

        assertThat(result.getTitle()).isEqualTo("Fake Title");
    }
}
