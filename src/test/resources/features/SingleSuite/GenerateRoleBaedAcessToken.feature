Feature: Generate Access Token based on roles

  @RoleBasedTokenRunner
  Scenario Outline: Generate Access Token
    When Send username "<username>" and password "<password>" to get Access Token from Authentication Endpoint.
    Then verify that status code be equal to "200"
    Examples:
      | username | password    |
      | 6789014  | Dmart@12345 |
      | 828282   | Dmart@123   |
      | 929292   | Dmart@123   |
      | 727272   | Dmart@123   |

