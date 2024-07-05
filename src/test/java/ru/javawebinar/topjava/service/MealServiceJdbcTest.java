package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.ActiveDbProfileResolver;

import static ru.javawebinar.topjava.Profiles.JDBC;


@ActiveProfiles(resolver = ActiveDbProfileResolver.class, profiles = JDBC)
//@Ignore
public class MealServiceJdbcTest extends MealServiceTestBase{

}