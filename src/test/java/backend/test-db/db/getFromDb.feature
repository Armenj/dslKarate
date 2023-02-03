@ignore
Feature: Проверка доступа к БД

  Background:
    * def config = { username: 'django', password: 'ahLa4eeshaeP', url: 'jdbc:postgresql://95.163.234.188:5432/demo', driverClassName: 'org.postgresql.Driver' }
    * def DbUtils = Java.type('utils.DbUtils')
    * def db = new DbUtils(config)

  @aircrafts-data=single-row
  Scenario: Получение одной строки из таблицы
    Given text query =
    """
    select * from aircrafts_data limit 1
    """
#    And replace query
#      | token | value |
#      | <id>  | id    |

    When def result = db.readRow(query)

  @aircrafts-data=one-row
  Scenario: Получить 1 запись из таблицы
    * def result = db.readRows('select aircraft_code from aircrafts_data limit 1')
    * print result

  @aircrafts-data=insert-all
  Scenario: Добавление данных в таблицу
    * def insertData = read('insert.js')
    * def sqlStatements = karate.repeat(10, insertData)
    * karate.forEach(sqlStatements, function(sql){ db.addRow(sql) })

  @aircrafts-data=delete-generated-rows
  Scenario: Получение одной строки из таблицы
    Given text query =
    """
    DELETE FROM aircrafts_data WHERE aircraft_code LIKE '78%'
    """
    When def result = db.deleteRows(query)
