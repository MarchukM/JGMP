insert into some_info (id, city, country, population, postal_code)
values
    (1, 'Tokyo', 'Japan', 9735500, '100-0001'),
    (2, 'London', 'United Kingdom', 9304016, 'SW1A 0AA'),
    (3, 'New York City', 'United States', 8336817, '10001'),
    (4, 'Mumbai', 'India', 12478447, '400001'),
    (5, 'Sydney', 'Australia', 5312163, '2000'),
    (6, 'Berlin', 'Germany', 3769495, '10115'),
    (7, 'Sao Paulo', 'Brazil', 12325232, '01000-000'),
    (8, 'Moscow', 'Russia', 12537954, '101000'),
    (9, 'Cairo', 'Egypt', 9077178, '11511'),
    (10, 'Toronto', 'Canada', 2731571, 'M5B 2R3');


insert into users (user_name, password, email, enabled)
values
    ('user', '$2a$10$ZmtX1UjjSjjysRxDLolTEO4oxykLg3ssfC3V9GhU1iCn86NJH.5VW', 'user@example.com', true),
    ('test', '$2a$10$ZmtX1UjjSjjysRxDLolTEO4oxykLg3ssfC3V9GhU1iCn86NJH.5VW', 'test@example.com', true),
    ('john', '$2a$10$ZmtX1UjjSjjysRxDLolTEO4oxykLg3ssfC3V9GhU1iCn86NJH.5VW', 'john@example.com', true),
    ('doe', '$2a$10$ZmtX1UjjSjjysRxDLolTEO4oxykLg3ssfC3V9GhU1iCn86NJH.5VW', 'doe@example.com', true),
    ('admin', '$2a$10$ZmtX1UjjSjjysRxDLolTEO4oxykLg3ssfC3V9GhU1iCn86NJH.5VW', 'admin@example.com', true);

insert into authorities (user_name, authority)
values
    ('user', 'ROLE_USER'),
    ('test', 'ROLE_USER'),
    ('john', 'ROLE_USER'),
    ('doe', 'ROLE_USER'),
    ('admin', 'ROLE_USER'),
    ('admin', 'ROLE_ADMIN');