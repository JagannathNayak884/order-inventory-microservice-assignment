<<<<<<< HEAD
# order-inventory-microservice-assignment
=======
# Order-Inventory Microservices Assignment

This multi-module Maven project contains two Spring Boot microservices:
- `inventory-service`
- `order-service`

Both use H2 in-memory databases and communicate via REST. The Inventory Service uses a Factory Pattern to allow future extension of inventory handling logic.

## Run locally

Build:
```
mvn clean install
```

Run Inventory:
```
cd inventory-service
mvn spring-boot:run
```

Run Order (in a separate terminal):
```
cd order-service
mvn spring-boot:run
```

## APIs

Inventory Service (port 8081):
- GET /inventory/{productId}
- POST /inventory/update

Order Service (port 8082):
- POST /order

## Tests

Run tests from project root:
```
mvn test
```
>>>>>>> 21b41d1 (initial commoit- order andinventory services)
