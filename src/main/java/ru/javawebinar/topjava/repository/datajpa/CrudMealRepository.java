package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
    int deleteByIdAndUserId(Integer mealId, Integer userId);

    List<Meal> findAllByUserIdOrderByDateTimeDesc(Integer userId);

    @Query("""
            SELECT m FROM Meal m
            WHERE m.user.id=:userId AND m.dateTime >= :startDateTime AND m.dateTime < :endDateTime 
            ORDER BY m.dateTime DESC""")
    List<Meal> getBetweenHalfOpen(@Param("userId") int userId,
                                  @Param("startDateTime") LocalDateTime start,
                                  @Param("endDateTime") LocalDateTime end);

    @Query("""
            SELECT m FROM Meal m JOIN FETCH m.user u
            WHERE m.id=:mealId AND u.id=:userId""")
    Optional<Meal> findByIdAndUserId(@Param("mealId")Integer mealId,
                                   @Param("userId")Integer userId);
}
