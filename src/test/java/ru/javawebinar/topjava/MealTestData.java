package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int USER_MEAL_ID = 100003;
    public static int USER_MEAL_COUNTER = USER_MEAL_ID;
    public static final int ADMIN_MEAL_ID = 100010;

    public static Meal USER_MEAL_1 = new Meal(USER_MEAL_COUNTER++, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static Meal USER_MEAL_2 = new Meal(USER_MEAL_COUNTER++, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static Meal USER_MEAL_3 = new Meal(USER_MEAL_COUNTER++, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    public static Meal USER_MEAL_4 = new Meal(USER_MEAL_COUNTER++, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
    public static Meal USER_MEAL_5 = new Meal(USER_MEAL_COUNTER++, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
    public static Meal USER_MEAL_6 = new Meal(USER_MEAL_COUNTER++, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
    public static Meal USER_MEAL_7 = new Meal(USER_MEAL_COUNTER++, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);

    public static Meal UPDATED_MEAL = new Meal(USER_MEAL_ID,
            LocalDateTime.of(2000, 1, 1, 0, 0),
            "updated description", 0);

    public static Meal NEW_MEAL = new Meal(LocalDateTime.of(2000, 1, 1, 0, 0),
            "test", 0);
}
