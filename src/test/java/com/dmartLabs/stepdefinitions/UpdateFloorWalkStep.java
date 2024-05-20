package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.GenricUtils;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.ConStants;
import com.dmartLabs.config.PropertyReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class UpdateFloorWalkStep {

 RequestGenerator requestGenerator=new RequestGenerator();
 Response UpdateFWResponse;
 String ActualFWstatus;
   //Updatefloorwalk
   String ActualFWId;
    @When("user calls create Updatefloorwalk endpoint")
    public void userCallsCreateUpdatefloorwalkEndpoint(DataTable dataTable) {
     Map<String, String> UpdateFloorwalkDT = dataTable.asMap(String.class, String.class);
     JSONObject UpdateFWjsonObject=new JSONObject();
      ActualFWId = CommonUtilities.getFloorWalkId();
     UpdateFWjsonObject.put("floor_walk_id",ActualFWId);
     UpdateFWjsonObject.put("completedAt", GenricUtils.getUTC_Format_CurrentDateTime());
      ActualFWstatus = UpdateFloorwalkDT.get("status");
     UpdateFWjsonObject.put("status",UpdateFloorwalkDT.get("status"));

      UpdateFWResponse = requestGenerator.getRequest(CommonUtilities.genericHeader(), UpdateFWjsonObject).log().all()
             .when().put(PropertyReader.getProperty(ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH, "UpdateFloorWalkEndpoint"));
     //UpdateFWResponse.then().log().all();
     ExtentReportManager.logJson("Response is " + UpdateFWResponse.getBody().prettyPrint());
     ExtentReportManager.logInfoDetails("Response status code is " + UpdateFWResponse.getStatusCode());
     long responseTime = UpdateFWResponse.getTimeIn(TimeUnit.MILLISECONDS);
     ExtentReportManager.logInfoDetails("Response Time is " + responseTime + " ms");
     CommonUtilities.setResponseInstance(UpdateFWResponse);

    }

    @And("verify that requestPayload is same as responsePayload in Updatefloorwalk")
    public void verifyThatRequestPayloadIsSameAsResponsePayloadInUpdatefloorwalk() {

     String ExpectedFwId = UpdateFWResponse.jsonPath().get("floor_walk_id");
     String ExpectedFWStatus = UpdateFWResponse.jsonPath().get("status");


     if (ExpectedFwId.equals(ActualFWId)) {
      ExtentReportManager.logPassDetails("Passed");
      ExtentReportManager.logInfoDetails("ExpectedFwId is " + ExpectedFwId + " and the ActualFWId is " + ActualFWId);
      Assert.assertEquals(ExpectedFwId, ActualFWId);
      System.out.println(ExpectedFwId + " ===================>" + "ExpectedFwId validation successful");

     } else {
      ExtentReportManager.logFailureDetails("Failed");
      ExtentReportManager.logInfoDetails("ExpectedFwId is " + ExpectedFwId + " and the ActualFWIdis " + ActualFWId);

     }
     if (ExpectedFWStatus.equals(ActualFWstatus)) {
      ExtentReportManager.logPassDetails("Passed");
      ExtentReportManager.logInfoDetails("ExpectedFWStatus is " + ExpectedFWStatus + " and the ActualFWstatus is " + ActualFWstatus);
      Assert.assertEquals(ExpectedFWStatus, ActualFWstatus);
      System.out.println(ExpectedFWStatus + " ===================>" + "ExpectedFWStatus validation successful");

     } else {
      ExtentReportManager.logFailureDetails("Failed");
      ExtentReportManager.logInfoDetails("ExpectedFWStatus is " + ExpectedFWStatus + " and the ActualFWstatus " + ActualFWstatus);
     }
    }


}