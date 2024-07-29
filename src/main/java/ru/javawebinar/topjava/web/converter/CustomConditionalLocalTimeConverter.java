package ru.javawebinar.topjava.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.time.LocalTime;

public class CustomConditionalLocalTimeConverter implements Converter<String, LocalTime> {

    @Override
    public LocalTime convert(String source) {
        if (StringUtils.hasLength(source)) {
            return LocalTime.parse(source);
        }
        return null;
    }
}
