
<img align="center" src="src/main/resources/static/monkey.png" />

# Spider Monkey  [![Build Status](https://travis-ci.org/nickmcdowall/spider-monkey.svg?branch=master)](https://travis-ci.org/nickmcdowall/spider-monkey)

A web application that combines a word cloud generator with a github api library
to allow to generate word clouds for any given repository.


## Build
All commits pushed to GitHub are built in Travis CI and if successful pushed to [DockerHub](https://hub.docker.com/).

To build local changes you can use:
```mvn clean package docker:build```

## Run

### IDE
Run the ```com.nick.Application.java``` class

### Docker
To run the latest image from DockerHub use:
```
docker run --rm -p 8080:8080 -ti nmcdowall/spider-monkey
```

### View in Browser
* Navigate to [localhost:8080](http:localhost:8080)

### Technologies
- [Spring Boot](https://projects.spring.io/spring-boot/)
- [Docker](https://www.docker.com/)
- [Kumo](https://github.com/kennycason/kumo)
- [GitHub API for Java](http://github-api.kohsuke.org/)
- [Travis CI](https://travis-ci.org/)




