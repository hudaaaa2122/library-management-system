package com.example.library_management_system.service;

import com.example.library_management_system.entity.Member;
import com.example.library_management_system.repository.FakeMemberRepository;
import com.example.library_management_system.repository.MemberRepository;
import com.example.library_management_system.repository.MemberRepositoryStubBase;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MemberServiceTest {

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
