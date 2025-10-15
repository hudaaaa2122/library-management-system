package com.example.library_management_system.service;

import com.example.library_management_system.entity.Book;
import com.example.library_management_system.entity.Member;
import com.example.library_management_system.repository.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;


    @Test
    void testgetAllBooks_UsingMock() {
        //given
        when(bookRepository.findAll()).thenReturn(List.of(
                new Book(UUID.randomUUID(), "Mock Title1", "Mock Author1", "ISBN123", 2023, 5),
                new Book(UUID.randomUUID(), "Mock Title2", "Mock Author2", "ISBN124", 2022, 3)
        ));
        //when

        List<Book> books = bookService.getAllBooks();
        //then
        assertThat(books.size()).isEqualTo(2);
    }


    @Test
    void testGetBookByTitle_UsingSpy() {
        //given
        BookRepository spyRepo = mock(BookRepository.class);
        when(spyRepo.findAll()).thenReturn(List.of(new Book(UUID.randomUUID(), "Spy Title", "Spy Author", "654", 2023, 6)));
        //when
        BookService spyService = spy(new BookService(spyRepo));
        Book result = spyService.getBookByTitle("Spy Title");
        //then
        assertThat(result.getTitle()).isEqualTo("Spy Title");
    }

    @Test
    void testCreateBook_UsingMock() {
        //given
        Book mockBook = new Book(UUID.randomUUID(), "Mock Title", "Mock Author", "ISBN123", 2023, 5);
        //when
        ResponseEntity<String> response = bookService.createBook(mockBook);
        //then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }



}
class BookServiceFakeTest {
    @Test
    void testdeleteBook_UsingFakeRepository() {
        FakeBookRepository fakeRepo = new FakeBookRepository();
        BookService service = new BookService(fakeRepo);
        UUID id = UUID.randomUUID();
        Book fakeBook = new Book(id, "Fake Title", "Fake Author", "ISBN456", 2022, 3);
        fakeRepo.save(fakeBook);
        ResponseEntity<String> response = service.deleteBook(id);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testupdateBookCopies_UsingStub() {
        UUID id = UUID.randomUUID();
        Book expected = new Book(id, "Stub Title", "Stub Author", "ISBN789", 2021, 4);
        BookRepository stubRepo = new BookRepositoryStubBase() {
            @Override
            public Optional<Book> findById(UUID uuid) {
                if (uuid.equals(id)) return Optional.of(expected);
                return Optional.empty();
            };
            @Override
            public Book save(Book book) {
                return book;
            }
        };
        BookService service = new BookService(stubRepo);
        Book actual = service.updateBookCopies(id, new com.example.library_management_system.dto.UpdateCopiesRequest());
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals (expected.getAvailableCopies() , actual.getAvailableCopies());
    }
}



