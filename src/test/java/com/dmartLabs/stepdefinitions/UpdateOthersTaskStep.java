package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.ConStants;
import com.dmartLabs.config.PropertyReader;
import gherkin.lexer.Da;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class UpdateOthersTaskStep {

RequestGenerator requestGenerator=new RequestGenerator();
    //UpdateOthersTask
    String ActualOthersStatus;
    String  ActualrefillerComments;
 public static    String  ActualOtherRefillId;
    JSONObject othersObj=new JSONObject();
    Response UpdateOthersResponse;


    //update task by refiller
    @When("user calls create UpdateOthersTask  endpoint")
    public void userCallsCreateUpdateOthersTaskEndpoint(DataTable dataTable) {
        Map<String, String> UpdateOthersTaskDT = dataTable.asMap(String.class, String.class);
        ActualOthersStatus=UpdateOthersTaskDT.get("status");
        ActualrefillerComments=UpdateOthersTaskDT.get("refillrComments");
        ActualOtherRefillId = CommonUtilities.getothersrefillId();

        othersObj.put("refillId",ActualOtherRefillId);
        othersObj.put("status",ActualOthersStatus);
        othersObj.put("refillrComments",ActualrefillerComments);
        UpdateOthersResponse = requestGenerator.getRequest(CommonUtilities.genericHeader(), othersObj).log().all()
                .when().put(PropertyReader.getProperty(ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH, "updateOtherTaskEndPoint"));
        //UpdateOthersResponse.then().log().all();
        ExtentReportManager.logJson("Response is " + UpdateOthersResponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " + UpdateOthersResponse.getStatusCode());
        long responseTime = UpdateOthersResponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response Time is " + responseTime + " ms");
        CommonUtilities.setResponseInstance(UpdateOthersResponse);

    }
    @And("verify that requestPayload is same as responsePayload in UpdateOthersTask")
    public void verifyThatRequestPayloadIsSameAsResponsePayloadInUpdateOthersTask() {
        String ExpectedUpdateOthersRefillId = UpdateOthersResponse.jsonPath().get("refillId");
        String ExpectedStatus = UpdateOthersResponse.jsonPath().get("status");
        String ExpectedrefillerComments = UpdateOthersResponse.jsonPath().get("refillrComments");


        if (ExpectedUpdateOthersRefillId.equals(ActualOtherRefillId)) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("ExpectedUpdateOthersRefillId is " + ExpectedUpdateOthersRefillId + " and the ActualOtherRefillId is " + ActualOtherRefillId);
            Assert.assertEquals(ExpectedUpdateOthersRefillId, ActualOtherRefillId);
            System.out.println(ExpectedUpdateOthersRefillId + " ===================>" + "ExpectedUpdateOthersRefillId validation successful");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("ExpectedUpdateOthersRefillId is " + ExpectedUpdateOthersRefillId + " and the actual ActualOtherRefillId is " + ActualOtherRefillId);

        }

        if (ExpectedStatus.equals(ActualOthersStatus)) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("ExpectedStatus is " + ExpectedStatus + " and the ActualOthersStatus is " + ActualOthersStatus);
            Assert.assertEquals(ExpectedStatus, ActualOthersStatus);
            System.out.println(ExpectedStatus + " ===================>" + "ExpectedStatus validation successful");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("ExpectedStatus  is " + ExpectedStatus + " and the ActualOthersStatus is " + ActualOthersStatus);
        }
        ExtentReportManager.logInfoDetails("ActualrefillerComments is"+"==========================>"+ActualrefillerComments);
        System.out.println(ActualrefillerComments + " ===================>" + "ActualrefillerComments validation successful");

    }

   //==================================================================================================================
    //update task by auditor
    @When("user calls create UpdateOthersTaskByRefiller  endpoint")
    public void userCallsCreateUpdateOthersTaskByRefillerEndpoint(DataTable dataTable) {
        Map<String, String> UpdateOthersTaskDT = dataTable.asMap(String.class, String.class);
        ActualOthersStatus=UpdateOthersTaskDT.get("status");
        ActualrefillerComments=UpdateOthersTaskDT.get("auditorComments");

        othersObj.put("refillId",ActualOtherRefillId);
        othersObj.put("status",ActualOthersStatus);
        othersObj.put("auditorComments",ActualrefillerComments);
        UpdateOthersResponse = requestGenerator.getRequest(CommonUtilities.genericHeader(), othersObj).log().all()
                .when().put(PropertyReader.getProperty(ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH, "updateOtherTaskEndPoint"));
        //UpdateOthersResponse.then().log().all();
        ExtentReportManager.logJson("Response is " + UpdateOthersResponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " + UpdateOthersResponse.getStatusCode());
        long responseTime = UpdateOthersResponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response Time is " + responseTime + " ms");
        CommonUtilities.setResponseInstance(UpdateOthersResponse);

    }




    @And("verify that requestPayload is same as responsePayload in UpdateOthersTaskByAuditor")
    public void verifyThatRequestPayloadIsSameAsResponsePayloadInUpdateOthersTaskByAuditor() {
        String ExpectedUpdateOthersRefillId = UpdateOthersResponse.jsonPath().get("refillId");
        String ExpectedStatus = UpdateOthersResponse.jsonPath().get("status");
        String ExpectedAuditorComments = UpdateOthersResponse.jsonPath().get("auditorComments");


        if (ExpectedUpdateOthersRefillId.equals(ActualOtherRefillId)) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("ExpectedUpdateOthersRefillId is " + ExpectedUpdateOthersRefillId + " and the ActualOtherRefillId is " + ActualOtherRefillId);
            Assert.assertEquals(ExpectedUpdateOthersRefillId, ActualOtherRefillId);
            System.out.println(ExpectedUpdateOthersRefillId + " ===================>" + "ExpectedUpdateOthersRefillId validation successful");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("ExpectedUpdateOthersRefillId is " + ExpectedUpdateOthersRefillId + " and the actual ActualOtherRefillId is " + ActualOtherRefillId);

        }

        if (ExpectedStatus.equals(ActualOthersStatus)) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("ExpectedStatus is " + ExpectedStatus + " and the ActualOthersStatus is " + ActualOthersStatus);
            Assert.assertEquals(ExpectedStatus, ActualOthersStatus);
            System.out.println(ExpectedStatus + " ===================>" + "ExpectedStatus validation successful");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("ExpectedStatus  is " + ExpectedStatus + " and the ActualOthersStatus is " + ActualOthersStatus);
        }
        ExtentReportManager.logInfoDetails("ActualrefillerComments is"+"==========================>"+ActualrefillerComments);

    }
}
