package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.ActiveDbProfileResolver;
import ru.javawebinar.topjava.model.User;

import static ru.javawebinar.topjava.MealTestData.MEAL_MATCHER;
import static ru.javawebinar.topjava.MealTestData.meals;
import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ActiveProfiles(resolver = ActiveDbProfileResolver.class, profiles = DATAJPA)
public class UserServiceDataJpaTest extends UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void checkUserMeals() {
        User user = service.get(USER_ID);
        MEAL_MATCHER.assertMatch(user.getMeals().reversed(), meals);
    }
}
