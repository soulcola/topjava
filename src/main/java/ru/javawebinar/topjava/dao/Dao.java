package ru.javawebinar.topjava.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {
    T create(T entity);

    Optional<T> findById(K id);

    void update(K id, T entity);

    void delete(K id);

    List<T> findAll();
}
