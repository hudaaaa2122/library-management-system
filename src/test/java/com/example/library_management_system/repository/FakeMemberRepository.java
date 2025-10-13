package com.example.library_management_system.repository;

import com.example.library_management_system.entity.Member;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;

public abstract class FakeMemberRepository implements MemberRepository {

    private final Map<UUID, Member> fakeDatabase = new HashMap<>();

    @Override
    public Optional<Member> findById(UUID id) {
        return Optional.ofNullable(fakeDatabase.get(id));
    }

    @Override
    public List<Member> findAllById(Iterable<UUID> ids) {
        List<Member> result = new ArrayList<>();
        for (UUID id : ids) {
            if (fakeDatabase.containsKey(id)) {
                result.add(fakeDatabase.get(id));
            }
        }
        return result;
    }

    @Override
    public <S extends Member> S save(S entity) {
        fakeDatabase.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(fakeDatabase.values());
    }

    @Override
    public void deleteById(UUID id) {
        fakeDatabase.remove(id);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> uuids) {
        for (UUID id : uuids) {
            fakeDatabase.remove(id);
        }
    }

    // --- Unused methods (just dummy implementations) ---

    @Override
    public List<Member> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Member> findAll(Pageable pageable) {
        return Page.empty();
    }

    @Override
    public boolean existsById(UUID id) {
        return fakeDatabase.containsKey(id);
    }

    @Override
    public long count() {
        return fakeDatabase.size();
    }

    @Override
    public void delete(Member entity) {
    }

    @Override
    public void deleteAll() {
    }

    @Override
    public void flush() {
    }

    @Override
    public <S extends Member> S saveAndFlush(S entity) {
        return entity;
    }

    @Override
    public void deleteAllInBatch() {
    }

    @Override
    public void deleteAllInBatch(Iterable<Member> entities) {
    }

    @Override
    public Member getOne(UUID uuid) {
        return fakeDatabase.get(uuid);
    }

    @Override
    public Member getById(UUID uuid) {
        return fakeDatabase.get(uuid);
    }

    @Override
    public Member getReferenceById(UUID uuid) {
        return fakeDatabase.get(uuid);
    }
    @Override
    public <S extends Member> List<S> saveAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S entity : entities) {
            fakeDatabase.put(entity.getId(), entity);
            result.add(entity);
        }
        return result;
    }}