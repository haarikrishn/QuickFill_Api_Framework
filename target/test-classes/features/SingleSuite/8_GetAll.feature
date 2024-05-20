Feature: Verify the functionality of GetAll  Endpoint

  Background: access token id by
    Given Send Generic data using Feature File
      | username | 675432     |
      | password | Sweety123@ |

  @GetAll
  Scenario: verify the functionality of GetAll
    And  pass pagenumber and pagesizeto getall endpoint
      | pageNumber | 1 |
      | pageSize   | 10 |
    When  user calls GetAll end point
    Then  verify that status code be equal to "200"
    And validate responsebody of GetAll Endpoint

    @GetSummary
    Scenario: verify the functionality of GetSummary
      When  user calls GetSummary end point
      Then  verify that status code be equal to "200"
      And validate responsebody of GetSummary Endpoint
