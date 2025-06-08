package com.colvir.taskDictionary.repository;

import com.colvir.taskDictionary.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.function.Predicate.not;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;

@DataJpaTest
@DirtiesContext(classMode = BEFORE_CLASS)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private GoalRepository goalRepository;

    @Test
    public void findAllTest() {
        List<Task> tasks = taskRepository.findAll();
        assertThat(tasks).isNotNull().hasSize(4)
                .allMatch(not(t -> t.getId() == null))
                .allMatch(not(t -> t.getName().isEmpty()))
                .allMatch(not(t -> t.getGoal() == null));
    }

    @Test
    public void findByIdTest() {
        Task task = taskRepository.findById(1L).orElse(null);
        assertThat(task).isNotNull()
                .hasFieldOrPropertyWithValue("name", "Выполнить домашнюю работу Spring Data JPA");
    }

    @Test
    public void findByDateTest() {
        List<Task> tasks = taskRepository.findByDate(LocalDate.now());
        assertThat(tasks).isNotNull().hasSize(3)
                .allMatch(not(t -> t.getId() == null))
                .allMatch(t -> t.getDate().equals(LocalDate.now()))
                .allMatch(not(t -> t.getName().isEmpty()))
                .allMatch(not(t -> t.getGoal() == null));
    }

    @Test
    public void findByGoalIdTest() {
        List<Task> tasks = taskRepository.findByGoalId(1L);
        assertThat(tasks).isNotNull().hasSize(2)
                .allMatch(not(t -> t.getId() == null))
                .allMatch(not(t -> t.getName().isEmpty()))
                .allMatch(t -> t.getGoal().getId() == 1L);
    }

    @Test
    public void saveTest() {
        Task task = new Task();
        task.setDate(LocalDate.now());
        task.setName("Test task");
        task.setGoal(goalRepository.findById(1L)
                .orElseThrow(() -> new NoSuchElementException("Goal with id = 1 not found")));
        taskRepository.save(task);
        List<Task> tasks = taskRepository.findAll();
        assertThat(tasks).isNotNull().hasSize(5)
                .contains(task);
    }

    @Test
    public void deleteTest() {
        Task task = taskRepository.findById(1L)
                .orElseThrow(() -> new NoSuchElementException("Task with id = 1 not found"));
        taskRepository.deleteById(task.getId());
        List<Task> tasks = taskRepository.findAll();
        assertThat(tasks).isNotNull().hasSize(3)
                .doesNotContain(task);
    }
}
