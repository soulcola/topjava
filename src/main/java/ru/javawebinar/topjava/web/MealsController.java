package ru.javawebinar.topjava.web;

import lombok.extern.slf4j.Slf4j;
import ru.javawebinar.topjava.repository.RepositoryBase;
import ru.javawebinar.topjava.repository.InMemoryMealRepository;
import ru.javawebinar.topjava.mapper.MealCreateMapper;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealCreateUpdateDto;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
public class MealsController extends HttpServlet {
    RepositoryBase<Integer, Meal> mealsRepositoryBase = InMemoryMealRepository.getInstance();
    MealCreateMapper mealCreateMapper = MealCreateMapper.getInstance();
    private static final String FIND_ALL_JSP = "/meals.jsp";
    private static final String EDIT_CREATE_JSP = "/meal.jsp";
    private static final String FIND_ALL_ACTION = "list";
    private static final String UPDATE_ACTION = "update";
    private static final String DELETE_ACTION = "delete";
    private static final String CREATE_ACTION = "create";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var action = req.getParameter("action");
        log.debug("method {} invoked", action);
        if (action == null || action.equals(FIND_ALL_ACTION)) {
            List<Meal> meals = mealsRepositoryBase.findAll();
            log.debug("database: {}", meals);
            req.setAttribute("meals", MealsUtil.filteredByStreamsWoTime(meals, 2000));
            getServletContext().getRequestDispatcher(FIND_ALL_JSP).forward(req, resp);
        } else if (action.equals(UPDATE_ACTION)) {

            var id = req.getParameter("id");
            var nonNull = Objects.requireNonNull(id);
            var maybeMeal = mealsRepositoryBase.findById(Integer.parseInt(id));
            log.debug("entity = {}, id = {}", maybeMeal, id);
            maybeMeal.ifPresent(meal -> req.setAttribute("meal", meal));
            getServletContext().getRequestDispatcher(EDIT_CREATE_JSP).forward(req, resp);
        } else if (action.equals(DELETE_ACTION)) {
            var id = req.getParameter("id");
            mealsRepositoryBase.delete(Integer.parseInt(id));
            log.debug("entity id = {} deleted", id);
            resp.sendRedirect(req.getContextPath() + "/meals");
        } else if (action.equals(CREATE_ACTION)) {
            getServletContext().getRequestDispatcher(EDIT_CREATE_JSP).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String date = req.getParameter("date");
        String description = req.getParameter("desc");
        String calories = req.getParameter("cal");
        String id = req.getParameter("id");
        var dto = new MealCreateUpdateDto(id, date, description, calories);
        Meal entity = mealCreateMapper.map(dto);
        if (id == null || id.isEmpty()) {
            log.debug("entity created: {}", entity);
        } else {
            log.debug("entity with id {} updated: {}", id, entity);
        }
        mealsRepositoryBase.create(entity);
        resp.sendRedirect(req.getContextPath() + "/meals");
    }
}
