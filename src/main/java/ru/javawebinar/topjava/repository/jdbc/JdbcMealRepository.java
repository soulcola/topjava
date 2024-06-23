package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository()
@Profile("!test")
public class JdbcMealRepository implements MealRepository {
    private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Meal.class);
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert insertMeal;
    private static final String DELETE_MEAL_SQL = "DELETE FROM meals WHERE id=? AND user_id=?";
    private static final String GET_ALL_SQL = "SELECT id, user_id, date_time, description, calories " +
                                              "FROM meals " +
                                              "WHERE user_id=:userId " +
                                              "ORDER BY date_time DESC";
    private static final String GET_USER_MEAL_SQL = "SELECT id, user_id, date_time, description, calories " +
                                                    "FROM meals " +
                                                    "WHERE id=:id AND user_id=:userId " +
                                                    "ORDER BY date_time DESC";
    private static final String GET_BETWEEN_HALF_OPEN_SQL = "SELECT id, user_id, date_time, description, calories " +
                                                            "FROM meals " +
                                                            "WHERE user_id=:userId AND user_id=:userId " +
                                                            "AND date_time BETWEEN :startDate AND :endDate AND date_time != :endDate " +
                                                            "ORDER BY date_time DESC";
    private static final String UPDATE_MEAL_SQL = "UPDATE meals SET " +
                                                  "date_time=:dateTime, " +
                                                  "description=:description, " +
                                                  "calories=:calories " +
                                                  "WHERE id=:id AND user_id=:userId";

    public JdbcMealRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertMeal = new SimpleJdbcInsert(jdbcTemplate).withTableName("meals")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Meal save(Meal meal, int userId) {
        MapSqlParameterSource valuesMap = new MapSqlParameterSource()
                .addValue("id", meal.getId())
                .addValue("userId", userId)
                .addValue("dateTime", Timestamp.valueOf(meal.getDateTime()))
                .addValue("description", meal.getDescription())
                .addValue("calories", meal.getCalories());
        if (meal.isNew()) {
            Number id = insertMeal.executeAndReturnKey(valuesMap);
            meal.setId(id.intValue());
        } else if (namedParameterJdbcTemplate.update(UPDATE_MEAL_SQL, valuesMap) == 0) {
            return null;
        }
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update(DELETE_MEAL_SQL, id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        MapSqlParameterSource valuesMap = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("userId", userId);
        return namedParameterJdbcTemplate.query(GET_USER_MEAL_SQL, valuesMap, ROW_MAPPER)
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Meal> getAll(int userId) {
        MapSqlParameterSource valuesMap = new MapSqlParameterSource()
                .addValue("userId", userId);
        return namedParameterJdbcTemplate.query(GET_ALL_SQL, valuesMap, ROW_MAPPER);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        MapSqlParameterSource valuesMap = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("startDate", Timestamp.valueOf(startDateTime))
                .addValue("endDate", Timestamp.valueOf(endDateTime));
        return namedParameterJdbcTemplate.query(GET_BETWEEN_HALF_OPEN_SQL, valuesMap, ROW_MAPPER);
    }
}
