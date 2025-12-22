package com.example.planeo_back.application.service;

import org.quartz.SchedulerException;

import java.util.List;

public interface IGenericService<T> {
    T findById(Long id);

    List<T> findAll();

    T save(T t) throws SchedulerException, IllegalAccessException;

    void delete(T t);

}
