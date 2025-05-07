package com.colvir.homework5_taskDictionary.dao;

import com.colvir.homework5_taskDictionary.model.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskDao {

    List<Task> findAll();

    Task findById(Long id);

    List<Task> findByDate(LocalDate date);

    void save(Task task);

    boolean delete(Long id);
}
