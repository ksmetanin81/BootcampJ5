package com.colvir.mongo.repository;

import com.colvir.mongo.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByDate(LocalDate date);

    List<Task> findByGoalId(String goalId);
}
