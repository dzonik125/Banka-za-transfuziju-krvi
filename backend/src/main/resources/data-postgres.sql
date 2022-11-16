INSERT INTO public.address(
    id, city, country, "number", street)
VALUES (1, 'Beograd', 'Srbija', '12A', 'Nemanjina');

INSERT INTO public.address(
    id, city, country, "number", street)
VALUES (2, 'Novi Sad', 'Srbija', '15', 'Brankova');

INSERT INTO public.address(
    id, city, country, "number", street)
VALUES (3, 'Kovilj', 'Srbija', '50', 'Živojina Mišića');



INSERT INTO public.blood_bank(
    id, api_key, avg_grade, description, image, name, address_id)
VALUES (1, null, 3.4, ' ','https://firebasestorage.googleapis.com/v0/b/isapsw-6ef61.appspot.com/o/h1.jpg?alt=media&token=5268f3af-3bda-4014-b18e-3e69b57fb3ea','A banka' , 1);

INSERT INTO public.blood_bank(
    id, api_key, avg_grade, description, image, name, address_id)
VALUES (2, null, 4.4, ' ','https://firebasestorage.googleapis.com/v0/b/isapsw-6ef61.appspot.com/o/h2.jpg?alt=media&token=3d2fca8e-7272-4dcc-bafc-231afce6eeac','B banka' , 2);

INSERT INTO public.blood_bank(
    id, api_key, avg_grade, description, image, name, address_id)
VALUES (3, null, 1.8, ' ','https://firebasestorage.googleapis.com/v0/b/isapsw-6ef61.appspot.com/o/h3.jpg?alt=media&token=482263cb-3590-405f-9c1e-e1fcd46b5229','C banka' , 3);



INSERT INTO public.appointment(
    id, duration, start, blood_bank_id)
VALUES (1, 30, null, 1);

INSERT INTO public.appointment(
    id, duration, start, blood_bank_id)
VALUES (2, 20, null, 2);



INSERT INTO public.blood_type_blood_bank(
    blood_bank_id, blood_type_amount, blood_type_key)
VALUES (1, 10, 'Aneg');

INSERT INTO public.blood_type_blood_bank(
    blood_bank_id, blood_type_amount, blood_type_key)
VALUES (1, 20, 'Bneg');



INSERT INTO public.blood_type_blood_bank(
    blood_bank_id, blood_type_amount, blood_type_key)
VALUES (2, 13, 'Bpos');


INSERT INTO public.users(
    id, email, gender, jmbg, name, occupation, password, surname, user_type, address_id)
VALUES (1, 'nikola@mail.com', 1, '1232312321321', 'Nikola', 'student', 'nikola123', 'Kolarov', 'MEDICAL_WORKER', 1);

INSERT INTO public.users(
    id, email, gender, jmbg, name, occupation, password, surname, user_type, address_id)
VALUES (2, 'david@mail.com', 1, '5732312321321', 'David', 'imac kada', 'david123', 'Mijailovic', 'MEDICAL_WORKER', 1);

INSERT INTO public.users(
    id, email, gender, jmbg, name, occupation, password, surname, user_type, address_id)
VALUES (3, 'tosa12@mail.com', 1, '0832312321321', 'Todor', 'roker', 'todortodor123', 'Belic', 'MEDICAL_WORKER', 2);

INSERT INTO public.users(
    id, email, gender, jmbg, name, occupation, password, surname, user_type, address_id)
VALUES (4, 'marko11@mail.com', 1, '1132312321321', 'Marko', ' ', 'marko11123', 'Nikolic', 'DONOR', 2);

INSERT INTO public.users(
    id, email, gender, jmbg, name, occupation, password, surname, user_type, address_id)
VALUES (5, 'marika@mail.com', 0, '7132312321321', 'Marija', ' ', 'marecare123', 'Bozic', 'DONOR', 3);

INSERT INTO public.users(
    id, email, gender, jmbg, name, occupation, password, surname, user_type, address_id)
VALUES (8, 'nedeljko@mail.com', 1, '9932312321321', 'Nedeljko', ' ', 'nedjo123', 'Milic', 'DONOR', 1);


INSERT INTO public.users(
    id, email, gender, jmbg, name, occupation, password, surname, user_type, address_id)
VALUES (6, 'slavica@mail.com', 0, '8132312321321', 'Slavica', ' ', 'slava123', 'Krstic', 'SYSTEM_ADMINISTRATOR', 1);

INSERT INTO public.users(
    id, email, gender, jmbg, name, occupation, password, surname, user_type, address_id)
VALUES (7, 'dragica@mail.com', 0, '8132312321321', 'Dragica', ' ', 'dragalav123', 'Ristovic', 'SYSTEM_ADMINISTRATOR', 3);


INSERT INTO public.donor(
    blood_type, category, penalty, points, id)
VALUES (0, 0, 0, 11, 4);

INSERT INTO public.donor(
    blood_type, category, penalty, points, id)
VALUES (1, 1, 1, 46, 5);

INSERT INTO public.donor(
    blood_type, category, penalty, points, id)
VALUES (2, 2, 0, 99, 8);


INSERT INTO public.admin(
    id)
VALUES (6);

INSERT INTO public.admin(
    id)
VALUES (7);


INSERT INTO public.medical_worker(
    id, blood_bank_id)
VALUES (1, null);

INSERT INTO public.medical_worker(
    id, blood_bank_id)
VALUES (2, null);

INSERT INTO public.medical_worker(
    id, blood_bank_id)
VALUES (3, 1);


INSERT INTO public.medical_worker_appointments(
    medical_worker_id, appointment_id)
VALUES (1, 1);

INSERT INTO public.medical_worker_appointments(
    medical_worker_id, appointment_id)
VALUES (2, 2);

