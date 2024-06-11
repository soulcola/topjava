package ru.javawebinar.topjava.mapper;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealCreateUpdateDto;

import java.time.LocalDateTime;

public class MealCreateMapper {
    public Meal map (MealCreateUpdateDto object){
        return new Meal(LocalDateTime.parse(object.getDateTime()),
                object.getDescription(),
                Integer.parseInt(object.getCalories()));
    }
}
