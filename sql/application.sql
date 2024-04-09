CREATE TABLE "GroceryRegister" (
    groceryId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    name VARCHAR(64) NOT NULL,
    image VARCHAR(64) NOT NULL,
    unit VARCHAR(3) NOT NULL
);

CREATE TABLE "Inventory" (
    groceryId INTEGER NOT NULL,
    groceryAmount DOUBLE NOT NULL,
    FOREIGN KEY (groceryId) REFERENCES "GroceryRegister"(groceryId)
);

CREATE TABLE "RecipeList" (
    recipeId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    recipeName VARCHAR(64) NOT NULL,
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

INSERT INTO "GroceryRegister" (name, image, unit) VALUES ('Milk', 'milk.jpg', 'ml');
INSERT INTO "GroceryRegister" (name, image, unit) VALUES ('Egg', 'egg.jpg', 'stk');
INSERT INTO "GroceryRegister" (name, image, unit) VALUES ('Bread', 'bread.jpg', 'stk');
INSERT INTO "GroceryRegister" (name, image, unit) VALUES ('Butter', 'butter.jpg', 'g');
INSERT INTO "GroceryRegister" (name, image, unit) VALUES ('Cheese', 'cheese.jpg', 'g');
INSERT INTO "GroceryRegister" (name, image, unit) VALUES ('Yogurt', 'yogurt.jpg', 'ml');
INSERT INTO "GroceryRegister" (name, image, unit) VALUES ('Apple', 'apple.jpg', 'stk');
INSERT INTO "GroceryRegister" (name, image, unit) VALUES ('Banana', 'banana.jpg', 'stk');
INSERT INTO "GroceryRegister" (name, image, unit) VALUES ('Orange', 'orange.jpg', 'stk');
INSERT INTO "GroceryRegister" (name, image, unit) VALUES ('Tomato', 'tomato.jpg', 'stk');

INSERT INTO "Inventory" (groceryId, groceryAmount) VALUES (1, 2);
INSERT INTO "Inventory" (groceryId, groceryAmount) VALUES (2, 12);
INSERT INTO "Inventory" (groceryId, groceryAmount) VALUES (3, 1);
INSERT INTO "Inventory" (groceryId, groceryAmount) VALUES (4, 1);
INSERT INTO "Inventory" (groceryId, groceryAmount) VALUES (5, 1);
INSERT INTO "Inventory" (groceryId, groceryAmount) VALUES (6, 100);
INSERT INTO "Inventory" (groceryId, groceryAmount) VALUES (7, 1);

INSERT INTO "RecipeList" (instructions, recipeName, numberOfPeople) VALUES ('Just do it', 'French toast', 4);
INSERT INTO "RecipeList" (instructions, recipeName, numberOfPeople) VALUES ('Add a thing to the other thing', 'Yoghurt with fruits', 2);

INSERT INTO "RecipeGrocery" (recipeId, groceryId, amount) VALUES (1, 2, 3);
INSERT INTO "RecipeGrocery" (recipeId, groceryId, amount) VALUES (1, 3, 6);

INSERT INTO "RecipeGrocery" (recipeId, groceryId, amount) VALUES (2, 6, 400);
INSERT INTO "RecipeGrocery" (recipeId, groceryId, amount) VALUES (2, 7, 1);
INSERT INTO "RecipeGrocery" (recipeId, groceryId, amount) VALUES (2, 8, 1);
