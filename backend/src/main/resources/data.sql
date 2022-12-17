INSERT INTO address(
    city, country, "number", street)
VALUES ('Beograd', 'Srbija', '12A', 'Nemanjina');

INSERT INTO address(
    city, country, "number", street)
VALUES ('Novi Sad', 'Srbija', '15', 'Brankova');

INSERT INTO address(
    city, country, "number", street)
VALUES ('Kovilj', 'Srbija', '50', 'Živojina Mišića');



INSERT INTO blood_bank(
    api_key, avg_grade, description, image, name, address_id)
VALUES ('123', 3.4, ' ','https://firebasestorage.googleapis.com/v0/b/isapsw-6ef61.appspot.com/o/h1.jpg?alt=media&token=5268f3af-3bda-4014-b18e-3e69b57fb3ea','A banka' , 1);

INSERT INTO blood_bank(
    api_key, avg_grade, description, image, name, address_id)
VALUES ('unknown', 4.4, ' ','https://firebasestorage.googleapis.com/v0/b/isapsw-6ef61.appspot.com/o/h2.jpg?alt=media&token=3d2fca8e-7272-4dcc-bafc-231afce6eeac','B banka' , 2);

INSERT INTO blood_bank(
    api_key, avg_grade, description, image, name, address_id)
VALUES (null, 1.8, ' ','https://firebasestorage.googleapis.com/v0/b/isapsw-6ef61.appspot.com/o/h3.jpg?alt=media&token=482263cb-3590-405f-9c1e-e1fcd46b5229','C banka' , 3);



INSERT INTO appointment(
    duration, start, blood_bank_id)
VALUES (30, null, 1);

INSERT INTO appointment(
    duration, start, blood_bank_id)
VALUES (20, null, 2);



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
VALUES ('admin@mail.com', 0, '8132312321321', 'Slavica', 'IT strucnjak', '$2y$10$YQtYG49nm8UhWrtT3Zq1gOoGYCkhVMC9IKczv5M.mThbC.QH0xnYu', 'Krstic', 2, true, '2022-11-28 18:17:53.840417');


INSERT INTO donor(
    blood_type, category, penalty, points, id, has_survey)
VALUES (0, 0, 0, 11, 1, false);

INSERT INTO medical_worker(
    id, blood_bank_id)
VALUES (2, null);

INSERT INTO admin(
    id)
VALUES (3);



INSERT INTO medical_worker_appointments(
    medical_worker_id, appointment_id)
VALUES (2, 1);

INSERT INTO appointment_slot(
    start_time, end_time, donor_id, blood_bank_id, status)
VALUES ('2022-12-15 16:00:28.111756','2022-12-15 18:00:28.112755',null, 1, 0);

INSERT INTO appointment_slot(
    start_time, end_time, donor_id, blood_bank_id, status)
VALUES ('2022-12-19 16:00:28.111756','2022-12-19 18:00:28.112755',null, 1, 0);

INSERT INTO appointment_slot(
    start_time, end_time, donor_id, blood_bank_id, status)
VALUES ('2022-12-19 16:00:28.111756','2022-12-19 18:00:28.112755',null, 1, 0);

INSERT INTO appointment_slot(
    start_time, end_time, donor_id, blood_bank_id, status)
VALUES ('2022-12-20 16:00:28.111756','2022-12-20 18:00:28.112755',null, 1, 0);





-- INSERT INTO survey(
--     answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8, answer9, answer10, donor_id)
-- VALUES ('yes','yes','yes','yes','yes','no','yes','yes','yes','yes', 1);



INSERT INTO role (name) VALUES ('ROLE_DONOR');
INSERT INTO role (name) VALUES ('ROLE_MEDICALWORKER');
INSERT INTO role (name) VALUES ('ROLE_ADMIN');

INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1); -- user-u dodeljujemo rolu DONOR
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 2); -- user-u dodeljujemo rolu MW
INSERT INTO USER_ROLE (user_id, role_id) VALUES (3, 3); -- user-u dodeljujemo rolu ADMIN