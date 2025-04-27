-- Create the database if it doesn't exist (optional)
CREATE DATABASE IF NOT EXISTS sql_practice_schema;
USE sql_practice_schema;

-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL UNIQUE,
  `email` VARCHAR(100) NOT NULL UNIQUE,
  `first_name` VARCHAR(50) NULL,
  `last_name` VARCHAR(50) NULL,
  `registration_date` DATE NULL,
  `is_active` BOOLEAN DEFAULT TRUE,
  `role` ENUM('customer', 'author', 'admin') NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC)
  );


-- -----------------------------------------------------
-- Table `categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `categories` (
  `category_id` INT NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(50) NOT NULL UNIQUE,
  PRIMARY KEY (`category_id`),
  UNIQUE INDEX `category_name_UNIQUE` (`category_name` ASC)
  );


-- -----------------------------------------------------
-- Table `products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `products` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(100) NOT NULL,
  `description` TEXT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `category_id` INT NOT NULL,
  `stock_quantity` INT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`product_id`),
  INDEX `fk_products_categories_idx` (`category_id` ASC),
  CONSTRAINT `fk_products_categories`
    FOREIGN KEY (`category_id`)
    REFERENCES `categories` (`category_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
  );


-- -----------------------------------------------------
-- Table `orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `order_date` DATE NULL,
  `total_amount` DECIMAL(10,2) NULL,
  `order_status` ENUM('pending', 'processing', 'shipped', 'delivered', 'cancelled') NULL,
  PRIMARY KEY (`order_id`),
  INDEX `fk_orders_users_idx` (`user_id` ASC),
  CONSTRAINT `fk_orders_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`user_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
  );


-- -----------------------------------------------------
-- Table `order_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_items` (
  `order_item_id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  `price_per_unit` DECIMAL(10,2) NULL,
  PRIMARY KEY (`order_item_id`),
  INDEX `fk_order_items_orders1_idx` (`order_id` ASC),
  INDEX `fk_order_items_products1_idx` (`product_id` ASC),
  CONSTRAINT `fk_order_items_orders1`
    FOREIGN KEY (`order_id`)
    REFERENCES `orders` (`order_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_items_products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `products` (`product_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
  );


-- -----------------------------------------------------
-- Table `reviews`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reviews` (
  `review_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `rating` INT NULL,
  `comment` TEXT NULL,
  `review_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`review_id`),
  INDEX `fk_reviews_users1_idx` (`user_id` ASC),
  INDEX `fk_reviews_products1_idx` (`product_id` ASC),
  CONSTRAINT `fk_reviews_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`user_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_reviews_products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `products` (`product_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
  );