package ru.javawebinar.topjava.service.datajpa;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.BaseUserServiceTest;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.MEAL_MATCHER;
import static ru.javawebinar.topjava.MealTestData.meals;
import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(profiles = DATAJPA)
public class DataJpaUserServiceTest extends BaseUserServiceTest {

    @Test
    public void getWithMeals() {
        User actualUser = service.getWithMeals(USER_ID);
        MEAL_MATCHER.assertMatch(actualUser.getMeals(), meals);
        USER_MATCHER.assertMatch(actualUser, user);

    }

    @Test
    public void getWithMealsNotFound() {
        assertThrows(NotFoundException.class, () -> service.getWithMeals(NOT_FOUND));
    }

    @Test
    public void getWithMealsGuest() {
        var guest = service.getWithMeals(GUEST_ID);
        Assertions.assertThat(guest.getMeals()).isEmpty();
        System.out.println(guest);
    }
}
