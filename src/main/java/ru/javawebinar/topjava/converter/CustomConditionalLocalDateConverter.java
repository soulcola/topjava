package ru.javawebinar.topjava.converter;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Objects;

import static java.time.format.DateTimeFormatter.ISO_DATE;
import static ru.javawebinar.topjava.converter.DateTimeFormat.Type.DATE;

public class CustomConditionalLocalDateConverter implements Converter<String, LocalDate>, ConditionalConverter {
    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return targetType.hasAnnotation(DateTimeFormat.class)
                && Objects.requireNonNull(targetType.getAnnotation(DateTimeFormat.class)).type() == DATE;
    }

    @Override
    public LocalDate convert(String source) {
        if (StringUtils.hasLength(source)) {
            return null;
        }
        return LocalDate.parse(source, ISO_DATE);
    }
}
