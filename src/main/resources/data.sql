-- Creating products
INSERT INTO products (name, quantity, price)
VALUES ('Grillkorv Hots', 1, 17.90),
       ('Pepsi Max', 1, 21.95),
       ('Digestive', 1, 13.95),
       ('Mellanmjölk', 1, 17.90);


-- Creating users
INSERT INTO users (name, address, phone, email)
VALUES ('Fredrik', 'Prästgårdsängen 8', '0708388404', 'flundell89@gmail.com'),
       ('Jonas', 'Runslingan 29', '0701111111', 'bloosucker85@hotmail.com');

-- Creating shoppingcarts
INSERT INTO shoppingcarts(user_id)
VALUES (1),
       (2);

-- Adding products into shoppingcarts
INSERT INTO shoppingcart_products (shoppingcart_id, product_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2,4);