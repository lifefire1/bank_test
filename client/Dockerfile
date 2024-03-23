FROM ubuntu:latest
LABEL authors="lifefire1"

# Установка OpenJDK 17
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    apt-get clean;

# Создаем директорию для приложения
WORKDIR /app

# Копируем jar-файл внутрь контейнера
COPY target/client-0.0.1-SNAPSHOT.jar /app/client-service.jar

# Определяем команду для запуска приложения
CMD ["java", "-jar", "client-service.jar"]
