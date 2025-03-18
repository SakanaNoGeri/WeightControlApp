# REST API сервис для отслеживания дневной нормы калорий пользователя и учета съеденных блюд

Данный REST API подходит для управления пользователями, блюдами, приемами пищи и отчетами о потреблении калорий. Реализован с использованием Spring Boot, PostgreSQL и включает валидацию данных, обработку ошибок и юнит-тесты на .

## В API реализовано 4 контроллера:

    DishController: Управление блюдами.
    MealController: Учет приемов пищи с указанием блюд.
    ReportController: Генерация отчетов (дневной отчет, проверка нормы калорий, история питания).
    UserController: Создание пользователей с расчетом дневной нормы калорий
    
## Требования

    Java 17+
    Gradle 8.x
    Docker (для запуска PostgreSQL через docker-compose.yaml)
    PostgreSQL 16 (при запуске локально)
    
## Инструкция по запуску
1. Поднять PostgresQL локально или же при помощи docker-compose, который находится в папке ресурсов программы. Postgres поднимается на стандартном 5432 порте по умолчанию.
При запуске в контейнере, из папки src/main/resources/docker-compose.yaml выполнить:
``` text
docker-compose up -d
```
2. Собрать основное приложение, запустить

Ссылка на коллекцию Postman https://elements.getpostman.com/redirect?entityId=40143606-c9cc12bb-9b4b-40c7-a4ec-2785167a0a50&entityType=collection
