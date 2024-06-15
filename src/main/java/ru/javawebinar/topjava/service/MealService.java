package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealFilter;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static ru.javawebinar.topjava.util.MealsUtil.*;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal, Integer userId) {
        return repository.save(meal, userId);
    }

    public void delete(int id, Integer userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public Meal get(int id, Integer userId) {
        var maybeMealTo = repository.get(id, userId);
        return checkNotFoundWithId(maybeMealTo, id);
    }

    public List<MealTo> getAll(Integer userId) {
        return getTos(repository.getAll(userId).stream().toList(), DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealTo> getAllByFilter(Integer userId, MealFilter mealFilter) {
        LocalTime startTime = Optional.ofNullable(mealFilter.startTime()).orElse(LocalTime.MIN);
        LocalTime endTime = Optional.ofNullable(mealFilter.endTime()).orElse(LocalTime.MAX);
        return getFilteredTos((repository).getAllByFilter(userId, mealFilter)
                        .stream()
                        .toList(),
                DEFAULT_CALORIES_PER_DAY, startTime, endTime);
    }

    public void update(Meal meal, Integer userId) {
        checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

}