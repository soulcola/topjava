package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Database;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.Optional;

public class MealsDao implements Dao<Integer, Meal>{
//    private static final List<Meal> mealsDb = Database.mealsDb;
    @Override
    public boolean create(Meal entity) {
        entity.setId(Database.index);
        Database.index++;
        Database.mealsDb.add(entity);
        return true;
    }

    @Override
    public Optional<Meal> findById(Integer id) {
        return null;
    }

    @Override
    public void update(Meal entity) {

    }

    @Override
    public boolean delete(Meal entity) {
        return false;
    }

    @Override
    public List<Meal> findAll() {
        return Database.mealsDb;
    }
}
