package com.colvir.taskDictionary.dao.JDBC;

import com.colvir.taskDictionary.dao.GoalDao;
import com.colvir.taskDictionary.model.Goal;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GoalJdbcDao implements GoalDao {
    private final JdbcOperations jdbcTemplate;

    private final RowMapper<Goal> rowMapper = (resultSet, rowNum) -> {
        Goal goal = new Goal();
        goal.setId(resultSet.getLong("id"));
        goal.setName(resultSet.getString("name"));
        goal.setMotivation(resultSet.getString("motivation"));
        goal.setResources(resultSet.getString("resources"));
        goal.setDeadline(resultSet.getDate("deadline").toLocalDate());
        goal.setDescription(resultSet.getString("description"));
        return goal;
    };

    @Override
    public List<Goal> findAll() {
        return jdbcTemplate.query("""
                select g.id, g.name, g.motivation, g.resources, g.deadline, g.description
                from goals g
                order by g.id
                """, rowMapper);
    }

    @Override
    public Goal findById(Long id) {
        return DataAccessUtils.singleResult(
                jdbcTemplate.query("""
                        select g.id, g.name, g.motivation, g.resources, g.deadline, g.description
                        from goals g
                        where g.id = ?
                        """, rowMapper, id));
    }

    @Override
    public void save(Goal goal) {
        if (goal.getId() == null) {
            jdbcTemplate.update("""
                        insert into goals(name, motivation, resources, deadline, description)
                        values (?, ?, ?, ?, ?)
                    """, goal.getName(), goal.getMotivation(), goal.getResources(), goal.getDeadline(), goal.getDescription());
        } else {
            jdbcTemplate.update("""
                        update goals set name = ?, motivation = ?, resources = ?, deadline = ?, description = ?
                        where id = ?
                    """, goal.getName(), goal.getMotivation(), goal.getResources(), goal.getDeadline(), goal.getDescription(), goal.getId());
        }
    }

    @Override
    public boolean delete(Long id) {
        return jdbcTemplate.update("""
                    delete from goals
                    where id = ?
                """, id) == 1;
    }
}
