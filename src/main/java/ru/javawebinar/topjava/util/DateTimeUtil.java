package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T lt, T start, T end) {
        return lt.compareTo(start) >= 0 && lt.compareTo(end) < 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDateTime formatDate(String dateTime) {
        try {
            return LocalDate.parse(dateTime, DATE_FORMATTER).atStartOfDay();
        } catch (DateTimeParseException | NullPointerException e) {
            return null;
        }
    }

    public static LocalTime formatTime(String time) {
        try {
            return LocalTime.parse(time, TIME_FORMATTER);
        } catch (DateTimeParseException | NullPointerException e) {
            return null;
        }
    }
}

