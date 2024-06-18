package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;
import static ru.javawebinar.topjava.util.MealsUtil.getTos;
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
        Meal maybeMealTo = repository.get(id, userId);
        return checkNotFoundWithId(maybeMealTo, id);
    }

    public List<MealTo> getAll(Integer userId) {
        return getTos(new ArrayList<>(repository.getAll(userId)), DEFAULT_CALORIES_PER_DAY);
    }

    public List<Meal> getAllByFilter(Integer userId, LocalDate startDate, LocalDate endDate) {
        return (repository).getAllByFilter(userId, startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
    }

    public void update(Meal meal, Integer userId) {
        checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

}