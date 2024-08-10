# Getting Started

A starter template for java spring boot with Docker

## Requirements
```
Java 17
Gradle 8
Tomcat 10
```

## Run Locally
```
./gradlew bootRun
```

## Run in Docker
```
docker-compose build
docker-compose up -d
```

## Test
```
curl -i -XGET http://localhost:8080/api/v1/hello
HTTP/1.1 200
Content-Type: text/plain;charset=UTF-8
Content-Length: 31
Date: Thu, 08 Aug 2024 15:26:39 GMT

Hello, Spring Boot With Docker!
```