package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int USER_MEAL_ID = START_SEQ + 3;
    public static int userMealCounter = USER_MEAL_ID;
    public static Meal userMeal1 = new Meal(userMealCounter++,  LocalDateTime.parse("2024-06-23T08:00:00"), "Завтрак", 350);
    public static Meal userMeal2 = new Meal(userMealCounter++,  LocalDateTime.parse("2024-06-23T13:00:00"), "Обед", 600);
    public static Meal userMeal3 = new Meal(userMealCounter++,  LocalDateTime.parse("2024-06-23T17:00:00"), "Полдник", 250);
    public static Meal userMeal4 = new Meal(userMealCounter++,  LocalDateTime.parse("2024-06-23T20:00:00"), "Ужин", 500);
    public static Meal userMeal5 = new Meal(userMealCounter++,  LocalDateTime.parse("2024-06-24T08:00:00"), "Завтрак", 350);

    public static Meal updatedMeal = new Meal(USER_MEAL_ID, LocalDateTime.of(2000, 1, 1, 0, 0), "updated description", 0);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().containsExactlyInAnyOrderElementsOf(expected);
    }

    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2000, 1, 1, 0, 0), "test", 0);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(userMeal1);
        updated.setDescription("update");
        updated.setDateTime(LocalDateTime.of(2000, 1, 1, 0, 0));
        updated.setCalories(0);
        return updated;
    }
}
