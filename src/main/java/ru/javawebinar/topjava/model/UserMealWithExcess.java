package ru.javawebinar.topjava.model;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class UserMealWithExcess {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean excess;
    private final Day day;

    public UserMealWithExcess(LocalDateTime dateTime, String description, int calories, boolean excess, Day day) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
        this.day = day;
    }

    @Override
    public String toString() {
        return "UserMealWithExcess{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + day.isExcess() +
                '}';
    }
}
