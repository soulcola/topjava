package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static ru.javawebinar.topjava.util.MealsUtil.*;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {

    @Autowired
    private MealService service;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public List<MealTo> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll");
        log.info("userId: {}", userId);
        List<Meal> filteredMeals = service.getAll(SecurityUtil.authUserId());
        return getTos(filteredMeals, DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealTo> getAllByFilter(LocalDate maybeStartDate, LocalDate maybeEndDate,
                                       LocalTime maybeStartTime, LocalTime maybeEndTime) {
        log.info("getAllByFilter");
        int authUserId = SecurityUtil.authUserId();
        log.info("userId: {}", authUserId);

        LocalDate startDate = Optional.ofNullable(maybeStartDate).orElse(LocalDate.MIN);
        LocalDate endDate = Optional.ofNullable(maybeEndDate).orElse(LocalDate.MAX);
        LocalTime startTime = Optional.ofNullable(maybeStartTime).orElse(LocalTime.MIN);
        LocalTime endTime = Optional.ofNullable(maybeEndTime).orElse(LocalTime.MAX);

        List<Meal> filteredMeals = service.getAllByFilter(authUserId, startDate, endDate);
        return getFilteredTos(filteredMeals, SecurityUtil.authUserCaloriesPerDay(), startTime, endTime);
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id, SecurityUtil.authUserId());
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal, SecurityUtil.authUserId());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, SecurityUtil.authUserId());
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.update(meal, SecurityUtil.authUserId());
    }
}