package com.colvir.homework5_taskDictionary.service.impl;

import com.colvir.homework5_taskDictionary.dao.TaskDao;
import com.colvir.homework5_taskDictionary.dto.TaskDto;
import com.colvir.homework5_taskDictionary.mapper.TaskMapper;
import com.colvir.homework5_taskDictionary.model.Task;
import com.colvir.homework5_taskDictionary.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskDao taskDao;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskDto> getTasks() {
        return taskDao.findAll().stream().map(taskMapper::toDto).toList();
    }

    @Override
    public Optional<TaskDto> getTaskById(Long id) {
        Task task = taskDao.findById(id);
        return task != null ? Optional.of(taskMapper.toDto(task)) : Optional.empty();
    }

    @Override
    public List<TaskDto> getTasksByDate(LocalDate date) {
        return taskDao.findByDate(date).stream().map(taskMapper::toDto).toList();
    }

    @Override
    public void save(TaskDto taskDto) {
        taskDao.save(taskMapper.toEntity(taskDto));
    }

    @Override
    public boolean delete(Long id) {
        return taskDao.delete(id);
    }
}
