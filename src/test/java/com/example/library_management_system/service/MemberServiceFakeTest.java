package com.example.library_management_system.service;

import com.example.library_management_system.entity.Member;
import com.example.library_management_system.repository.FakeMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceFakeTest {

    @Test
    void testGetMemberById_UsingFakeRepository() {
        FakeMemberRepository fakeRepo = new FakeMemberRepository() {
            @Override
            public void deleteAllById(Iterable<? extends UUID> uuids) {

            }

            @Override
            public void deleteAll(Iterable<? extends Member> entities) {

            }

            @Override
            public <S extends Member> List<S> saveAllAndFlush(Iterable<S> entities) {
                return List.of();
            }

            @Override
            public <S extends Member> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Member> List<S> findAll(Example<S> example) {
                return List.of();
            }

            @Override
            public <S extends Member> List<S> findAll(Example<S> example, Sort sort) {
                return List.of();
            }

            @Override
            public <S extends Member> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Member> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Member> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends Member, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
        MemberService service = new MemberService(fakeRepo);

        UUID id = UUID.randomUUID();
        Member fakeMember = new Member(id, "Noor", "noor@example.com", LocalDate.now());
        fakeRepo.save(fakeMember);

        Member result = service.getMemberById(id);

        assertNotNull(result);
        assertEquals("Noor", result.getName());
    }
}
