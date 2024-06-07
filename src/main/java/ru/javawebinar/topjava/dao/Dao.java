package ru.javawebinar.topjava.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {
    boolean create(T entity);

    Optional<T> findById(K id);

    void update(T entity);

    boolean delete(T entity);
    List<T> findAll();

}
