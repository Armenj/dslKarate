Feature: Automate sequential API calls on reqres.in

	Background:
    # Устанавливаем базовый URL
		* url 'https://reqres.in/api'
		* configure headers = { 'Content-Type': 'application/xml', 'x-api-key': 'reqres-free-v1' }

	@regres-automated-tests
	Scenario: Automated API calls sequence on reqres.in

    # Шаг 1: Создаем нового пользователя
		Given path 'users'
		And request { "name": "morpheus", "job": "leader" }
		When method POST
		Then status 201
		And match response.id != null
		* def userId = response.id
		* print 'User created with ID:', userId

    # Шаг 2: Получаем список пользователей и сохраняем ID последнего
		Given path 'users?page=2'
		When method GET
		Then status 200
		And match response.data[0].id != null
		* def lastUserId = response.data[response.data.length - 1].id
		* print 'Last user ID on page 2:', lastUserId

    # Шаг 3: Запрашиваем данные последнего пользователя
		Given path 'users', lastUserId
		When method GET
		Then status 200
		And match response.data.id == lastUserId
		* print 'Fetched data for last user ID:', lastUserId

    # Шаг 4: Обновляем данные для созданного пользователя
		Given path 'users', userId
		And request { "name": "morpheus", "job": "zion resident" }
		When method PUT
		Then status 200
		And match response.job == 'zion resident'
		* print 'User updated to job:', response.job

    # Шаг 5: Удаляем созданного пользователя
		Given path 'users', userId
		When method DELETE
		Then status 204
		* print 'User with ID:', userId, 'successfully deleted'

    # Шаг 6: Проверяем, что пользователь удален
		Given path 'users', userId
		When method GET
		Then status 404
		* print 'User with ID:', userId, 'not found after deletion'


		Scenario: Успешная регистрация нового пользователя
			Given path 'register'
			And request { "email": "eve.holt@reqres.in", "password": "pistol" }
			When method POST
			Then status 200
			And match response.id != null
			And match response.token != null
			* def userId = response.id
			* def authToken = response.token
			* print 'User registered with ID:', userId, 'and token:', authToken

			Scenario: Успешный вход пользователя
				Given path 'login'
				And request { "email": "eve.holt@reqres.in", "password": "pistol" }
				When method POST
				Then status 200
				And match response.token == authToken
				* print 'User logged in successfully with token:', response.token

			Scenario: Создание нового пользователя
				Given path 'users'
				And request { "name": "morpheus", "job": "leader" }
				When method POST
				Then status 201
				And match response.id != null
				* def newUserId = response.id
				* print 'New user created with ID:', newUserId

#				Given path 'users', newUserId
#				When method GET
#				Then status 200
#				And match response.data.id == newUserId
#				* print 'Fetched data for new user ID:', newUserId