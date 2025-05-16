package com.colvir.homework5_taskDictionary.service.usesDao;

import com.colvir.homework5_taskDictionary.dao.GoalDao;
import com.colvir.homework5_taskDictionary.dto.GoalDto;
import com.colvir.homework5_taskDictionary.dto.TaskDto;
import com.colvir.homework5_taskDictionary.mapper.GoalMapper;
import com.colvir.homework5_taskDictionary.model.Goal;
import com.colvir.homework5_taskDictionary.service.GoalService;
import com.colvir.homework5_taskDictionary.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoalUsesDaoService implements GoalService {

    private final GoalDao goalDao;
    private final GoalMapper goalMapper;

    @Setter(onMethod_ = {@Autowired, @Lazy})
    private TaskService taskService;

    @Override
    public List<GoalDto> getGoals() {
        return goalDao.findAll().stream().map(goalMapper::toDto).toList();
    }

    @Override
    public Optional<GoalDto> getGoalById(Long id) {
        Goal goal = goalDao.findById(id);
        return goal != null ? Optional.of(goalMapper.toDto(goal)) : Optional.empty();
    }

    @Override
    public List<TaskDto> getTasksByGoalId(Long goalId) {
        return taskService.getTasksByGoalId(goalId);
    }

    @Override
    public void save(GoalDto goalDto) {
        goalDao.save(goalMapper.toEntity(goalDto));
    }

    @Override
    public boolean delete(Long id) {
        return goalDao.delete(id);
    }
}
