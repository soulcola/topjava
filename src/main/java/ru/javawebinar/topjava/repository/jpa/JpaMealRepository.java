package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepository implements MealRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        User userRef = em.getReference(User.class, userId);
        if (meal.isNew()) {
            meal.setUser(userRef);
            em.persist(meal);
            return meal;
        } else {
            Query query = em.createQuery("UPDATE Meal m " +
                                         "   SET description=:description, calories=:calories, dateTime=:date_time " +
                                         " WHERE id=:id AND user=:user");
            return query.setParameter("id", meal.getId())
                           .setParameter("description", meal.getDescription())
                           .setParameter("calories", meal.getCalories())
                           .setParameter("date_time", meal.getDateTime())
                           .setParameter("user", userRef)
                           .executeUpdate() != 0 ? meal : null;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        Optional<Meal> meal = Optional.ofNullable(em.find(Meal.class, id));
        if (meal.isPresent() && meal.get().getUser().getId() == userId) {
            em.remove(meal.get());
            return true;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        Optional<Meal> meal = Optional.ofNullable(em.find(Meal.class, id));
        if (meal.isPresent() && meal.get().getUser().getId() == userId) {
            return meal.get();
        }
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class).setParameter("userId", userId).getResultList();
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return em.createNamedQuery(Meal.GET_BETWEEN_HALF_OPEN, Meal.class)
                .setParameter("userId", userId)
                .setParameter("start", startDateTime)
                .setParameter("end", endDateTime)
                .getResultList();
    }
}