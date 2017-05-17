DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS productcategories;


CREATE TABLE suppliers
(
id SERIAL PRIMARY KEY,
name varchar(80),
description varchar(250)
);

CREATE TABLE users
(
id SERIAL PRIMARY KEY,
name varchar(40),
password varchar(60),
rank int
);

CREATE TABLE productcategories
(
id SERIAL PRIMARY KEY,
name varchar(40),
department varchar(10),
description varchar(200)
);

CREATE TABLE products
(
id SERIAL PRIMARY KEY,
name varchar(40),
price VARCHAR(20),
currency VARCHAR (4),
price FLOAT,
currency VARCHAR (10),
description VARCHAR (200),
productcategory int,
supplier int,
FOREIGN KEY (productcategory) REFERENCES productcategories(id),
FOREIGN KEY (supplier) REFERENCES suppliers(id)
);