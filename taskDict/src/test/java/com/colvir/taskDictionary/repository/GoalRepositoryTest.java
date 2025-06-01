package com.colvir.taskDictionary.repository;

import com.colvir.taskDictionary.model.Goal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.function.Predicate.not;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;

@DataJpaTest
@DirtiesContext(classMode = BEFORE_CLASS)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DisplayName("GoalRepository")
public class GoalRepositoryTest {

    @Autowired
    private GoalRepository goalRepository;

    @Test
    @DisplayName("findAll")
    public void findAllTest() {
        List<Goal> goals = goalRepository.findAll();
        assertThat(goals).isNotNull().hasSize(2)
                .allMatch(not(g -> g.getId() == null))
                .allMatch(not(g -> g.getName().isEmpty()));
    }

    @Test
    @DisplayName("findById")
    public void findByIdTest() {
        Goal goal = goalRepository.findById(1L).orElse(null);
        assertThat(goal).isNotNull()
                .hasFieldOrPropertyWithValue("name", "Освоить курс Bootcamp Java Advanced");
    }

    @Test
    @DisplayName("save")
    public void saveTest() {
        Goal goal = new Goal();
        goal.setName("Test goal");
        goalRepository.save(goal);
        List<Goal> goals = goalRepository.findAll();
        assertThat(goals).isNotNull().hasSize(3)
                .contains(goal);
    }

    @Test
    @DisplayName("delete")
    public void deleteTest() {
        Goal goal = goalRepository.findById(1L).orElseThrow(() -> new NoSuchElementException("Goal with id = 1 not found"));
        goalRepository.deleteById(goal.getId());
        List<Goal> goals = goalRepository.findAll();
        assertThat(goals).isNotNull().hasSize(1)
                .doesNotContain(goal);
    }
}
