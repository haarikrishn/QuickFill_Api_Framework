package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExcelUtils;
import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.PropertyReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.dmartLabs.config.ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH;

public class UpdateRefillTaskSteps_v1 {

//    private static LinkedHashMap<String, Object> requestPayload = new LinkedHashMap<>();
    private static List<LinkedHashMap<String, Object>> requestPayload = new ArrayList<>();
    private static LinkedHashMap<String, Object> taskDetails = new LinkedHashMap<>();
    private static ExcelUtils excelUtils;
    private Response response;

    @And("Give the Excel file to get the request payload for updating Refill task {string}")
    public void giveTheExcelFileToGetTheRequestPayloadForUpdatingRefillTask(String excelFile) {
        ExtentReportManager.logInfoDetails("Give the Excel file to get the request payload for updating Refill task");
        excelUtils = new ExcelUtils(excelFile);
    }

    @And("Give the sheet name {string} to get all the Refill Task to be updated")
    public void giveTheSheetNameAndTheNumberOfTasksToBeUpdated(String sheetName) {
        ExtentReportManager.logInfoDetails("Give the sheet name to get all the Refill Task to be updated");
        int size = CommonUtilities.getRefillTask_v1_RefillIds().size();
//        System.out.println("refillId size in updateRefillTaskv3 =============> "+CommonUtilities.getRefillTask_v1_RefillIds().size());
//        System.out.println("refillIds in updateRefillTaskv3 ==================> "+CommonUtilities.getRefillTask_v1_RefillIds());
        requestPayload = excelUtils.updateRefillTask_v1(sheetName, CommonUtilities.getRefillTask_v1_RefillIds().size());
        for (int i=0; i<CommonUtilities.getRefillTask_v1_RefillIds().size(); i++){
            requestPayload.get(i).put("taskId", CommonUtilities.getRefillTask_v1_RefillIds().get(i));
        }
    }

    @And("Get the refillId to update refill task from v1 endpoint")
    public void getTheRefillIdToUpdateRefillTask() {
        ExtentReportManager.logInfoDetails("Get the taskId to update refill task from v1 endpoint");
        taskDetails = new LinkedHashMap<>();
        taskDetails.put("taskId", CommonUtilities.getRefillTaskRefillId());
    }

    @And("Give the status to update the refill task {string} from v1 endpoint")
    public void giveTheStatusToUpdateTheRefillTask(String status) {
        ExtentReportManager.logInfoDetails("Give the status to update the refill task from v1 endpoint");
        if (status.equals("null"))
            status = null;
        taskDetails.put("Status", status);
    }

    @And("Get the refillId to update refill task {string} from v1 endpoint")
    public void getTheRefillIdToUpdateRefillTask(String taskId) {
        ExtentReportManager.logInfoDetails("Get the refillId to update refill task from v1 endpoint");
        taskDetails = new LinkedHashMap<>();
        if (taskId.equals("null"))
            taskId = null;

        taskDetails.put("taskId", taskId);
    }

    @And("Provide the refilled quantity for updating refill task {int} from v1 endpoint")
    public void provideTheRefilledQuantityForUpdatingRefillTask(int refilledQuantity) {
        ExtentReportManager.logInfoDetails("Provide the refill quantity for updating refill task from v1 endpoint");
        taskDetails.put("refilledQuantity", refilledQuantity);
    }

    @And("Give the comments for updating refill task from v1 endpoint {string}")
    public void giveTheCommentsForUpdatingRefillTask(String refillerComments) {
        ExtentReportManager.logInfoDetails("Give the comments for updating refill task from v1 endpoint");
        taskDetails.put("refillerComments", refillerComments);
    }

    @And("Give the comments for updating refill task by auditor from v1 endpoint {string}")
    public void giveTheCommentsForUpdatingRefillTaskByAuditor(String refillerComments) {
        ExtentReportManager.logInfoDetails("Give the comments for updating refill task by auditor from v1 endpoint");
        taskDetails.put("auditorComments", refillerComments);
    }

