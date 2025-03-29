# Scaler E-Commerce API

A Spring Boot e-commerce application that provides a RESTful API for managing products and categories. The application integrates with FakeStore API and also provides a self-implemented service layer for data persistence.

## Overview

This project is a Spring Boot application that provides RESTful API endpoints for an e-commerce system. It supports two modes of operation:
1. Integration with external FakeStore API for product and category management
2. Self-implemented services with JPA repositories for local data persistence

The application follows a clean architecture with separate layers for controllers, services, repositories, and DTOs.

## Features

- Product management (CRUD operations)
- Category management
- Authentication and authorization
- Exception handling
- Caching with Redis
- Integration with external API

## Architecture

The application follows a layered architecture:

- **Controllers**: Handle HTTP requests and responses
- **Services**: Implement business logic
- **Repositories**: Manage data persistence
- **DTOs**: Data transfer objects for communication
- **Models**: Domain entities
- **Clients**: External API integration
- **Exception Handling**: Custom exception handling

## Technologies

- Java 11+
- Spring Boot
- Spring Web
- Spring Data JPA
- Redis for caching
- Lombok for reducing boilerplate code
- RESTful API design
- External API integration (FakeStore API)

## API Endpoints

### Products

- `GET /products`: Get all products (requires admin authentication)
- `GET /products/{productID}`: Get a single product
- `POST /products`: Add a new product
- `PATCH /products/{productID}`: Update a single product
- `PUT /products/{productID}`: Replace a single product
- `DELETE /products/{productID}`: Delete a single product

### Categories

- `GET /products/categories`: Get all categories
- `GET /products/categories/{categoryName}`: Get all products by category
- `POST /products/categories`: Add a new category

## Authentication

The application uses token-based authentication for certain endpoints. The authentication system is integrated with an external authentication service through the `AuthenticationClient`.

### Authentication Flow:

1. Client requests are authenticated using a token in the `AUTH_TOKEN` header
2. The token is validated through the `AuthenticationClient`
3. Token validation returns user information and session status
4. Based on the user's roles, access to protected resources is granted or denied

### Authentication Components:

- `SessionStatus`: Enum representing possible token states (ACTIVE, EXPIRED, LOGGED_OUT, INVALID)
- `UserDto`: Contains user information including email and roles
- `RoleDto`: Represents user roles for authorization
- `ValidateResponseDto`: Response from authentication service with user details and session status
- `ValidateTokenRequestDto`: Request object for token validation

### Protected Endpoints:

The `getAllProducts` endpoint is protected and requires:
1. A valid authentication token in the `AUTH_TOKEN` header
2. The user must have the `ADMIN` role

## Caching

The application implements caching using Redis for improved performance:

- Products are cached in Redis after they are fetched
- The system checks the cache before making external API calls

```html
src/main/java/org/example/scaler_e_commerce/
├── authenticationClient/
│   ├── AuthenticationClient.java
│   └── dtos/
│       ├── RoleDto.java
│       ├── SessionStatus.java
│       ├── UserDto.java
│       ├── ValidateResponseDto.java
│       └── ValidateTokenRequestDto.java
├── clients/
│   └── fakeStoreApi/
│       ├── StoreCategoryClient.java
│       └── StoreProductClient.java
├── controllers/
│   ├── CategoryController.java
│   ├── ExceptionAdvices.java
│   └── ProductController.java
├── dtos/
│   ├── CategoryDto.java
│   ├── ErrorResponseDto.java
│   └── ProductDto.java
├── exceptions/
│   └── NotFoundException.java
├── models/
│   ├── BaseModel.java
│   ├── Category.java
│   └── Product.java
├── repositories/
│   ├── CategoryRepository.java
│   └── ProductRepository.java
└── services/
    ├── CategoryService.java
    ├── CategoryServiceImpl.java
    ├── ProductService.java
    ├── ProductServiceImpl.java
    ├── SelfCategoryService.java
    └── SelfProductService.java
```

The project follows a standard Spring Boot application structure with clear separation of concerns between different components.