[![Build Status](https://travis-ci.org/systelab/reactive-fundamentals.svg?branch=master)](https://travis-ci.org/systelab/reactive-fundamentals)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/7ce4e563c45b4d09a975d61bed7d5d50)](https://www.codacy.com/app/alfonsserra/reactive-fundamentals?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=systelab/reactive-fundamentals&amp;utm_campaign=Badge_Grade)
[![Known Vulnerabilities](https://snyk.io/test/github/systelab/reactive-fundamentals/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/systelab/reactive-fundamentals?targetFile=pom.xml)

# SpringBoot Reactive sample repository

This project is an application skeleton for a typical [Spring Boot][sboot] Reactive backend application. You can use it
to quickly bootstrap your projects and dev environment.

## Getting Started

To get you started you can simply clone the `reactive-fundamentals` repository and install the dependencies:

### Prerequisites

You need [git][git] to clone the `reactive-fundamentals` repository.

You will need [Java™ SE Development Kit 8][jdk-download] and [Maven][maven].

### Clone `reactive-fundamentals`

Clone the `reactive-fundamentals` repository using git:

```bash
git clone https://github.com/systelab/reactive-fundamentals.git
cd reactive-fundamentals
```

### Install Dependencies

In order to install the dependencies and generate the Uber jar you must run:

```bash
mvn clean install
```

### Run

In order to run the server, you will need a Mongo DB. The easiest way to get one is with docker:

```bash
docker run -p 27017:27017 --name some-mongo -d mongo
```

To launch the server, simply run with java -jar the generated jar file.

```bash
cd target
java -jar reactive-fundamentals-1.0.jar
```

## API

You will find the swagger UI at http://localhost:8080/swagger-ui.html 

## Docker

### Build docker image

There is an Automated Build Task in Docker Cloud in order to build the Docker Image. 
This task, triggers a new build with every git push to your source code repository to create a 'latest' image.
There is another build rule to trigger a new tag and create a 'version-x.y.z' image

You can always manually create the image with the following command:

```bash
docker build -t systelab/reactive-fundamentals . 
```

### Run the container

```bash
docker run -p 8080:8080 systelab/reactive-fundamentals
```

The app will be available at http://localhost:8080/swagger-ui.html




[git]: https://git-scm.com/
[sboot]: https://projects.spring.io/spring-boot/
[maven]: https://maven.apache.org/download.cgi
[jdk-download]: http://www.oracle.com/technetwork/java/javase/downloads
[JEE]: http://www.oracle.com/technetwork/java/javaee/tech/index.html
[jwt]: https://jwt.io/
[cors]: https://en.wikipedia.org/wiki/Cross-origin_resource_sharing
[swagger]: https://swagger.io/
[allure]: https://docs.qameta.io/allure/
[junit]: https://junit.org/junit5/


