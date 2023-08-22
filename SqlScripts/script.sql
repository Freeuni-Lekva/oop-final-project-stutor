CREATE DATABASE stutor_db;

USE stutor_db;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
    username VARCHAR(64) primary key,
    hashedPassword VARCHAR(64),
    firstname VARCHAR(64),
    lastname VARCHAR(64),
    email VARCHAR(64) UNIQUE
);

DROP TABLE IF EXISTS subjects;

CREATE TABLE subjects (
    subject_name VARCHAR(64) primary key
);

drop table if exists chat;

create table chat (
    message_id INT PRIMARY KEY AUTO_INCREMENT,
    sender VARCHAR(64),
    receiver VARCHAR(64),
    message VARCHAR(255),
    FOREIGN KEY (sender) REFERENCES users(username) ON DELETE CASCADE,
    FOREIGN KEY (receiver) REFERENCES users(username) ON DELETE CASCADE
);

DROP TABLE IF EXISTS posts;

CREATE TABLE posts (
    post_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(64),
    subject_name VARCHAR(64),
    type VARCHAR(64),
    text TEXT,
    FOREIGN KEY (subject_name) REFERENCES subjects(subject_name) ON DELETE CASCADE,
    FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE
);

DROP TABLE IF EXISTS admins;

create table admins (
    adminName varchar(64) primary key,
    foreign key (adminName) references users(username) ON DELETE cascade
);

DROP TABLE IF EXISTS ratings;

CREATE TABLE ratings (
    user VARCHAR(64),
    rated VARCHAR(64),
    rating_value TINYINT UNSIGNED CHECK (rating_value BETWEEN 1 AND 5),
    foreign key (user) references users(username) ON DELETE CASCADE,
    foreign key (rated) references users(username) ON DELETE CASCADE,
    CONSTRAINT unique_user_rated_pair UNIQUE (user, rated),
    CONSTRAINT check_user_not_equal_rated CHECK (user <> rated)
);

DROP TABLE IF EXISTS followers;

CREATE TABLE followers (
    following VARCHAR(64),
    follower VARCHAR(64),
    foreign key (following) references users(username) ON DELETE CASCADE,
    foreign key (follower) references users(username) ON DELETE CASCADE,
    CONSTRAINT check_user_not_equal_friend CHECK (following <> follower),
    CONSTRAINT unique_user_friend_pair UNIQUE (following, follower)
);