package com.colvir.taskDictionary.controller;

import com.colvir.taskDictionary.dto.GoalDto;
import com.colvir.taskDictionary.service.GoalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalService goalService;

    @GetMapping
    public List<GoalDto> getGoals() {
        return goalService.getGoals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoalDto> getGoalById(@PathVariable Long id) {
        return goalService.getGoalById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid GoalDto goalDto) {
        if (goalDto.getId() != null) {
            throw new IllegalArgumentException("Goal id should be null");
        }
        if (goalDto.getDeadline().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Goal deadline must be greater or equal to the current one");
        }
        goalService.save(goalDto);
    }

    @PutMapping
    public void update(@RequestBody @Valid GoalDto goalDto) {
        goalService.getGoalById(goalDto.getId()).ifPresentOrElse(it -> goalService.save(goalDto), () -> {
            throw new NoSuchElementException("Goal not found");
        });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return goalService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
