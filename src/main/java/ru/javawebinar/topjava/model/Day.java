package ru.javawebinar.topjava.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class Day {
    private Integer caloriesSum;
    private LocalDate date;
    private boolean excess;
}
