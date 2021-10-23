# Projeto Catalogo de Produtos

## About

The objective of this project is to implement a product catalog with Java and Spring Boot.

## Stack

- java 8
- Spring Boot 2.5.0-SNAPSHOT
- DBMS: MySQL 8.0

## Development Tools

- IDE: Sprint Tool Suite 4.9.0.RELEASE
- DBMS: MySQL Workbench 8.0 CE
- Postman

## Installed Depencies

The dependencies which are used in this project can be found in: https://mvnrepository.com/

- spring-boot-starter-data-jpa -> JPA (Java Persistence API), is used for the database's persistence.
- spring-boot-starter-web -> Spring Boot package for web applications.
- spring-boot-devtools -> Used for higher productivity due to automatic reload.
- spring-boot-starter-test -> Spring Boot package for tests.
- mysql-connector-java -> Connector for the DBMS MySQL.
- flyway-core -> Used to control database versions and scripts.
- spring-boot-starter-webflux -> is part of Spring 5 and provides reactive programming support for web applications.

## Endpoints

- POST localhost:9999/products 
- PUT localhost:9999/products/{id}
- GET localhost:9999/products/{id}
- GET localhost:9999/products
- DELETE localhost:9999/products/{id}

