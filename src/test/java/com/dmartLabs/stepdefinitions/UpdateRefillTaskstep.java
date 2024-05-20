package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
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

public class UpdateRefillTaskstep {


    JSONObject RefillObject=new JSONObject();
    RequestGenerator requestGenerator=new RequestGenerator();
    Response UpdateRefillTaskResponse;
    String ActualRefillStatus;
    String ActualRefillrefilledQuantity;
    public  static  String ActualRefilId;

    // UpdateRefillTask by refiller
    @When("user calls create UpdateRefillTask  endpoint")
    public void userCallsCreateUpdateRefillTaskEndpoint(DataTable dataTable) {

        Map<String, String> UpdateRefillTaskDT = dataTable.asMap(String.class, String.class);
        ActualRefillStatus=UpdateRefillTaskDT.get("status");
        ActualRefillrefilledQuantity=UpdateRefillTaskDT.get("refiledQuantity");
        String RefillComments=UpdateRefillTaskDT.get("refillrComments");
        ActualRefilId = CommonUtilities.getRefillTaskRefillId();
        RefillObject.put("refillId",ActualRefilId);
        RefillObject.put("status",ActualRefillStatus);
        RefillObject.put("refiledQuantity",Integer.parseInt(ActualRefillrefilledQuantity));
        RefillObject.put("refillrComments",RefillComments);
        UpdateRefillTaskResponse = requestGenerator.getRequest(CommonUtilities.genericHeader(), RefillObject).log().all()
                .when().put(PropertyReader.getProperty(ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH, "UpdateRefillTaskEndPoint"));
      //  UpdateRefillTaskResponse.then().log().all();
        ExtentReportManager.logJson("Response is " + UpdateRefillTaskResponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " + UpdateRefillTaskResponse.getStatusCode());
        long responseTime = UpdateRefillTaskResponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response Time is " + responseTime + " ms");
        CommonUtilities.setResponseInstance(UpdateRefillTaskResponse);

    }

    @And("verify that requestPayload is same as responsePayload in UpdateRefillTask Task")
    public void verifyThatRequestPayloadIsSameAsResponsePayloadInUpdateRefillTaskTask() {
        String ExpectedRefillId = UpdateRefillTaskResponse.jsonPath().get("refillId");
        String Expectedstatus = UpdateRefillTaskResponse.jsonPath().get("status");
      //  String ExpectedrefilledQuantity= UpdateRefillTaskResponse.jsonPath().get("refilledQuantity");

        //validating ExpectedRefillId
        if (ExpectedRefillId.equals(ActualRefilId)) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("ExpectedRefillId is " + ExpectedRefillId + " and the ActualRefilId is " + ActualRefilId);
            Assert.assertEquals(ExpectedRefillId, ActualRefilId);
            System.out.println(ExpectedRefillId + " ===================>" + "ExpectedRefillId validation successful");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("ExpectedRefillId is " + ExpectedRefillId + " and the actual ActualRefilId is " + ActualRefilId);

        }
        //validating site id
        if (Expectedstatus.equals(ActualRefillStatus)) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("Expectedstatus is " + Expectedstatus + " and the ActualRefillStatus is " + ActualRefillStatus);
            Assert.assertEquals(Expectedstatus, ActualRefillStatus);
            System.out.println(Expectedstatus + " ===================>" + "ActualRefillStatus validation successful");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("Expectedstatus  is " + Expectedstatus + " and the ActualSiteId is " + ActualRefillStatus);

        }

    }
//==============================================================================================================
    //update refill task by auditor



    @When("user calls create UpdateRefillTaskByAuditor  endpoint")
    public void userCallsCreateUpdateRefillTaskByAuditorEndpoint(DataTable dataTable ){
        Map<String, String> UpdateRefillTaskByAuditorDT = dataTable.asMap(String.class, String.class);
        ActualRefillStatus=UpdateRefillTaskByAuditorDT.get("status");
        ActualRefillrefilledQuantity=UpdateRefillTaskByAuditorDT.get("refiledQuantity");
        String RefillComments=UpdateRefillTaskByAuditorDT.get("AuditorComments");
        RefillObject.put("refillId",ActualRefilId);
        RefillObject.put("status",ActualRefillStatus);
        RefillObject.put("refiledQuantity",Integer.parseInt(ActualRefillrefilledQuantity));
        RefillObject.put("AuditorComments",RefillComments);
        UpdateRefillTaskResponse = requestGenerator.getRequest(CommonUtilities.genericHeader(), RefillObject).log().all()
                .when().put(PropertyReader.getProperty(ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH, "UpdateRefillTaskEndPoint"));
        //  UpdateRefillTaskResponse.then().log().all();
        ExtentReportManager.logJson("Response is " + UpdateRefillTaskResponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " + UpdateRefillTaskResponse.getStatusCode());
        long responseTime = UpdateRefillTaskResponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response Time is " + responseTime + " ms");
        CommonUtilities.setResponseInstance(UpdateRefillTaskResponse);


    }

    @And("verify that requestPayload is same as responsePayload in UpdateRefillTaskByAuditor")
    public void verifyThatRequestPayloadIsSameAsResponsePayloadInUpdateRefillTaskByAuditor() {

        String ExpectedRefillId = UpdateRefillTaskResponse.jsonPath().get("refillId");
        String Expectedstatus = UpdateRefillTaskResponse.jsonPath().get("status");
        //  String ExpectedrefilledQuantity= UpdateRefillTaskResponse.jsonPath().get("refilledQuantity");

        //validating ExpectedRefillId
        if (ExpectedRefillId.equals(ActualRefilId)) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("ExpectedRefillId is " + ExpectedRefillId + " and the ActualRefilId is " + ActualRefilId);
            Assert.assertEquals(ExpectedRefillId, ActualRefilId);
            System.out.println(ExpectedRefillId + " ===================>" + "ExpectedRefillId validation successful");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("ExpectedRefillId is " + ExpectedRefillId + " and the actual ActualRefilId is " + ActualRefilId);

        }
        //validating site id
        if (Expectedstatus.equals(ActualRefillStatus)) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("Expectedstatus is " + Expectedstatus + " and the ActualRefillStatus is " + ActualRefillStatus);
            Assert.assertEquals(Expectedstatus, ActualRefillStatus);
            System.out.println(Expectedstatus + " ===================>" + "ActualRefillStatus validation successful");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("Expectedstatus  is " + Expectedstatus + " and the ActualSiteId is " + ActualRefillStatus);

        }

    }
}
