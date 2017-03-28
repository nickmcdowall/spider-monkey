# Speckly
Spring Boot web application running in a Docker container.

## Commands


### Run from IDE
Run the Application.java class

### Run via Docker
* Build the latest image then run:
```
mvn clean package docker:build && \
docker run --rm -p 8089:8080 -ti nmcdowall/microservices/docker-maven-bootstrap
```

### View app in Browser
* Navigate to [http:localhost:8089]


## TODO
* Get maven filtering to work on the Dockerfile without breaking the template
resolving (want to populate the jar name depending on project version, artifact etc.).
May need to use maven assembly plugin


