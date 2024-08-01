FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/FoodAPI-0.0.1-SNAPSHOT.jar /app/foodApi.jar
CMD ["java", "-jar", "foodApi.jar"]
