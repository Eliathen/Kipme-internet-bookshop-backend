# Kipme - e-commerce app
> Backend for simple e-commerce app to purchase books.

## General Information
A simple e-commerce app written as a university project. The main purpose for create this app was learning how to build web apps.

## Prerequisite

- Docker

## Getting started

To install this application, run the following commands:

```
git clone https://github.com/Eliathen/Kipme-internet-bookshop-backend
```

## Run the application

To run the application, go into the root folder of the project and run command:

```
docker-compose -f docker/docker-compose.yml up -d
```

## Technologies Used
- Java 11
- Spring boot
- MariaDB
- Redis (only for storage indexes of last visited books)


## Features
- Admin:
    - create new admin
    - add new books
    - create sales
- User:
    - register new user
    - add books to basket
    - create orders
    - add book to favorites
    - review books

## Init users

- user:
  - email: `client@client.client`
  - password: `client`
- admin:
  - email: `admin@admin.admin`
  - password: `admin`

## Swagger

- https://localhost/api/v1/swagger-ui/index.html

## Other information

- The application uses HTTPS with a self-signed certificate and depending on when you use the application, the
  certificate can be past the expiration date.
