package com.colvir.homework5_taskDictionary.dao.JPA;

import com.colvir.homework5_taskDictionary.dao.TaskDao;
import com.colvir.homework5_taskDictionary.model.Task;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Primary
@Repository
@AllArgsConstructor
public class TaskJpaDao implements TaskDao {

    private final EntityManager entityManager;

    @Override
    public List<Task> findAll() {
        return entityManager.createQuery("select t from Task t order by t.id", Task.class).getResultList();
    }

    @Override
    public Task findById(Long id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public List<Task> findByDate(LocalDate date) {
        return entityManager.createQuery("select t from Task t where t.date = :date order by t.id", Task.class).setParameter("date", date).getResultList();
    }

    @Override
    public List<Task> findByGoalId(Long goalId) {
        return entityManager.createQuery("""
                select t from Task t
                join fetch t.goal g
                where g.id = :goalId
                order by t.id
                """, Task.class).setParameter("goalId", goalId).getResultList();
    }

    @Transactional
    @Override
    public void save(Task task) {
        if (task.getId() == null) {
            entityManager.persist(task);
        } else {
            entityManager.merge(task);
        }
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        return entityManager.createQuery("delete from Task t where t.id = :id")
                .setParameter("id", id)
                .executeUpdate() == 1;
    }
}
