package com.example.library_management_system.repository;

import com.example.library_management_system.entity.BorrowRecord;
import com.example.library_management_system.entity.Member;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;

public class FakeBorrowBooKRepository implements BorrowRecordRepository {
    private final Map<UUID, BorrowRecord> FakeDB = new HashMap<>();


    @Override
    public void flush() {

    }

    @Override
    public <S extends BorrowRecord> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends BorrowRecord> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<BorrowRecord> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> uuids) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public BorrowRecord getOne(UUID uuid) {
        return null;
    }

    @Override
    public BorrowRecord getById(UUID uuid) {
        return null;
    }

    @Override
    public BorrowRecord getReferenceById(UUID uuid) {
        return null;
    }

    @Override
    public <S extends BorrowRecord> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends BorrowRecord> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends BorrowRecord> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends BorrowRecord> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends BorrowRecord> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends BorrowRecord> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends BorrowRecord, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends BorrowRecord> S save(S entity) {
        return null;
    }

    @Override
    public <S extends BorrowRecord> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<BorrowRecord> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public List<BorrowRecord> findAll() {
        return List.of();
    }



    @Override
    public List<BorrowRecord> findAllById(Iterable<UUID> uuids) {
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
    public void delete(BorrowRecord entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends BorrowRecord> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<BorrowRecord> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<BorrowRecord> findAll(Pageable pageable) {
        return null;
    }
}
