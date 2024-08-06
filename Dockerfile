# Use an official Java runtime as a parent image
FROM openjdk:17-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Package the application
RUN ./mvnw package

# Run the application
CMD ["java", "-jar", "target/digitalmovie-0.0.1-SNAPSHOT.jar"]
