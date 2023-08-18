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
    sub_id INT AUTO_INCREMENT primary key,
    subject_name VARCHAR(64)
);

INSERT INTO subjects (subject_name) VALUES
    ('Mathematics'),
    ('Science'),
    ('English Language'),
    ('Physics'),
    ('Chemistry'),
    ('Biology'),
    ('History'),
    ('Geography'),
    ('Computer Science'),
    ('Economics'),
    ('Literature'),
    ('Social Studies'),
    ('Languages'),
    ('Music'),
    ('Art'),
    ('Physical Education'),
    ('Psychology'),
    ('Environmental Science'),
    ('Political Science'),
    ('Business Studies');

select * from subjects;

# DROP TABLE IF EXISTS tutor_subjects;

# CREATE INDEX idx_column ON subjects (sub_id);


DROP TABLE IF EXISTS teaching_subjects;

CREATE TABLE teaching_subjects (
    user_id INT,
    sub_id INT,
    foreign key (user_id) references users(user_id) ON DELETE CASCADE,
    foreign key (sub_id) references subjects(sub_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS learning_subjects;

CREATE TABLE learning_subjects (
    user_id INT,
    sub_id INT,
    foreign key (user_id) references users(user_id) ON DELETE CASCADE,
    foreign key (sub_id) references subjects(sub_id) ON DELETE CASCADE
);

select * from learning_subjects;

select * from teaching_subjects;

DROP TABLE IF EXISTS posts;

CREATE TABLE post (
    post_id INT AUTO_INCREMENT primary key,
    user_id INT,
    post_text TEXT,
    sub_id INT,
    foreign key (sub_id) references subjects(sub_id) ON DELETE CASCADE,
    foreign key (user_id) references users(user_id) ON DELETE CASCADE
);






