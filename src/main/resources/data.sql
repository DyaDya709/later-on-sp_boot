insert into users(first_name, email, last_name, state, registration_date)
VALUES ('admin1', 'admin1@admin', 'lastname admin1','active',now()),
       ('admin2', '2@admin', 'lastname admin2','active',now()),
       ('admin3', '3@admin', 'lastname admin3','active',now());

insert into items(user_id, url)
VALUES (1, 'http://ya.ru'),
       (2, 'http://mail.ru'),
       (3, 'http://google.ru'),
       (1, 'http://yandex.ru');

insert into item_notes(note, created_at, item_id)
VALUES ('some note1', now(),1),
       ('some note2', now(),2),
       ('some note3', now(),3),
       ('some note4', now(),1);

insert into tags(name, item_id)
VALUES ('ya', 1),
       ('mail', 2),
       ('go', 3),
       ('yan', 4);



