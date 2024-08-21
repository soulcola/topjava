package ru.javawebinar.topjava.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.web.SecurityUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Component
public class DateTimeValidator implements ConstraintValidator<ValidDateTime, Meal> {

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean isValid(Meal meal, ConstraintValidatorContext context) {
        if (meal == null || meal.getDateTime() == null || SecurityUtil.safeGet() == null){
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                        messageSource.getMessage("meal.dateDuplicate", null, LocaleContextHolder.getLocale()))
                .addConstraintViolation();
        int userId = SecurityUtil.authUserId();
        List<Meal> persistedMeal = mealRepository.getBetweenHalfOpen(meal.getDateTime(), meal.getDateTime().plusMinutes(1), userId);
        return persistedMeal.isEmpty() || persistedMeal.getFirst().getId().equals(meal.getId());
    }
}
