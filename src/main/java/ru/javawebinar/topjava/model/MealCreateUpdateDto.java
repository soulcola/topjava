package ru.javawebinar.topjava.model;

import lombok.Value;

@Value
public class MealCreateUpdateDto {
    String dateTime;
    String description;
    String calories;
}
