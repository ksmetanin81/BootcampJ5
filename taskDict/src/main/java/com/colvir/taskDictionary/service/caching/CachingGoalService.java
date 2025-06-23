package com.colvir.taskDictionary.service.caching;

import com.colvir.taskDictionary.dto.GoalDto;
import com.colvir.taskDictionary.service.GoalService;
import com.colvir.taskDictionary.service.usesRepository.GoalUsesRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Primary
@Service
@RequiredArgsConstructor
public class CachingGoalService implements GoalService {

    private final GoalUsesRepositoryService goalService;

    @Override
    @Transactional(readOnly = true)
    public List<GoalDto> getGoals() {
        return goalService.getGoals();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GoalDto> getGoalById(Long id) {
        return goalService.getGoalById(id);
    }

    @Override
    @Transactional
    public void save(GoalDto goalDto) {
        goalService.save(goalDto);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return goalService.delete(id);
    }

}
