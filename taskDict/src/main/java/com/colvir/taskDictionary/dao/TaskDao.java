package com.colvir.taskDictionary.dao;

import com.colvir.taskDictionary.model.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskDao {

    List<Task> findAll();

    Task findById(Long id);

    List<Task> findByDate(LocalDate date);

    List<Task> findByGoalId(Long goalId);

    void save(Task task);

    boolean delete(Long id);
}
