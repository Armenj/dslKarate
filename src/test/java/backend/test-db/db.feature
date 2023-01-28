@db-feature @integration
Feature: Проверка доступа к БД

  Background:

    Scenario: Получение одной строки из таблицы
      * def getPostgesData = call read('db/getFromDb.feature@aircrafts-data=single-row')
      * print getPostgesData