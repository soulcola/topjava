package ru.javawebinar.topjava.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public record UserMeal(LocalDateTime dateTime, String description, int calories) {

}
