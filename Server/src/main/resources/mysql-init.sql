-- mysql script

-- drop tables if they exist
DROP TABLE IF EXISTS order_details;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS order_statuses;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS products;

-- create a table of products and their prices
CREATE TABLE products
(
    product_id   BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    product_no   VARCHAR(25)    NOT NULL,
    product_name VARCHAR(100)   NOT NULL,
    price        DECIMAL(10, 2) NOT NULL
);

-- create a table of customers
CREATE TABLE customers
(
    customer_id            BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    account_no             VARCHAR(10) NOT NULL UNIQUE,
    first_name             VARCHAR(50) NOT NULL,
    last_name              VARCHAR(50) NOT NULL,
    email                  VARCHAR(50) NOT NULL UNIQUE,
    registration_timestamp TIMESTAMP   NOT NULL
);
CREATE INDEX idx_customers_account_no ON customers (account_no);

-- create a table of order statuses
CREATE TABLE order_statuses
(
    order_status_id   BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    order_status_name VARCHAR(10) NOT NULL UNIQUE
);

-- create a table of orders
CREATE TABLE orders
(
    order_id            BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    account_no          VARCHAR(10)     NOT NULL REFERENCES customers (account_no),
    order_status_id     BIGINT UNSIGNED NOT NULL REFERENCES order_statuses,
    placed_timestamp    TIMESTAMP       NOT NULL,
    delivered_timestamp TIMESTAMP       NULL,
    cancelled_timestamp TIMESTAMP       NULL
);
CREATE INDEX idx_orders_customer_id ON orders (account_no);
CREATE INDEX idx_orders_order_status_id ON orders (order_status_id);

-- create a table of order details
CREATE TABLE order_details
(
    order_detail_id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    order_id        BIGINT UNSIGNED NOT NULL REFERENCES orders,
    product_id      BIGINT UNSIGNED NOT NULL REFERENCES products,
    quantity        INTEGER         NOT NULL
);
CREATE INDEX idx_order_details_order_id ON order_details (order_id);
CREATE INDEX idx_order_details_product_id ON order_details (product_id);