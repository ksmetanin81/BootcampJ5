package com.colvir.homework5_taskDictionary.dao.JPA;

import com.colvir.homework5_taskDictionary.dao.GoalDao;
import com.colvir.homework5_taskDictionary.model.Goal;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
@AllArgsConstructor
public class GoalJpaDao implements GoalDao {

    private final EntityManager entityManager;

    @Override
    public List<Goal> findAll() {
        return entityManager.createQuery("select g from Goal g order by g.id", Goal.class).getResultList();
    }

    @Override
    public Goal findById(Long id) {
        return entityManager.find(Goal.class, id);
    }

    @Transactional
    @Override
    public void save(Goal goal) {
        if (goal.getId() == null) {
            entityManager.persist(goal);
        } else {
            entityManager.merge(goal);
        }
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        return entityManager.createQuery("delete from Goal g where g.id = :id")
                .setParameter("id", id)
                .executeUpdate() == 1;
    }
}
