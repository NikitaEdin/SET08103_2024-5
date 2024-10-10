# Use the latest OpenJDK image
FROM openjdk:latest
# Copy the packaged JAR file from local machine to the container
COPY ./target/seMethods-0.1.0.3-jar-with-dependencies.jar /tmp
# Makes tmp directory the working directory
WORKDIR /tmp
# Command to run the jarfile that was copied earlier
ENTRYPOINT ["java", "-jar", "seMethods-0.1.0.3-jar-with-dependencies.jar"]