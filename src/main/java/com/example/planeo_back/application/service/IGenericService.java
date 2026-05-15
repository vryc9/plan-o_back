package com.example.planeo_back.application.service;

import org.quartz.SchedulerException;

import java.util.List;

public interface IGenericService<T, TR> {
    TR findById(Long id);

    List<TR> findAll();

    TR save(T t) throws SchedulerException, IllegalAccessException;

    void delete(T t);

}
