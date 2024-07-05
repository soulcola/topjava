package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Component
@Profile("hsqldb")
public class HsqlDateTimeFormatter implements DateTimeFormatter<Timestamp>{
    @Override
    public Timestamp format(LocalDateTime dateTime) {
        return Timestamp.valueOf(dateTime);
    }
}
