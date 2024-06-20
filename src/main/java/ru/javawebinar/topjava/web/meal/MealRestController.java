package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.getFilteredTos;
import static ru.javawebinar.topjava.util.MealsUtil.getTos;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {

    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    private final Logger log = LoggerFactory.getLogger(getClass());

    public List<MealTo> getAll() {
        int userId = authUserId();
        log.info("getAll");
        log.info("userId: {}", userId);
        return getTos(service.getAll(authUserId()), authUserCaloriesPerDay());
    }

    public List<MealTo> getAllByFilter(String maybeStartDate, String maybeEndDate,
                                       String maybeStartTime, String maybeEndTime) {
        log.info("getAllByFilter");
        int authUserId = authUserId();
        log.info("userId: {}", authUserId);

        LocalDate startDate = DateTimeUtil.formatDate(maybeStartDate);
        LocalDate endDate = DateTimeUtil.formatDate(maybeEndDate);
        LocalTime startTime = DateTimeUtil.formatTime(maybeStartTime);
        LocalTime endTime = DateTimeUtil.formatTime(maybeEndTime);

        List<Meal> filteredMeals = service.getAllByFilter(authUserId, startDate, endDate);
        return getFilteredTos(filteredMeals, authUserCaloriesPerDay(), startTime, endTime);
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id, authUserId());
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal, authUserId());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, authUserId());
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.update(meal, authUserId());
    }
}