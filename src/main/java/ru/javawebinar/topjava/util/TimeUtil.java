package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        if (startTime != null || endTime != null)
            return !lt.isBefore(startTime) && lt.isBefore(endTime);
        else return true;
    }
    private static final String PATTERN = "yyyy-MM-dd HH:mm";


    public static String format(LocalDateTime from) {
        var dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN);
        return dateTimeFormatter.format(from);
    }
}
