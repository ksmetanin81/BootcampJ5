package com.colvir.homework5_taskDictionary.service.usesRepository;

import com.colvir.homework5_taskDictionary.dto.GoalDto;
import com.colvir.homework5_taskDictionary.dto.TaskDto;
import com.colvir.homework5_taskDictionary.mapper.GoalMapper;
import com.colvir.homework5_taskDictionary.repository.GoalRepository;
import com.colvir.homework5_taskDictionary.service.GoalService;
import com.colvir.homework5_taskDictionary.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
@RequiredArgsConstructor
public class GoalUsesRepositoryService implements GoalService {

    private final GoalRepository goalRepository;
    ;
    private final GoalMapper goalMapper;

    @Setter(onMethod_ = {@Autowired, @Lazy})
    private TaskService taskService;

    @Override
    public List<GoalDto> getGoals() {
        return goalRepository.findAll().stream().map(goalMapper::toDto).toList();
    }

    @Override
    public Optional<GoalDto> getGoalById(Long id) {
        return goalRepository.findById(id).map(goalMapper::toDto);
    }

    @Override
    public List<TaskDto> getTasksByGoalId(Long goalId) {
        return taskService.getTasksByGoalId(goalId);
    }

    @Override
    public void save(GoalDto goalDto) {
        goalRepository.save(goalMapper.toEntity(goalDto));
    }

    @Override
    public boolean delete(Long id) {
        if (goalRepository.findById(id).isPresent()) {
            goalRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
