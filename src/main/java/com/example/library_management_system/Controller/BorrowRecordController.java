package com.example.library_management_system.Controller;

import com.example.library_management_system.Entity.Book;
import com.example.library_management_system.Entity.BorrowRecord;
import com.example.library_management_system.Entity.Member;
import com.example.library_management_system.Repository.BookRepository;
import com.example.library_management_system.Repository.BorrowRecordRepository;
import com.example.library_management_system.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/borrow")
    public String borrowBook(@RequestParam UUID bookId, @RequestParam UUID memberId) {

        Optional<Book> bookOpt = bookRepository.findById(bookId);
        Optional<Member> memberOpt = memberRepository.findById(memberId);
        if (bookOpt.isEmpty() || memberOpt.isEmpty()) {
            return "Book or Member not found!";
        }
        Book book = bookOpt.get();
        Member member = memberOpt.get();
        if (book.getAvailableCopies() <= 0) {
            return "No available copies to borrow.";
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
        BorrowRecord record = new BorrowRecord();
        record.setBookId(bookId);
        record.setMemberId (memberId);
        record.setBorrowDate(LocalDate.now());
        borrowRecordRepository.save(record);
        return "Book borrowed successfully!";
    }
    @PostMapping("/return")
    public String returnBook(@RequestParam UUID borrowRecordId) {
        Optional<BorrowRecord> recordOpt = borrowRecordRepository.findById(borrowRecordId);
        if (recordOpt.isEmpty()) {
            return "Borrow record not found!";
        }
        BorrowRecord record = recordOpt.get();
        if (record.getReturnDate() != null) {
            return "Book has already been returned.";
        }
        record.setReturnDate(LocalDate.now());
        borrowRecordRepository.save(record);
        Book book = bookRepository.findById(record.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
        return "Book returned successfully!";
    }
    @GetMapping("/all")
    public Object getAllBorrowRecords() {
        return borrowRecordRepository.findAll();
    }
    @GetMapping("/{id}")
    public Object getBorrowRecordById(@PathVariable UUID id) {
        return borrowRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrow record not found with id: " + id));
    }
}
