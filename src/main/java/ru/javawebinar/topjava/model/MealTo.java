package ru.javawebinar.topjava.model;

import lombok.Getter;
import ru.javawebinar.topjava.util.DateTimeFormat;

import java.time.LocalDateTime;
@Getter
public class MealTo {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean excess;

    private final Integer id;

    public MealTo(LocalDateTime dateTime, String description, int calories, boolean excess, Integer id) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
        this.id = id;
    }

    public String getFormattedDateTime(){
        return new DateTimeFormat().format(dateTime);
    }
    @Override
    public String toString() {
        return "MealTo{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }
}
