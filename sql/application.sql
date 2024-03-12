SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP DATABASE IF EXISTS applicationDB;
CREATE DATABASE applicationDB /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE applicationDB;

DROP TABLE IF EXISTS groceryList;

-- create more tables here if needed

CREATE TABLE `recipeList` (
                        recipeId INT NOT NULL AUTO_INCREMENT,
                        instructions VARCHAR(128) NOT NULL,
                        numberOfPeople INT,
                        PRIMARY KEY (recipeId)
)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `recipeGrocery` (
                        recipeId INT NOT NULL,
                        groceryId INT NOT NULL,
                        amount double NOT NULL,
                        FOREIGN KEY (recipeId) REFERENCES recipeList(recipeId),
                        FOREIGN KEY (groceryId) REFERENCES groceryList(groceryId)
)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- inserting some test data/users
INSERT INTO recipeList (instructions, numberOfPeople) VALUES ('Just do it', 4);
INSERT INTO recipeList (instructions, numberOfPeople) VALUES ('Add a thing to the other thing', 2);

-- insert data for recipeGrocery
INSERT INTO recipeGrocery (recipeId, groceryId, amount) VALUES (1, 2, 3);
INSERT INTO recipeGrocery (recipeId, groceryId, amount) VALUES (1, 3, 6);

INSERT INTO recipeGrocery (recipeId, groceryId, amount) VALUES (2, 6, 400);
INSERT INTO recipeGrocery (recipeId, groceryId, amount) VALUES (2, 7, 1);
INSERT INTO recipeGrocery (recipeId, groceryId, amount) VALUES (2, 8, 1);