FROM ubuntu:latest
LABEL authors="lifefire1"

# Установка OpenJDK 17
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    apt-get clean;

# Создаем директорию для приложения
WORKDIR /app

# Копируем jar-файл внутрь контейнера
COPY target/server-0.0.1-SNAPSHOT.jar /app/server-service.jar

# Определяем команду для запуска приложения
CMD ["java", "-jar", "server-service.jar"]
