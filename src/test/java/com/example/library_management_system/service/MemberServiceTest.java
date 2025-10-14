package com.example.library_management_system.service;

import com.example.library_management_system.entity.Member;
import com.example.library_management_system.repository.FakeMemberRepository;
import com.example.library_management_system.repository.MemberRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MemberServiceTest {

    @Test
    void testGetMemberById_UsingStub() {
        UUID id = UUID.randomUUID();
        MemberRepository stubRepo = new MemberRepository() {
            @Override
            public List<Member> findAll(Sort sort) {
                return List.of();
            }

            @Override
            public Page<Member> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Member> S save(S entity) {
                return null;
            }

            @Override
            public <S extends Member> List<S> saveAll(Iterable<S> entities) {
                return List.of();
            }

            @Override
            public List<Member> findAll() {
                return List.of();
            }

            @Override
            public List<Member> findAllById(Iterable<UUID> uuids) {
                return List.of();
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(UUID uuid) {

            }

            @Override
            public void delete(Member entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends UUID> uuids) {

            }

            @Override
            public void deleteAll(Iterable<? extends Member> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Member> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends Member> List<S> saveAllAndFlush(Iterable<S> entities) {
                return List.of();
            }

            @Override
            public void deleteAllInBatch(Iterable<Member> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<UUID> uuids) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Member getOne(UUID uuid) {
                return null;
            }

            @Override
            public Member getById(UUID uuid) {
                return null;
            }

            @Override
            public Member getReferenceById(UUID uuid) {
                return null;
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

            @Override
            public Optional<Member> findById(UUID id) {
                return Optional.of(new Member(id, "Test Name", "test@email.com", LocalDate.now()));
            }

            @Override
            public boolean existsById(UUID uuid) {
                return false;
            }
        };
        MemberService stubService = new MemberService(stubRepo);
        assertEquals(id, stubService.getMemberById(id).getId());
    }
}

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