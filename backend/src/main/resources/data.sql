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
VALUES ('321', 4.4, ' ','https://firebasestorage.googleapis.com/v0/b/isapsw-6ef61.appspot.com/o/h2.jpg?alt=media&token=3d2fca8e-7272-4dcc-bafc-231afce6eeac','B banka' , 2);

INSERT INTO blood_bank(
    api_key, avg_grade, description, image, name, address_id)
VALUES ('213', 1.8, ' ','https://firebasestorage.googleapis.com/v0/b/isapsw-6ef61.appspot.com/o/h3.jpg?alt=media&token=482263cb-3590-405f-9c1e-e1fcd46b5229','C banka' , 3);



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


INSERT INTO blood_type_blood_bank(
    blood_bank_id, blood_type_amount, blood_type_key)
VALUES (2, 13, 'Bpos');


INSERT INTO users(
    email, gender, jmbg, name, occupation, password, surname, user_type, address_id)
VALUES ('nikola@mail.com', 1, '1232312321321', 'Nikola', 'student', 'nikola123', 'Kolarov', 'MEDICAL_WORKER', 1);

INSERT INTO users(
    email, gender, jmbg, name, occupation, password, surname, user_type, address_id)
VALUES ('david@mail.com', 1, '5732312321321', 'David', 'imac kada', 'david123', 'Mijailovic', 'MEDICAL_WORKER', 1);

INSERT INTO users(
    email, gender, jmbg, name, occupation, password, surname, user_type, address_id)
VALUES ('tosa12@mail.com', 1, '0832312321321', 'Todor', 'roker', 'todortodor123', 'Belic', 'MEDICAL_WORKER', 2);

INSERT INTO users(
    email, gender, jmbg, name, occupation, password, surname, user_type, address_id)
VALUES ('marko11@mail.com', 1, '1132312321321', 'Marko', ' ', 'marko11123', 'Nikolic', 'DONOR', 2);

INSERT INTO users(
    email, gender, jmbg, name, occupation, password, surname, user_type, address_id)
VALUES ('marika@mail.com', 0, '7132312321321', 'Marija', ' ', 'marecare123', 'Bozic', 'DONOR', 3);

INSERT INTO users(
    email, gender, jmbg, name, occupation, password, surname, user_type, address_id)
VALUES ('nedeljko@mail.com', 1, '9932312321321', 'Nedeljko', ' ', 'nedjo123', 'Milic', 'DONOR', 1);


INSERT INTO users(
    email, gender, jmbg, name, occupation, password, surname, user_type, address_id)
VALUES ('slavica@mail.com', 0, '8132312321321', 'Slavica', ' ', 'slava123', 'Krstic', 'SYSTEM_ADMINISTRATOR', 1);

INSERT INTO users(
    email, gender, jmbg, name, occupation, password, surname, user_type, address_id)
VALUES ('dragica@mail.com', 0, '8132312321321', 'Dragica', ' ', 'dragalav123', 'Ristovic', 'SYSTEM_ADMINISTRATOR', 3);


INSERT INTO donor(
    blood_type, category, penalty, points, id)
VALUES (0, 0, 0, 11, 4);

INSERT INTO donor(
    blood_type, category, penalty, points, id)
VALUES (1, 1, 1, 46, 5);

INSERT INTO donor(
    blood_type, category, penalty, points, id)
VALUES (2, 2, 0, 99, 8);


INSERT INTO admin(
    id)
VALUES (6);

INSERT INTO admin(
    id)
VALUES (7);


INSERT INTO medical_worker(
    id, blood_bank_id)
VALUES (1, null);

INSERT INTO medical_worker(
    id, blood_bank_id)
VALUES (2, null);

INSERT INTO medical_worker(
    id, blood_bank_id)
VALUES (3, 1);


INSERT INTO medical_worker_appointments(
    medical_worker_id, appointment_id)
VALUES (1, 1);

INSERT INTO medical_worker_appointments(
    medical_worker_id, appointment_id)
VALUES (2, 2);