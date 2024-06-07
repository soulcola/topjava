package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.Dao;
import ru.javawebinar.topjava.dao.MealsDao;
import ru.javawebinar.topjava.mapper.MealCreateMapper;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealCreateDto;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class MealsServlet extends HttpServlet {
    Dao<Integer, Meal> mealsDao = new MealsDao();
    MealCreateMapper mealCreateMapper = new MealCreateMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Meal> meals = mealsDao.findAll();
        List<MealTo> mealToList = MealsUtil.filteredByStreams(meals, null, null, 2000);
        req.setAttribute("meals", mealToList);
        getServletContext().getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String date = req.getParameter("date");
        String description = req.getParameter("desc");
        String calories = req.getParameter("cal");
        mealsDao.create(mealCreateMapper.map(new MealCreateDto(date, description, calories)));
        resp.sendRedirect("./meals");
    }
}
