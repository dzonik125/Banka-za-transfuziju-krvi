INSERT INTO address(
    city, country, "number", street)
VALUES ('Beograd', 'Srbija', '12A', 'Nemanjina');

INSERT INTO address(
    city, country, "number", street)
VALUES ('Novi Sad', 'Srbija', '15', 'Brankova');

INSERT INTO address(
    city, country, "number", street)
VALUES ('Kovilj', 'Srbija', '50', 'Živojina Mišića');

INSERT INTO blood_bank_blood(
    id, ab_negative, ab_positive, a_negative, a_positive, b_negative, b_positive, o_negative, o_positive)
VALUES (1, 10, 11, 4, 6, 3, 88, 3, 5);



INSERT INTO blood_bank(
    api_key, avg_grade, description, image, name, address_id, blood_id)
VALUES ('123', 3.4, ' ','https://firebasestorage.googleapis.com/v0/b/isapsw-6ef61.appspot.com/o/h1.jpg?alt=media&token=5268f3af-3bda-4014-b18e-3e69b57fb3ea','A banka' , 1, 1);

INSERT INTO blood_bank(
    api_key, avg_grade, description, image, name, address_id)
VALUES ('unknown', 4.4, ' ','https://firebasestorage.googleapis.com/v0/b/isapsw-6ef61.appspot.com/o/h2.jpg?alt=media&token=3d2fca8e-7272-4dcc-bafc-231afce6eeac','B banka' , 2);

INSERT INTO blood_bank(
    api_key, avg_grade, description, image, name, address_id)
VALUES (null, 1.8, ' ','https://firebasestorage.googleapis.com/v0/b/isapsw-6ef61.appspot.com/o/h3.jpg?alt=media&token=482263cb-3590-405f-9c1e-e1fcd46b5229','C banka' , 3);



INSERT INTO public.appointment(
    donor_id, duration, start, blood_bank_id)
VALUES (1, 20, '2022-12-24 18:17:53.840417', 1);

INSERT INTO public.appointment(
    donor_id, duration, start, blood_bank_id)
VALUES (8, 20, '2022-12-25 18:17:53.840417', 1);

INSERT INTO public.appointment(
    donor_id, duration, start, blood_bank_id)
VALUES (7, 20, '2022-12-24 13:17:53.840417', 1);

INSERT INTO public.appointment(
    donor_id, duration, start, blood_bank_id)
VALUES (7, 20, '2022-12-27 13:17:53.840417', 2);


INSERT INTO blood_type_blood_bank(
    blood_bank_id, blood_type_amount, blood_type_key)
VALUES (1, 1000, 'Aneg');

INSERT INTO blood_type_blood_bank(
    blood_bank_id, blood_type_amount, blood_type_key)
VALUES (1, 1000, 'Apos');

INSERT INTO blood_type_blood_bank(
    blood_bank_id, blood_type_amount, blood_type_key)
VALUES (1, 1000, 'ABpos');

INSERT INTO blood_type_blood_bank(
    blood_bank_id, blood_type_amount, blood_type_key)
VALUES (1, 1000, 'ABneg');

INSERT INTO blood_type_blood_bank(
    blood_bank_id, blood_type_amount, blood_type_key)
VALUES (1, 1000, 'Bpos');

INSERT INTO blood_type_blood_bank(
    blood_bank_id, blood_type_amount, blood_type_key)
VALUES (1, 1000, 'Bneg');

INSERT INTO blood_type_blood_bank(
    blood_bank_id, blood_type_amount, blood_type_key)
VALUES (1, 1000, 'Opos');

INSERT INTO blood_type_blood_bank(
    blood_bank_id, blood_type_amount, blood_type_key)
VALUES (1, 1000, 'Oneg');

--sifre su Donor         -> donor
--         MedicalWorker -> medicalworker
--         Admin         -> admin
INSERT INTO users(
    email, gender, jmbg, name, occupation, password, surname, address_id, enabled, last_password_reset_date)
VALUES ('donor@mail.com', 0, '7132312321321', 'Marko', 'Poljoprivrednik', '$2y$10$9INQk3/KYVWY1FbUqA0W5upioL.6RVl63zJU9iEwQG.XvHEk9Aug6', 'Bozic', 3, true, '2022-11-28 18:17:53.840417');

INSERT INTO users(
    email, gender, jmbg, name, occupation, password, surname, address_id, enabled, last_password_reset_date)
VALUES ('medicalworker@mail.com', 1, '1232312321321', 'Nikola', 'Student', '$2y$10$1KJBnDRXEz87Zcrq.b3vR.hkmaVVIvXarI.4IwbtebcbAcMSjOJkK', 'Kolarov', 1, true, '2022-11-28 18:17:53.840417');

INSERT INTO users(
    email, gender, jmbg, name, occupation, password, surname, address_id, enabled, last_password_reset_date)
VALUES ('medicalworker2@mail.com', 1, '1232312321321', 'Nikola', 'Student', '$2y$10$1KJBnDRXEz87Zcrq.b3vR.hkmaVVIvXarI.4IwbtebcbAcMSjOJkK', 'Kolarov', 1, true, '2022-11-28 18:17:53.840417');

INSERT INTO users(
    email, gender, jmbg, name, occupation, password, surname, address_id, enabled, last_password_reset_date)
