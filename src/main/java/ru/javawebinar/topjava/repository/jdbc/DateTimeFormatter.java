package ru.javawebinar.topjava.repository.jdbc;

import java.time.LocalDateTime;

public interface DateTimeFormatter<T> {
    T format(LocalDateTime dateTime);
}
