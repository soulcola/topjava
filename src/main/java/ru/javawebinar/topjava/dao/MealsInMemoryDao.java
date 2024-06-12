package ru.javawebinar.topjava.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.javawebinar.topjava.model.Database;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MealsInMemoryDao implements Dao<Integer, Meal> {
    private static final Map<Integer, Meal> mealsDb = Database.getMealsDb();
    private static final MealsInMemoryDao INSTANCE = new MealsInMemoryDao();

    public static MealsInMemoryDao getInstance() {
        return INSTANCE;
    }
    @Override
    public Meal create(Meal entity) {
        return mealsDb.put(Database.index.get(), entity);
    }

    @Override
    public Optional<Meal> findById(Integer id) {
        return mealsDb.entrySet().stream()
                .filter(integerMealEntry -> integerMealEntry.getKey().equals(id))
                .findFirst()
                .map(Map.Entry::getValue);
    }

    @Override
    public void update(Integer id, Meal entity) {
        entity.setId(id);
        mealsDb.replace(id, entity);
    }

    @Override
    public void delete(Integer id) {
        mealsDb.remove(id);
    }

    @Override
    public List<Meal> findAll() {
        return mealsDb.values().stream().toList();
    }
}
