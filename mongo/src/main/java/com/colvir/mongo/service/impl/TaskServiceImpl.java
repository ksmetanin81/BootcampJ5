package com.colvir.mongo.service.impl;

import com.colvir.mongo.dto.TaskDto;
import com.colvir.mongo.model.Task;
import com.colvir.mongo.repository.TaskRepository;
import com.colvir.mongo.service.GoalService;
import com.colvir.mongo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Primary
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final GoalService goalService;

    @Override
    public TaskDto mapDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setDate(task.getDate());
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        taskDto.setGoalId(task.getGoal().getId());
        return taskDto;
    }

    @Override
    public Task mapEntity(TaskDto taskDto) {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setDate(taskDto.getDate());
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setGoal(goalService.getGoalById(taskDto.getGoalId())
                .map(goalService::mapEntity)
                .orElseThrow(() -> new NoSuchElementException("Goal with id " + taskDto.getGoalId() + " not found")));
        return task;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDto> getTasks() {
        return taskRepository.findAll().stream().map(this::mapDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TaskDto> getTaskById(String id) {
        return taskRepository.findById(id).map(this::mapDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDto> getTasksByDate(LocalDate date) {
        return taskRepository.findByDate(date).stream().map(this::mapDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDto> getTasksByGoalId(String goalId) {
        return taskRepository.findByGoalId(goalId).stream().map(this::mapDto).toList();
    }

    @Override
    @Transactional
    public void save(TaskDto taskDto) {
        taskRepository.save(mapEntity(taskDto));
    }

    @Override
    @Transactional
    public boolean delete(String id) {
        if (taskRepository.findById(id).isPresent()) {
            taskRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
