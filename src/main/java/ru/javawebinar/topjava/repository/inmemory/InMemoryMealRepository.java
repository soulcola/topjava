package ru.javawebinar.topjava.repository.inmemory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.AbstractBaseEntity;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealFilter;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

@Slf4j
@Repository
public class InMemoryMealRepository implements MealRepository {
    private final UserRepository userRepository;
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);


    public InMemoryMealRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
        MealsUtil.userMeals.forEach(meal -> this.save(meal, SecurityUtil.DEFAULT_USER_ID));
        MealsUtil.adminMeals.forEach(meal -> this.save(meal, SecurityUtil.DEFAULT_ADMIN_ID));
    }

    @Override
    public Meal save(Meal meal, Integer userId) {
        var maybeUser = Optional.ofNullable(userRepository.get(userId));
        if (maybeUser.isPresent() && meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            maybeUser.get().setUserMeal(meal);
            return meal;
        }
        // handle case: update, but not present in storage
        if (maybeUser.isPresent() && maybeUser.get().getUserMeals().containsKey(meal.getId())) {
            return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        }
        return null;
    }

    @Override
    public boolean delete(int id, Integer userId) {
        var maybeUser = Optional.ofNullable(userRepository.get(userId));
        return maybeUser.filter(user -> repository.values()
                .removeIf(meal -> user.getUserMeals().containsKey(meal.getId()) && meal.getId() == id)).isPresent();
    }

    @Override
    public Meal get(int id, Integer userId) {
        var maybeUser = Optional.ofNullable(userRepository.get(userId));
        var maybeMeal = Optional.ofNullable(repository.get(id));
        if (maybeUser.isEmpty() || maybeMeal.isEmpty()
                || !maybeUser.get().getUserMeals().containsKey(maybeMeal.get().getId())) {
            return null;
        }
        return maybeMeal.get();
    }

    @Override
    public Collection<Meal> getAll(Integer userId) {
        return getAllByFilter(userId, new MealFilter(LocalDate.MIN, LocalDate.MAX, LocalTime.MIN, LocalTime.MAX));
    }

    @Override
    public Collection<Meal> getAllByFilter(Integer userId, MealFilter mealFilter) {
        var maybeUser = Optional.ofNullable(userRepository.get(userId));
        if (maybeUser.isEmpty()) {
            return Collections.emptyList();
        }
        var predicates = Predicates.builder(Meal.class)
                .add(mealFilter.startDate(), localDate -> meal -> !meal.getDate().isBefore(localDate))
                .add(mealFilter.endDate(), localDate -> meal -> !meal.getDate().isAfter(localDate))
                .add(maybeUser, user1 -> meal -> user1.get().getUserMeals().containsKey((meal).getId()))
                .build();

        Predicate<Meal> predicate2 = meal -> DateTimeUtil.isBetweenHalfOpen2(meal.getDate(), mealFilter.startDate(),
                mealFilter.endDate());
        Predicate<Meal> predicate1 = predicates.getPredicates().stream().reduce(x -> true, Predicate::and);
        return repository.values().stream()
                .filter(predicate1)
                .sorted((o1, o2) -> o2.getDate().compareTo(o1.getDate()))
                .collect(toList());
    }

}

