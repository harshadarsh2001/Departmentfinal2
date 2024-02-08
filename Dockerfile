#FROM openjdk:17
#ADD target/department-spring-boot-docker.jar department-spring-boot-docker.jar
#ENTRYPOINT ["java","-jar","/department-spring-boot-docker.jar"]

# First stage: Build the Spring Boot application
#FROM maven:3.8.3-openjdk-17 AS builder
#WORKDIR /app
#COPY . .
#RUN mvn clean package -DskipTests

# Second stage: Run the FakeSMTP server
#FROM openjdk:17
#RUN mkdir -p /output
#ADD http://nilhcem.github.io/FakeSMTP/downloads/fakeSMTP-latest.zip /fakeSMTP-latest.zip
#RUN unzip /fakeSMTP-latest.zip
#VOLUME /output
#EXPOSE 25
#ENTRYPOINT ["java","-jar","/fakeSMTP-2.0.jar","--background", "--output-dir", "/output", "--port", "25", "--start-server"]

# Final stage: Copy the built Spring Boot application
#FROM openjdk:17
#COPY --from=builder /app/target/department-spring-boot-docker.jar /department-spring-boot-docker.jar
#ENTRYPOINT ["java","-jar","/department-spring-boot-docker.jar"]
# Use OpenJDK 17 as the base image
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY target/department-spring-boot-docker.jar department-spring-boot-docker.jar

# Specify the command to run the Spring Boot application
CMD ["java", "-jar", "department-spring-boot-docker.jar"]
