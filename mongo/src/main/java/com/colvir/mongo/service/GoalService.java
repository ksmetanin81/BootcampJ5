package com.colvir.mongo.service;

import com.colvir.mongo.dto.GoalDto;
import com.colvir.mongo.model.Goal;

import java.util.List;
import java.util.Optional;

public interface GoalService {

    GoalDto mapDto(Goal goal);

    Goal mapEntity(GoalDto goalDto);

    List<GoalDto> getGoals();

    Optional<GoalDto> getGoalById(String id);

    void save(GoalDto goalDto);

    boolean delete(String id);
}
