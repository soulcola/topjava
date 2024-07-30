package ru.javawebinar.topjava.web.converter;

import org.springframework.core.convert.converter.Converter;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalTime;

public class CustomConditionalLocalTimeConverter implements Converter<String, LocalTime> {

    @Override
    public LocalTime convert(String source) {
        return DateTimeUtil.parseLocalTime(source);
    }
}
