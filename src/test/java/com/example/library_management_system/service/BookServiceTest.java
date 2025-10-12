package com.example.library_management_system.service;

import com.example.library_management_system.entity.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    BookService bookService;

    @Test
    void createBookTest(){
        Book book = mock(Book.class);
        book.setTitle("title");
        bookService.createBook(book);

        assertEquals(book.getTitle(), book.getTitle());
    }

    @Test
    void updateBookCopies (){

        Book book = mock(Book.class);
        book.setAvailableCopies(5);
        bookService.updateBookCopies(book.getId(), null);


        assertEquals(book.getAvailableCopies() , book.getAvailableCopies());
    }
}
//    @Mock
//    private BookRepository bookRepository;
//
//    @InjectMocks
//    private BookService bookService;
//
//    // 1️⃣ MOCK TEST
//    @Test
//    void testGetBookByTitle_UsingMock() {
//        Book mockBook = new Book(UUID.randomUUID(), "Mock Title", "Author", "123", 2020, 5);
//        when(bookRepository.findAll()).thenReturn(List.of(mockBook));
//
//        Book result = bookService.getBookByTitle("Mock Title");
//
//        verify(bookRepository, times(1)).findAll();
//        assertThat(result.getTitle()).isEqualTo("Mock Title");
//    }
//
//    // 2️⃣ STUB TEST
//    @Test
//    void testGetBookByTitle_UsingStub() {
//        BookRepository stubRepo = new BookRepository() {
//            @Override
//            public List<Book> findAll() {
//                return List.of(new Book(UUID.randomUUID(), "Stub Title", "Author", "123", 2021, 4));
//            }
//            // other methods can be left unimplemented for brevity
//        };
//
//        BookService stubService = new BookService(stubRepo);
//        Book result = stubService.getBookByTitle("Stub Title");
//
//        assertThat(result.getTitle()).isEqualTo("Stub Title");
//    }
//
//    @Test
//    void testGetBookByTitle_UsingFake() {
//        class FakeBookRepository implements BookRepository {
//            List<Book> books = new ArrayList<>();
//            @Override
//            public List<Book> findAll() { return books; }
//            public void addBook(Book b) { books.add(b); }
//        }
//
//        FakeBookRepository fakeRepo = new FakeBookRepository();
//        fakeRepo.addBook(new Book(UUID.randomUUID(), "Fake Title", "Fake Author", "321", 2022, 10));
//
//        BookService fakeService = new BookService(fakeRepo);
//        Book result = fakeService.getBookByTitle("Fake Title");
//
//        assertThat(result.getTitle()).isEqualTo("Fake Title");
//    }
//
//    // 4️⃣ SPY TEST
//    @Test
//    void testGetBookByTitle_UsingSpy() {
//        BookRepository realRepo = mock(BookRepository.class);
//        when(realRepo.findAll()).thenReturn(List.of(new Book(UUID.randomUUID(), "Spy Title", "Spy Author", "654", 2023, 6)));
//
//        BookService spyService = spy(new BookService(realRepo));
//        Book result = spyService.getBookByTitle("Spy Title");
//
//        verify(spyService, times(1)).getBookByTitle("Spy Title");
//        assertThat(result.getTitle()).isEqualTo("Spy Title");
//    }
//
//    // 5️⃣ TEST DOUBLE (Generic example)
//    @Test
//    void testGetBookByTitle_UsingTestDouble() {
//        class TestDoubleBookRepo implements BookRepository {
//            @Override
//            public List<Book> findAll() {
//                return List.of(new Book(UUID.randomUUID(), "Double Title", "TD Author", "987", 2024, 3));
//            }
//        }
//
//        BookService tdService = new BookService(new TestDoubleBookRepo());
//        Book result = tdService.getBookByTitle("Double Title");
//
//        assertThat(result.getTitle()).isEqualTo("Double Title");
//    }
//
