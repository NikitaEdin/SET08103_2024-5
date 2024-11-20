# Use the latest OpenJDK image
FROM openjdk:latest
# Copy the packaged JAR file from local machine to the container
COPY ./target/seMethods-0.3.0.0.jar /tmp
# Makes tmp directory the working directory
WORKDIR /tmp
# Command to run the jarfile that was copied earlier
ENTRYPOINT ["java", "-jar", "seMethods-0.3.0.0.jar", "db:3306", "10000"]