CREATE DATABASE db;

CREATE TABLE users(
id INT AUTO_INCREMENT PRIMARY KEY,
login VARCHAR(35) NOT NULL,
password VARCHAR(20) NOT NULL,
email VARCHAR(25) NOT NULL,
firstname VARCHAR(25) NOT NULL,
lastname VARCHAR(25) NOT NULL
);

INSERT INTO users VALUES(DEFAULT, 'user1', 'user1', 'user1@gmail.com', 'Ivan', 'Ivanenko'),
(DEFAULT, 'user2', 'user2', 'user2@gmail.com', 'Igor', 'Igorenko'),
(DEFAULT, 'user3', 'user3', 'user3@gmail.com', 'Denys', 'Denysenko'),
(DEFAULT, 'user4', 'user4', 'user4@gmail.com', 'Dmitriy', 'Dmitrenko'),
(DEFAULT, 'user5', 'user5', 'user5@gmail.com', 'Artem', 'Artemenko'),
(DEFAULT, 'user6', 'user6', 'user6@gmail.com', 'Leonid', 'Leonidov'),
(DEFAULT, 'user7', 'user7', 'user7@gmail.com', 'Nikita', 'Nikituk');