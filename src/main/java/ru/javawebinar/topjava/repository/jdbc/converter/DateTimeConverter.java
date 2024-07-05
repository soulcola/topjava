package ru.javawebinar.topjava.repository.jdbc.converter;

import java.time.LocalDateTime;

public interface DateTimeConverter<T> {
    T format(LocalDateTime dateTime);
}
