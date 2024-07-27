package ru.javawebinar.topjava.converter;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.time.LocalTime;
import java.util.Objects;

import static java.time.format.DateTimeFormatter.ISO_TIME;
import static ru.javawebinar.topjava.converter.DateTimeFormat.Type.TIME;

public class CustomConditionalLocalTimeConverter implements Converter<String, LocalTime>, ConditionalConverter {
    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return targetType.hasAnnotation(DateTimeFormat.class)
                && Objects.requireNonNull(targetType.getAnnotation(DateTimeFormat.class)).type() == TIME;
    }

    @Override
    public LocalTime convert(String source) {
        if (StringUtils.hasLength(source)) {
            return null;
        }
        return LocalTime.parse(source, ISO_TIME);
    }
}
