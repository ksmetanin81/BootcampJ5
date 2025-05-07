package com.colvir.homework5_taskDictionary.dao.JDBC;

import com.colvir.homework5_taskDictionary.dao.TaskDao;
import com.colvir.homework5_taskDictionary.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TaskJDBCDao implements TaskDao {

    private final JdbcOperations jdbcTemplate;

    @Override
    public List<Task> findAll() {
        return jdbcTemplate.query("""
                select t.id, t.date, t.name, t.description
                from tasks t
                order by t.id
                """, (resultSet, rowNum) -> {
            Task task = new Task();
            task.setId(resultSet.getLong("id"));
            task.setDate(resultSet.getDate("date").toLocalDate());
            task.setName(resultSet.getString("name"));
            task.setDescription(resultSet.getString("description"));
            return task;
        });
    }

    @Override
    public Task findById(Long id) {
        return DataAccessUtils.singleResult(
                jdbcTemplate.query("""
                        select t.id, t.date, t.name, t.description
                        from tasks t
                        where t.id = ?
                        """, (resultSet, rowNum) -> {
                    Task task = new Task();
                    task.setId(resultSet.getLong("id"));
                    task.setDate(resultSet.getDate("date").toLocalDate());
                    task.setName(resultSet.getString("name"));
                    task.setDescription(resultSet.getString("description"));
                    return task;
                }, id));
    }

    @Override
    public List<Task> findByDate(LocalDate date) {
        return jdbcTemplate.query("""
                select t.id, t.date, t.name, t.description
                from tasks t
                where t.date = ?
                order by t.id
                """, (resultSet, rowNum) -> {
            Task task = new Task();
            task.setId(resultSet.getLong("id"));
            task.setDate(resultSet.getDate("date").toLocalDate());
            task.setName(resultSet.getString("name"));
            task.setDescription(resultSet.getString("description"));
            return task;
        }, date);
    }

    @Override
    public void save(Task task) {
        if (task.getId() == null) {
            jdbcTemplate.update("""
                        insert into tasks(date, name, description)
                        values (?, ?, ?)
                    """, task.getDate(), task.getName(), task.getDescription());
        } else {
            jdbcTemplate.update("""
                        update tasks set date = ?, name = ?, description = ?
                        where id = ?
                    """, task.getDate(), task.getName(), task.getDescription(), task.getId());
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
