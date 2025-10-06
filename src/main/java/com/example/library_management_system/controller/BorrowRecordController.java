package com.example.library_management_system.controller;

import com.example.library_management_system.entity.Book;
import com.example.library_management_system.entity.BorrowRecord;
import com.example.library_management_system.entity.Member;
import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.repository.BorrowRecordRepository;
import com.example.library_management_system.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping ("/borrowrecords")
public class BorrowRecordController {
    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping
    public ResponseEntity <String> borrowBook(@RequestParam UUID bookId, @RequestParam UUID memberId) {

        Optional<Book> bookOpt = bookRepository.findById(bookId);
        Optional<Member> memberOpt = memberRepository.findById(memberId);
        if (bookOpt.isEmpty() || memberOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found") ;
        }
        Book book = bookOpt.get();
        Member member = memberOpt.get();
        if (book.getAvailableCopies() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book available copies not allowed") ;
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
        BorrowRecord record = new BorrowRecord();
        record.setBookId(bookId);
        record.setMemberId (memberId);
        record.setBorrowDate(LocalDate.now());
        record.setId(UUID.randomUUID());
        borrowRecordRepository.save(record);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book borrowed successfully!") ;
    }
    @PostMapping
    public ResponseEntity <String> returnBook(@RequestParam UUID borrowRecordId) {
        Optional<BorrowRecord> recordOpt = borrowRecordRepository.findById(borrowRecordId);
        if (recordOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Borrow record not found!") ;
        }
        BorrowRecord record = recordOpt.get();
        if (record.getReturnDate() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Return Date already exists!") ;
        }
        record.setReturnDate(LocalDate.now());
        borrowRecordRepository.save(record);
        Book book = bookRepository.findById(record.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book returned successfully!") ;
    }
    @GetMapping
    public Object getAllBorrowRecords() {
        return borrowRecordRepository.findAll();
    }
    @GetMapping("/{id}")
    public Object getBorrowRecordById(@PathVariable UUID id) {
        return borrowRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrow record not found with id: " + id));
    }
}
