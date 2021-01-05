[![Build Status](https://travis-ci.org/systelab/seed-springboot.svg?branch=master)](https://travis-ci.org/systelab/seed-springboot)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/7ce4e563c45b4d09a975d61bed7d5d50)](https://www.codacy.com/app/alfonsserra/seed-springboot?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=systelab/seed-springboot&amp;utm_campaign=Badge_Grade)
[![Known Vulnerabilities](https://snyk.io/test/github/systelab/seed-springboot/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/systelab/seed-springboot?targetFile=pom.xml)

# `seed-springboot` — Seed for Spring Boot Systelab projects

This project is an application skeleton for a typical [Spring Boot][sboot] backend application. You can use it
to quickly bootstrap your projects and dev environment.

The seed contains a Patient Management sample application and is preconfigured to install the JEE
framework and a bunch of development and testing tools for instant development gratification.

The app doesn't do too much, just shows how to use different standards and other suggested tools together:

* Spring Boot
* Spring Boot Web
* Spring Boot Data JPA
* [JWT][jwt]
* [CORS][cors]
* [Swagger][swagger]
* Spring Boot Security
* Spring Boot Test
* [Allure][allure] with [JUnit][junit]

## Getting Started

To get you started you can simply clone the `seed-springboot` repository and install the dependencies:

### Prerequisites

You need [git][git] to clone the `seed-springboot` repository.

You will need [OpenJDK 11][jdk-download] and [Maven][maven].

### Clone `seed-springboot`

Clone the `seed-springboot` repository using git:

```bash
git clone https://github.com/systelab/seed-springboot.git
cd seed-springboot
```

### Install Dependencies

In order to install the dependencies and generate the Uber jar you must run:

```bash
mvn clean install
```

### Run

To launch the server, simply run with java -jar the generated jar file.

```bash
cd target
java -jar seed-springboot-1.0.jar
```

#### Run as a Service on Windows

An option is to use the Windows Service Wrapper, steps:
1. Take WinSW.exe or zip from the distribution
1. Write seed-sprintboot-1.0.xml. Here you have a basic one but it can be customized to define for example dependencies.
```xml
<service>
    <id>seed-springboot-1.0</id>
    <name>seed-springboot-1.0</name>
    <description>This runs Spring Boot Seed as a Service</description>
    <env name="MYAPP_HOME" value="%BASE%"/>
    <executable>java</executable>
    <arguments>-Xmx256m -jar "%BASE%\seed-springboot-1.0.jar"</arguments>
    <logmode>rotate</logmode>
</service>
```
1. Run winsw install seed-sprintboot-1.0.xml
1. Run winsw start seed-sprintboot-1.0.xml

### Certificate

A self signed certificate is provided in order to show use how to setup the application.

The certificate was generated with the following commands:

```bash
keytool -genkey -keyalg RSA -alias selfsigned -keystore keystore.jks -storepass password -validity 365 -keysize 2048
keytool -importkeystore -srckeystore keystore.jks -destkeystore keystore.p12 -deststoretype pkcs12
```

> Do not use the certificate provided in production and never put any secret in your application.yml file.


## API

You will find the swagger UI at https://localhost:8443/swagger-ui.html and http://localhost:8080/swagger-ui.html 

First generate a token by login as user Systelab and password Systelab. After that authorize Swagger by copying the bearer.

Head to https://localhost:8443/hystrix and setup the URL https://localhost:8443/actuator/hystrix.stream in order to get the Hystrix Dashboard.

## Docker

### Build docker image

There is an Automated Build Task in Docker Cloud in order to build the Docker Image. 
This task, triggers a new build with every git push to your source code repository to create a 'latest' image.
There is another build rule to trigger a new tag and create a 'version-x.y.z' image

You can always manually create the image with the following command:

```bash
docker build -t systelab/seed-springboot . 
```

### Run the container

```bash
docker run -p 8443:8443 -p 8080:8080 systelab/seed-springboot
```

The app will be available at https://localhost:8443/swagger-ui.html

## Documentation

See [Documentation](doc/README.md) section for further details about other technical specifications.


[git]: https://git-scm.com/
[sboot]: https://projects.spring.io/spring-boot/
[maven]: https://maven.apache.org/download.cgi
[jdk-download]: https://adoptopenjdk.net/
[JEE]: http://www.oracle.com/technetwork/java/javaee/tech/index.html
[jwt]: https://jwt.io/
[cors]: https://en.wikipedia.org/wiki/Cross-origin_resource_sharing
[swagger]: https://swagger.io/
[allure]: https://docs.qameta.io/allure/
[junit]: https://junit.org/junit5/

