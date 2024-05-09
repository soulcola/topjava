package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime,
                                                            LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> result = new ArrayList<>();
        Map<LocalDate, Integer> caloriesPerDayMap = new HashMap<>();

        for (UserMeal meal : meals) {
            var date = meal.dateTime().toLocalDate();
            caloriesPerDayMap.put(meal.dateTime().toLocalDate(),
                    caloriesPerDayMap.getOrDefault(date, 0) + meal.calories());
        }

        for (UserMeal meal : meals) {
            if (TimeUtil.isBetweenHalfOpen(meal.dateTime().toLocalTime(), startTime, endTime)) {
                boolean isExcess = caloriesPerDayMap.get(meal.dateTime().toLocalDate()) > caloriesPerDay;
                result.add(new UserMealWithExcess(meal.dateTime(), meal.description(),
                        meal.calories(), isExcess));
            }
        }

        return result;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesPerDayMap = new HashMap<>();
        return meals.stream()
                .peek(m -> caloriesPerDayMap.put(m.dateTime().toLocalDate(),
                        caloriesPerDayMap.getOrDefault(m.dateTime().toLocalDate(), 0) + m.calories()))
                .filter(m -> TimeUtil.isBetweenHalfOpen(m.dateTime().toLocalTime(), startTime, endTime))
                .toList().stream()
                .map(m -> new UserMealWithExcess(m.dateTime(), m.description(), m.calories(), caloriesPerDayMap.get(m.dateTime().toLocalDate()) > caloriesPerDay))
                .toList();
    }
}
