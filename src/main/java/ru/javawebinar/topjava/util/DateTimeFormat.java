package ru.javawebinar.topjava.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateTimeFormat {
    private static final DateTimeFormat INSTANCE = new DateTimeFormat();

    public static DateTimeFormat getInstance() {
        return INSTANCE;
    }
    private static final String PATTERN = "yyyy-MM-dd HH:mm";
    public String format(LocalDateTime from) {
        var dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN);
        return dateTimeFormatter.format(from);
    }
}
