INSERT INTO role (id, name)
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN');

INSERT INTO app_user (id, email, password, name, phone_number, address)
VALUES (1, 'admin@example.com', '$2a$10$22ZndzPnmpZSeazR7ZvgW.h/FIqrentk8.1SW59ojJCnaQ6MnZlDa',
        'Gabi', '+36301234567', '1143 Budapest, Tavasz u. 11'),
       (2, 'user0@example.com', '$2a$10$ZhJUwpHtUDZi87r6VQedvup2S3ciz89tpuf8KPwphEwxiL/qTrhKa',
        'Miki', '+36301234567', '1145 Budapest, Csavargyár u. 41'),
       (3, 'user1@example.com', '$2a$10$5hZWTtO8dYqMxy/3fSoKIOWpp0gQpAAJZ2x4270Cx8r3wsZWqMpd.',
        'Viki', '+36301234567', '1147 Budapest, Napos u. 30'),
       (4, 'user2@example.com', '$2a$10$/tmBd1CQUEparWurBsRcnuZKronXtF2liXTIUxEq7zBBWUbfm2Ag.',
        'Peti', '+36301234567', '1143 Budapest, Balaton u. 111'),
       (5, 'user3@example.com', '$2a$10$4j9URPgRHAML5m5lTt/vr.oBm/5iQnLnMhzCWiYCvMEB/awlO.gQ2',
        'Niki', '+36301234567', '1144 Budapest, Eper u. 8'),
       (6, 'user4@example.com', '$2a$10$Nl2lEs8XBC8u08.mq.nime2zI3NaezuUlPR.oPFY3XihTjVYqok2O',
        'Zsolti', '+36301234567', '1143 Budapest, Tavasz u. 12');

INSERT INTO appuser_role (appuser_id, role_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (3, 1),
       (4, 1),
       (4, 2),
       (5, 1),
       (6, 1);

INSERT INTO user_group (id, owner_id, name)
VALUES (1, 1, '5.b osztály'),
       (2, 1, '7.b osztály'),
       (3, 1, 'Társasházi lakóközösség');

INSERT INTO user_group_members (members_id, user_group_id)
VALUES (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1);

