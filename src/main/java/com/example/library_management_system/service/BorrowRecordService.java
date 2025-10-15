package com.example.library_management_system.service;


import com.example.library_management_system.entity.Book;
import com.example.library_management_system.entity.BorrowRecord;
import com.example.library_management_system.entity.Member;
import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.repository.BorrowRecordRepository;
import com.example.library_management_system.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BorrowRecordService {


    private final BorrowRecordRepository borrowRecordRepository;


    private final BookRepository bookRepository;


    private final MemberRepository memberRepository;

    public BorrowRecordService(BorrowRecordRepository borrowRecordRepository, BookRepository bookRepository, MemberRepository memberRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    public ResponseEntity<String> borrowBook(@RequestParam UUID bookId, @RequestParam UUID memberId) {

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND ,"Book not found"));
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.OK).body("Book returned successfully!") ;
    }


    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowRecordRepository.findAll();
    }


    public BorrowRecord getBorrowRecordById(@PathVariable UUID id) {
        return borrowRecordRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Borrow record not found with id: " + id));
    }
}
