-- DROP TABLE if exists items;
-- DROP TABLE if exists users;
create table if not exists users
(
    id                BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name        varchar(100),
    email             varchar(55),
    last_name         varchar(100),
    state             varchar(15),
    registration_date timestamp
);

create table if not exists items
(
    id      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id bigint not null
        constraint items_users_id_fk
            references users,
    url     varchar(255)
);

