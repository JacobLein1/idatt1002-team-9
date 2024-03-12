SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP DATABASE IF EXISTS applicationDB;
CREATE DATABASE applicationDB /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE applicationDB;

DROP TABLE IF EXISTS groceryList;

CREATE TABLE `groceryList` (
                        userId INT(11) NOT NULL AUTO_INCREMENT,
                        username VARCHAR(64) NOT NULL UNIQUE,
                        password TEXT,
                        PRIMARY KEY (userId)
)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- create more tables here if needed

-- inserting some test data/users
INSERT INTO groceryList (username) VALUES ('Ola');
INSERT INTO groceryList (username) VALUES ('Kari');
