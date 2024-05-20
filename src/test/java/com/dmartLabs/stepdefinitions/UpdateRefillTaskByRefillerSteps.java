package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.PropertyReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import static com.dmartLabs.config.ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH;
import static com.dmartLabs.config.ConStants.JSON_SCHEMA_VALIDATION_PATH;

public class UpdateRefillTaskByRefillerSteps {

    private static LinkedHashMap<String,Object> requestPayload = new LinkedHashMap<>();
    private Response response;


    @And("Get the refillId to update refill task")
    public void getTheRefillIdToUpdateRefillTask() {
        ExtentReportManager.logInfoDetails("Get the refillId to update refill task");
        requestPayload = new LinkedHashMap<>();
        requestPayload.put("refillId", CommonUtilities.getRefillTaskRefillId());
    }

    @And("Give the status to update the refill task {string}")
    public void giveTheStatusToUpdateTheRefillTask(String status) {
        ExtentReportManager.logInfoDetails("Give the status to update the refill task");
        if (status.equals("null"))
            status = null;
        requestPayload.put("Status", status);
    }

    @And("Get the refillId to update refill task {string}")
    public void getTheRefillIdToUpdateRefillTask(String refillID) {
        ExtentReportManager.logInfoDetails("Get the refillId to update refill task");
        if (refillID.equals("null"))
            refillID = null;

        requestPayload.put("refillId", refillID);
    }

    @And("Provide the refilled quantity for updating refill task {int}")
    public void provideTheRefilledQuantityForUpdatingRefillTask(int refilledQuantity) {
        ExtentReportManager.logInfoDetails("Provide the refill quantity for updating refill task");
        requestPayload.put("refilledQuantity", refilledQuantity);
    }

    @And("Give the comments for updating refill task {string}")
    public void giveTheCommentsForUpdatingRefillTask(String refillerComments) {
        ExtentReportManager.logInfoDetails("Give the comments for updating refill task");
        requestPayload.put("refillerComments", refillerComments);
    }

    @And("Give the comments for updating refill task by auditor {string}")
    public void giveTheCommentsForUpdatingRefillTaskByAuditor(String refillerComments) {
        ExtentReportManager.logInfoDetails("Give the comments for updating refill task by auditor");
        requestPayload.put("auditorComments", refillerComments);
    }


