package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.ActiveDbProfileResolver;

import static ru.javawebinar.topjava.Profiles.REPOSITORY_IMPLEMENTATION;


@ActiveProfiles(resolver = ActiveDbProfileResolver.class, profiles = REPOSITORY_IMPLEMENTATION)
//@Ignore
public class MealServiceDataJpaTest extends MealServiceTestBase{

}