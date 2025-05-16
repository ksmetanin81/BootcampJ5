package com.colvir.homework5_taskDictionary.dao;

import com.colvir.homework5_taskDictionary.model.Goal;

import java.util.List;

public interface GoalDao {

    List<Goal> findAll();

    Goal findById(Long id);

    void save(Goal goal);

    boolean delete(Long id);
}
