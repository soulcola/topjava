package ru.javawebinar.topjava.web.converter;

import org.springframework.core.convert.converter.Converter;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDate;

public class CustomConditionalLocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        return DateTimeUtil.parseLocalDate(source);
    }
}
