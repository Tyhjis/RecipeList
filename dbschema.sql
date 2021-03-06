PRAGMA foreign_keys=1;
CREATE TABLE recipes(
id INTEGER PRIMARY KEY NOT NULL,
name TEXT NOT NULL UNIQUE,
instructions TEXT NOT NULL);
CREATE TABLE ingredients(
id INTEGER PRIMARY KEY NOT NULL,
name TEXT NOT NULL UNIQUE);
CREATE TABLE categories(
id INTEGER PRIMARY KEY NOT NULL,
name TEXT NOT NULL UNIQUE);
CREATE TABLE recipe_ingredients(
id INTEGER PRIMARY KEY NOT NULL,
recipe_id INTEGER NOT NULL,
ingredient_id INTEGER NOT NULL,
FOREIGN KEY(recipe_id) REFERENCES recipes(id) ON DELETE CASCADE,
FOREIGN KEY(ingredient_id) REFERENCES ingredients(id) ON DELETE CASCADE);
CREATE TABLE recipe_categories(
id INTEGER PRIMARY KEY NOT NULL,
recipe_id INTEGER NOT NULL,
category_id INTEGER NOT NULL,
FOREIGN KEY(recipe_id) REFERENCES recipes(id) ON DELETE CASCADE,
FOREIGN KEY(category_id) REFERENCES categories(id) ON DELETE CASCADE);
