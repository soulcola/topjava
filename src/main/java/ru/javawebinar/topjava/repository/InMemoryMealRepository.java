package ru.javawebinar.topjava.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InMemoryMealRepository implements MealRepository {

    public static AtomicInteger index = new AtomicInteger(1);
    private static final Map<Integer, Meal> mealsDb = new ConcurrentHashMap<>();
    private static final InMemoryMealRepository INSTANCE = new InMemoryMealRepository();

    public static InMemoryMealRepository getInstance() {
        return INSTANCE;
    }

    {
        MealsUtil.meals.forEach(this::create);
    }

    @Override
    public Meal create(Meal entity) {
        if (isNew(entity)) {
            entity.setId(index.getAndIncrement());
            mealsDb.put(entity.getId(), entity);
            return entity;
        }
        return mealsDb.computeIfPresent(entity.getId(), (i, e) -> entity);
    }

    @Override
    public Optional<Meal> findById(Integer id) {
        return Optional.ofNullable(mealsDb.get(id));
    }

    @Override
    public void delete(Integer id) {
        mealsDb.remove(id);
    }

    @Override
    public List<Meal> findAll() {
        return mealsDb.values().stream().toList();
    }

    private static boolean isNew(Meal entity) {
        return entity.getId() == null;
    }
}
