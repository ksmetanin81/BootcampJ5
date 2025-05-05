package com.colvir.homework5_taskDictionary.dao;

import com.colvir.homework5_taskDictionary.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.time.ZoneId;

@Repository
@RequiredArgsConstructor
public class TaskDao {

    private final JdbcOperations jdbcTemplate;

    public Task findById(long id) {
        return jdbcTemplate.queryForObject(
                """
                        select id, date, name, description
                        from task
                        where id = ?
                        """, (rs, rowNum) -> {
                    Task task = new Task();
                    task.setId(rs.getLong("id"));
                    task.setDate(rs.getDate("date").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    task.setName(rs.getString("name"));
                    task.setDescription(rs.getString("description"));
                    return task;
                }, id );
    }
}
