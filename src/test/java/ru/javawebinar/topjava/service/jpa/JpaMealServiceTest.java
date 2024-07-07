package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.BaseMealServiceTest;

import static ru.javawebinar.topjava.Profiles.JPA;

@ActiveProfiles(profiles = JPA)
public class JpaMealServiceTest extends BaseMealServiceTest {

}