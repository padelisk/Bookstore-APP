
CREATE DATABASE IF NOT EXISTS 'mybookstore_database';


USE 'mybookstore_database';

-- Create the user table
CREATE TABLE IF NOT EXISTS user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    full_name VARCHAR(100),
    address VARCHAR(255),
    age INT,
    phone_number VARCHAR(20),
    preferred_categories VARCHAR(255),
    favorite_authors VARCHAR(255)
);

-- Create the book_offer table
CREATE TABLE IF NOT EXISTS book_offer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    category VARCHAR(50),
    summary TEXT,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

-- Create the book_request table
CREATE TABLE IF NOT EXISTS book_request (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    book_offer_id BIGINT,
    user_id BIGINT,
    status VARCHAR(50),
    FOREIGN KEY (book_offer_id) REFERENCES book_offer(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);
