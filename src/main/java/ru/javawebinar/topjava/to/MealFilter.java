package ru.javawebinar.topjava.to;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

public record MealFilter(
        LocalDate startDate,
        LocalDate endDate,
        LocalTime startTime,
        LocalTime endTime) {
}
