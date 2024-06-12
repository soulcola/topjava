package ru.javawebinar.topjava.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealCreateUpdateDto;

import java.time.LocalDateTime;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MealCreateMapper {
    public static MealCreateMapper INSTANCE = new MealCreateMapper();

    public static MealCreateMapper getInstance() {
        return INSTANCE;
    }
    public Meal map (MealCreateUpdateDto object){
        return new Meal(LocalDateTime.parse(object.getDateTime()),
                object.getDescription(),
                Integer.parseInt(object.getCalories()));
    }
}
