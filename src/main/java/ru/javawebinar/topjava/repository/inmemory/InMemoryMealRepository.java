package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);


    public InMemoryMealRepository() {
        MealsUtil.userMeals.forEach(meal -> this.save(meal, SecurityUtil.DEFAULT_USER_ID));
        MealsUtil.adminMeals.forEach(meal -> this.save(meal, SecurityUtil.DEFAULT_ADMIN_ID));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.computeIfAbsent(userId, key -> new ConcurrentHashMap<>()).put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return Optional.ofNullable(repository.get(userId))
                .map(mealMap -> mealMap.computeIfPresent(meal.getId(), (id, oldMeal) -> meal))
                .orElse(null);

    }

    @Override
    public boolean delete(int id, int userId) {
        return Optional.ofNullable(repository.get(userId))
                .map(v -> v.remove(id) != null)
                .orElse(false);
    }

    @Override
    public Meal get(int id, int userId) {
        return Optional.ofNullable(repository.get(userId))
                .orElseGet(Collections::emptyMap)
                .get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return getAllByFilter(userId, LocalDateTime.MIN, LocalDateTime.MAX);
    }

    @Override
    public List<Meal> getAllByFilter(int userId, LocalDateTime startDate, LocalDateTime endDate) {

        return Optional.ofNullable(repository.get(userId)).orElseGet(Collections::emptyMap)
                .values().stream()
                .filter(meal -> !meal.getDateTime().isBefore(startDate) && !meal.getDateTime().isAfter(endDate))
                .sorted(Comparator.comparing(Meal::getDate).reversed())
                .collect(toList());
    }
}

