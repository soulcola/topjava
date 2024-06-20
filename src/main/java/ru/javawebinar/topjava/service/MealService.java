package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.getNextDayOrMaxDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.getOrMinDate;
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

    public List<Meal> getAll(Integer userId) {
        return repository.getAll(userId);
    }

    public List<Meal> getAllByFilter(Integer userId, LocalDate startDate, LocalDate endDate) {
        return repository.getAllByFilter(userId, getOrMinDate(startDate), getNextDayOrMaxDate(endDate));
    }

    public void update(Meal meal, Integer userId) {
        checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

}