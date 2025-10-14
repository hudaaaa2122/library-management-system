package com.example.library_management_system.repository;

import com.example.library_management_system.entity.Member;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;


public  class MemberRepositoryStubBase implements MemberRepository {

    // Provide no-op or safe-default implementations for all JpaRepository methods
    @Override public List<Member> findAll() { return List.of(); }
    @Override public List<Member> findAll(Sort sort) { return List.of(); }
    @Override public Page<Member> findAll(Pageable pageable) { return Page.empty(); }
    @Override public List<Member> findAllById(Iterable<UUID> uuids) { return List.of(); }
    @Override public <S extends Member> List<S> saveAll(Iterable<S> entities) { return List.of(); }

    @Override public void flush() {}
    @Override public <S extends Member> S saveAndFlush(S entity) { return entity; }
    @Override public <S extends Member> List<S> saveAllAndFlush(Iterable<S> entities) { return List.of(); }
    @Override public void deleteAllInBatch() {}
    @Override public void deleteAllInBatch(Iterable<Member> entities) {}
    @Override public void deleteAllByIdInBatch(Iterable<UUID> uuids) {}
    @Override public Member getOne(UUID uuid) { return null; }
    @Override public Member getById(UUID uuid) { return null; }
    @Override public Member getReferenceById(UUID uuid) { return null; }

    @Override public <S extends Member> Optional<S> findOne(Example<S> example) { return Optional.empty(); }
    @Override public <S extends Member> List<S> findAll(Example<S> example) { return List.of(); }
    @Override public <S extends Member> List<S> findAll(Example<S> example, Sort sort) { return List.of(); }
    @Override public <S extends Member> Page<S> findAll(Example<S> example, Pageable pageable) { return Page.empty(); }
    @Override public <S extends Member> long count(Example<S> example) { return 0; }
    @Override public <S extends Member> boolean exists(Example<S> example) { return false; }
    @Override public <S extends Member, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return null; }

    @Override public long count() { return 0; }
    @Override public void delete(Member entity) {}

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override public void deleteAll() {}
    @Override public void deleteAll(Iterable<? extends Member> entities) {}
    @Override public void deleteById(UUID uuid) {}
    @Override public boolean existsById(UUID uuid) { return false; }
    @Override public <S extends Member> S save(S entity) { return entity; }
    @Override public Optional<Member> findById(UUID uuid) { return Optional.empty(); }
}
