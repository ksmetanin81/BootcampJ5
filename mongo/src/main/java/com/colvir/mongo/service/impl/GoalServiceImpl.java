package com.colvir.mongo.service.impl;

import com.colvir.mongo.dto.GoalDto;
import com.colvir.mongo.model.Goal;
import com.colvir.mongo.repository.GoalRepository;
import com.colvir.mongo.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Primary
@Service
@RequiredArgsConstructor
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;

    public GoalDto mapDto(Goal goal) {
        GoalDto goalDto = new GoalDto();
        goalDto.setId( goal.getId() );
        goalDto.setName( goal.getName() );
        goalDto.setMotivation( goal.getMotivation() );
        goalDto.setResources( goal.getResources() );
        goalDto.setDeadline( goal.getDeadline() );
        goalDto.setDescription( goal.getDescription() );
        return goalDto;
    }

    public Goal mapEntity(GoalDto goalDto) {
        Goal goal = new Goal();
        goal.setId( goalDto.getId() );
        goal.setName( goalDto.getName() );
        goal.setMotivation( goalDto.getMotivation() );
        goal.setResources( goalDto.getResources() );
        goal.setDeadline( goalDto.getDeadline() );
        goal.setDescription( goalDto.getDescription() );
        return goal;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GoalDto> getGoals() {
        return goalRepository.findAll().stream().map(this::mapDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GoalDto> getGoalById(String id) {
        return goalRepository.findById(id).map(this::mapDto);
    }

    @Override
    @Transactional
    public void save(GoalDto goalDto) {
        goalRepository.save(this.mapEntity(goalDto));
    }

    @Override
    @Transactional
    public boolean delete(String id) {
        if (goalRepository.findById(id).isPresent()) {
            goalRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
