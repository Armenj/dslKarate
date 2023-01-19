Feature: test1
  Background:
    * url 'https://swapi.dev/api/people'

  Scenario: Swapi
    When method get
    Then status 200
    Then match response == read('response/success.json')

    Scenario: Reqres
      Given url 'https://reqres.in/api/users?page=2'
      When method get
      Then status 200