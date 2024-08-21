package ru.javawebinar.topjava;

import ru.javawebinar.topjava.web.validator.CreateAction;
import ru.javawebinar.topjava.web.validator.ValidateEmail;

@ValidateEmail(groups = {CreateAction.class})
public interface HasEmail {
    String getEmail();

    void setEmail(String email);
}
