package ru.javawebinar.topjava.repository.jdbc.converter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
@Profile("!hsqldb")
public class PostgresDateTimeConverter implements DateTimeConverter<LocalDateTime> {

    @Override
    public LocalDateTime format(LocalDateTime dateTime) {
        return dateTime;
    }
}
