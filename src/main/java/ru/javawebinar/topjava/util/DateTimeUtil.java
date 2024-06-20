package ru.javawebinar.topjava.util;

import jdk.internal.joptsimple.internal.Strings;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final LocalDateTime DATE_MIN = LocalDateTime.of(1, 1, 1, 0, 0);
    private static final LocalDateTime DATE_MAX = LocalDateTime.of(3000, 1, 1, 0, 0);


    public static LocalDateTime getOrMinDate(LocalDate date) {
        return date != null ? date.atStartOfDay() : DATE_MIN;
    }

    public static LocalDateTime getNextDayOrMaxDate(LocalDate date) {
        return date != null ? date.atStartOfDay().plusDays(1) : DATE_MAX;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate formatDate(String dateTime) {
        return Strings.isNullOrEmpty(dateTime) ? null : LocalDate.parse(dateTime);
    }

    public static LocalTime formatTime(String time) {
        return Strings.isNullOrEmpty(time) ? null : LocalTime.parse(time);
    }
}