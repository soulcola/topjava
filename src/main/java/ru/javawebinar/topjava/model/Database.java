package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.dao.MealsDao;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Database {
    public static Integer index = 1;
    private static final Map<Integer, Meal> mealsDb = new ConcurrentHashMap<>();

    public static Map<Integer, Meal> getMealsDb() {
        return (Map<Integer, Meal>) Proxy.newProxyInstance(Database.class.getClassLoader(),
                new Class[]{Map.class}, ((proxy, method, args) -> {
                    if (method.getName().equals("put")) {
                        Method setId = args[1].getClass().getMethod("setId", Integer.class);
                        setId.invoke(args[1], index);
                        ++index;
                    }
                    return method.invoke(mealsDb, args);
                }));
    }

    static {
        init();
    }

    public static void init() {
        Map<Integer, Meal> db = getMealsDb();
        Arrays.stream(new Meal[]{
                        new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                        new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                        new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                        new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                        new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                        new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                        new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)})
                .forEach(meal -> {
                    meal.setId(index);
                    db.put(index, meal);
                });
    }
}
