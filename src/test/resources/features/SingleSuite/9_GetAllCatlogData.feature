Feature: Verify the functionality of GetAllCatlog  Endpoint
  Background: access token id by
    Given Send Generic data using Feature File
      | username | 675432     |
      | password | Sweety123@ |
#    scenario is fetching all records and updated MAxMilliseconds from catlog and change values in data base
#    and pass recent Max milliseconds and validate data
  @GetAllCatlogData
  Scenario: verify the functionality of GetAllCatlog
    When  user calls GetAllCatlogData end point
    Then  verify that status code be equal to "200"
    And verify that GetAllCatlogData ResponseBody

  @GetCatlogDataBySecondMaxMilliSeconds
  Scenario: verify the functionality of GetCatlogDataBySecondMaxMilliSeconds
    And  pass the SecondMaxMilliSeconds to GetCatlogDataByMaxMilliSeconds
      | pageNumber | 1 |
      | pageSize   | 5 |
    When  user calls GetCatlogDataByMaxMilliSeconds end point
    Then  verify that status code be equal to "200"
    And  verify that GetCatlogDataByMaxMilliSeconds ResponseBody

  @GetCatlogDataByFirstMaxMilliSeconds
  Scenario: verify the functionality of GetCatlogDataByFirstMaxMilliSeconds
    And  pass the data to GetCatlogDataByMaxMilliSeconds
      | pageNumber | 1 |
      | pageSize   | 5 |
    When  user calls GetCatlogDataByMaxMilliSeconds end point
    Then  verify that status code be equal to "200"
    And  verify that GetCatlogDataByMaxMilliSeconds ResponseBody

  @GetCatlogDataByEan
  Scenario: verify the functionality of GetCatlogDataByEan
    And  pass the data to GetCatlogDataByEan
    When  user calls GetCatlogDataByEan end point
    Then  verify that status code be equal to "200"
    And Validate responsebody of catlog ean

  @GetCatlogDataByOldEan
  Scenario: verify the functionality of GetCatlogDataByOldEan
    And  pass the data to GetCatlogDataByOldEan
      | ean | 8901030962226 |
    When  user calls GetCatlogDataByEan end point
    Then  verify that status code be equal to "200"
    And Validate responsebody of catlog ean





















#  queryParams = new HashMap<>();
#  queryParams.put("dlvNumber", CommonUtilities.getDeliveryNumber());
#  queryParams.put("truckType", CommonUtilities.getTruckType());