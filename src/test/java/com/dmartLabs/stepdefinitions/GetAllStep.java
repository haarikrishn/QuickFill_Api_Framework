package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.JSONUtils;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.PropertyReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.dmartLabs.config.ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH;

public class GetAllStep {
    int ActualPgNum;
    int ActualPgsize;
    Map<String, String> GetallDT;
    JSONObject getallObj;
    Response GetAllResponse;

    @And("pass pagenumber and pagesizeto getall endpoint")
    public void passPagenumberAndPagesizetoGetallEndpoint(DataTable dataTable) {
    GetallDT = dataTable.asMap(String.class, String.class);
    ActualPgNum = Integer.parseInt(GetallDT.get("pageNumber"));
       ActualPgsize = Integer.parseInt(GetallDT.get("pageSize"));
      getallObj=new JSONObject();
        getallObj.put("pageNumber",ActualPgNum);
        getallObj.put("pageSize",ActualPgsize);
    }

    @When("user calls GetAll end point")
    public void userCallsGetAllEndPoint() {
 GetAllResponse = RequestGenerator.getRequest(CommonUtilities.genericHeader(), getallObj).log().all().
                when().post(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "GetAllEndpoint"));
       // GetAllResponse.then().log().all();
        ExtentReportManager.logJson("Response is " + GetAllResponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " + GetAllResponse.getStatusCode());
        long responseTime = GetAllResponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response Time is " + responseTime + " ms");
        CommonUtilities.setResponseInstance(GetAllResponse);
    }
    @And("validate responsebody of GetAll Endpoint")
    public void validateResponsebodyOfGetAllEndpoint() {

        int expectedpagesize = GetAllResponse.jsonPath().get("pageSize");
        int expectedpagenum = GetAllResponse.jsonPath().get("pageNumber");

        int ExpectedgetAllTotalcount = GetAllResponse.jsonPath().get("totalCount");

        List<Object> actrefillist = GetAllResponse.jsonPath().getList("refills");
        List<Object> actualADlist = GetAllResponse.jsonPath().getList("refills.articleDetails");
        int ActualTotallist = actrefillist.size() + actualADlist.size();

        System.out.println(ExpectedgetAllTotalcount+" "+"ExpectedgetAllTotalcount");
        System.out.println(ActualTotallist+" "+"ActualTotallist");
        if (expectedpagesize==ActualPgsize) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("expectedpagesize is " + expectedpagesize + " and the ActualPgsize is " + ActualPgsize);
            Assert.assertEquals(expectedpagesize, ActualPgsize);
            System.out.println(expectedpagesize + " ===================>" + "expectedpagesize validation successful");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("expectedpagesize is " + expectedpagesize + " and the ActualPgsize " + ActualPgsize);

        }

        if (expectedpagenum==ActualPgNum) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("expectedpagenum is " + expectedpagenum + " and the ActualPgNum is " + ActualPgNum);
            Assert.assertEquals(expectedpagenum, ActualPgNum);
            System.out.println(expectedpagenum + " ===================>" + "expectedpagenum validation successful");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("expectedpagenum is " + expectedpagenum + " and the ActualPgNum " + ActualPgNum);

        }
    }
    //        if (ExpectedgetAllTotalcount==ActualTotallist) {
//            ExtentReportManager.logPassDetails("Passed");
//            ExtentReportManager.logInfoDetails("ExpectedgetAllTotalcount is " + ExpectedgetAllTotalcount + " and the TotalCount is " + TotalCount);
//            Assert.assertEquals(ExpectedgetAllTotalcount, ActualTotallist);
//            System.out.println(ExpectedgetAllTotalcount + " ===================>" + "no.of records validation successful");
//
//        } else {
//            ExtentReportManager.logFailureDetails("Failed");
//            ExtentReportManager.logInfoDetails("ExpectedgetAllTotalcount is " + ExpectedgetAllTotalcount + " and the TotalCount " + TotalCount);
//
//        }
//===================================================================================================================
    //get summary
Response GetSummaryResponse;
    @When("user calls GetSummary end point")
    public void userCallsGetSummaryEndPoint() {

       GetSummaryResponse = RequestGenerator.getRequest(CommonUtilities.genericHeader()).log().all().
                when().get(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "GetSummaryEndpoint"));
        // GetAllResponse.then().log().all();
        ExtentReportManager.logJson("Response is " + GetSummaryResponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " + GetSummaryResponse.getStatusCode());
        long responseTime = GetSummaryResponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response Time is " + responseTime + " ms");
        CommonUtilities.setResponseInstance(GetSummaryResponse);
    }
//"openTasks": 1,
//        "completedTasks": 1,
//        "verifiedTasks": 1
    @And("validate responsebody of GetSummary Endpoint")
    public void validateResponsebodyOfGetSummaryEndpoint() {

int totalopentaskcount=0;
int totalcompletedTasks=0;
  int  totalverifiedTasks=0;

        List<Map<String,Integer>>listSummatries= GetSummaryResponse.jsonPath().get("Summaries");
        for(int i=0;i<listSummatries.size();i++) {
            Integer Allopentaskcount = listSummatries.get(i).get("openTasks");
            Integer allcompletedTasks = listSummatries.get(i).get("completedTasks");
            Integer allverifiedTasks = listSummatries.get(i).get("verifiedTasks");
            totalopentaskcount = Allopentaskcount + totalopentaskcount;
            totalcompletedTasks = totalcompletedTasks+allcompletedTasks;
            totalverifiedTasks=allverifiedTasks+totalverifiedTasks;
        }
        System.out.println(totalopentaskcount+"=========================>"+" "+"totalopentaskcount");
        System.out.println(totalcompletedTasks+"=========================>"+" "+"totalcompletedTasks");
        System.out.println(totalverifiedTasks+"=========================>"+" "+"totalverifiedTasks");
        ExtentReportManager.logInfoDetails(totalopentaskcount+"=========================>"+" "+"totalopentaskcount");
        ExtentReportManager.logInfoDetails(totalcompletedTasks+"=========================>"+" "+"totalcompletedTasks");
        ExtentReportManager.logInfoDetails(totalverifiedTasks+"=========================>"+" "+"totalverifiedTasks");
        //================================================================
        if(GetSummaryResponse.getBody()!=null)
        {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("Expected body is present for get all summary");
            System.out.println("all summary successfully validated");
        }
        else{
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("Expected body is not present");
        }


    }
}
