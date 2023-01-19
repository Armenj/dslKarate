@soapRequest
Feature: Test SOAP

  Scenario: Test soap interface
    Given url 'https://www.dataaccess.com/webservicesserver/NumberConversion.wso'
    When header Content-Type = 'text/xml'
    Then request body = read('request/body.xml')
    Then method post
    Then status 200
    # для динамически изменяемых значений, в результатах ответа, можно использовать рег. выражение
    * def bodyResponseRequest = read('response/success.xml')
    * xml resultBody = response
    * match resultBody == bodyResponseRequest
