package com.colvir.taskDictionary.service.usesDao;

import com.colvir.taskDictionary.dao.TaskDao;
import com.colvir.taskDictionary.dto.TaskDto;
import com.colvir.taskDictionary.mapper.GoalMapper;
import com.colvir.taskDictionary.mapper.TaskMapper;
import com.colvir.taskDictionary.model.Task;
import com.colvir.taskDictionary.service.GoalService;
import com.colvir.taskDictionary.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskUsesDaoService implements TaskService {

    private final TaskDao taskDao;
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
    @Transactional(readOnly = true)
    public List<TaskDto> getTasks() {
        return taskDao.findAll().stream().map(this::mapDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TaskDto> getTaskById(Long id) {
        Task task = taskDao.findById(id);
        return task != null ? Optional.of(this.mapDto(task)) : Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDto> getTasksByDate(LocalDate date) {
        return taskDao.findByDate(date).stream().map(this::mapDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDto> getTasksByGoalId(Long goalId) {
        return taskDao.findByGoalId(goalId).stream().map(this::mapDto).toList();
    }

    @Override
    @Transactional
    public void save(TaskDto taskDto) {
        taskDao.save(mapEntity(taskDto));
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return taskDao.delete(id);
    }
}
