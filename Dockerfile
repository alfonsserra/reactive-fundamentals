### STAGE 1: Build ###

# We label our stage as 'builder'
FROM maven:alpine as builder

## Storing node modules on a separate layer will prevent unnecessary npm installs at each build
RUN mkdir /reactive-fundamentals

WORKDIR /reactive-fundamentals

COPY . .

## Build the angular app in production mode and store the artifacts in dist folder
RUN mvn package

### STAGE 2: Setup ###

FROM openjdk:8-jre-alpine


COPY --from=builder /reactive-fundamentals/target/reactive-fundamentals-1.0.jar reactive-fundamentals.jar


CMD ["java","-jar","reactive-fundamentals.jar"]
