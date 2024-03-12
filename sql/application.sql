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

CREATE TABLE 'GroceryRegister'(
                        groceryId INT(11) NOT NULL AUTO_INCREMENT,
                        name VARCHAR(64) NOT NULL,
                        image VARCHAR(64) NOT NULL,
                        unit INT(11) NOT NULL,
                        PRIMARY KEY (groceryId)
)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE 'Inventory'(
                        groceryId INT(11) NOT NULL AUTO_INCREMENT,
                        groceryAmount INT(11) NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- create more tables here if needed

-- inserting some test data/users

INSERT INTO GroceryRegister (name, image, unit) VALUES ('Milk', 'milk.jpg', 1);
INSERT INTO GroceryRegister (name, image, unit) VALUES ('Egg', 'egg.jpg', 12);
INSERT INTO GroceryRegister (name, image, unit) VALUES ('Bread', 'bread.jpg', 1);
INSERT INTO GroceryRegister (name, image, unit) VALUES ('Butter', 'butter.jpg', 1);
INSERT INTO GroceryRegister (name, image, unit) VALUES ('Cheese', 'cheese.jpg', 1);
INSERT INTO GroceryRegister (name, image, unit) VALUES ('Yogurt', 'yogurt.jpg', 1);
INSERT INTO GroceryRegister (name, image, unit) VALUES ('Apple', 'apple.jpg', 1);
INSERT INTO GroceryRegister (name, image, unit) VALUES ('Banana', 'banana.jpg', 1);
INSERT INTO GroceryRegister (name, image, unit) VALUES ('Orange', 'orange.jpg', 1);
INSERT INTO GroceryRegister (name, image, unit) VALUES ('Tomato', 'tomato.jpg', 1);

INSERT INTO Inventory (groceryId, groceryAmount) VALUES (1, 2);
INSERT INTO Inventory (groceryId, groceryAmount) VALUES (2, 12);
INSERT INTO Inventory (groceryId, groceryAmount) VALUES (3, 1);
INSERT INTO Inventory (groceryId, groceryAmount) VALUES (4, 1);
INSERT INTO Inventory (groceryId, groceryAmount) VALUES (5, 1);
INSERT INTO Inventory (groceryId, groceryAmount) VALUES (6, 1);
INSERT INTO Inventory (groceryId, groceryAmount) VALUES (7, 1);

INSERT INTO groceryList (username) VALUES ('Ola');
INSERT INTO groceryList (username) VALUES ('Kari');
