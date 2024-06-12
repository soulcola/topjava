package ru.javawebinar.topjava.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealCreateUpdateDto;

import java.time.LocalDateTime;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MealCreateMapper {
    public static MealCreateMapper INSTANCE = new MealCreateMapper();

    public static MealCreateMapper getInstance() {
        return INSTANCE;
    }
    public Meal map (MealCreateUpdateDto object){
        return new Meal(
                Optional.ofNullable(object.getId())
                        .map(Integer::valueOf)
                        .orElse(null),
                LocalDateTime.parse(object.getDateTime()),
                object.getDescription(),
                Integer.parseInt(object.getCalories()));
    }
}
