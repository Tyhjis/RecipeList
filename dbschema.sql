CREATE TABLE recipes(
id INTEGER PRIMARY KEY NOT NULL,
name TEXT NOT NULL,
instructions TEXT NOT NULL);
CREATE TABLE ingredients(
id INTEGER PRIMARY KEY NOT NULL,
name TEXT NOT NULL);
CREATE TABLE categories(
id INTEGER PRIMARY KEY NOT NULL,
name TEXT NOT NULL);
CREATE TABLE recipe_ingredients(
id INTEGER PRIMARY KEY NOT NULL,
recipe_id INTEGER NOT NULL,
ingredient_id INTEGER NOT NULL,
FOREIGN KEY(recipe_id) REFERENCES recipes(id),
FOREIGN KEY(ingredient_id) REFERENCES ingredients(id));
CREATE TABLE recipe_categories(
id INTEGER PRIMARY KEY NOT NULL,
recipe_id INTEGER NOT NULL,
category_id INTEGER NOT NULL,
FOREIGN KEY(recipe_id) REFERENCES recipes(id),
FOREIGN KEY(category_id) REFERENCES categories(id));
