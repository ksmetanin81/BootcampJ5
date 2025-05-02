package com.colvir.homework5_taskDictionary.service.impl;

import com.colvir.homework5_taskDictionary.dto.TaskDto;
import com.colvir.homework5_taskDictionary.mapper.TaskMapper;
import com.colvir.homework5_taskDictionary.model.Task;
import com.colvir.homework5_taskDictionary.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;
    private final List<Task> tasks = new CopyOnWriteArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    private Optional<Task> getById(Long id) {
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst();
    }

    @Override
    public List<TaskDto> getTasks() {
        return tasks.stream().map(taskMapper::toDto).toList();
    }

    @Override
    public Optional<TaskDto> getTaskById(Long id) {
        return getById(id).map(taskMapper::toDto);
    }

    @Override
    public List<TaskDto> getTasksByDate(LocalDate date) {
        return tasks.stream().filter(task -> task.getDate().equals(date)).map(taskMapper::toDto).toList();
    }

    @Override
    public void save(TaskDto taskDto) {
        if (taskDto.getId() == null) {
            taskDto.setId(counter.incrementAndGet());
            tasks.add(taskMapper.toEntity(taskDto));
        } else {
            getById(taskDto.getId()).ifPresent(it -> tasks.set(tasks.indexOf(it), taskMapper.toEntity(taskDto)));
        }
    }

    @Override
    public boolean delete(Long id) {
        return tasks.remove(getById(id).orElse(null));
    }
}
