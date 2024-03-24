# bank_test

## Запуск программы без тестов

Для сборки приложения без запуска тестов используйте команду:

mvn package -DskipTests

## Запуск тестов

Для запуска тестов замените содержимое файла src/main/resources/application.yml на следующее:

# Настройки для подключения к PostgreSQL

```yml
# connect to  PostgreSQL
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



# Connect to Cassandra
  cassandra:
    keyspace-name: your_keyspace
    schema-action: recreate
    local-datacenter: datacenter1
    username: cassandra_user
    password: cassandra_password
    contact-points: cassandra



#  Server settings
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

После этого создайте пространство ключей в Cassandra с именем your_keyspace. 
Для этого необходимо пеерейти в контейнер cassandra и выполнить в нем скрипт init.sh

```shell
bash init.sh
```

mvn test

Для начала работы с API нужно:

1) Создать пользователя в таблице users.
2) Добавить текущий лимит ноль в таблице user_spending.

