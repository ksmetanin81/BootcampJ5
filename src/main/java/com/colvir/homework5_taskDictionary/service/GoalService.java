package com.colvir.homework5_taskDictionary.service;

import com.colvir.homework5_taskDictionary.dto.GoalDto;
import com.colvir.homework5_taskDictionary.dto.TaskDto;

import java.util.List;
import java.util.Optional;

public interface GoalService {

    List<GoalDto> getGoals();

    Optional<GoalDto> getGoalById(Long id);

    List<TaskDto> getTasksByGoalId(Long goalId);

    void save(GoalDto goalDto);

    boolean delete(Long id);
}
