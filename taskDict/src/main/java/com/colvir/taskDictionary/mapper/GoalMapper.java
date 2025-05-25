package com.colvir.taskDictionary.mapper;

import com.colvir.taskDictionary.dto.GoalDto;
import com.colvir.taskDictionary.model.Goal;
import org.mapstruct.Mapper;

@Mapper
public interface GoalMapper {

    GoalDto toDto(Goal goal);

    Goal toEntity(GoalDto goalDto);
}
