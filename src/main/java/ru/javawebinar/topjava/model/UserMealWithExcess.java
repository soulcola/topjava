package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class UserMealWithExcess {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private boolean excess;
    private final Day day;

    public static Map<LocalDate, Day> days = new HashMap<>();

    public UserMealWithExcess(LocalDateTime dateTime, String description, int calories, Day day) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = day.isExcess;
        this.day = day;
    }

    public boolean isExcess() {
        return day.isExcess;
    }

    @Override
    public String toString() {
        return "UserMealWithExcess{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + isExcess() +
                '}';
    }
}
