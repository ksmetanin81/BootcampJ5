package com.colvir.taskDictionary.dao.JDBC;

import com.colvir.taskDictionary.dao.TaskDao;
import com.colvir.taskDictionary.model.Goal;
import com.colvir.taskDictionary.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TaskJdbcDao implements TaskDao {

    private final JdbcOperations jdbcTemplate;

    private final RowMapper<Task> rowMapper = (resultSet, rowNum) -> {
        Task task = new Task();
        task.setId(resultSet.getLong("id"));
        task.setDate(resultSet.getDate("date").toLocalDate());
        task.setName(resultSet.getString("name"));
        task.setDescription(resultSet.getString("description"));

        Goal goal = new Goal();
        goal.setId(resultSet.getLong("goal_id"));
        goal.setName(resultSet.getString("goal_name"));
        goal.setMotivation(resultSet.getString("motivation"));
        goal.setResources(resultSet.getString("resources"));
        goal.setDeadline(resultSet.getDate("deadline").toLocalDate());
        goal.setDescription(resultSet.getString("goal_description"));
        task.setGoal(goal);

        return task;
    };

    @Override
    public List<Task> findAll() {
        return jdbcTemplate.query("""
                select t.id, t.date, t.name, t.description, t.goal_id,
                       g.name as goal_name, g.motivation, g.resources, g.deadline, g.description as goal_description
                from tasks t
                join goals g on g.id = t.goal_id
                order by t.id
                """, rowMapper);
    }

    @Override
    public Task findById(Long id) {
        return DataAccessUtils.singleResult(
                jdbcTemplate.query("""
                        select t.id, t.date, t.name, t.description, t.goal_id,
                               g.name as goal_name, g.motivation, g.resources, g.deadline, g.description as goal_description
                        from tasks t
                        join goals g on g.id = t.goal_id
                        where t.id = ?
                        """, rowMapper, id));
    }

    @Override
    public List<Task> findByDate(LocalDate date) {
        return jdbcTemplate.query("""
                select t.id, t.date, t.name, t.description, t.goal_id,
                       g.name as goal_name, g.motivation, g.resources, g.deadline, g.description as goal_description
                from tasks t
                join goals g on g.id = t.goal_id
                where t.date = ?
                order by t.id
                """, rowMapper, date);
    }

    @Override
    public List<Task> findByGoalId(Long goalId) {
        return jdbcTemplate.query("""
                select t.id, t.date, t.name, t.description, t.goal_id,
                       g.name as goal_name, g.motivation, g.resources, g.deadline, g.description as goal_description
                from tasks t
                join goals g on g.id = t.goal_id
                where g.id = ?
                order by t.id
                """, rowMapper, goalId);
    }

    @Override
    public void save(Task task) {
        if (task.getId() == null) {
            jdbcTemplate.update("""
                        insert into tasks(date, name, description, goal_id)
                        values (?, ?, ?, ?)
                    """, task.getDate(), task.getName(), task.getDescription(), task.getGoal().getId());
        } else {
            jdbcTemplate.update("""
                        update tasks set date = ?, name = ?, description = ?, goal_id = ?
                        where id = ?
                    """, task.getDate(), task.getName(), task.getDescription(), task.getId(), task.getGoal().getId());
        }
    }

    @Override
    public boolean delete(Long id) {
        return jdbcTemplate.update("""
                    delete from tasks
                    where id = ?
                """, id) == 1;
    }
}
