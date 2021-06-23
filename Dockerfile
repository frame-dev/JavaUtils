FROM openjdk:8-jre-alpine3.9

FROM maven:3.6.0-jdk-11-slim AS build
RUN mkdir /javautils
COPY . /javautils
WORKDIR /javautils
RUN mvn clean package