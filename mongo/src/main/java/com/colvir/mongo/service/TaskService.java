package com.colvir.mongo.service;

import com.colvir.mongo.dto.TaskDto;
import com.colvir.mongo.model.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskService {

    TaskDto mapDto(Task task);

    Task mapEntity(TaskDto taskDto);

    List<TaskDto> getTasks();

    Optional<TaskDto> getTaskById(String id);

    List<TaskDto> getTasksByDate(LocalDate date);

    List<TaskDto> getTasksByGoalId(String goalId);

    void save(TaskDto taskDto);

    boolean delete(String id);
}
