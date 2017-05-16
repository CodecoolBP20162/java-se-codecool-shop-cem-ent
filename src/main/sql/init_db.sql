DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS productcategory;


CREATE TABLE supplier
(
id int PRIMARY KEY,
name varchar(80),
description varchar(250)
);

CREATE TABLE users
(
id int PRIMARY KEY,
name varchar(40),
password varchar(60),
rank int
);

CREATE TABLE productcategory
(
id int PRIMARY KEY,
name varchar(40),
department varchar(10),
description varchar(200)
);

CREATE TABLE product
(
id int PRIMARY KEY,
name varchar(40),
price int,
currency VARCHAR (4),
description VARCHAR (200),
supplier int,
productcategory int,
FOREIGN KEY (supplier) REFERENCES supplier(id),
FOREIGN KEY (productcategory) REFERENCES productcategory(id)
);