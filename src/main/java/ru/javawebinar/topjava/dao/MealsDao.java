package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Database;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MealsDao implements Dao<Integer, Meal>{
    private static final Map<Integer, Meal> mealsDb = Database.getMealsDb();
    @Override
    public boolean create(Meal entity) {
        mealsDb.put(Database.index, entity);
        return true;
    }

    @Override
    public Optional<Meal> findById(Integer id) {
        return mealsDb.entrySet().stream()
                .filter(integerMealEntry -> integerMealEntry.getKey().equals(id))
                .findFirst()
                .map(Map.Entry::getValue);
    }

    @Override
    public void update(Meal entity) {

    }

    @Override
    public boolean delete(Integer id) {
        return mealsDb.remove(id) != null;
    }

    @Override
    public List<Meal> findAll() {
        return mealsDb.values().stream().toList();
    }
}
