CREATE TABLE some_info (
    id INT PRIMARY KEY,
    city VARCHAR(100),
    country VARCHAR(100),
    population INT,
    postal_code VARCHAR(20)
);

create table users (
    user_name varchar(128) not null primary key,
    password varchar(256) not null,
    email varchar(256) not null,
    enabled boolean not null
);

create table authorities (
    user_name varchar(128) not null,
    authority varchar(128) not null,
    constraint fk_authorities_users foreign key (user_name) references users(user_name)
);

create unique index ix_auth_username on authorities (user_name, authority);