# KitchenSink Migration to Spring Boot

## Overview
This is a migration of the legacy 'kitchensink' JBoss application from the Red Hat JBoss EAP Quickstarts repository to the Spring Boot framework.

## Prerequisites
- Java 21
- Maven 3.x
- MongoDB (optional stretch goal)
  
# What is it?
The kitchensink quickstart is a deployable Spring Boot project with Maven build.

It demonstrates how to create a Spring Boot application using Spring Boot, JPA, and Bean Validation. It also includes a persistence unit and some sample persistence and transaction code to introduce you to database access in enterprise Java.

# Considerations for Use in a Production Environment

# H2 Database
This quickstart uses the H2 database included with Spring Boot application. It is a lightweight, relational example datasource that is used for examples only. It is not robust or scalable, is not supported, and should NOT be used in a production environment.

# MongoDB Database
This quickstart also uses the MongoDB database with embedded MongoDB included with Spring Boot application. It is a lightweight, non relational example datasource that is used for examples only. It is not robust or scalable, is not supported, and should NOT be used in a production environment.

# System Requirements
The application can run on embedded tomcat server that is coming with Spring boot. No need to configure server separately. 

All you need to build this project is Java 21.0 (Java SDK 21) or later, Spring boot vesrion 3.3.4 and Maven 3.9.9 or later. See Configure Maven to Build and Deploy the Quickstarts to make sure you are configured correctly for testing the quickstarts.

# Building and running the quickstart Spring boot application

# Build, Test and Run the Spring boot application
This command builds the Maven project and packages it into a JAR, WAR, etc.:
mvn package

This command runs the test cases of the project:
mvn test

Run the Spring boot application using Maven:
$ mvn spring-boot:run

# Access the Application

# Register the member
$ curl -X POST http://localhost:8080/kitchensink/member {"id" :2, "email" : "abcd@gmail.com", "name" : "Pramod", "phoneNumber" : "7982151513"}

# Retrive all members
$ curl -X GET http://localhost:8080/kitchensink/members

# Retrive specific member
$ curl -X GET http://localhost:8080/kitchensink/members/1



