# FoodAPI
## Overview
FoodAPI is a RESTful service designed to manage a food ordering system. It includes functionalities to manage countries, locations, restaurants, menus, foods, and customer orders.

## Technologies Used
- Java
- Spring Boot
- JPA/Hibernate
- PostgreSQL (or any other preferred database)
- Maven

## API Endpoints

### Country Endpoints

#### Create a Country
- **URL**: `/country/`
- **Method**: `POST`
- **Request Body**:
    ```json
    {
        "name": "Egypt",
        "code": "EG",
        "emoji": "🇪🇬"
    }
    ```
- **Response**:
    ```json
    {
        "code": "SUCCESS",
        "message": "Country created successfully.",
        "data": {
            "id": 1,
            "name": "Egypt",
            "code": "EG",
            "emoji": "🇪🇬"
        }
    }
    ```

### Food Endpoints

#### Create a Food
- **URL**: `/food/create`
- **Method**: `POST`
- **Request Body**:
    ```json
    {
        "name": "Poha",
        "price": 40,
        "menuId": "1"
    }
    ```
- **Response**: HTTP Status 200 OK

### Order Endpoints

#### Create a Customer Order
- **URL**: `/order/create`
- **Method**: `POST`
- **Request Body**:
    ```json
    [
        {
            "foodId": 1,
            "count": 2
        },
        {
            "foodId": 2,
            "count": 3
        }
    ]
    ```
- **Response**: HTTP Status 200 OK

## Models

### Country
- **name**: String
- **code**: String

### Food
- **id**: Long
- **name**: String
- **price**: int
- **menu**: Menu

### Menu
- **id**: Long
- **name**: String
- **restaurant**: Restaurant

### Restaurant
- **id**: Long
- **name**: String
- **rating**: int
- **location**: Location

### Location
- **id**: Long
- **address**: String
- **country**: Country

### CustomerOrder
- **id**: Long
- **status**: String
- **amount**: int
- **count**: int
- **items**: List\<Items>

### Items
- **foodId**: Long
- **foodName**: String
- **foodPrice**: int
- **count**: int
- **menu**: String
- **restaurantName**: String
- **restaurantAddress**: String
- **restaurantCountry**: String

