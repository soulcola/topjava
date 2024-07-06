package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.ActiveDbProfileResolver;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.Profiles.REPOSITORY_IMPLEMENTATION;
import static ru.javawebinar.topjava.UserTestData.*;


@ActiveProfiles(resolver = ActiveDbProfileResolver.class, profiles = REPOSITORY_IMPLEMENTATION)
//@Ignore
public class MealServiceDataJpaTest extends MealServiceTestBase {
    @Autowired
    private MealService service;
    public void get() {
        Meal actual = service.get(ADMIN_MEAL_ID, ADMIN_ID);
        User actualUser = actual.getUser();
        USER_MATCHER.assertMatch(admin, actualUser);
    }
}