DELETE
FROM user_role;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES
    -- Приемы пищи для пользователя 100001
    (100000, '2024-06-23 08:00:00', 'Завтрак', 350),
    (100000, '2024-06-23 13:00:00', 'Обед', 600),
    (100000, '2024-06-23 17:00:00', 'Полдник', 250),
    (100000, '2024-06-23 20:00:00', 'Ужин', 500),
    (100000, '2024-06-24 08:00:00', 'Завтрак', 350),

    -- Приемы пищи для пользователя 100002
    (100001, '2024-06-23 08:30:00', 'Завтрак', 300),
    (100001, '2024-06-23 13:30:00', 'Обед', 550),
    (100001, '2024-06-23 17:30:00', 'Полдник', 200),
    (100001, '2024-06-23 20:30:00', 'Ужин', 450),
    (100001, '2024-06-24 08:30:00', 'Завтрак', 300);
