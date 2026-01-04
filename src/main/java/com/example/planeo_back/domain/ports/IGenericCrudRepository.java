package com.example.planeo_back.domain.ports;

import java.util.List;
import java.util.Optional;

public interface IGenericCrudRepository<T> {
    T save(T entity);

    Optional<T> findById(Long id);

    List<T> findAll();

    void delete(T t);
}
