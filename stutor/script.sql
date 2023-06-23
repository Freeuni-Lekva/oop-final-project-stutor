CREATE DATABASE stutor_db;

USE stutor_db;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT primary key,
    username VARCHAR(64),
    role_id INT
);

# INSERT INTO users (username, role_id) VALUES
#         ('luka', 5),
#         ('beka', 6);

# select * from users;

DROP TABLE IF EXISTS students;

CREATE TABLE students (
   student_id INT AUTO_INCREMENT primary key,
   user_id INT,
   username VARCHAR(64),
   first_name VARCHAR(64),
   last_name VARCHAR(64),
   rating DECIMAL(4,2),
   rate_number INT,
   role_id INT,
   bio TEXT,
   foreign key (user_id) references users(user_id)
);


CREATE TABLE tutors (
    tutor_id INT AUTO_INCREMENT primary key,
    user_id INT,
    username VARCHAR(64),
    first_name VARCHAR(64),
    last_name VARCHAR(64),
    rating DECIMAL(4,2),
    rate_number INT,
    role_id INT,
    bio TEXT,
    foreign key (user_id) references users(user_id)
);


