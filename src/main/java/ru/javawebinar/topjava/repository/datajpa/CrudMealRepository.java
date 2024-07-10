package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Modifying
    @Query("""
            DELETE FROM Meal m
            WHERE m.id=:mealId AND m.user.id=:userId
            """)
    int deleteByIdAndUserId(@Param("mealId") int mealId,
                            @Param("userId") int userId);

    @Query("""
            SELECT m FROM Meal m
            WHERE m.user.id=:userId
            ORDER BY m.dateTime DESC
            """)
    List<Meal> findAllByUserId(@Param("userId") int userId);

    @Query("""
            SELECT m FROM Meal m
            WHERE m.id=:mealId AND m.user.id=:userId
            """)
    Meal findByIdAndUserId(@Param("mealId") int mealId,
                           @Param("userId") int userId);

    @Query("""
            SELECT m FROM Meal m
            WHERE m.user.id=:userId AND m.dateTime >= :startDateTime AND m.dateTime < :endDateTime 
            ORDER BY m.dateTime DESC""")
    List<Meal> getBetweenHalfOpen(@Param("userId") int userId,
                                  @Param("startDateTime") LocalDateTime start,
                                  @Param("endDateTime") LocalDateTime end);

    @Query("""
            SELECT m FROM Meal m JOIN FETCH m.user u JOIN FETCH u.roles
            WHERE m.id=:mealId AND u.id=:userId""")
    Meal getWithUser(@Param("mealId") int mealId,
                     @Param("userId") int userId);
}
