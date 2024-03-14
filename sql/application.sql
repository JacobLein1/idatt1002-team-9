CREATE TABLE "GroceryRegister" (
    groceryId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    name VARCHAR(64) NOT NULL,
    image VARCHAR(64) NOT NULL,
    unit INTEGER NOT NULL
);

CREATE TABLE "Inventory" (
    groceryId INTEGER NOT NULL,
    groceryAmount INTEGER NOT NULL,
    FOREIGN KEY (groceryId) REFERENCES "GroceryRegister"(groceryId)
);

CREATE TABLE "RecipeList" (
    recipeId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    instructions VARCHAR(128) NOT NULL,
    numberOfPeople INTEGER NOT NULL
);

CREATE TABLE "RecipeGrocery" (
    recipeId INTEGER NOT NULL,
    groceryId INTEGER NOT NULL,
    amount DOUBLE NOT NULL,
    FOREIGN KEY (recipeId) REFERENCES "RecipeList"(recipeId),
    FOREIGN KEY (groceryId) REFERENCES "GroceryRegister"(groceryId)
);

INSERT INTO "GroceryRegister" (name, image, unit) VALUES ('Milk', 'milk.jpg', 1);
INSERT INTO "GroceryRegister" (name, image, unit) VALUES ('Egg', 'egg.jpg', 12);
INSERT INTO "GroceryRegister" (name, image, unit) VALUES ('Bread', 'bread.jpg', 1);
INSERT INTO "GroceryRegister" (name, image, unit) VALUES ('Butter', 'butter.jpg', 1);
INSERT INTO "GroceryRegister" (name, image, unit) VALUES ('Cheese', 'cheese.jpg', 1);
INSERT INTO "GroceryRegister" (name, image, unit) VALUES ('Yogurt', 'yogurt.jpg', 1);
INSERT INTO "GroceryRegister" (name, image, unit) VALUES ('Apple', 'apple.jpg', 1);
INSERT INTO "GroceryRegister" (name, image, unit) VALUES ('Banana', 'banana.jpg', 1);
INSERT INTO "GroceryRegister" (name, image, unit) VALUES ('Orange', 'orange.jpg', 1);
INSERT INTO "GroceryRegister" (name, image, unit) VALUES ('Tomato', 'tomato.jpg', 1);

INSERT INTO "Inventory" (groceryId, groceryAmount) VALUES (1, 2);
INSERT INTO "Inventory" (groceryId, groceryAmount) VALUES (2, 12);
INSERT INTO "Inventory" (groceryId, groceryAmount) VALUES (3, 1);
INSERT INTO "Inventory" (groceryId, groceryAmount) VALUES (4, 1);
INSERT INTO "Inventory" (groceryId, groceryAmount) VALUES (5, 1);
INSERT INTO "Inventory" (groceryId, groceryAmount) VALUES (6, 1);
INSERT INTO "Inventory" (groceryId, groceryAmount) VALUES (7, 1);

INSERT INTO "RecipeList" (instructions, numberOfPeople) VALUES ('Just do it', 4);
INSERT INTO "RecipeList" (instructions, numberOfPeople) VALUES ('Add a thing to the other thing', 2);

INSERT INTO "RecipeGrocery" (recipeId, groceryId, amount) VALUES (1, 2, 3);
INSERT INTO "RecipeGrocery" (recipeId, groceryId, amount) VALUES (1, 3, 6);

INSERT INTO "RecipeGrocery" (recipeId, groceryId, amount) VALUES (2, 6, 400);
INSERT INTO "RecipeGrocery" (recipeId, groceryId, amount) VALUES (2, 7, 1);
INSERT INTO "RecipeGrocery" (recipeId, groceryId, amount) VALUES (2, 8, 1);
