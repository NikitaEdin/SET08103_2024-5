# Use the latest OpenJDK image
FROM openjdk:latest
# Copy the packaged JAR file from local machine to the container
COPY ./target/seMethods.jar /tmp
# Makes tmp directory the working directory
WORKDIR /tmp
# Command to run the jarfile that was copied earlier
ENTRYPOINT ["java", "-jar", "seMethods.jar", "db:3306", "10000", "30"]