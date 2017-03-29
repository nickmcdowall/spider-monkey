# Speckly
Spring Boot web application running in a Docker container.

## Commands

### Run from IDE
Run the Application.java class

### Run via Docker
* Builds and runs the latest image:
```
mvn clean package docker:build && \
docker run --rm -p 8080:8080 -ti nmcdowall/speckly
```

### View app in Browser
* Navigate to [localhost:8080](http:localhost:8089)



