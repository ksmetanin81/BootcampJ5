package com.colvir.taskDictionary.repository;

import com.colvir.taskDictionary.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {

}