    @When("Requester calls the Update Refill Task's v1 endpoint to update list of all refill task")
    public void requesterCallsTheUpdateRefillTaskSVEndpointToUpdateListOfAllRefillTask() {
        ExtentReportManager.logInfoDetails("Requester calls the Update Refill Task's v1 endpoint to update list of all refill task");
        RequestSpecification requestSpecification = RequestGenerator.getRequest(CommonUtilities.genericHeader(), requestPayload).log().all();
        response = requestSpecification.put(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "UPDATE_REFILL_TASK_v1"));
        response.then().log().all();
        ExtentReportManager.logInfoDetails("Status Code for Updated Refill Task is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Update Refill Task Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time for Update Refill Task is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }

    @When("Requester calls the Update Refill Task's v1 endpoint to update refill task")
    public void requesterCallsTheUpdateRefillTaskSEndpointToUpdateRefillTask() {
        ExtentReportManager.logInfoDetails("Requester calls the Update Refill Task's v1 endpoint to update refill task");
        requestPayload = new ArrayList<>();
        requestPayload.add(taskDetails);
        RequestSpecification requestSpecification = RequestGenerator.getRequest(CommonUtilities.genericHeader(), requestPayload).log().all();
        response = requestSpecification.put(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "UPDATE_REFILL_TASK_v1"));
        response.then().log().all();
        ExtentReportManager.logInfoDetails("Status Code for Updated Refill Task is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Update Refill Task Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time for Update Refill Task is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }

    @And("Check the response time for the Refill Task updated from v1 endpoint")
    public void checkTheResponseTimeForTheRefillTaskUpdated() {
        ExtentReportManager.logInfoDetails("Check the response time for the Refill Task updated from v1 endpoint");
        GenericSteps.validateTheResponseTime(response.getTimeIn(TimeUnit.MILLISECONDS));
    }

    @And("Verify that refill task is updated successfully from v1 endpoint")
    public void verifyThatRefillTaskIsUpdatedSuccessfully() {
        ExtentReportManager.logInfoDetails("Verify that refill task is updated successfully from v1 endpoint");
        List<LinkedHashMap<String, String>> responsePayload = response.as(new TypeRef<List<LinkedHashMap<String, String>>>() {});
//        List<LinkedHashMap<String, String>> reqPayload = (List<LinkedHashMap<String, String>>) requestPayload.get("refills");
        for (int i=0; i<responsePayload.size(); i++){
            if (responsePayload.get(i).get("taskId").equals(requestPayload.get(i).get("taskId"))) {
                ExtentReportManager.logPassDetails("taskId field is passed");
                ExtentReportManager.logInfoDetails("Expected taskId is "+requestPayload.get(i).get("taskId")+" and the Actual taskId is "+responsePayload.get(i).get("taskId"));
            } else {
                ExtentReportManager.logFailureDetails("taskId field is failed");
                ExtentReportManager.logInfoDetails("Expected taskId is "+requestPayload.get(i).get("taskId")+" but the Actual taskId is "+responsePayload.get(i).get("taskId"));
            }
            Assert.assertEquals(responsePayload.get(i).get("taskId"), requestPayload.get(i).get("taskId"));

            if (responsePayload.get(i).get("status").equals(requestPayload.get(i).get("Status"))){
                ExtentReportManager.logPassDetails("status field is passed");
                ExtentReportManager.logInfoDetails("Expected status is "+requestPayload.get(i).get("Status")+" and the Actual status is "+responsePayload.get(i).get("status"));
            } else {
                ExtentReportManager.logFailureDetails("status field is failed");
                ExtentReportManager.logInfoDetails("Expected status is "+requestPayload.get(i).get("Status")+" but the Actual status is "+responsePayload.get(i).get("status"));
            }
            Assert.assertEquals(responsePayload.get(i).get("status"), requestPayload.get(i).get("Status"));
        }
    }

    @And("Verify that refill task is updated successfully by Auditor from v1 endpoint")
    public void verifyThatRefillTaskIsUpdatedSuccessfullyByAuditor() {
        ExtentReportManager.logInfoDetails("Verify that refill task is updated successfully by Auditor from v1 endpoint");
        List<LinkedHashMap<String, String>> responsePayload = response.as(new TypeRef<List<LinkedHashMap<String, String>>>() {});
//        List<LinkedHashMap<String, String>> reqPayload = (List<LinkedHashMap<String, String>>) requestPayload.get("refills");
        for (int i=0; i<responsePayload.size(); i++){
            if (responsePayload.get(i).get("taskId").equals(requestPayload.get(i).get("taskId"))) {
                ExtentReportManager.logPassDetails("taskId field is passed");
                ExtentReportManager.logInfoDetails("Expected taskId is "+requestPayload.get(i).get("taskId")+" and the Actual taskId is "+responsePayload.get(i).get("taskId"));
            } else {
                ExtentReportManager.logFailureDetails("taskId field is failed");
                ExtentReportManager.logInfoDetails("Expected taskId is "+requestPayload.get(i).get("taskId")+" but the Actual taskId is "+responsePayload.get(i).get("taskId"));
            }
            Assert.assertEquals(responsePayload.get(i).get("taskId"), requestPayload.get(i).get("taskId"));

            if (responsePayload.get(i).get("status").equals(requestPayload.get(i).get("Status"))){
                ExtentReportManager.logPassDetails("status field is passed");
                ExtentReportManager.logInfoDetails("Expected status is "+requestPayload.get(i).get("Status")+" and the Actual status is "+responsePayload.get(i).get("status"));
            } else {
                ExtentReportManager.logFailureDetails("status field is failed");
                ExtentReportManager.logInfoDetails("Expected status is "+requestPayload.get(i).get("Status")+" but the Actual status is "+responsePayload.get(i).get("status"));
            }
            Assert.assertEquals(responsePayload.get(i).get("status"), requestPayload.get(i).get("Status"));
        }
    }

}
