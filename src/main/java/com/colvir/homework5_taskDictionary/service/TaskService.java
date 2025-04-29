package com.colvir.homework5_taskDictionary.service;

import com.colvir.homework5_taskDictionary.dto.TaskDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<TaskDto> getTasks();

    Optional<TaskDto> getTaskById(Long id);

    List<TaskDto> getTasksByDate(LocalDate date);

    void save(TaskDto taskDto);

    boolean delete(Long id);
}
