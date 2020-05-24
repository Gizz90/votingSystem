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
VALUES ('Admin', 'admin@gmail.com', 'admin'),
       ('User', 'user@yandex.ru', 'password'),
       ('Mike', 'newuser@gmail.com', 'mike');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_ADMIN', 100000),
       ('ROLE_USER', 100000),
       ('ROLE_USER', 100001),
       ('ROLE_USER', 100002);

INSERT INTO restaurants (name)
VALUES ('Restaurant1'),
       ('Restaurant2');

INSERT INTO meals (name, date, price, restaurant_id)
VALUES ('chicken noodles', '2020-04-01', '1000', 100003),
       ('roast beef', '2020-04-01', '1500', 100003),
       ('flounder', '2020-04-01', '800', 100003),
       ('pizza', '2020-04-01', '1000', 100003),
       ('green tea', '2020-04-01', '200', 100003),

       ('vegetable soup', '2020-04-01', '1000', 100004),
       ('frankfurters', '2020-04-01', '1400', 100004),
       ('shrimps', '2020-04-01', '1100', 100004),
       ('spaghetti', '2020-04-01', '800', 100004),
       ('cappuccino', '2020-04-01', '150', 100004),

       ('fish soup', now(), '1000', 100003),
       ('schnitzel', now(), '1800', 100003),
       ('salmon', now(), '1300', 100003),
       ('omelette', now(), '500', 100004),
       ('coffee', now(), '100', 100004);

INSERT INTO votes (date, user_id, restaurant_id)
VALUES ('2020-04-01', 100000, 100003),
       ('2020-04-01', 100001, 100004),
       ('2020-04-01', 100002, 100003),
       (now(), 100000, 100004),
       (now(), 100001, 100004);
