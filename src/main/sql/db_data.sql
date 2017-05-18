INSERT INTO suppliers(name, description)
VALUES
('Amazon', 'Digital content and services'),('Lenovo', 'Computers'),('Apple', 'Luxury products'),('Microsoft', 'IT products');

INSERT INTO users(name, password, rank)
VALUES('admin','admin',1),
  ('user','user',2);

INSERT INTO productcategories(name, department, description)
VALUES('Tablet', 'Hardware', 'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.'),('Phone', 'Hardware', 'Moblie phones. Your mother can ask you what you eaten for lunch through these devices.'),('Notebook', 'Hardware', 'Like a tablet but with keyboard'), ('Software', 'Software', 'Programs and subscriptions'),('Parts', 'Parts', 'Parts for different types of products.');

INSERT INTO products(name, price, currency, description, productcategory, supplier)
VALUES('Amazon Fire', 49.9, 'USD', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 1, 1),('Lenovo IdeaPad Miix 700', 479, 'USD', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 1, 2),('Amazon Fire HD 8', 89, 'USD', 'Amazons latest Fire HD 8 tablet is a great value for media consumption.', 1, 1),('Macbook Pro 2017', 2999.99, 'USD', 'Apples latest notebook is a great value for media consumption.', 3, 3),('Iphone 7', 899.9, 'USD', 'Latest product of Apple.', 2, 3),('Microsoft Office subscription', 99.9, 'USD', 'Microsoft Office is an office suite of applications, servers, and services developed by Microsoft.', 4, 3),('Battery for Iphone 7', 69.9, 'USD', 'New battery to replace Iphone 7s old battery.', 5, 3);