# bank_test

## Запуск программы без тестов

Для сборки приложения без запуска тестов используйте команду:

mvn package -DskipTests

## Запуск тестов

Для запуска тестов замените содержимое файла src/main/resources/application.yml на следующее:

# Настройки для подключения к PostgreSQL

```yml
# ????????? ??? ??????????? ? PostgreSQL
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/your_database
    username: bank_user
    password: your_password
    driver-class-name: org.postgresql.Driver
  jpa:
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: create



# ????????? ??? ??????????? ? Cassandra
  cassandra:
    keyspace-name: your_keyspace
    schema-action: recreate
    local-datacenter: datacenter1
    username: cassandra_user
    password: cassandra_password
    contact-points: cassandra



  # ????????? ?????????
  application:
    name: server
  server:
    port: 8080
  quartz:
    job-store-type: memory
  main:
    allow-bean-definition-overriding: true
```

Запустить докер командой 

```shell
docker-compose up -d
```

После этого создайте пространство ключей в Cassandra с именем your_keyspace с помощью команды:

mvn test

