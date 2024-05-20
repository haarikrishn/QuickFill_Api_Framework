package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.GenricUtils;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.PropertyReader;
import com.dmartLabs.pojo.priceBoardSubpojo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.dmartLabs.config.ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH;

public class UpdatePriceboardTaskByRefellierSteps {

    private static LinkedHashMap<String, Object> requestPayload = new LinkedHashMap<>();
    private Response response;
    private static int flag;

    @And("Get the refillId to update priceboard task")
    public void getTheRefillIdToUpdatePriceboardTask() {
        ExtentReportManager.logInfoDetails("Get the refillId to update priceboard task");
        requestPayload.put("refillId", CommonUtilities.getPriceboardTaskRefillId());
    }

    @And("Get the refillId to update priceboard task {string}")
    public void getTheRefillIdToUpdatePriceboardTask(String refillId) {
        ExtentReportManager.logInfoDetails("Get the refillId to update priceboard task");
        if (refillId.equals("null"))
            refillId = null;
        requestPayload.put("refillId", refillId);
    }

    @And("Give the status to update the priceboard task {string}")
    public void giveTheStatusToUpdateThePriceboardTask(String status) {
        ExtentReportManager.logInfoDetails("Give the status to update the priceboard task");
        requestPayload.put("Status", status);
    }

    @And("Provide the priceboards to be updated")
    public void provideThePriceboardsToBeUpdated(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide the priceboards to be updated");
        List<Map<String, Object>> priceboards = new ArrayList<>(dataTable.asMaps(String.class, Object.class));
        List<Map<String, Object>> priceboards1 = new ArrayList<>();
        for (Map<String, Object> priceboard:priceboards){
            Map<String, Object> priceboard1 = new HashMap<>();
            if (priceboard.containsKey("priceBoard"))
               priceboard1.put("priceBoard", priceboard.get("priceBoard"));

            if (priceboard.containsKey("pbStatus")) {
                if (priceboard.get("pbStatus").equals("false"))
                    priceboard1.put("pbStatus", false);
                else if (priceboard.get("pbStatus").equals("true")) {
                    priceboard1.put("pbStatus", true);
                    flag++;
                }
            }
            priceboards1.add(priceboard1);
        }
        requestPayload.put("priceBoards", priceboards1);
    }

    @And("Provide the priceboards to be updated as null")
    public void provideThePriceboardsToBeUpdatedAsNull() {
        ExtentReportManager.logInfoDetails("Provide the priceboards to be updated as null");
        requestPayload.put("priceBoards", null);
    }

    @And("Provide the priceboards to be updated without priceBoard and pbStatus field")
    public void provideThePriceboardsToBeUpdatedWithoutPriceBoardAndPbStatusField() {
        ExtentReportManager.logInfoDetails("Provide the priceboards to be updated without priceBoard and pbStatus field");
        List<Map<String, Object>> priceboards = new ArrayList<>();
        requestPayload.put("priceBoards", priceboards);
    }

    @And("Give the comments for updating priceboard task {string}")
    public void giveTheCommentsForUpdatingPriceboardTask(String comments) {
        ExtentReportManager.logInfoDetails("Give the comments for updating priceboard task");
        requestPayload.put("refillerComments", comments);
    }

    @And("Give the comments for updating priceboard task by auditor {string}")
    public void giveTheCommentsForUpdatingPriceboardTaskByAuditor(String comments) {
        ExtentReportManager.logInfoDetails("Give the comments for updating priceboard task by auditor");
        requestPayload.put("auditorComments", comments);
    }

