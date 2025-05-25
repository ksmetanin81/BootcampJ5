package com.colvir.taskDictionary.repository;

import com.colvir.taskDictionary.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByDate(LocalDate date);

    List<Task> findByGoalId(Long goalId);
}
