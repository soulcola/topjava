package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import javax.persistence.EntityManagerFactory;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.UserTestData.*;

public abstract class AbstractUserServiceTest extends AbstractServiceTest {
    private static final Logger log = LoggerFactory.getLogger(AbstractUserServiceTest.class);

    @Autowired
    protected UserService service;

    @Autowired(required = false)
    public EntityManagerFactory entityManagerFactory;

    @Autowired
    private Environment environment;

    @Before
    public void setup() {
        if (environment.matchesProfiles(Profiles.DATAJPA, Profiles.JPA)) {
            Object o = entityManagerFactory.getProperties().get("hibernate.cache.use_second_level_cache");
            log.debug("hibernate.cache.use_second_level_cache = {}", o);
        }
    }

    @Test
    public void createGuest() {
        User created = service.create(getNewGuest());
        int newId = created.id();
        User newGuest = getNewGuest();
        newGuest.setId(newId);
        USER_MATCHER.assertMatch(created, newGuest);
        USER_MATCHER.assertMatch(service.get(newId), newGuest);
    }

    @Test
    public void createUser() {
        User created = service.create(getNew());
        int newId = created.id();
        User newUser = getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(service.get(newId), newUser);
    }

    @Test
    public void createAdmin() {
        User created = service.create(getNewAdmin());
        int newId = created.id();
        User newAdmin = getNewAdmin();
        newAdmin.setId(newId);
        USER_MATCHER.assertMatch(created, newAdmin);
        USER_MATCHER.assertMatch(service.get(newId), newAdmin);
    }

    @Test
    public void duplicateMailCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.USER)));
    }

    @Test
    public void deleteUser() {
        service.delete(ADMIN_ID);
        assertThrows(NotFoundException.class, () -> service.get(ADMIN_ID));
    }

    @Test
    public void deleteAdmin() {
        service.delete(USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(USER_ID));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    public void getUser() {
        User user = service.get(USER_ID);
        USER_MATCHER.assertMatch(user, UserTestData.user);
    }

    @Test
    public void getAdmin() {
        User admin = service.get(ADMIN_ID);
        USER_MATCHER.assertMatch(admin, UserTestData.admin);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    public void getUserByEmail() {
        User user = service.getByEmail("user@yandex.ru");
        USER_MATCHER.assertMatch(user, UserTestData.user);
    }

    @Test
    public void getAdminByEmail() {
        User admin = service.getByEmail("admin@gmail.com");
        USER_MATCHER.assertMatch(admin, UserTestData.admin);
    }

    @Test
    public void update() {
        User updated = getUpdated();
        service.update(updated);
        USER_MATCHER.assertMatch(service.get(USER_ID), getUpdated());
    }

    @Test
    public void updateAdmin() {
        User updated = getUpdatedAdmin();
        service.update(updated);
        USER_MATCHER.assertMatch(service.get(ADMIN_ID), getUpdatedAdmin());
    }
    @Test
    public void updateToGuest() {
        User updated = getUpdatedAdminWoRoles();
        service.update(updated);
        USER_MATCHER.assertMatch(service.get(ADMIN_ID), getUpdatedAdminWoRoles());
    }


    @Test
    public void getAll() {
        List<User> all = service.getAll();
        USER_MATCHER.assertMatch(all, admin, guest, user);
    }

    @Test
    public void createWithException() {
        validateRootCause(ConstraintViolationException.class, () -> service.create(new User(null, "  ", "mail@yandex.ru", "password", Role.USER)));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new User(null, "User", "  ", "password", Role.USER)));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new User(null, "User", "mail@yandex.ru", "  ", Role.USER)));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new User(null, "User", "mail@yandex.ru", "password", 9, true, new Date(), Set.of())));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new User(null, "User", "mail@yandex.ru", "password", 10001, true, new Date(), Set.of())));
    }
}