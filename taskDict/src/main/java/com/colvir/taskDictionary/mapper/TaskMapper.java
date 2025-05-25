package com.colvir.taskDictionary.mapper;

import com.colvir.taskDictionary.dto.TaskDto;
import com.colvir.taskDictionary.model.Task;
import org.mapstruct.Mapper;

@Mapper
public interface TaskMapper {

    TaskDto toDto(Task task);

    Task toEntity(TaskDto taskDto);
}
