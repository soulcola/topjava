package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
        Meal maybeMealTo = repository.get(id, userId);
        return checkNotFoundWithId(maybeMealTo, id);
    }

    public List<MealTo> getAll(Integer userId) {
        return getTos(new ArrayList<>(repository.getAll(userId)), DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealTo> getAllByFilter(Integer userId, LocalDateTime maybeStartDate, LocalDateTime maybeEndDate,
                                       LocalTime maybeStartTime, LocalTime maybeEndTime) {
        LocalTime startTime = Optional.ofNullable(maybeStartTime).orElse(LocalTime.MIN);
        LocalTime endTime = Optional.ofNullable(maybeEndTime).orElse(LocalTime.MAX);
        return getFilteredTos(new ArrayList<>((repository).getAllByFilter(userId, maybeStartDate, maybeEndDate)),
                DEFAULT_CALORIES_PER_DAY, startTime, endTime);
    }

    public void update(Meal meal, Integer userId) {
        checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

}