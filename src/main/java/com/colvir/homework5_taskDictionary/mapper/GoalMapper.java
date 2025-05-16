package com.colvir.homework5_taskDictionary.mapper;

import com.colvir.homework5_taskDictionary.dto.GoalDto;
import com.colvir.homework5_taskDictionary.model.Goal;
import org.mapstruct.Mapper;

@Mapper
public interface GoalMapper {

    GoalDto toDto(Goal goal);

    Goal toEntity(GoalDto goalDto);
}
