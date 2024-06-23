package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal actual = service.get(USER_MEAL_ID, USER_ID);
        assertMatch(actual, userMeal1);
    }

    @Test
    public void getNotYour() {
        Assert.assertThrows(NotFoundException.class, () -> service.get(USER_MEAL_ID, ADMIN_ID));
    }

    @Test
    public void delete() {
        service.delete(USER_MEAL_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(USER_MEAL_ID, USER_ID));
    }

    @Test
    public void deleteNotYour() {
        assertThrows(NotFoundException.class, () -> service.delete(USER_MEAL_ID, ADMIN_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> actual = service.getBetweenInclusive(userMeal1.getDate(), userMeal3.getDate(), USER_ID);
        assertMatch(actual, Arrays.asList(userMeal1, userMeal2, userMeal3, userMeal4));
    }

    @Test
    public void getAll() {
        List<Meal> actual = service.getAll(USER_ID);
        assertMatch(actual, Arrays.asList(userMeal1, userMeal2, userMeal3, userMeal4, userMeal5));
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        Meal actual = service.get(USER_MEAL_ID, USER_ID);
        assertMatch(actual, getUpdated());
    }

    @Test
    public void updateNotYour() {
        assertThrows(NotFoundException.class,
                () -> service.update(updatedMeal, ADMIN_ID));
    }


    @Test
    public void create() {
        Meal actual = service.create(getNew(), USER_ID);
        Integer newId = actual.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        assertMatch(actual, newMeal);
        assertMatch(service.get(newId, USER_ID), newMeal);
    }

    @Test
    public void duplicateMailCreate() {
        assertThrows(DataAccessException.class,
                () -> service.create(new Meal(userMeal1.getDateTime(), "test", 0), USER_ID));
    }
}