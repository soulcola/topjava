package ru.javawebinar.topjava.model;

import lombok.Value;

@Value
public class MealCreateDto {
    String dateTime;
    String description;
    String calories;
}
