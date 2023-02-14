@db-feature @integration
Feature: Проверка доступа к БД

  Background:
    * callonce read('db/getFromDb.feature@delete-generated-rows')
    * callonce read('db/getFromDb.feature@aircrafts_data=insert-all')

    Scenario: Получение одной строки из таблицы
      * def getPostgesData = call read('db/getFromDb.feature@aircrafts-data=single-row')
      * print getPostgesData