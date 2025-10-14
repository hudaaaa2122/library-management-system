package com.example.library_management_system.repository;

import com.example.library_management_system.entity.Member;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;

public class FakeMemberRepository implements MemberRepository {
    private final Map<UUID, Member> db = new HashMap<>();

    @Override
    public Optional<Member> findById(UUID id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public <S extends Member> S save(S entity) {
        db.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public List<Member> findAllById(Iterable<UUID> ids) {
        List<Member> result = new ArrayList<>();
        for (UUID id : ids) {
            if (db.containsKey(id)) result.add(db.get(id));
        }
        return result;
    }

    @Override
    public void deleteById(UUID id) { db.remove(id); }

    @Override
    public long count() { return db.size(); }

    @Override public void deleteAll() { db.clear(); }
    @Override public boolean existsById(UUID id) { return db.containsKey(id); }

    // implement the rest with safe defaults to satisfy compiler:
    @Override public List<Member> findAll(Sort sort) { return List.of(); }
    @Override public Page<Member> findAll(Pageable pageable) { return Page.empty(); }
    @Override public <S extends Member> List<S> saveAll(Iterable<S> entities) { return List.of(); }
    @Override public void flush() {}
    @Override public <S extends Member> S saveAndFlush(S entity) { return save(entity); }

    @Override
    public <S extends Member> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override public void deleteAllInBatch() {}
    @Override public Member getOne(UUID uuid) { return db.get(uuid); }
    @Override public Member getById(UUID uuid) { return db.get(uuid); }
    @Override public Member getReferenceById(UUID uuid) { return db.get(uuid); }
    @Override public void delete(Member entity) { db.remove(entity.getId()); }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override public void deleteAll(Iterable<? extends Member> entities) {
        for (Member m : entities) db.remove(m.getId());
    }
    @Override public void deleteAllInBatch(Iterable<Member> entities) { deleteAll(entities); }
    @Override public void deleteAllByIdInBatch(Iterable<UUID> uuids) { for (UUID id : uuids) db.remove(id); }
    @Override public <S extends Member> Optional<S> findOne(Example<S> example) { return Optional.empty(); }
    @Override public <S extends Member> List<S> findAll(Example<S> example) { return List.of(); }
    @Override public <S extends Member> List<S> findAll(Example<S> example, Sort sort) { return List.of(); }
    @Override public <S extends Member> Page<S> findAll(Example<S> example, Pageable pageable) { return Page.empty(); }
    @Override public <S extends Member> long count(Example<S> example) { return 0; }
    @Override public <S extends Member> boolean exists(Example<S> example) { return false; }
    @Override public <S extends Member, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return null; }
}
