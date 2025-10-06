@reqres @integration
Feature: Example Test

  Background:
    * url 'https://reqres.in/'
    * configure headers = { Content-Type: 'application/json', x-api-key: reqres-free-v1, Accept: 'application/json' }
    * def body = { customerIds: [ "FIX33"] }

  Scenario:  Негативный кейс. Вызов метода с граничным количеством параметров (customerIds>40 )
    * set body.customerIds = karate.repeat(41, function() { return '1234567890'; })
    Given request body
    When method post
    Then status 404
    * print response
    Then match response == 'Not found'

  Scenario: Get
    * path 'api/unknown'
    When method get
    Then status 200
    * def inst = response.data
    And match response == read('response/unknown.json')


  Scenario: Error
    Given url 'https://reqres.in/api/users?page=2'
    When method get
    Then status 200
    And match each $response.data[*].email contains 'reqres.in'

  Scenario: Post
    * path 'api/users'
    #В данном виде будет создан массив строк ["morf","morf","morf","morf","morf"]
    #Если надо создать просто создать строку заданной длины, то можно добавть .join('') в конце функции
    * def morpheus = karate.repeat(5, function() { return 'morf'; })
    Given request { name: '#(morpheus)', "job": "leader" }
    When method post
    Then status 201
    Then match response == read('response/success.json')

  Scenario: Post with repeat
#    * def generateString = function(str) { return karate.repeat(str, function() { return 'A'; }).join(''); }
#    * def invalidName = generateString(21)
#    * def invalidJob = generateString(11)

    * def invalidName = karate.repeat(21, function() { return 'A'; }).join('')
    * def invalidJob = karate.repeat(11, function() { return 'A'; }).join('')

    Given path 'api/users'
    And request { "name": "#(invalidName)", "job": "#(invalidJob)" }
    When method post
    Then status 201
    Then match response == read('response/success.json')


  Scenario Outline: DDT
    Given path 'api/users'
    And request { name: '<name>', job: '<job>' }
    When method post
    Then status 201
    And match response == read('response/success.json')

    Examples:
      | read('request/person.csv') |

  Scenario: Karate append
    * def arr1 = [ 'param1', 'param2' ]
    * def arr2 = [ 'param3', 'param4' ]
    * def final = karate.append(arr1, arr2)
    * match final == [ "param1", "param2", "param3", "param4" ]

  Scenario: Karate append
    * def arr1 = [{name: 'John', age: 30}]
    * def arr2 = karate.append(arr1, {name: 'Jane', age: 25})
    * match arr1 == [{name: 'John', age: 30}] #arr1 не изменился
    * match arr2 == [{name: 'John', age: 30}, {name: 'Jane', age: 25}] #arr2 содержит оба элемента
    * print arr2

  Scenario: Karate merge 1
    # Когда у нас есть базовая конфигурация или стандартный набор данных,
    # и мы хотим переопределить отдельные поля в зависимости от конкретных условий
    * def baseConfig = { url: 'https://api.example.com', timeout: 5000, retries: 3 }
    * def testConfig = { timeout: 10000 } // переопределяем только timeout
    * def finalConfig = karate.merge(baseConfig, testConfig)
    * print finalConfig

  Scenario: Karate merge 2
    #Либо можно сделать в 2 строки
    * def baseConfig = { url: 'https://api.example.com', timeout: 5000, retries: 3 }
    * def testConfig = karate.merge(baseConfig, { timeout: 10000 })
    * print baseConfig

  Scenario: Digits
    * def generateNumber = function(digits) { return Number(karate.repeat(digits, function() { return '9'; }).join('')); }
    * def invalidName = generateNumber(10)
    * def invalidJob = generateNumber(10)

    Given path 'api/users'
    And request { "name": "#(invalidName)", "job": "#(invalidJob)" }
    When method post
    Then status 201

  Scenario Outline: Create new user
    * path 'api/users'
    Given request { name: '<name>', job: '<job>' }
    When method post
    Then status 201
    And match response.name == '<name>'
    And match response.job == '<job>'
    And match response.id == '#string'
    And match response.createdAt != null

    Examples:
      | name     | job          |
      | Morpheus | Team lead    |
      | Iron Man | QA Engineer  |
      | Thor     | Data Analyst |

  Scenario Outline: Update users
    * path 'api/users/2'
    Given request { name: '<name>', job: '<job>' }
    When method put
    Then status 200
    And match response.name == '<name>'
    And match response.job == '<job>'
    And match response.updatedAt != null

    Examples:
      | name           | job          |
      | Hulk           | Team lead    |
      | Doctor Strange | QA Engineer  |
      | Black Panther  | Data Analyst |

  Scenario: Delete users
    * path 'api/users/2'
    When method delete
    Then status 204

  Scenario: Игнор синтетических атрибутов, т.к кол-во номеров >50  * def attributes = read('request/attributes.json')
    * def ctnsArray = karate.repeat(51, function() { return '9681690001'; })
    Given request { "ctns": '#(ctnsArray)', "attributes": '#(attributes)' }  When method post
    Then status 200
    * def data = response.data.number_info[0].attributes
    * def expected =
    """
    { "federalOperatorCode": null,
      "federalRegionCode": null,
      "ExternalOperatorId": null,
      "OperatorName": null,
      "ShortOperatorName": null }
    """
    # создание пустого объекта для хранения фактических значений параметров
    * def actual = {}
    # Инициализация 'actual' ключами из 'expected' со значениями null
    * karate.forEach(expected, function(key) { actual[key] = null })
    # перебор массива атрибутов data, и если имя атрибута присутствует в объекте expected,
    # обновляется соответствующее значение в объекте actual
    * karate.forEach(data, function(attr) { if (expected[attr.name] !== undefined) { actual[attr.name] = attr.value } })
    * match actual == expected



  Scenario: Преобразование элементов массива.
    # Допустим, у вас есть массив чисел, и вы хотите увеличить каждое число на 1
    * def numbers = [1, 2, 3, 4, 5]
    * def addOne = function(x) { return x + 1 }
    * def newNumbers = karate.map(numbers, addOne)
    * match newNumbers == [2, 3, 4, 5, 6]

  Scenario: Фильтрация свойств объекта. Допустим, у вас есть объект с несколькими свойствами,
    # и вы хотите создать новый объект, который содержит только некоторые из этих свойств
    * def user = { "name": "John", "age": 30, "country": "USA", "occupation": "engineer" }
    * def keys = [ "name", "age" ]
    * def newUser = {}
    * karate.forEach(keys, function(key) { newUser[key] = user[key] })
    * match newUser == { "name": "John", "age": 30 }
    * print newUser

  Scenario: Подсчет элементов, которые соответствуют определенному условию.
    # Допустим, у вас есть массив чисел, и вы хотите подсчитать, сколько из них больше 10
    * def numbers = [5, 12, 7, 15, 9, 20]
    * def count = 0
    * karate.forEach(numbers, function(x) { if (x > 10) { count++ } })
    * match count == 3