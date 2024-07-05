package ru.javawebinar.topjava.repository.jdbc.converter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Component
@Profile("hsqldb")
public class HsqlDateTimeConverter implements DateTimeConverter<Timestamp> {
    @Override
    public Timestamp format(LocalDateTime dateTime) {
        return Timestamp.valueOf(dateTime);
    }
}
