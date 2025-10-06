@reqres @integration
Feature: test2

  Background:
    * url 'https://reqres.in/api/users?page=2'

  Scenario: First example includes
    * def data = [{id: 1, avatar: 'avatar1.jpg'}, {id: 2, avatar: 'avatar2.jpg'}, {id: 3, avatar: 'avatar3.jpg'}]
    * eval data.forEach(function(d){ if(!d.avatar.includes('' + d.id)) karate.fail('ERROR: avatar should contain id')})

  Scenario: First example contains
    * def data = [{id: 1, avatar: 'avatar1.jpg'}, {id: 2, avatar: 'avatar2.jpg'}, {id: 3, avatar: 'avatar3.jpg'}]
    * eval data.forEach(function(d){if(d.avatar.contains('' + d.id)) karate.log('Pass'); else karate.fail('Fail')})

  Scenario: Second example
    When method get
    Then status 200
    * def data = response.data
#    * def data = [{id: 8, avatar: 'avatar1.jpg'}, {id: 2, avatar: 'avatar2.jpg'}, {id: 3, avatar: 'avatar3.jpg'}]
    * eval data.forEach(function(d){ if(!d.avatar.includes('' + d.id)) karate.log('avatar should contain id')})

  Scenario: Third example
    * def checkAvatarId = function(response) {eval(response.data.forEach(function(d){ if(!d.avatar.includes('' + d.id)) karate.log('avatar should contain id')}))}
    When method get
    Then status 200
    And match response.data == '#array'
    And checkAvatarId(response)

  Scenario: Fourth example
    When method get
    Then status 200
    * def data = response.data
    * def check =
  """
  function(data) {
  for (var i = 0; i < data.length; i++) {
  if (data[i].id != data[i].avatar.split('/').pop().split('-')[0]) {
  return false;
  }
  }
    return true;
  }
  """
  * eval check(data) == true

  Scenario: Fifth example
    When method get
    Then status 200
    * def checkAvatarId =
  """
  function(data) {
  for (let i = 0; i < data.length; i++) {
  let currentObject = data[i];
  let currentId = currentObject.id;
  let currentAvatar = currentObject.avatar;

  if (currentAvatar.includes(currentId.toString())) {
  console.log('Avatar for object with id ${currentId} is correct.');
  } else {
  console.log('Avatar for object with id ${currentId} is incorrect.');
  }
  }
  }
  """
  And checkAvatarId(response.data)

  Scenario: EndsWith example1
    # самый простой способ проверки того, что все email оканчиваются на reqres.in
    When method get
    Then status 200
    * def emails = $response.data[*].email
    Then match each emails contains 'reqres.in'

  Scenario: EndsWith example2
    When method get
    Then status 200
    * def emails = $response.data[*].email
    * def checkEmails = function(email) { return email.endsWith('reqrep.in') }
    * def result = karate.map(emails, checkEmails)

  Scenario: EndsWith example3
    When method get
    Then status 200
    * def emails = $response.data[*].email
    # тест падает, если email не оканчивается на reqres.in
    * eval emails.forEach(function(email){ if(!email.endsWith('reqres.in')) karate.fail(email + ' is not ending with reqres.in')})