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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal actual = service.get(USER_MEAL_ID, USER_ID);
        assertThat(USER_MEAL_1).isEqualTo(actual);
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
        List<Meal> actual = service.getBetweenInclusive(USER_MEAL_1.getDate(), USER_MEAL_4.getDate(), USER_ID);
        assertThat(actual).usingRecursiveFieldByFieldElementComparator()
                .containsAll(Arrays.asList(
                        USER_MEAL_1, USER_MEAL_2, USER_MEAL_3, USER_MEAL_4, USER_MEAL_5, USER_MEAL_6, USER_MEAL_7));
    }

    @Test
    public void getAll() {
        List<Meal> actual = service.getAll(USER_ID);
        assertThat(actual).usingRecursiveFieldByFieldElementComparator()
                .containsAll(Arrays.asList(
                        USER_MEAL_1, USER_MEAL_2, USER_MEAL_3, USER_MEAL_4, USER_MEAL_5, USER_MEAL_6, USER_MEAL_7));
    }

    @Test
    public void update() {
        service.update(UPDATED_MEAL, USER_ID);
        Meal actualMeal = service.get(USER_MEAL_ID, USER_ID);
        assertThat(actualMeal).usingRecursiveComparison().isEqualTo(UPDATED_MEAL);
    }

    @Test
    public void updateNotYour() {
        assertThrows(NotFoundException.class, () -> service.update(UPDATED_MEAL, ADMIN_ID));
    }


    @Test
    public void create() {
        Meal actual = service.create(NEW_MEAL, USER_ID);
        Integer newId = actual.getId();
        Meal expected = NEW_MEAL;
        expected.setId(newId);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        assertThat(service.get(newId, USER_ID)).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void duplicateMailCreate() {
        assertThrows(DataAccessException.class, () -> service.create(
                new Meal(USER_MEAL_1.getDateTime(), "test", 0),
                USER_ID));
    }
}