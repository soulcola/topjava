package ru.javawebinar.topjava.util.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.web.SecurityUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class DateTimeValidator implements ConstraintValidator<ValidDateTime, Meal> {
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean isValid(Meal value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                        messageSource.getMessage("meal.dateDuplicate", null, LocaleContextHolder.getLocale()))
                .addConstraintViolation();
        int userId = SecurityUtil.authUserId();
        return mealRepository.getBetweenHalfOpen(value.getDateTime(), value.getDateTime().plusMinutes(1), userId).isEmpty();
    }
}
