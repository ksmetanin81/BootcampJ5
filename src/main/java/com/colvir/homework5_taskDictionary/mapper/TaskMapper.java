package com.colvir.homework5_taskDictionary.mapper;

import com.colvir.homework5_taskDictionary.dto.TaskDto;
import com.colvir.homework5_taskDictionary.model.Task;
import org.mapstruct.Mapper;

@Mapper
public interface TaskMapper {

    TaskDto toDto(Task task);

    Task toEntity(TaskDto taskDto);
}