    @When("Requester calls the Update Refill Task's endpoint to update refill task")
    public void requesterCallsTheUpdateRefillTaskSEndpointToUpdateRefillTask() {
        ExtentReportManager.logInfoDetails("Requester calls the Update Refill Task's endpoint to update refill task");
        RequestSpecification requestSpecification = RequestGenerator.getRequest(CommonUtilities.genericHeader(), requestPayload).log().all();
        response = requestSpecification.put(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "UPDATE_REFILL_TASK"));
        response.then().log().all();
        ExtentReportManager.logInfoDetails("Status Code for Updated Refill Task is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Update Refill Task Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time for Update Refill Task is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }

    @And("Check the response time for the Refill Task updated")
    public void checkTheResponseTimeForTheRefillTaskUpdated() {
        ExtentReportManager.logInfoDetails("Check the response time for the Refill Task updated");
        GenericSteps.validateTheResponseTime(response.getTimeIn(TimeUnit.MILLISECONDS));
    }

    @And("Verify that refill task is updated successfully")
    public void verifyThatRefillTaskIsUpdatedSuccessfully() {
        ExtentReportManager.logInfoDetails("Verify that refill task is updated successfully");
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

        if (CommonUtilities.getRequester().equals(response.jsonPath().getString("refilledBy"))){
            ExtentReportManager.logPassDetails("refilledBy field is passed");
            ExtentReportManager.logInfoDetails("Expected refilledBy is "+CommonUtilities.getRequester()+" and the Actual refilledBy is "+response.jsonPath().get("refilledBy"));
        } else {
            ExtentReportManager.logFailureDetails("refilledBy field is failed");
            ExtentReportManager.logInfoDetails("Expected refilledBy is "+CommonUtilities.getRequester()+" but the Actual refilledBy is "+response.jsonPath().get("refilledBy"));
        }
        Assert.assertEquals(response.jsonPath().getString("refilledBy"), CommonUtilities.getRequester());

        if (requestPayload.get("refilledQuantity").equals(response.jsonPath().get("refilledQuantity"))){
            ExtentReportManager.logPassDetails("refilledQuantity field is passed");
            ExtentReportManager.logInfoDetails("Expected refilledQuantity is "+requestPayload.get("refilledQuantity")+" and the Actual refilledQuantity is "+response.jsonPath().get("refilledQuantity"));
        } else {
            ExtentReportManager.logFailureDetails("refilledQuantity field is failed");
            ExtentReportManager.logInfoDetails("Expected refilledQuantity is "+requestPayload.get("refilledQuantity")+" but the Actual status is "+response.jsonPath().get("refilledQuantity"));
        }
        Assert.assertEquals(response.jsonPath().get("refilledQuantity"), requestPayload.get("refilledQuantity"));

        if (CommonUtilities.getRequestedQuantity()-(int)requestPayload.get("refilledQuantity")==0){
            if (response.jsonPath().getString("action").equals("COMPLETED")){
                ExtentReportManager.logPassDetails("action field is passed");
                ExtentReportManager.logInfoDetails("Expected action field is COMPLETED and the Actual action field is "+response.jsonPath().getString("action"));
            } else {
                ExtentReportManager.logPassDetails("action field is failed");
                ExtentReportManager.logInfoDetails("Expected action field is COMPLETED but the Actual action field is "+response.jsonPath().getString("action"));
            }
            Assert.assertEquals(response.jsonPath().getString("action"), "COMPLETED");
        } else {
            if (response.jsonPath().getString("action").equals("PARTIAL")){
                ExtentReportManager.logPassDetails("action field is passed");
                ExtentReportManager.logInfoDetails("Expected action field is PARTIAL and the Actual action field is "+response.jsonPath().getString("action"));
            } else {
                ExtentReportManager.logPassDetails("action field is failed");
                ExtentReportManager.logInfoDetails("Expected action field is PARTIAL but the Actual action field is "+response.jsonPath().getString("action"));
            }
            Assert.assertEquals(response.jsonPath().getString("action"), "PARTIAL");
        }
    }

    @And("Verify that refill task is updated successfully by Auditor")
    public void verifyThatRefillTaskIsUpdatedSuccessfullyByAuditor() {
        ExtentReportManager.logInfoDetails("Verify that refill task is updated successfully by Auditor");
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

        if (CommonUtilities.getRequester().equals(response.jsonPath().getString("auditedBy"))){
            ExtentReportManager.logPassDetails("auditedBy field is passed");
            ExtentReportManager.logInfoDetails("Expected auditedBy is "+CommonUtilities.getRequester()+" and the Actual auditedBy is "+response.jsonPath().get("auditedBy"));
        } else {
            ExtentReportManager.logFailureDetails("auditedBy field is failed");
            ExtentReportManager.logInfoDetails("Expected auditedBy is "+CommonUtilities.getRequester()+" but the Actual auditedBy is "+response.jsonPath().get("auditedBy"));
        }
        Assert.assertEquals(response.jsonPath().getString("auditedBy"), CommonUtilities.getRequester());

        if (requestPayload.get("refilledQuantity").equals(response.jsonPath().get("refilledQuantity"))){
            ExtentReportManager.logPassDetails("refilledQuantity field is passed");
            ExtentReportManager.logInfoDetails("Expected refilledQuantity is "+requestPayload.get("refilledQuantity")+" and the Actual refilledQuantity is "+response.jsonPath().get("refilledQuantity"));
        } else {
            ExtentReportManager.logFailureDetails("refilledQuantity field is failed");
            ExtentReportManager.logInfoDetails("Expected refilledQuantity is "+requestPayload.get("refilledQuantity")+" but the Actual status is "+response.jsonPath().get("refilledQuantity"));
        }
        Assert.assertEquals(response.jsonPath().get("refilledQuantity"), requestPayload.get("refilledQuantity"));

        if (CommonUtilities.getRequestedQuantity()-(int)requestPayload.get("refilledQuantity")==0){
            if (response.jsonPath().getString("action").equals("COMPLETED")){
                ExtentReportManager.logPassDetails("action field is passed");
                ExtentReportManager.logInfoDetails("Expected action field is COMPLETED and the Actual action field is "+response.jsonPath().getString("action"));
            } else {
                ExtentReportManager.logPassDetails("action field is failed");
                ExtentReportManager.logInfoDetails("Expected action field is COMPLETED but the Actual action field is "+response.jsonPath().getString("action"));
            }
            Assert.assertEquals(response.jsonPath().getString("action"), "COMPLETED");
        } else {
            if (response.jsonPath().getString("action").equals("PARTIAL")){
                ExtentReportManager.logPassDetails("action field is passed");
                ExtentReportManager.logInfoDetails("Expected action field is PARTIAL and the Actual action field is "+response.jsonPath().getString("action"));
            } else {
                ExtentReportManager.logPassDetails("action field is failed");
                ExtentReportManager.logInfoDetails("Expected action field is PARTIAL but the Actual action field is "+response.jsonPath().getString("action"));
            }
            Assert.assertEquals(response.jsonPath().getString("action"), "PARTIAL");
        }
    }

}
