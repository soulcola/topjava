package ru.javawebinar.topjava.repository;

import java.util.List;
import java.util.Optional;

public interface RepositoryBase<K, T> {
    T create(T entity);

    Optional<T> findById(K id);

    void delete(K id);

    List<T> findAll();
}
