package com.example.library_management_system.service;

import com.example.library_management_system.entity.Book;
import com.example.library_management_system.entity.Member;
import com.example.library_management_system.repository.FakeMemberRepository;
import com.example.library_management_system.repository.MemberRepository;
import com.example.library_management_system.repository.MemberRepositoryStubBase;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    void testgetAllMembers_UsingMock() {
        //given
        when(memberRepository.findAll()).thenReturn(List.of(
                new Member(UUID.randomUUID(), " Mock User1", " mockuser1@gmail.com" , LocalDate.now()),
                new Member(UUID.randomUUID(), " Mock User2", "mockuser2@gmal.com ", LocalDate.now())
    ));
        //when
        List<Member> members = memberService.getAllMembers();
        //then
        assertThat(members.size()).isEqualTo(2);
    }

        @Test
        void testGetMemberById_UsingStub() {
            UUID id = UUID.randomUUID();
            Member expected = new Member(id, "Stub User", "stub@example.com", LocalDate.now());
            MemberRepository stubRepo = new MemberRepositoryStubBase() {
                @Override
                public Optional<Member> findById(UUID uuid) {
                    if (uuid.equals(id)) return Optional.of(expected);
                    return Optional.empty();
                }
            };
            MemberService service = new MemberService(stubRepo);
            Member actual = service.getMemberById(id);
            assertNotNull(actual);
            assertEquals(expected.getId(), actual.getId());
            assertEquals("Stub User", actual.getName());
        }
    }

    class MemberServiceFakeTest {
        @Test
        void testGetMemberById_UsingFakeRepository() {
            FakeMemberRepository fakeRepo = new FakeMemberRepository();
            MemberService service = new MemberService(fakeRepo);
            UUID id = UUID.randomUUID();
            Member fakeMember = new Member(id, "Noor", "noor@example.com", LocalDate.now());
            fakeRepo.save(fakeMember);
            Member result = service.getMemberById(id);
            assertNotNull(result);
            assertEquals("Noor", result.getName());
        }
    }
