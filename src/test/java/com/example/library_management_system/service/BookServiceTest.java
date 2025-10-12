package com.example.library_management_system.service;

import com.example.library_management_system.entity.Book;
import com.example.library_management_system.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void testGetBookByTitle_UsingSpy() {
        BookRepository realRepo = mock(BookRepository.class);
        when(realRepo.findAll()).thenReturn(List.of(new Book(UUID.randomUUID(), "Spy Title", "Spy Author", "654", 2023, 6)));
        BookService spyService = spy(new BookService(realRepo));
        Book result = spyService.getBookByTitle("Spy Title");
        assertThat(result.getTitle()).isEqualTo("Spy Title");
    }

    @Test
    void testCreateBook_UsingMock() {
        Book mockBook = new Book(UUID.randomUUID(), "Mock Title", "Mock Author", "ISBN123", 2023, 5);
        ResponseEntity<String> response = bookService.createBook(mockBook);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
}

