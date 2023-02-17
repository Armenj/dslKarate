@ignore
Feature: Проверка доступа к БД

  Background:
    * def db = Java.type('utils.DbUtils').getInstance('agent-b2b')

  @aircrafts-data=single-row
  Scenario: Получение одной строки из таблицы
    Given text query =
    """
    select * from aircrafts_data limit 1
    """
#    And replace query
#      | token | value |
#      | <id>  | id    |

    And print query
    When def result = db.readRow(query)

  @aircrafts-data=one-row
  Scenario: Получить 1 запись из таблицы
    * def result = db.readRows('select aircraft_code from aircrafts_data limit 1')
    * print result