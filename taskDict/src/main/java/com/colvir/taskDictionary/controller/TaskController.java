package com.colvir.taskDictionary.controller;

import com.colvir.taskDictionary.dto.TaskDto;
import com.colvir.taskDictionary.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<TaskDto> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping("/today")
    public List<TaskDto> getTasksToday() {
        return taskService.getTasksByDate(LocalDate.now());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/onDate/{date}")
    public List<TaskDto> getTasksOnDate(@PathVariable LocalDate date) {
        return taskService.getTasksByDate(date);
    }

    @GetMapping("/byGoalId/{goalId}")
    public List<TaskDto> getTasksByGoalId(@PathVariable Long goalId) {
        return taskService.getTasksByGoalId(goalId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid TaskDto taskDto) {
        if (taskDto.getId() != null) {
            throw new IllegalArgumentException("Task id should be null");
        }
        taskService.save(taskDto);
    }

    @PutMapping
    public void update(@RequestBody @Valid TaskDto taskDto) {
        taskService.getTaskById(taskDto.getId()).ifPresentOrElse(it -> taskService.save(taskDto), () -> {
            throw new NoSuchElementException("Task not found");
        });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return taskService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
