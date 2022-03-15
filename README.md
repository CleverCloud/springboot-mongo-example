# Spring Boot as RESTful Demo for Clever-Cloud

## Description

This is a small Spring Boot app that uses java + maven Clever Cloud application type.

This is a RESTful app which is using the MongoDB addon.

## Features

As a RESTful you can use HTTP requests like:

-   `GET /users`
-   `GET /users/{userId}`
-   `POST /users "body": {"firstName":"Robert", "lastName":"Cloud"}`
-   `DELETE /users/{userId}`

## Model

```json
"users":[
    {
        "id" : "...",
        "firstName" : "...",
        "lastName" : "..."
    }
]
```

## Configuration

### MongoDB

MongoDB URI is set in `src/main/resources/application.properties` and looks like:

```java
spring.data.mongodb.uri=${MONGODB_ADDON_URI}
```

Where `MONGODB_ADDON_URI` is an environment variable which is specified by MongoDB addon link.

## Install and run locally

-   install dependencies: `mvn install`
-   tests: `mvn test`
-   run: `mvn spring-boot:run`

## Deploy on the Clever Cloud console

Create a mongodb add-on and link it to your app.

### Set the goal in the environment

In the environment variables, create `MAVEN_DEPLOY_GOAL` and set it to `spring-boot:run`

## Example

Create new user

```bash
curl -d '{"firstName":"Robert", "lastName":"Cloud"}' -H "Content-Type: application/json" -X POST https://<appId>.cleverapps.io/users
```

Get users list

```bash
curl -X GET https://<appId>.cleverapps.io/users
```

Find user by id

```bash
curl -X GET https://<appId>.cleverapps.io/users/<userId>
```

Remove user by id

```bash
curl -X DELETE https://<appId>.cleverapps.io/users/<userId>
```

## Test

I wrote an example of test in `src/test/java/com/cc/demo/test/UserMongoRepositoryTest.java` which is called during `mvn test`.
