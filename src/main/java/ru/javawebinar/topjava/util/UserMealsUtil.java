package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Day;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
//        mealsTo.forEach(System.out::println);
        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles
        return null;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> result = new ArrayList<>();
        var collect = meals.stream()
                .collect(
                        Collectors.groupingBy(userMeal -> userMeal.getDateTime().toLocalDate(),
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        listPart -> {
                                            Day day = new Day(0, listPart.get(0).getDateTime().toLocalDate(), false);
                                            listPart.stream()
                                                    .map(userMeal -> {
                                                        int calories = userMeal.getCalories();
                                                        day.setCaloriesSum(day.getCaloriesSum() + calories);
                                                        LocalTime mealTime = userMeal.getDateTime().toLocalTime();
                                                        return new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), false, day);
                                                    })
                                                    .filter(userMealWithExcess -> TimeUtil.isBetweenHalfOpen(userMealWithExcess.getDateTime().toLocalTime(), startTime, endTime))
                                                    .forEach(result::add);
                                            day.setExcess(day.getCaloriesSum() > caloriesPerDay);
                                            return result;
                                        }
                                )
                        )
                );
        return result;
    }
}