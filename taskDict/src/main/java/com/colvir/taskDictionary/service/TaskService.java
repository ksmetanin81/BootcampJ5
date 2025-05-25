package com.colvir.taskDictionary.service;

import com.colvir.taskDictionary.dto.TaskDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<TaskDto> getTasks();

    Optional<TaskDto> getTaskById(Long id);

    List<TaskDto> getTasksByDate(LocalDate date);

    List<TaskDto> getTasksByGoalId(Long goalId);

    void save(TaskDto taskDto);

    boolean delete(Long id);
}