    @When("Requester calls the Update Priceboard Task's endpoint to update priceboard task")
    public void requesterCallsTheUpdatePriceboardTaskSEndpointToUpdatePriceboardTask() {
        ExtentReportManager.logInfoDetails("Requester calls the Update Priceboard Task's endpoint to update priceboard task");
        RequestSpecification requestSpecification = RequestGenerator.getRequest(CommonUtilities.genericHeader(), requestPayload).log().all();
        response = requestSpecification.put(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "UPDATE_PRICEBOARD_TASK"));
        response.then().log().all();
        ExtentReportManager.logInfoDetails("Status Code for Updated Priceboard Task is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Update Priceboard Task Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time for Update Priceboard Task is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }

    @And("Check the response time for the Priceboard Task updated")
    public void checkTheResponseTimeForThePriceboardTaskUpdated() {
        ExtentReportManager.logInfoDetails("Check the response time for the Priceboard Task updated");
        GenericSteps.validateTheResponseTime(response.getTimeIn(TimeUnit.MILLISECONDS));
    }

    @And("Verify that priceboard task is updated successfully")
    public void verifyThatPriceboardTaskIsUpdatedSuccessfully() {
        ExtentReportManager.logInfoDetails("Verify that priceboard task is updated successfully");
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

        List<Map<String, Object>> expectedPriceBoards = (List<Map<String, Object>>) requestPayload.get("priceBoards");
        List<Map<String, Object>> actualPriceBoards = (List<Map<String, Object>>) response.jsonPath().get("priceBoards");
        validatePriceboard(actualPriceBoards, expectedPriceBoards);

        if (CommonUtilities.getRequester().equals(response.jsonPath().getString("refilledBy"))){
            ExtentReportManager.logPassDetails("refilledBy field is passed");
            ExtentReportManager.logInfoDetails("Expected refilledBy is "+CommonUtilities.getRequester()+" and the Actual refilledBy is "+response.jsonPath().get("refilledBy"));
        } else {
            ExtentReportManager.logFailureDetails("refilledBy field is failed");
            ExtentReportManager.logInfoDetails("Expected refilledBy is "+CommonUtilities.getRequester()+" but the Actual refilledBy is "+response.jsonPath().get("refilledBy"));
        }
        Assert.assertEquals(response.jsonPath().getString("refilledBy"), CommonUtilities.getRequester());

        if (actualPriceBoards.size()-flag==0){
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

    @And("Verify that priceboard task is updated successfully by Auditor")
    public void verifyThatPriceboardTaskIsUpdatedSuccessfullyByAuditor() {
        ExtentReportManager.logInfoDetails("Verify that priceboard task is updated successfully by Auditor");
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

        List<Map<String, Object>> expectedPriceBoards = (List<Map<String, Object>>) requestPayload.get("priceBoards");
        List<Map<String, Object>> actualPriceBoards = (List<Map<String, Object>>) response.jsonPath().get("priceBoards");
        validatePriceboard(actualPriceBoards, expectedPriceBoards);

        if (CommonUtilities.getRequester().equals(response.jsonPath().getString("auditedBy"))){
            ExtentReportManager.logPassDetails("auditedBy field is passed");
            ExtentReportManager.logInfoDetails("Expected auditedBy is "+CommonUtilities.getRequester()+" and the Actual auditedBy is "+response.jsonPath().get("refilledBy"));
        } else {
            ExtentReportManager.logFailureDetails("auditedBy field is failed");
            ExtentReportManager.logInfoDetails("Expected auditedBy is "+CommonUtilities.getRequester()+" but the Actual auditedBy is "+response.jsonPath().get("refilledBy"));
        }
        Assert.assertEquals(response.jsonPath().getString("auditedBy"), CommonUtilities.getRequester());

        if (actualPriceBoards.size()-flag==0){
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

    private void validatePriceboard(List<Map<String, Object>> actualPriceBoards, List<Map<String, Object>> expectedPriceBoards) {
        for (int i=0;i< actualPriceBoards.size();i++) {
            Object actualPriceBoard = actualPriceBoards.get(i).get("priceBoard");
            Object actualPbStatus = actualPriceBoards.get(i).get("pbStatus");
            Object expectedPriceBoard = expectedPriceBoards.get(i).get("priceBoard");
            Object expectedPbStatus = expectedPriceBoards.get(i).get("pbStatus");
            if (actualPriceBoard.equals(expectedPriceBoard) && actualPbStatus==expectedPbStatus) {
                ExtentReportManager.logPassDetails("priceBoards field is Passed");
                ExtentReportManager.logInfoDetails("Expected priceBoard is " +expectedPriceBoard + " and the Actual priceBoard is " +actualPriceBoard);
                ExtentReportManager.logInfoDetails("Expected pbStatus is " +expectedPbStatus + " and the Actual pbStatus is " +actualPbStatus);
            }
            else{
                ExtentReportManager.logPassDetails("priceBoards field is Failed");
                ExtentReportManager.logInfoDetails("Expected priceBoard is " +expectedPriceBoard + " and the Actual priceBoard is " +actualPriceBoard);
                ExtentReportManager.logInfoDetails("Expected pbStatus is " +expectedPbStatus + " and the Actual pbStatus is " +actualPbStatus);
            }
            Assert.assertEquals(actualPriceBoard, expectedPriceBoard);
            Assert.assertEquals(actualPbStatus, expectedPbStatus);
        }
    }

    @When("Requester calls the Update Refill Task's endpoint to update priceboard task")
    public void requesterCallsTheUpdateRefillTaskSEndpointToUpdatePriceboardTask() {
        ExtentReportManager.logInfoDetails("Requester calls the Update Refill Task's endpoint to update priceboard task");
        RequestSpecification requestSpecification = RequestGenerator.getRequest(CommonUtilities.genericHeader(), requestPayload).log().all();
        response = requestSpecification.put(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "UPDATE_REFILL_TASK"));
        response.then().log().all();
        ExtentReportManager.logInfoDetails("Status Code for Updated Priceboard Task is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Update Priceboard Task Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time for Update Priceboard Task is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }

    @When("Requester calls the Update Others Task's endpoint to update priceboard task")
    public void requesterCallsTheUpdateOthersTaskSEndpointToUpdatePriceboardTask() {
        ExtentReportManager.logInfoDetails("Requester calls the Update Others Task's endpoint to update priceboard task");
        RequestSpecification requestSpecification = RequestGenerator.getRequest(CommonUtilities.genericHeader(), requestPayload).log().all();
        response = requestSpecification.put(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "UPDATE_OTHERS_TASK"));
        response.then().log().all();
        ExtentReportManager.logInfoDetails("Status Code for Updated Priceboard Task is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Update Priceboard Task Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time for Update Priceboard Task is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }
}