VALUES ('medicalworker3@mail.com', 1, '1232312321321', 'Nikola', 'Student', '$2y$10$1KJBnDRXEz87Zcrq.b3vR.hkmaVVIvXarI.4IwbtebcbAcMSjOJkK', 'Kolarov', 1, true, '2022-11-28 18:17:53.840417');

INSERT INTO users(
     email, gender, jmbg, name, occupation, password, surname, address_id, enabled, last_password_reset_date)
VALUES ( 'admin@mail.com', 0, '8132312321321', 'Slavica', 'IT strucnjak', '$2y$10$YQtYG49nm8UhWrtT3Zq1gOoGYCkhVMC9IKczv5M.mThbC.QH0xnYu', 'Krstic', 2, true, '2022-11-28 18:17:53.840417');

INSERT INTO users(
    id, email, gender, jmbg, name, occupation, password, surname, address_id, enabled, last_password_reset_date)
VALUES (8, 'donor2@mail.com', 0, '7132312321325', 'Trifko', 'Agrikola', '$2y$10$9INQk3/KYVWY1FbUqA0W5upioL.6RVl63zJU9iEwQG.XvHEk9Aug6', 'Milic', 3, true, '2022-11-28 18:17:53.840417');

INSERT INTO users(
    id, email, gender, jmbg, name, occupation, password, surname, address_id, enabled, last_password_reset_date)
VALUES (7, 'donor3@mail.com', 0, '7132312321321', 'Zarko', 'Vodoinstalater', '$2y$10$9INQk3/KYVWY1FbUqA0W5upioL.6RVl63zJU9iEwQG.XvHEk9Aug6', 'Dinic', 3, true, '2022-11-28 18:17:53.840417');


INSERT INTO donor(
    blood_type, category, penalty, points, id, has_survey)
VALUES (0, 0, 0, 11, 1, false);

INSERT INTO donor(
    blood_type, category, penalty, points, id)
VALUES (1, 1, 0, 11, 7);

INSERT INTO donor(
    blood_type, category, penalty, points, id)
VALUES (2, 2, 0, 14, 8);


INSERT INTO medical_worker(
    id, blood_bank_id)
VALUES (2, 1);

INSERT INTO medical_worker(
    id, blood_bank_id)
VALUES (3, 2);

INSERT INTO medical_worker(
    id, blood_bank_id)
VALUES (4, 3);

INSERT INTO admin(
    first_login, id)
VALUES (false, 5);



INSERT INTO medical_worker_appointments(
    medical_worker_id, appointment_id)
VALUES (2, 1);

INSERT INTO appointment_slot(
    start_time, end_time, donor_id, blood_bank_id, status)
VALUES ('2022-12-02 13:00:28.111756','2022-12-02 14:00:28.112755',1, 1, 0);

INSERT INTO appointment_slot(
    start_time, end_time, donor_id, blood_bank_id, status)
VALUES ('2022-12-27 17:00:28.111756','2022-12-27 18:00:28.112755',null, 1, 0);

INSERT INTO appointment_slot(
    start_time, end_time, donor_id, blood_bank_id, status)
VALUES ('2022-12-30 11:00:28.111756','2022-12-30 10:00:28.112755',null, 1, 0);

INSERT INTO appointment_slot(
    start_time, end_time, donor_id, blood_bank_id, status, version)
VALUES ('2023-01-22 10:00:00','2023-01-22 11:00:00',null, 1, 0, 0);




-- INSERT INTO survey(
--     answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8, answer9, answer10, donor_id)
-- VALUES ('yes','yes','yes','yes','yes','no','yes','yes','yes','yes', 1);


INSERT INTO role (name) VALUES ('ROLE_DONOR');
INSERT INTO role (name) VALUES ('ROLE_MEDICALWORKER');
INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO role (name) VALUES ('ROLE_NEW_ADMIN');

INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1); -- user-u dodeljujemo rolu DONOR
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 2); -- user-u dodeljujemo rolu MW
INSERT INTO USER_ROLE (user_id, role_id) VALUES (3, 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (4, 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (5, 3); -- user-u dodeljujemo rolu ADMIN

INSERT INTO working_hours(
    id, end_time, start_time, blood_bank_id)
VALUES (1, '12:00:00', '08:00:00', 1);
INSERT INTO working_hours(
    id, end_time, start_time, blood_bank_id)
VALUES (2, '18:00:00', '12:00:00', 2);
INSERT INTO working_hours(
    id, end_time, start_time, blood_bank_id)
VALUES (3, '13:00:00', '11:00:00', 3);

INSERT INTO USER_ROLE (user_id, role_id) VALUES (7, 1); -- user-u dodeljujemo rolu DONOR
INSERT INTO USER_ROLE (user_id, role_id) VALUES (8, 1); -- user-u dodeljujemo rolu DONOR


INSERT INTO complaint(
    id, description, version, donor_id)
VALUES (1, 'Zalba1 asdsad.  (banka bankaB)', 0, 7);


INSERT INTO complaint(
    id, description, version, donor_id)
VALUES (2, 'Zalba2 asdas.  (banka bankaB)', 0, 8);

INSERT INTO complaint(
    id, description, donor_id, version, answer)
VALUES (3, 'Zalba2 asdas.  (banka bankaB)', 7, 0, 'odg');

