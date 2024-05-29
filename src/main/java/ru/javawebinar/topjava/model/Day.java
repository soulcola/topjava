package ru.javawebinar.topjava.model;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Day {
    private LocalDate date;
    private Integer caloriesSum;
    private Boolean excess;

    public Day(LocalDate date, Integer caloriesSum) {
        this.date = date;
        this.caloriesSum = caloriesSum;
    }
}
