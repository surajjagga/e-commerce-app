INSERT INTO category (id, description, name) VALUES
                                                 (nextval('category_seq'), 'Books of all genres', 'Books'),
                                                 (nextval('category_seq'), 'Electronic devices like phones, laptops, and tablets', 'Electronics'),
                                                 (nextval('category_seq'), 'Apparel and accessories for men and women', 'Clothing'),
                                                 (nextval('category_seq'), 'Items for home and kitchen use', 'Home & Kitchen'),
                                                 (nextval('category_seq'), 'Toys and games for all ages', 'Toys & Games');

INSERT INTO product (id, description, name, price, available_quantity, category_id) VALUES
                                                                                        (nextval('product_seq'), 'A fictional novel about a young hero''s journey', 'The Lost Kingdom', 25.99, 150.0, (SELECT id FROM category WHERE name = 'Books')),
                                                                                        (nextval('product_seq'), 'The latest smartphone with a high-resolution camera', 'Vertex X Pro', 999.00, 75.0, (SELECT id FROM category WHERE name = 'Electronics')),
                                                                                        (nextval('product_seq'), 'A comfortable and stylish cotton t-shirt', 'Essential T-Shirt', 19.50, 200.0, (SELECT id FROM category WHERE name = 'Clothing')),
                                                                                        (nextval('product_seq'), 'A durable and non-stick frying pan', 'Chef''s Frying Pan', 45.00, 50.0, (SELECT id FROM category WHERE name = 'Home & Kitchen')),
                                                                                        (nextval('product_seq'), 'A popular strategy board game for 2-4 players', 'Galactic Conquest', 35.75, 120.0, (SELECT id FROM category WHERE name = 'Toys & Games')),
                                                                                        (nextval('product_seq'), 'A powerful and sleek laptop for work and gaming', 'Quantum Notebook', 1450.00, 30.0, (SELECT id FROM category WHERE name = 'Electronics')),
                                                                                        (nextval('product_seq'), 'A set of three suspense novels', 'Mystery Trilogy Box Set', 59.99, 90.0, (SELECT id FROM category WHERE name = 'Books'));