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

public class UpdateOthersTaskSteps_v1 {

    private static List<LinkedHashMap<String, Object>> requestPayload = new ArrayList<>();
    private static LinkedHashMap<String, Object> taskDetails = new LinkedHashMap<>();
    private Response response;
    private ExcelUtils excelUtils;

    @And("Give the Excel file to get the request payload for updating Others task {string}")
    public void giveTheExcelFileToGetTheRequestPayloadForUpdatingOthersTask(String excelFile) {
        ExtentReportManager.logInfoDetails("Give the Excel file to get the request payload for updating Others task");
        excelUtils = new ExcelUtils(excelFile);
    }

    @And("Give the sheet name {string} to get all the Others Task to be updated")
    public void giveTheSheetNameToGetAllTheOthersTaskToBeUpdated(String sheetName) {
        ExtentReportManager.logInfoDetails("Give the sheet name to get all the Others Task to be updated");
        int size = CommonUtilities.getOthersTask_v1_RefillIds().size();
//        System.out.println("Others refillID size in updateOthers ===============> "+CommonUtilities.getOthersTask_v1_RefillIds().size());
//        System.out.println("Others refillID in updateOthers===============> "+CommonUtilities.getOthersTask_v1_RefillIds());
        requestPayload = excelUtils.updateOthersTask_v1(sheetName, CommonUtilities.getOthersTask_v1_RefillIds().size());
//        List<LinkedHashMap<String, Object>> allUpdateOthersTasksDetails = (List<LinkedHashMap<String, Object>>) allUpdateOthersTasks.get("refills");
        for (int i=0; i<CommonUtilities.getOthersTask_v1_RefillIds().size(); i++){
            requestPayload.get(i).put("taskId", CommonUtilities.getOthersTask_v1_RefillIds().get(i));
        }
    }

    @And("Get the refillId to update others task from v1 endpoint")
    public void getTheRefillIdToUpdateOthersTask() {
        ExtentReportManager.logInfoDetails("Get the refillId to update others task from v1 endpoint");
        taskDetails = new LinkedHashMap<>();
        taskDetails.put("taskId", CommonUtilities.getOthersTaskRefillId());
    }

    @And("Get the refillId to update others task from v1 endpoint {string}")
    public void getTheRefillIdToUpdateOthersTask(String taskId) {
        ExtentReportManager.logInfoDetails("Get the refillId to update others task from v1 endpoint");
        taskDetails = new LinkedHashMap<>();

        if (taskId.equals("null"))
            taskId = null;

        taskDetails.put("taskId", taskId);
    }

    @And("Give the status to update the others task from v1 endpoint {string}")
    public void giveTheStatusToUpdateTheOthersTask(String status) {
        ExtentReportManager.logInfoDetails("Give the status to update the others task from v1 endpoint");
        taskDetails.put("Status", status);
    }

    @And("Give the comments for updating others task from v1 endpoint {string}")
    public void giveTheCommentsForUpdatingOthersTask(String comments) {
        ExtentReportManager.logInfoDetails("Give the comments for updating others task from v1 endpoint");
        taskDetails.put("refillerComments", comments);
    }

    @And("Give the comments for updating others task by auditor from v1 endpoint {string}")
    public void giveTheCommentsForUpdatingOthersTaskByAuditor(String comments) {
        ExtentReportManager.logInfoDetails("Give the comments for updating others task by auditor from v1 endpoint");
        taskDetails.put("auditorComments", comments);
    }

    @When("Requester calls the Update Others Task v1 endpoint to update list of all others task")
    public void requesterCallsTheUpdateOthersTaskVEndpointToUpdateListOfAllOthersTask() {
        ExtentReportManager.logInfoDetails("Requester calls the Update Others Task v1 endpoint to update list of all others task");
        RequestSpecification requestSpecification = RequestGenerator.getRequest(CommonUtilities.genericHeader(), requestPayload).log().all();
        response = requestSpecification.put(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "UPDATE_OTHERS_TASK_v1"));
        response.then().log().all();
        ExtentReportManager.logInfoDetails("Status Code for Updated Others Task is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Update Others Task Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time for Update Others Task is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }

    @When("Requester calls the Update Others Task v1 endpoint to update others task")
    public void requesterCallsTheUpdateOthersTaskSEndpointToUpdateOthersTask() {
        ExtentReportManager.logInfoDetails("Requester calls the Update Others Task v1 endpoint to update others task");
        requestPayload =new ArrayList<>();
        requestPayload.add(taskDetails);
        RequestSpecification requestSpecification = RequestGenerator.getRequest(CommonUtilities.genericHeader(), requestPayload).log().all();
        response = requestSpecification.put(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "UPDATE_OTHERS_TASK_v1"));
        response.then().log().all();
        ExtentReportManager.logInfoDetails("Status Code for Updated Others Task is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Update Others Task Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time for Update Others Task is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }

    @And("Check the response time for the Others Task updated from v1 endpoint")
    public void checkTheResponseTimeForTheOthersTaskUpdated() {
        ExtentReportManager.logInfoDetails("Check the response time for the Others Task updated from v1 endpoint");
        GenericSteps.validateTheResponseTime(response.getTimeIn(TimeUnit.MILLISECONDS));
    }

    @And("Verify that others task is updated successfully from v1 endpoint")
    public void verifyThatOthersTaskIsUpdatedSuccessfully() {
        ExtentReportManager.logInfoDetails("Verify that others task is updated successfully from v1 endpoint");
        List<LinkedHashMap<String, String>> responsePayload = response.as(new TypeRef<List<LinkedHashMap<String, String>>>() {});
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

    @And("Verify that others task is updated successfully by Auditor from v1 endpoint")
    public void verifyThatOthersTaskIsUpdatedSuccessfullyByAuditor() {
        ExtentReportManager.logInfoDetails("Verify that others task is updated successfully by Auditor");
        List<LinkedHashMap<String, String>> responsePayload = response.as(new TypeRef<List<LinkedHashMap<String, String>>>() {});
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
