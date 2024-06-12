package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.Dao;
import ru.javawebinar.topjava.dao.MealsInMemoryDao;
import ru.javawebinar.topjava.mapper.MealCreateMapper;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealCreateUpdateDto;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.DateTimeFormat;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MealsController extends HttpServlet {
    Dao<Integer, Meal> mealsDao = MealsInMemoryDao.getInstance();
    MealCreateMapper mealCreateMapper = MealCreateMapper.getInstance();
    DateTimeFormat formatter = DateTimeFormat.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var action = req.getParameter("action");
        if (action == null || action.equals("list")) {
            List<Meal> meals = mealsDao.findAll();
            List<MealTo> mealToList = MealsUtil.filteredByStreams(meals, null, null, 2000);
            req.setAttribute("meals", mealToList);
            req.setAttribute("formatter", formatter);
            getServletContext().getRequestDispatcher("/meals.jsp").forward(req, resp);
        } else if (action.equals("update")) {
            var id = req.getParameter("id");
            var maybeMeal = mealsDao.findById(Integer.parseInt(id));
            maybeMeal.ifPresent(meal -> req.setAttribute("meal", meal));
            req.setAttribute("formatter", formatter);
            getServletContext().getRequestDispatcher("/meal.jsp").forward(req, resp);
        } else if (action.equals(("delete"))) {
            var id = req.getParameter("id");
            mealsDao.delete(Integer.parseInt(id));
            resp.sendRedirect(req.getContextPath() + "/meals");
        } else if (action.equals("create")) {
            getServletContext().getRequestDispatcher("/meal.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String date = req.getParameter("date");
        String description = req.getParameter("desc");
        String calories = req.getParameter("cal");
        String id = req.getParameter("id");
        var dto = new MealCreateUpdateDto(date, description, calories);
        Meal entity = mealCreateMapper.map(dto);
        if (id == null || id.isEmpty()) {
            mealsDao.create(entity);
        } else {
            mealsDao.update(Integer.parseInt(id), entity);
        }
        resp.sendRedirect("./meals?action=list");
    }
}
