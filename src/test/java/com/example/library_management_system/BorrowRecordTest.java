package com.example.library_management_system;

import com.example.library_management_system.Entity.Book;
import com.example.library_management_system.Entity.BorrowRecord;
import com.example.library_management_system.Entity.Member;
import com.example.library_management_system.Repository.BookRepository;
import com.example.library_management_system.Repository.BorrowRecordRepository;
import com.example.library_management_system.Repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BorrowRecordTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Member member;
    private Book book;

    @BeforeEach
    public void setup() {
        borrowRecordRepository.deleteAll();
        bookRepository.deleteAll();
        memberRepository.deleteAll();

        member = new Member(UUID.randomUUID(), "Alice Smith", "Bob@example.com", LocalDate.now());
        book = new Book(UUID.randomUUID(), "Sample Book", "Author A", "ISBN0001", 3, 3);

        member = memberRepository.save(member);
        book = bookRepository.save(book);
    }

    @Test
    void testBorrowBookNoCopiesLeft() throws Exception {
        book.setAvailableCopies(0);
        bookRepository.save(book);
        mvc.perform(post("/borrowrecords/borrow")
                        .param("bookId", book.getId().toString())
                        .param("memberId", member.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("No available copies to borrow."));
    }

    @Test
    void testBorrowBookSuccess() throws Exception {
        mvc.perform(post("/borrowrecords/borrow")
                        .param("bookId", book.getId().toString())
                        .param("memberId", member.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("Book borrowed successfully!"));
        Book updatedBook = bookRepository.findById(book.getId()).orElseThrow();
        assertThat(updatedBook.getAvailableCopies()).isEqualTo(2);
    }

    @Test
    void testReturnBookAlreadyReturn() throws Exception {
        BorrowRecord record = new BorrowRecord();
        record.setBookId(book.getId());
        record.setMemberId(member.getId());
        record.setBorrowDate(LocalDate.now());
        record.setReturnDate(LocalDate.now());
        record = borrowRecordRepository.save(record);

        mvc.perform(post("/borrowrecords/return")
                        .param("borrowRecordId", record.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("Book has already been returned."));
    }

    @Test
    void testGetAllBorrowRecords() throws Exception {
        borrowRecordRepository.save(new BorrowRecord(null, book.getId(), member.getId(), LocalDate.now(), null));
        mvc.perform(get("/borrowrecords/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].bookId").value(book.getId().toString()));
    }

    @Test
    void testGetBorrowRecordByIdNotFound() throws Exception {
        mvc.perform(get("/borrowrecords/" + UUID.randomUUID()))
                .andExpect(status().isInternalServerError());
    }
}