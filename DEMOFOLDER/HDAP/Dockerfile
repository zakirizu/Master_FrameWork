FROM maven:3.9-eclipse-temurin-17

WORKDIR /app

# Copy only pom.xml first to leverage Docker layer caching
COPY pom.xml .

# Download dependencies (including TestNG)
RUN mvn dependency:go-offline

# Now copy source code
COPY . .

# Run tests (this can also be overridden by docker-compose)
CMD ["mvn", "test"]
