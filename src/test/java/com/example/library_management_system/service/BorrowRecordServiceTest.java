package com.example.library_management_system.service;

import com.example.library_management_system.entity.Book;
import com.example.library_management_system.entity.BorrowRecord;
import com.example.library_management_system.entity.Member;
import com.example.library_management_system.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BorrowRecordServiceTest {

    @Mock
    private BorrowRecordRepository borrowRecordRepository;

    @InjectMocks
    private BorrowRecordService borrowRecordService;

    @Test
    public void testgetAllBorrowRecords_UsingMock (){
        //given
            BorrowRecord record1 = new BorrowRecord  ( UUID.randomUUID() , UUID.randomUUID(), UUID.randomUUID(), null, null);
           BorrowRecord record2 = new BorrowRecord ( UUID.randomUUID() , UUID.randomUUID(), UUID.randomUUID(), null, null);
        when(borrowRecordRepository.findAll()).thenReturn(List.of( record1 , record2 ));


        //when
        List<BorrowRecord> borrowRecords = borrowRecordService.getAllBorrowRecords();
        //then
        assertThat(borrowRecords.size()).isEqualTo(2);
    }

    @Test
    void testgetBorrowRecordById_UsingSpy() {
        //given
        BorrowRecordRepository spyRepo = mock(BorrowRecordRepository.class);
        UUID id = UUID.randomUUID();
        BorrowRecord expected = new BorrowRecord(id, UUID.randomUUID(), UUID.randomUUID(), LocalDate.now(), null);
        when(spyRepo.findById(id)).thenReturn(java.util.Optional.of(expected));
        //when
        BorrowRecordService spyService = spy(new BorrowRecordService(spyRepo, mock(BookRepository.class), mock(FakeMemberRepository.class)));
        BorrowRecord actual = spyService.getBorrowRecordById(id);
        //then
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    void testborrowBook_UsingStub() {
        UUID bookId = UUID.randomUUID();
        UUID memberId = UUID.randomUUID();
        Book book = new Book(bookId, "Stub Title", "Stub Author", "ISBN000", 2023, 2);
        Member member = new Member(memberId, "Stub User", "stub@example.com", LocalDate.now());
        BookRepository stubBookRepo = new FakeBookRepository() {
            @Override
            public Optional<Book> findById(UUID uuid) {
                if (uuid.equals(bookId)) return Optional.of(book);
                return Optional.empty();
            }
        };
        MemberRepository stubMemberRepo = new FakeMemberRepository() {
            @Override
            public Optional<Member> findById(UUID uuid) {
                if (uuid.equals(memberId)) return Optional.of(member);
                return Optional.empty();
            }
        };
        BorrowRecordRepository stubBorrowRepo = new FakeBorrowBooKRepository() {
            @Override
            public BorrowRecord save(BorrowRecord borrowRecord) {
                return borrowRecord;
            }

        };
        BorrowRecordService service = new BorrowRecordService(stubBorrowRepo, stubBookRepo, stubMemberRepo);
        var response = service.borrowBook(bookId, memberId);
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());

    }

    @Test
    public void testreturnBook_UsingMock (){
        //given
        UUID bookId = UUID.randomUUID();
        UUID memberId = UUID.randomUUID();
        UUID recordId = UUID.randomUUID();
        Book book = new Book(bookId, "Mock Title", "Mock Author", "ISBN111", 2023, 1);
        Member member = new Member(memberId, "Mock User", "stub@example.com", LocalDate.now());
        BorrowRecord record = new BorrowRecord(recordId, bookId, memberId, LocalDate.now(), null);
        when(borrowRecordRepository.findById(recordId)).thenReturn(Optional.of(record));
        BookRepository mockBookRepo = mock(BookRepository.class);
        when(mockBookRepo.findById(bookId)).thenReturn(Optional.of(book));
        //when
        BorrowRecordService service = new BorrowRecordService(borrowRecordRepository, mockBookRepo, mock(MemberRepository.class));
        var response = service.returnBook(recordId);
        //then
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

    }


}



