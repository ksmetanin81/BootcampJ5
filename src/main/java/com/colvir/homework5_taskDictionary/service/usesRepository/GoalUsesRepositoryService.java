package com.colvir.homework5_taskDictionary.service.usesRepository;

import com.colvir.homework5_taskDictionary.dto.GoalDto;
import com.colvir.homework5_taskDictionary.mapper.GoalMapper;
import com.colvir.homework5_taskDictionary.repository.GoalRepository;
import com.colvir.homework5_taskDictionary.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Primary
@Service
@RequiredArgsConstructor
public class GoalUsesRepositoryService implements GoalService {

    private final GoalRepository goalRepository;
    private final GoalMapper goalMapper;

    @Override
    @Transactional(readOnly = true)
    public List<GoalDto> getGoals() {
        return goalRepository.findAll().stream().map(goalMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GoalDto> getGoalById(Long id) {
        return goalRepository.findById(id).map(goalMapper::toDto);
    }

    @Override
    @Transactional
    public void save(GoalDto goalDto) {
        goalRepository.save(goalMapper.toEntity(goalDto));
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (goalRepository.findById(id).isPresent()) {
            goalRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
