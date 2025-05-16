package com.colvir.homework5_taskDictionary.repository;

import com.colvir.homework5_taskDictionary.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {

}
