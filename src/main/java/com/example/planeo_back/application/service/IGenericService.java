package com.example.planeo_back.application.service;

import java.util.List;

public interface IGenericService<T> {
    T findById(Long id);

    List<T> findAll();

    T save(T t);

    void delete(T t);

}
