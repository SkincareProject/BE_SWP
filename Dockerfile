# Sử dụng hình ảnh chính thức của OpenJDK 23
FROM openjdk:23-jdk

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép tệp JAR vào container
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose cổng 8080
EXPOSE 8080

# Chạy ứng dụng Spring Boot
CMD ["java", "-jar", "app.jar"]
