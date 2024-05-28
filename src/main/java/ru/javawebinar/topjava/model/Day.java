package ru.javawebinar.topjava.model;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Day {
    Integer caloriesSum;
    Boolean isExcess;
}
