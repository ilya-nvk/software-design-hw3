# HW-4

Сервис по работе с пользователями работает на порте 8080

Сервис по работе с заказами работает на порте 8081

У каждого сервиса своя БД



Функционал приложения:

1. Регистрация нового пользователя: POST http://localhost:8080/auth-app/user/register
2. Аутентификация пользователя: POST http://localhost:8080/auth-app/user/login
3. Получение информации о пользователе по JWT-токену (нужно указать BearerToken в заголовках): GET http://localhost:8080/auth-app/user
4. Создание заказа для пользователей с использованием JWT-токена: POST http://localhost:8081/order-app/order
5. Получение данных о заказе по ID: http://localhost:8081/order-app/order/{id}
6. Механизм обработки заказа внутри сервиса
7. Добавление новых станций (для удобства): POST http://localhost:8081/order-app/order/station



### Используемые технологии:
- Spring Boot Starter Parent (версия: 3.2.5) - базовый набор зависимостей для Spring Boot проекта
- Spring Boot Starter Data JPA - упрощает работу с базой данных через JPA
- Spring Boot Starter Security - обеспечивает аутентификацию и авторизацию пользователей
- Spring Boot Starter Web - обеспечивает возможности веб-разработки в Spring Boot
- Flyway Core - инструмент для управления и миграции базы данных
- PostgreSQL (scope: runtime) - база данных PostgreSQL для хранения и управления данными
- jjwt (версия: 0.9.1) - библиотека для создания и работы с JWT-токенами
- Spring Boot Starter Validation - обеспечивает валидацию входных данных в Spring Boot приложениях
- OkHttp - клиент для выполнения HTTP-запросов в Java/Kotlin
- Gson - библиотека для работы с JSON-данными, включая десериализацию