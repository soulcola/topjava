package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Database;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @see <a href="https://javaops-demo.ru/topjava">Demo application</a>
 * @see <a href="https://github.com/JavaOPs/topjava">Initial project</a>
 */
public class Main {
    public static void main(String[] args) {
        System.out.format("Hello TopJava Enterprise!");
        DateTimeFormat dateTimeFormat = new DateTimeFormat();
        System.out.println(dateTimeFormat.format(LocalDateTime.now()));
    }
}
