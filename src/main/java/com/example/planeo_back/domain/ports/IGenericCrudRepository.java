package com.example.planeo_back.domain.ports;

import java.util.List;

public interface IGenericCrudRepository<T> {
    T save(T entity);

    T findById(Long id);

    List<T> findAll();

    void delete(T t);
}
