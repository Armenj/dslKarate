Feature: test15

  Background:
    * url 'https://reqres.in/api/users?page=2'

  Scenario: Reqres1
    When method get
    Then status 200