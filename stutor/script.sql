CREATE DATABASE stutor_db;

USE stutor_db;

DROP TABLE IF EXISTS users;

# CREATE TABLE users (
#     user_id INT AUTO_INCREMENT primary key,
#     username VARCHAR(64),
#     role_id INT
# );

CREATE TABLE users (
    user_id INT AUTO_INCREMENT primary key,
    username VARCHAR(64) UNIQUE ,
    password VARCHAR(64),
#     first_name VARCHAR(64),
#     last_name VARCHAR(64),
#     phone VARCHAR(64),
#     email VARCHAR(64),
#     location VARCHAR(64),
#     bio TEXT,
    role_id INT
);

# INSERT INTO users (username, role_id) VALUES
#         ('luka', 5),
#         ('beka', 6);

select * from users;

SELECT * FROM users WHERE username like '%%';

DROP TABLE IF EXISTS students;

CREATE TABLE students (
   user_id INT,
   rating DECIMAL(4,2),
   rate_number INT,
   foreign key (user_id) references users(user_id)
);


CREATE TABLE tutors (
    user_id INT,
    rating DECIMAL(4,2),
    rate_number INT,
    foreign key (user_id) references users(user_id)
);


