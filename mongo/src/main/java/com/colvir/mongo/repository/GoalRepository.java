package com.colvir.mongo.repository;

import com.colvir.mongo.model.Goal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GoalRepository extends MongoRepository<Goal, String> {

}
