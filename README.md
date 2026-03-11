# Spring Boot REST + MongoDB Example Application on Clever Cloud
[![Clever Cloud - PaaS](https://img.shields.io/badge/Clever%20Cloud-PaaS-orange)](https://clever-cloud.com)

This is a simple Spring Boot RESTful application that demonstrates how to build a CRUD API backed by MongoDB and deploy it to Clever Cloud.

## Why MongoDB 4.0.3?

In October 2018, MongoDB Inc. switched the server license from the open-source GNU AGPL v3 to the [Server Side Public License (SSPL)](https://www.mongodb.com/licensing/server-side-public-license), starting with version 4.2. The SSPL is not recognized as open source by the [OSI](https://opensource.org/) and imposes restrictions that are incompatible with how cloud providers operate. As a result, major Linux distributions (Debian, Fedora, RHEL...) dropped MongoDB from their repositories, and most cloud providers stopped offering newer versions.

**Clever Cloud chose to stay with MongoDB 4.0.3**, the last version released under the truly open-source AGPL v3 license. This means the MongoDB Java driver version used in your application must remain compatible with MongoDB 4.0 (wire protocol version 7). In practice, this limits us to **MongoDB Java driver 5.4.x** or earlier, since driver 5.5+ requires MongoDB 4.2 at minimum.

This is why the `pom.xml` explicitly pins `<mongodb.version>5.4.0</mongodb.version>` to override the default driver shipped with Spring Boot 3.4.

## About the Application

This application provides a REST API to manage users:
- `GET /` - Welcome message
- `GET /users` - List all users
- `GET /users/{userId}` - Get a user by ID
- `POST /users` - Create a new user
- `DELETE /users/{userId}` - Delete a user by ID

### User Model

```json
{
    "id": "...",
    "firstName": "...",
    "lastName": "..."
}
```

## Technology Stack

- [Spring Boot 3.4](https://spring.io/projects/spring-boot) - Java application framework
- [Spring Data MongoDB](https://spring.io/projects/spring-data-mongodb) - MongoDB integration
- Java 25
- Maven
- MongoDB 4.0.3

## Prerequisites

- JDK 25+
- Maven 3.8.1+

## Running the Application Locally

### Development Mode

```bash
./mvnw spring-boot:run
```

The application will be accessible at http://localhost:8080.

### Running Tests

```bash
./mvnw test
```

> Tests require a running MongoDB instance. Set the `MONGODB_ADDON_URI` environment variable to your MongoDB connection string.

## Deploying on Clever Cloud

You have two options to deploy your Spring Boot application on Clever Cloud: using the Web Console or using the Clever Tools CLI.

### Option 1: Deploy using the Web Console

#### 1. Create an account on Clever Cloud

If you don't already have an account, go to the [Clever Cloud console](https://console.clever-cloud.com/) and follow the registration instructions.

#### 2. Set up your application on Clever Cloud

1. Log in to the [Clever Cloud console](https://console.clever-cloud.com/)
2. Click on "Create" and select "An application"
3. Choose "Java + Maven" as the runtime environment
4. Configure your application settings (name, region, etc.)

#### 3. Add a MongoDB Add-on

1. In your application's dashboard, go to "Service dependencies"
2. Click "Link add-ons" and select "MongoDB"
3. Choose the plan that fits your needs
4. Link the add-on to your application

The `MONGODB_ADDON_URI` environment variable will be automatically set.

#### 4. Configure Environment Variables

Add the following environment variable in the Clever Cloud console:

| Variable | Value | Description |
|----------|-------|-------------|
| `CC_JAVA_VERSION` | `25` | Specifies to use Java 25 |
| `CC_RUN_COMMAND` | `java -jar ./target/springboot-rest-mongodb-0.0.1-SNAPSHOT.jar` | Specifies the executable to run |

#### 5. Deploy Your Application

You can deploy your application using Git:

```bash
# Add Clever Cloud as a remote repository
git remote add clever git+ssh://git@push-par-clevercloud-customers.services.clever-cloud.com/app_<your-app-id>.git

# Push your code to deploy
git push clever master
```

### Option 2: Deploy using Clever Tools CLI

#### 1. Install Clever Tools

Install the Clever Tools CLI following the [official documentation](https://www.clever-cloud.com/doc/clever-tools/getting_started/):

```bash
# Using npm
npm install -g clever-tools

# Or using Homebrew (macOS)
brew install clever-tools
```

#### 2. Log in to your Clever Cloud account

```bash
clever login
```

#### 3. Create a new application

```bash
# Initialize the current directory as a Clever Cloud application
clever create --type maven <YOUR_APP_NAME>

# Add a MongoDB add-on
clever addon create mongodb-addon <YOUR_ADDON_NAME> --link <YOUR_APP_NAME>

# Set the required environment variables
clever env set CC_JAVA_VERSION 25
clever env set CC_RUN_COMMAND "java -jar ./target/springboot-rest-mongodb-0.0.1-SNAPSHOT.jar"
```

#### 4. Deploy your application

```bash
clever deploy
```

#### 5. Open your application in a browser

Once deployed, you can access your application at the URL provided by Clever Cloud.

```bash
# Create a new user
curl -d '{"firstName":"Robert", "lastName":"Cloud"}' -H "Content-Type: application/json" -X POST https://<your-domain>/users

# List all users
curl https://<your-domain>/users
```

### Monitoring Your Application

Once deployed, you can monitor your application through:

- **Web Console**: The Clever Cloud console provides logs, metrics, and other tools to help you manage your application.
- **CLI**: Use `clever logs` to view application logs and `clever status` to check the status of your application.

## Additional Resources

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/reference/)
- [Spring Data MongoDB Reference](https://docs.spring.io/spring-data/mongodb/reference/)
- [Clever Cloud Documentation](https://www.clever-cloud.com/doc/)
