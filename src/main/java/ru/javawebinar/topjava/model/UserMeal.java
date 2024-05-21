package ru.javawebinar.topjava.model;

import lombok.Getter;

import java.time.LocalDateTime;

public record UserMeal(LocalDateTime dateTime, String description, int calories) {

}
