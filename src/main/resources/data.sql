insert into movies (id, title, length_in_minutes) values (1, 'Iron man', 70);
insert into movies (id, title, length_in_minutes) values (2, 'Batman', 120);
insert into movies (id, title, length_in_minutes) values (3, 'Superman', 60);
insert into movies (id, title, length_in_minutes) values (4, 'Spider-man', 100);

insert into rooms (id, name) values (1, 'pokój 1');
insert into rooms (id, name) values (2, 'pokój 2');
insert into rooms (id, name) values (3, 'pokój 3');

--POKÓJ 1
insert into seats (id, room_row, seat_number, room_id) values (1, 1, 1, 1);
insert into seats (id, room_row, seat_number, room_id) values (2, 1, 2, 1);
insert into seats (id, room_row, seat_number, room_id) values (3, 1, 3, 1);
insert into seats (id, room_row, seat_number, room_id) values (4, 1, 4, 1);
insert into seats (id, room_row, seat_number, room_id) values (5, 1, 5, 1);
insert into seats (id, room_row, seat_number, room_id) values (6, 1, 6, 1);

insert into seats (id, room_row, seat_number, room_id) values (7,  2, 7,  1);
insert into seats (id, room_row, seat_number, room_id) values (8,  2, 8,  1);
insert into seats (id, room_row, seat_number, room_id) values (9,  2, 9,  1);
insert into seats (id, room_row, seat_number, room_id) values (10, 2, 10, 1);
insert into seats (id, room_row, seat_number, room_id) values (11, 2, 11, 1);
insert into seats (id, room_row, seat_number, room_id) values (12, 2, 12, 1);

insert into seats (id, room_row, seat_number, room_id) values (13, 3, 13, 1);
insert into seats (id, room_row, seat_number, room_id) values (14, 3, 14, 1);
insert into seats (id, room_row, seat_number, room_id) values (15, 3, 15, 1);
insert into seats (id, room_row, seat_number, room_id) values (16, 3, 16, 1);
insert into seats (id, room_row, seat_number, room_id) values (17, 3, 17, 1);
insert into seats (id, room_row, seat_number, room_id) values (18, 3, 18, 1);

--POKÓJ 2
insert into seats (id, room_row, seat_number, room_id) values (19, 1, 1, 2);
insert into seats (id, room_row, seat_number, room_id) values (20, 1, 2, 2);
insert into seats (id, room_row, seat_number, room_id) values (21, 1, 3, 2);
insert into seats (id, room_row, seat_number, room_id) values (22, 1, 4, 2);
insert into seats (id, room_row, seat_number, room_id) values (23, 1, 5, 2);
insert into seats (id, room_row, seat_number, room_id) values (24, 1, 6, 2);

insert into seats (id, room_row, seat_number, room_id) values (25, 2, 7,  2);
insert into seats (id, room_row, seat_number, room_id) values (26, 2, 8,  2);
insert into seats (id, room_row, seat_number, room_id) values (27, 2, 9,  2);
insert into seats (id, room_row, seat_number, room_id) values (28, 2, 10, 2);
insert into seats (id, room_row, seat_number, room_id) values (29, 2, 11, 2);
insert into seats (id, room_row, seat_number, room_id) values (30, 2, 12, 2);

insert into seats (id, room_row, seat_number, room_id) values (31, 3, 13, 2);
insert into seats (id, room_row, seat_number, room_id) values (32, 3, 14, 2);
insert into seats (id, room_row, seat_number, room_id) values (33, 3, 15, 2);
insert into seats (id, room_row, seat_number, room_id) values (34, 3, 16, 2);
insert into seats (id, room_row, seat_number, room_id) values (35, 3, 17, 2);
insert into seats (id, room_row, seat_number, room_id) values (36, 3, 18, 2);

--POKÓJ 3
insert into seats (id, room_row, seat_number, room_id) values (37, 1, 1, 3);
insert into seats (id, room_row, seat_number, room_id) values (38, 1, 2, 3);
insert into seats (id, room_row, seat_number, room_id) values (39, 1, 3, 3);
insert into seats (id, room_row, seat_number, room_id) values (40, 1, 4, 3);
insert into seats (id, room_row, seat_number, room_id) values (41, 1, 5, 3);
insert into seats (id, room_row, seat_number, room_id) values (42, 1, 6, 3);

insert into seats (id, room_row, seat_number, room_id) values (43, 2, 7,  3);
insert into seats (id, room_row, seat_number, room_id) values (44, 2, 8,  3);
insert into seats (id, room_row, seat_number, room_id) values (45, 2, 9,  3);
insert into seats (id, room_row, seat_number, room_id) values (46, 2, 10, 3);
insert into seats (id, room_row, seat_number, room_id) values (47, 2, 11, 3);
insert into seats (id, room_row, seat_number, room_id) values (48, 2, 12, 3);

insert into seats (id, room_row, seat_number, room_id) values (49, 3, 13, 3);
insert into seats (id, room_row, seat_number, room_id) values (50, 3, 14, 3);
insert into seats (id, room_row, seat_number, room_id) values (51, 3, 15, 3);
insert into seats (id, room_row, seat_number, room_id) values (52, 3, 16, 3);
insert into seats (id, room_row, seat_number, room_id) values (53, 3, 17, 3);
insert into seats (id, room_row, seat_number, room_id) values (54, 3, 18, 3);

--POKÓJ 1
insert into screenings(id, screening_time, movie_id, room_id) values(1, '2022-10-18T10:00:00.000', 1, 1);
insert into screenings(id, screening_time, movie_id, room_id) values(2, '2022-10-18T11:30:00.000', 1, 1);
insert into screenings(id, screening_time, movie_id, room_id) values(3, '2022-10-18T13:00:00.000', 1, 1);
insert into screenings(id, screening_time, movie_id, room_id) values(4, '2022-10-18T14:30:00.000', 3, 1);
insert into screenings(id, screening_time, movie_id, room_id) values(5, '2022-10-18T15:00:00.000', 4, 1);
insert into screenings(id, screening_time, movie_id, room_id) values(6, '2022-10-18T17:00:00.000', 3, 1);
insert into screenings(id, screening_time, movie_id, room_id) values(7, '2022-10-18T18:10:00.000', 1, 1);

--POKÓJ 2
insert into screenings(id, screening_time, movie_id, room_id) values(8,  '2022-10-18T10:00:00.000', 3, 2);
insert into screenings(id, screening_time, movie_id, room_id) values(9,  '2022-10-18T11:30:00.000', 2, 2);
insert into screenings(id, screening_time, movie_id, room_id) values(10, '2022-10-18T14:00:00.000', 4, 2);
insert into screenings(id, screening_time, movie_id, room_id) values(11, '2022-10-18T15:50:00.000', 3, 2);
insert into screenings(id, screening_time, movie_id, room_id) values(12, '2022-10-18T17:00:00.000', 4, 2);

--POKÓJ 3
insert into screenings(id, screening_time, movie_id, room_id) values(13, '2022-10-18T10:00:00.000', 2, 3);
insert into screenings(id, screening_time, movie_id, room_id) values(14, '2022-10-18T12:10:00.000', 3, 3);
insert into screenings(id, screening_time, movie_id, room_id) values(15, '2022-10-18T13:30:00.000', 2, 3);
insert into screenings(id, screening_time, movie_id, room_id) values(16, '2022-10-18T16:00:00.000', 2, 3);
insert into screenings(id, screening_time, movie_id, room_id) values(17, '2022-10-18T18:10:00.000', 1, 3);



