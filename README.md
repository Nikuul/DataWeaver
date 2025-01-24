# DataWeaver

## Opis projektu

DataWeaver to aplikacja webowa napisana w Javie z wykorzystaniem frameworka Spring Boot. Aplikacja umożliwia zarządzanie użytkownikami poprzez interfejs webowy oraz REST API. Dane użytkowników są przechowywane w bazie danych MySQL uruchamianej za pomocą USBWebServer.

---

## Wymagania wstępne

Przed uruchomieniem projektu upewnij się, że posiadasz:

### 1. Środowisko Javy
- **Java 21** lub nowsza.

### 2. Narzędzia i środowisko developerskie
- **Maven** (do zarządzania zależnościami i budowy projektu).
- IDE, np. **Visual Studio Code**, **IntelliJ IDEA** lub **Eclipse**.

### 3. USBWebServer
- Pobierz i rozpakuj **USBWebServer**:
  - Oficjalna strona: [http://www.usbwebserver.net](http://www.usbwebserver.net).
  - Upewnij się, że MySQL działa w USBWebServer, a phpMyAdmin jest dostępny pod adresem `http://localhost/phpmyadmin`.

### 4. Konfiguracja bazy danych
- Utwórz bazę danych o nazwie `user_management` w phpMyAdmin.
- Skonfiguruj plik `application.properties`:
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/test
  spring.datasource.username=root
  spring.datasource.password=usbw
  spring.jpa.hibernate.ddl-auto=update
  ```

---

## Instrukcja uruchomienia

### 1. Klonowanie repozytorium

Sklonuj projekt na swój komputer:

### 2. Uruchomienie USBWebServer
- Uruchom USBWebServer i upewnij się, że:
  - MySQL działa.
  - phpMyAdmin jest dostępny pod adresem `http://localhost/phpmyadmin`.

### 3. Budowa projektu
- Otwórz projekt w wybranym IDE lub terminalu.

### 4. Uruchomienie aplikacji
- Uruchom aplikację za pomocą IDE lub w terminalu.

- Aplikacja będzie dostępna pod adresem: `http://localhost:8080`.

---

## Funkcjonalności

- **Interfejs webowy**:
  - Przeglądanie listy użytkowników.
  - Dodawanie nowych użytkowników.
  - Edytowanie istniejących użytkowników.
  - Usuwanie użytkowników.
- **REST API**:
  - Pobieranie listy użytkowników: `GET /api/users`
  - Dodawanie użytkownika: `POST /api/users`

---

## Struktura projektu

- **`model`**: Klasy encji JPA (np. `User`).
- **`repository`**: Interfejsy JPA do operacji na bazie danych.
- **`controller`**: Warstwa obsługująca żądania REST API i widoki.
- **`config`**: Konfiguracja aplikacji (np. `RestTemplateConfig`).
- **Plik `application.properties`**: Konfiguracja bazy danych i aplikacji.

---

## Technologie

- **Spring Boot 3.4.1**
- **Thymeleaf** (silnik szablonów HTML)
- **JPA/Hibernate** (do operacji na bazie danych)
- **MySQL** (system baz danych)
- **USBWebServer** (lokalne środowisko dla bazy danych MySQL i phpMyAdmin)

---

## Uwagi

Jeśli masz problemy z uruchomieniem aplikacji lub pojawiają się błędy, upewnij się, że:
1. USBWebServer działa i MySQL jest uruchomiony.
2. Dane w `application.properties` są poprawne.
3. Wszystkie zależności zostały poprawnie zainstalowane.
