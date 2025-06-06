package com.colvir.taskDictionary.repository;

import com.colvir.taskDictionary.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(value = "User.role")
    Optional<User> findByLogin(String login);
}
