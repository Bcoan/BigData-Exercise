# BigData-Exercise

## To run application
mvn spring-boot:run  (requires maven 3 and java8)

### create Person
curl -X POST http://localhost:8080/big-data/people?email=brunocoann@hotmail.com&personId=35&name=bruno

### get person by id
curl -X GET http://localhost:8080/big-data/people/35

### list people
curl -X GET http://localhost:8080/big-data/people?limit=0

### delete person
curl -X DELETE http://localhost:8080/big-data/people/35

### create product
curl -X POST http://localhost:8080/big-data/products?productId=35&name=teste&price=2.30

### get product by id
curl -X GET http://localhost:8080/big-data/products/35

### list products
curl -X GET http://localhost:8080/big-data/products?limit=0

### delete product
curl -X DELETE http://localhost:8080/big-data/products/35

### create viewed Action
curl -X POST http://localhost:8080/big-data/people/35/viewed/35

### create added to cart Action
curl -X POST http://localhost:8080/big-data/people/35/added-to-cart/35

### create bought Action
curl -X POST http://localhost:8080/big-data/people/35/bought/35

### list actions
curl -X GET http://localhost:8080/big-data/people/35/actions?limit=0

