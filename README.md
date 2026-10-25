# Online Social Bookstore

A web application where users can share books with each other. Users create a profile, offer books they own, and request books from other users. The owner of a book sees all requests and chooses who gets it.

Team project (2 students), University of Ioannina, 2024. Developed in Scrum, in four two-week sprints.

## Features

- User registration and login
- User profile with favourite categories and authors
- Add, view and delete book offers
- Request a book from another user
- View the requests for your books and accept one

## Built with

Java 17 · Spring Boot · Spring Security · Spring Data JPA (Hibernate) · MySQL · Thymeleaf · Maven · JUnit / MockMvc

## How to run

1. Install MySQL and make sure it is running on `localhost:3306`.
2. In `bookstore/bookstore/src/main/resources/application.properties`, set your MySQL username and password.
3. Open the `bookstore/bookstore` folder in IntelliJ IDEA and run `BookstoreApplication`.
4. Open http://localhost:8080 in your browser.

The database `mybookstore_database` is created automatically on first run.

## Documentation

`SprintReport-5060-4934.pdf` contains the sprint backlog, use cases and UML diagrams (in Greek).
