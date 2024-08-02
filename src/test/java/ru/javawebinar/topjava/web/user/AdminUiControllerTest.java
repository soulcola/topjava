package ru.javawebinar.topjava.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import static ru.javawebinar.topjava.UserTestData.*;

class AdminUiControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminUIController.URL + '/';

    @Autowired
    private UserService userService;

    @Test
    void disable() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL + USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(Boolean.FALSE)));
        user.setEnabled(false);
        USER_MATCHER.assertMatch(userService.get(USER_ID), user);
    }
}