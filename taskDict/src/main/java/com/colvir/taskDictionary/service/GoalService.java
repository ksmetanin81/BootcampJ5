package com.colvir.taskDictionary.service;

import com.colvir.taskDictionary.dto.GoalDto;

import java.util.List;
import java.util.Optional;

public interface GoalService {

    List<GoalDto> getGoals();

    Optional<GoalDto> getGoalById(Long id);

    void save(GoalDto goalDto);

    boolean delete(Long id);
}
