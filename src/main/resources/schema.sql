drop table if exists tags;
drop table if exists item_notes;
drop table if exists items;
drop table if exists users;

create table users
(
    id                bigint generated always as identity
        primary key,
    first_name        varchar(100),
    email             varchar(55),
    last_name         varchar(100),
    state             varchar(15),
    registration_date timestamp
);

alter table users
    owner to root;

create table items
(
    id      bigint generated always as identity
        primary key,
    user_id bigint not null
        constraint items_users_id_fk
            references users,
    url     varchar(255)
);

alter table items
    owner to root;

create table tags
(
    id      bigint generated always as identity,
    name    varchar(50),
    item_id bigint
        constraint tags_items_id_fk
            references items
);

alter table tags
    owner to root;

create table item_notes
(
    id         bigserial
        primary key,
    note       varchar(2000),
    created_at timestamp,
    item_id    bigint not null
        constraint item_notes_items_id_fk
            references items
);

alter table item_notes
    owner to root;

