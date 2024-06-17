package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;
import static ru.javawebinar.topjava.util.DateTimeUtil.isBetweenHalfOpen;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);


    public InMemoryMealRepository() {
        MealsUtil.userMeals.forEach(meal -> this.save(meal, SecurityUtil.DEFAULT_USER_ID));
        MealsUtil.adminMeals.forEach(meal -> this.save(meal, SecurityUtil.DEFAULT_ADMIN_ID));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        Optional<Meal> maybeMeal = Optional.ofNullable(repository.get(meal.getId()));
        if (maybeMeal.isPresent() && userId == maybeMeal.get().getUserId()) {
            meal.setUserId(userId);
            return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        }
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        Meal meal = repository.get(id);
        if (meal == null || meal.getUserId() != userId) {
            return false;
        }
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        Optional<Meal> maybeMeal = Optional.ofNullable(repository.get(id));
        return maybeMeal
                .filter(meal -> meal.getUserId() == userId)
                .orElse(null);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return getAllByFilter(userId, LocalDateTime.MIN, LocalDateTime.MAX);
    }

    @Override
    public List<Meal> getAllByFilter(int userId, LocalDateTime maybeStartDate, LocalDateTime maybeEndDate) {
        LocalDate startDate = Optional.ofNullable(maybeStartDate)
                .map(LocalDateTime::toLocalDate)
                .orElse(LocalDate.MIN);
        LocalDate endDate = Optional.ofNullable(maybeEndDate)
                .map(LocalDateTime::toLocalDate)
                .orElse(LocalDate.MAX);
        return repository.values().stream()
                .filter(meal -> meal.getUserId() == userId && isBetweenHalfOpen(meal.getDate(), startDate, endDate))
                .sorted(Comparator.comparing(Meal::getDate).reversed())
                .collect(toList());
    }

}

