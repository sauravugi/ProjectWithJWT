# Ecommerce_project(Product Application Using JWT Token)
REST API developed for a Product Application.This API handles an Admin and User and performs all the fundamental CRUD operations of any Product Application platform with user validation at every step. And In this project we can perform Some Frontend functionality Also Like sign up, Log In, Adding, Buy products.
This project is developed by me during Project week in Masai School.

## Badges

![Badge](https://visitor-counter-badge.vercel.app/api/sauravugi/Ecommerce_project)

## Tech Stack
- Java
- Spring Framework
- Spring Boot
- Spring Data JPA
- Hibernate
- Spring Validation
- MySQL
- Lambok
- Swagger Ui
- HTML
- CSS
- JavaScript
- Bootstrap

## Modules
- Login Module
- Admin Module
- User Module
- Product Management Module
- Index Module(Frontend)
- Admin Module(Frontend)
- User Module(Frontend)

## Features

- User and Admin authentication with Jwt Token & validation.
- Admin Features:
  - Admin can register, log in, log out, update and delete accounts
  - Admin have control over the entire application
  - Admin can manage User, order and product.
  - Admin can access the details of different users and products.
  - Only logged-in Admin can access all features of Admin and Every Operation perform Only help of JWT Token.
- User Features:
  - Users can register themselves with the application, logging in and logout into the application
  - Users can buy products and can cancel the products.
  - Users can update, or delete their accounts
  - Only logged-in users can access all user features and Every Operation perform Only help of JWT Token.


## Installation and Run

```
#changing the server port
server.port=8007
#db specific properties
spring.datasource.url=jdbc:mysql://localhost:3306/productsdb
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=0007
#ORM s/w specific properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
#No handler found
spring.mvc.throw-exception-if-no-handler-found=true
spring.web.resources.add-mappings=false
#swagger-ui
spring.mvc.pathmatch.matching-strategy = ANT_PATH_MATCHER
```

## API Root and Endpoint

```
https://localhost:8007/
```

```
http://localhost:8007/swagger-ui/
```

## Contributor

- [Saurav Kumar](https://github.com/sauravugi)





