package com.example.library_management_system.controller;

import com.example.library_management_system.entity.Book;
import com.example.library_management_system.entity.BorrowRecord;
import com.example.library_management_system.entity.Member;
import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.repository.BorrowRecordRepository;
import com.example.library_management_system.repository.MemberRepository;
import com.example.library_management_system.service.BookService;
import com.example.library_management_system.service.BorrowRecordService;
import com.example.library_management_system.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping ("/borrowrecords")
public class BorrowRecordController {


    private final BookService bookService;
    private final BorrowRecordService borrowRecordService;
    private final MemberService memberService;

    public BorrowRecordController(BookService bookService, BorrowRecordService borrowRecordService, MemberService memberService) {
        this.bookService = bookService;
        this.borrowRecordService = borrowRecordService;
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<String> borrowBook(@RequestParam UUID bookId, @RequestParam UUID memberId) {
       return borrowRecordService.borrowBook(bookId, memberId);
    }
    @PostMapping ("/return")
    public ResponseEntity<String> returnBook(@RequestParam UUID borrowRecordId) {
        return borrowRecordService.returnBook(borrowRecordId);
    }
    @GetMapping
    public Object getAllBorrowRecords() {
        return borrowRecordService.getAllBorrowRecords();
    }
    @GetMapping("/{id}")
    public Object getBorrowRecordById(@PathVariable UUID id) {
        return borrowRecordService.getBorrowRecordById(id);
    }
}
