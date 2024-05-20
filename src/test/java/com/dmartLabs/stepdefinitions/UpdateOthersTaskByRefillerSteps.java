package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.GenricUtils;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.PropertyReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import static com.dmartLabs.config.ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH;

public class UpdateOthersTaskByRefillerSteps {

    private static LinkedHashMap<String, String> requestPayload = new LinkedHashMap<>();
    private Response response;

    @And("Get the refillId to update others task")
    public void getTheRefillIdToUpdateOthersTask() {
        ExtentReportManager.logInfoDetails("Get the refillId to update others task");
        requestPayload = new LinkedHashMap<>();
        requestPayload.put("refillId", CommonUtilities.getOthersTaskRefillId());
    }

    @And("Get the refillId to update others task {string}")
    public void getTheRefillIdToUpdateOthersTask(String refillId) {
        ExtentReportManager.logInfoDetails("Get the refillId to update others task");
        requestPayload = new LinkedHashMap<>();

        if (refillId.equals("null"))
            refillId = null;

        requestPayload.put("refillId", refillId);
    }

    @And("Give the status to update the others task {string}")
    public void giveTheStatusToUpdateTheOthersTask(String status) {
        ExtentReportManager.logInfoDetails("Give the status to update the others task");
        requestPayload.put("Status", status);
    }

    @And("Give the comments for updating others task {string}")
    public void giveTheCommentsForUpdatingOthersTask(String comments) {
        ExtentReportManager.logInfoDetails("Give the comments for updating others task");
        requestPayload.put("refillerComments", comments);
    }

    @And("Give the comments for updating others task by auditor {string}")
    public void giveTheCommentsForUpdatingOthersTaskByAuditor(String comments) {
        ExtentReportManager.logInfoDetails("Give the comments for updating others task by the auditor");
        requestPayload.put("auditorComments", comments);
    }

