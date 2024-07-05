package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.ActiveDbProfileResolver;

import static ru.javawebinar.topjava.Profiles.JPA;


@ActiveProfiles(resolver = ActiveDbProfileResolver.class, profiles = JPA)
//@Ignore
public class MealServiceJpaTest extends MealServiceTestBase{

}