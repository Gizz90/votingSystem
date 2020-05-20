DELETE
FROM user_roles;
DELETE
FROM votes;
DELETE
FROM meals;
DELETE
FROM restaurants;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Mike', 'newuser@gmail.com', 'mike');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100000),
       ('ROLE_USER', 100001),
       ('ROLE_USER', 100002);

INSERT INTO restaurants (name)
VALUES ('Restaurant1'),
       ('Restaurant2'),
       ('Restaurant3');

INSERT INTO meals (name, date, price, restaurant_id)
VALUES ('chicken noodles', '2020-04-01', '1000', 100003),
       ('roast beef', '2020-04-01', '1000', 100003),
       ('flounder', '2020-04-01', '1000', 100003),
       ('pizza', '2020-04-01', '1000', 100003),
       ('green tea', '2020-04-01', '1000', 100003),

       ('vegetable soup', '2020-04-02', '1000', 100004),
       ('frankfurters', '2020-04-02', '1000', 100004),
       ('shrimps', '2020-04-02', '1000', 100004),
       ('spaghetti', '2020-04-02', '1000', 100004),
       ('cappuccino', '2020-04-02', '1000', 100004),

       ('fish soup', '2020-04-01', '1000', 100005),
       ('schnitzel', '2020-04-01', '1000', 100005),
       ('salmon', '2020-04-01', '1000', 100005),
       ('omelette', '2020-04-02', '1000', 100005),
       ('coffee', '2020-04-02', '1000', 100005);

INSERT INTO votes (date, user_id, restaurant_id)
VALUES ('2020-04-01', 100000, 100003),
       ('2020-04-01', 100001, 100004),
       ('2020-04-01', 100002, 100005),
       ('2020-04-02', 100000, 100004),
       ('2020-04-02', 100001, 100004),
       ('2020-04-02', 100002, 100004);
