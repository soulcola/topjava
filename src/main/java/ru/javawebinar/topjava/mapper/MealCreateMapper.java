package ru.javawebinar.topjava.mapper;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealCreateDto;

import java.time.LocalDateTime;

public class MealCreateMapper {
    public Meal map (MealCreateDto object){
        return new Meal(LocalDateTime.parse(object.getDateTime()),
                object.getDescription(),
                Integer.parseInt(object.getCalories()));
    }
}
