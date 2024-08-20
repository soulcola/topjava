package ru.javawebinar.topjava.util.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import ru.javawebinar.topjava.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, ValidatedEmail> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageSource messageSource;


    @Override
    public boolean isValid(ValidatedEmail user, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                messageSource.getMessage("user.emailDuplicate", null, LocaleContextHolder.getLocale()))
                .addConstraintViolation();
        return userRepository.getByEmail(user.getEmail()) == null;
    }
}
