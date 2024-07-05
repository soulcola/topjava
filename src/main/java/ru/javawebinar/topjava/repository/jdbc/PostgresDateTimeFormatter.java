package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
@Profile("!hsqldb")
public class PostgresDateTimeFormatter implements DateTimeFormatter<LocalDateTime>{

    @Override
    public LocalDateTime format(LocalDateTime dateTime) {
        return dateTime;
    }
}
