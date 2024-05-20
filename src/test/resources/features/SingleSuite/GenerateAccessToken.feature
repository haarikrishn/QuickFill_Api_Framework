Feature: Generate Access Token

  Background: access token id by
    Given Send Generic data using Feature File
      | username | 979797      |
      | password | Dmart@12345 |

  @GenerateToken
  Scenario: Generate Access Token
    When Send username and password to get accessToken
    Then verify that status code be equal to "200"