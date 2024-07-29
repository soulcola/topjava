package ru.javawebinar.topjava.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

public class CustomConditionalLocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        if (StringUtils.hasLength(source)) {
            return LocalDate.parse(source);
        }
        return null;
    }
}
