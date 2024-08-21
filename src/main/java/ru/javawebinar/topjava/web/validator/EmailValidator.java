package ru.javawebinar.topjava.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import ru.javawebinar.topjava.HasEmail;
import ru.javawebinar.topjava.HasId;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.web.SecurityUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class EmailValidator implements ConstraintValidator<ValidateEmail, HasEmail> {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean isValid(HasEmail user, ConstraintValidatorContext constraintValidatorContext) {
        String errorMsg = messageSource.getMessage("user.emailDuplicate", null, LocaleContextHolder.getLocale());
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(errorMsg).addConstraintViolation();
        User persistedUser = userRepository.getByEmail(user.getEmail());
        if (SecurityUtil.safeGet() == null) {
            return (persistedUser == null);
        } else {
            int authUserId = SecurityUtil.authUserId();
            return (persistedUser == null
                    || Objects.equals(persistedUser.getId(), ((HasId) user).getId())
                    || Objects.equals(persistedUser.getId(), authUserId));
        }
    }
}
