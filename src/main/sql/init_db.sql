DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS productcategory;
DROP TABLE IF EXISTS productdatastore;

CREATE TABLE supplier
(
id varchar(36) PRIMARY KEY,
name varchar(80),
description varchar(250)
);

CREATE TABLE users
(
id varchar(36) PRIMARY KEY,
name varchar(40),
password varchar(60),
rank int
);

CREATE TABLE productcategory
(
id varchar(36) PRIMARY KEY,
name varchar(40),
department varchar(10),
description varchar(200)
);

CREATE TABLE productdatastore
(
id varchar(36) PRIMARY KEY,
name varchar(40),
price int,
currency VARCHAR (4),
description VARCHAR (200),
FOREIGN KEY (supplier) REFERENCES supplier(id),
FOREIGN KEY (productcategors) REFERENCES productcategory(id)
);