CREATE DATABASE stutor_db;

USE stutor_db;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT primary key,
    username VARCHAR(64) unique UNIQUE,
    hashedPassword VARCHAR(64),
    firstname VARCHAR(64),
    lastname VARCHAR(64),
    email VARCHAR(64) UNIQUE
);

DROP TABLE IF EXISTS followers;

CREATE TABLE followers (
    user_id INT,
    follower_id INT,
    foreign key (user_id) references users(user_id) ON DELETE CASCADE,
    foreign key (follower_id) references users(user_id) ON DELETE CASCADE,
    CONSTRAINT check_user_not_equal_friend CHECK (user_id <> follower_id),
    CONSTRAINT unique_user_friend_pair UNIQUE (user_id, follower_id)
);


select * from users;
select * from ratings;


SELECT * FROM users WHERE username like '%%';

DROP TABLE IF EXISTS ratings;

CREATE TABLE ratings (
     user_id INT,
     rated_id INT,
     rating_value TINYINT UNSIGNED CHECK (rating_value BETWEEN 1 AND 5),
     foreign key (user_id) references users(user_id) ON DELETE CASCADE,
     foreign key (rated_id) references users(user_id) ON DELETE CASCADE,
     CONSTRAINT unique_user_rated_pair UNIQUE (user_id, rated_id),
     CONSTRAINT check_user_not_equal_rated CHECK (user_id <> rated_id)
);


DROP TABLE IF EXISTS subjects;

CREATE TABLE subjects (
    sub_id VARCHAR(4),
    subject_name VARCHAR(64)
);

INSERT INTO subjects (sub_id, subject_name) VALUES
    ('MATH', 'Mathematics'),
    ('SCIE', 'Science'),
    ('ENGL', 'English Language'),
    ('PHYS', 'Physics'),
    ('CHEM', 'Chemistry'),
    ('BIOL', 'Biology'),
    ('HIST', 'History'),
    ('GEOG', 'Geography'),
    ('COMP', 'Computer Science'),
    ('ECON', 'Economics'),
    ('LITR', 'Literature'),
    ('SOST', 'Social Studies'),
    ('LANG', 'Languages'),
    ('MUSI', 'Music'),
    ('ARTS', 'Art'),
    ('PETH', 'Physical Education'),
    ('PSYC', 'Psychology'),
    ('ENVS', 'Environmental Science'),
    ('POLS', 'Political Science'),
    ('BUST', 'Business Studies');

select * from subjects;

DROP TABLE IF EXISTS tutor_subjects;

CREATE INDEX idx_column ON subjects (sub_id);

CREATE TABLE tutor_subjects (
    user_id INT,
    sub_id VARCHAR(4),
    foreign key (user_id) references users(user_id) ON DELETE CASCADE,
    foreign key (sub_id) references subjects(sub_id) ON DELETE CASCADE
);

select subjects.sub_id from subjects;




