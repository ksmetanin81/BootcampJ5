package com.colvir.homework5_taskDictionary.service.usesDao;

import com.colvir.homework5_taskDictionary.dao.GoalDao;
import com.colvir.homework5_taskDictionary.dto.GoalDto;
import com.colvir.homework5_taskDictionary.mapper.GoalMapper;
import com.colvir.homework5_taskDictionary.service.GoalService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoalUsesDaoService implements GoalService {

    private final GoalDao goalDao;
    private final GoalMapper goalMapper;

    @Override
    @Transactional(readOnly = true)
    public List<GoalDto> getGoals() {
        return goalDao.findAll().stream().map(goalMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GoalDto> getGoalById(Long id) {
        return Optional.ofNullable(goalDao.findById(id)).map(goalMapper::toDto);
    }

    @Override
    @Transactional
    public void save(GoalDto goalDto) {
        goalDao.save(goalMapper.toEntity(goalDto));
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return goalDao.delete(id);
    }
}
