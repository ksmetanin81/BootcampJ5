package com.colvir.homework5_taskDictionary.service.usesRepository;

import com.colvir.homework5_taskDictionary.dto.TaskDto;
import com.colvir.homework5_taskDictionary.mapper.GoalMapper;
import com.colvir.homework5_taskDictionary.mapper.TaskMapper;
import com.colvir.homework5_taskDictionary.model.Task;
import com.colvir.homework5_taskDictionary.repository.TaskRepository;
import com.colvir.homework5_taskDictionary.service.GoalService;
import com.colvir.homework5_taskDictionary.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Primary
@Service
@RequiredArgsConstructor
public class TaskUsesRepositoryService implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final GoalMapper goalMapper;
    private final GoalService goalService;

    private TaskDto mapDto(Task task) {
        TaskDto taskDto = taskMapper.toDto(task);
        taskDto.setGoalId(task.getGoal().getId());
        return taskDto;
    }

    private Task mapEntity(TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        task.setGoal(goalService.getGoalById(taskDto.getGoalId())
                .map(goalMapper::toEntity)
                .orElseThrow(() -> new NoSuchElementException("Goal with id " + taskDto.getGoalId() + " not found")));
        return task;
    }

    @Override
    public List<TaskDto> getTasks() {
        return taskRepository.findAll().stream().map(this::mapDto).toList();
    }

    @Override
    public Optional<TaskDto> getTaskById(Long id) {
        return taskRepository.findById(id).map(this::mapDto);
    }

    @Override
    public List<TaskDto> getTasksByDate(LocalDate date) {
        return taskRepository.findByDate(date).stream().map(this::mapDto).toList();
    }

    @Override
    public List<TaskDto> getTasksByGoalId(Long goalId) {
        return taskRepository.findByGoalId(goalId).stream().map(this::mapDto).toList();
    }

    @Override
    public void save(TaskDto taskDto) {
        taskRepository.save(mapEntity(taskDto));
    }

    @Override
    public boolean delete(Long id) {
        if (taskRepository.findById(id).isPresent()) {
            taskRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
