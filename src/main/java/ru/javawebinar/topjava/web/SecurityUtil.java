package ru.javawebinar.topjava.web;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class SecurityUtil {
    public static final int DEFAULT_USER_ID = 1;
    public static final int DEFAULT_ADMIN_ID = 2;
    private static int userId = DEFAULT_USER_ID;

    public static int authUserId() {
        return userId;
    }

    public static void setAuthUserId(int id) {
        userId = id;
    }

    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}