    @When("Requester calls the Update Others Task's endpoint to update others task")
    public void requesterCallsTheUpdateOthersTaskSEndpointToUpdateOthersTask() {
        ExtentReportManager.logInfoDetails("Requester calls the Update Others Task's endpoint to update others task");
        RequestSpecification requestSpecification = RequestGenerator.getRequest(CommonUtilities.genericHeader(), requestPayload).log().all();
        response = requestSpecification.put(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "UPDATE_OTHERS_TASK"));
        response.then().log().all();
        ExtentReportManager.logInfoDetails("Status Code for Updated Others Task is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Update Others Task Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time for Update Others Task is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }

    @And("Check the response time for the Others Task updated")
    public void checkTheResponseTimeForTheOthersTaskUpdated() {
        ExtentReportManager.logInfoDetails("Check the response time for the Others Task updated");
        GenericSteps.validateTheResponseTime(response.getTimeIn(TimeUnit.MILLISECONDS));
    }

    @And("Verify that others task is updated successfully")
    public void verifyThatOthersTaskIsUpdatedSuccessfully() {
        ExtentReportManager.logInfoDetails("Verify that others task is updated successfully");
        if (requestPayload.get("refillId").equals(response.jsonPath().get("refillId"))){
            ExtentReportManager.logPassDetails("refillId field is passed");
            ExtentReportManager.logInfoDetails("Expected refillId is "+requestPayload.get("refillId")+" and the Actual refillId is "+response.jsonPath().get("refillId"));
        } else {
            ExtentReportManager.logFailureDetails("refillId field is failed");
            ExtentReportManager.logInfoDetails("Expected refillId is "+requestPayload.get("refillId")+" but the Actual refillId is "+response.jsonPath().get("refillId"));
        }
        Assert.assertEquals(response.jsonPath().get("refillId"), requestPayload.get("refillId"));

        if (requestPayload.get("Status").equals(response.jsonPath().get("status"))){
            ExtentReportManager.logPassDetails("status field is passed");
            ExtentReportManager.logInfoDetails("Expected status is "+requestPayload.get("Status")+" and the Actual status is "+response.jsonPath().get("status"));
        } else {
            ExtentReportManager.logFailureDetails("status field is failed");
            ExtentReportManager.logInfoDetails("Expected status is "+requestPayload.get("status")+" but the Actual status is "+response.jsonPath().get("status"));
        }
        Assert.assertEquals(response.jsonPath().get("status"), requestPayload.get("Status"));

        if(requestPayload.get("refillerComments").equals(response.jsonPath().get("refillerComments"))){
            ExtentReportManager.logPassDetails("refillerComments field is passed");
            ExtentReportManager.logInfoDetails("Expected status is "+requestPayload.get("refillerComments")+" and the Actual refillerComments is "+response.jsonPath().get("refillerComments"));
        } else {
            ExtentReportManager.logFailureDetails("refillerComments field is failed");
            ExtentReportManager.logInfoDetails("Expected refillerComments is "+requestPayload.get("status")+" but the Actual refillerComments is "+response.jsonPath().get("status"));
        }
        Assert.assertEquals(response.jsonPath().get("refillerComments"), requestPayload.get("refillerComments"));

        if (CommonUtilities.getRequester().equals(response.jsonPath().getString("refilledBy"))){
            ExtentReportManager.logPassDetails("refilledBy field is passed");
            ExtentReportManager.logInfoDetails("Expected refilledBy is "+CommonUtilities.getRequester()+" and the Actual refilledBy is "+response.jsonPath().get("refilledBy"));
        } else {
            ExtentReportManager.logFailureDetails("refilledBy field is failed");
            ExtentReportManager.logInfoDetails("Expected refilledBy is "+CommonUtilities.getRequester()+" but the Actual refilledBy is "+response.jsonPath().get("refilledBy"));
        }
        Assert.assertEquals(response.jsonPath().getString("refilledBy"), CommonUtilities.getRequester());
    }

    @And("Verify that others task is updated successfully by Auditor")
    public void verifyThatOthersTaskIsUpdatedSuccessfullyByAuditor() {
        ExtentReportManager.logInfoDetails("Verify that others task is updated successfully by Auditor");
        if (requestPayload.get("refillId").equals(response.jsonPath().get("refillId"))){
            ExtentReportManager.logPassDetails("refillId field is passed");
            ExtentReportManager.logInfoDetails("Expected refillId is "+requestPayload.get("refillId")+" and the Actual refillId is "+response.jsonPath().get("refillId"));
        } else {
            ExtentReportManager.logFailureDetails("refillId field is failed");
            ExtentReportManager.logInfoDetails("Expected refillId is "+requestPayload.get("refillId")+" but the Actual refillId is "+response.jsonPath().get("refillId"));
        }
        Assert.assertEquals(response.jsonPath().get("refillId"), requestPayload.get("refillId"));

        if (requestPayload.get("Status").equals(response.jsonPath().get("status"))){
            ExtentReportManager.logPassDetails("status field is passed");
            ExtentReportManager.logInfoDetails("Expected status is "+requestPayload.get("Status")+" and the Actual status is "+response.jsonPath().get("status"));
        } else {
            ExtentReportManager.logFailureDetails("status field is failed");
            ExtentReportManager.logInfoDetails("Expected status is "+requestPayload.get("status")+" but the Actual status is "+response.jsonPath().get("status"));
        }
        Assert.assertEquals(response.jsonPath().get("status"), requestPayload.get("Status"));

        if(requestPayload.get("refillerComments").equals(response.jsonPath().get("refillerComments"))){
            ExtentReportManager.logPassDetails("refillerComments field is passed");
            ExtentReportManager.logInfoDetails("Expected status is "+requestPayload.get("refillerComments")+" and the Actual refillerComments is "+response.jsonPath().get("refillerComments"));
        } else {
            ExtentReportManager.logFailureDetails("refillerComments field is failed");
            ExtentReportManager.logInfoDetails("Expected refillerComments is "+requestPayload.get("status")+" but the Actual refillerComments is "+response.jsonPath().get("status"));
        }
        Assert.assertEquals(response.jsonPath().get("refillerComments"), requestPayload.get("refillerComments"));

        if (CommonUtilities.getRequester().equals(response.jsonPath().getString("auditedBy"))){
            ExtentReportManager.logPassDetails("auditedBy field is passed");
            ExtentReportManager.logInfoDetails("Expected auditedBy is "+CommonUtilities.getRequester()+" and the Actual auditedBy is "+response.jsonPath().get("auditedBy"));
        } else {
            ExtentReportManager.logFailureDetails("auditedBy field is failed");
            ExtentReportManager.logInfoDetails("Expected auditedBy is "+CommonUtilities.getRequester()+" but the Actual auditedBy is "+response.jsonPath().get("auditedBy"));
        }
        Assert.assertEquals(response.jsonPath().getString("auditedBy"), CommonUtilities.getRequester());
    }

}
