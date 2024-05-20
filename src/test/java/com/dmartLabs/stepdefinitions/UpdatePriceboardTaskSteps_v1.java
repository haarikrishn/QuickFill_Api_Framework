package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExcelUtils;
import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.PropertyReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.dmartLabs.config.ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH;

public class UpdatePriceboardTaskSteps_v1 {

    private static List<LinkedHashMap<String, Object>> requestPayload = new ArrayList<>();
    private static LinkedHashMap<String, Object> taskDetails = new LinkedHashMap<>();
    private Response response;
    private ExcelUtils excelUtils;
    private static int flag;

    @And("Give the Excel file to get the request payload for updating Priceboad task {string}")
    public void giveTheExcelFileToGetTheRequestPayloadForUpdatingPriceboadTask(String excelFile) {
        ExtentReportManager.logInfoDetails("Give the Excel file to get the request payload for updating Priceboad task");
        excelUtils = new ExcelUtils(excelFile);
    }

    @And("Give the sheet name {string} to get all the Priceboard Task to be updated")
    public void giveTheSheetNameToGetAllThePriceboardTaskToBeUpdated(String sheetName) {
        ExtentReportManager.logInfoDetails("Give the sheet name to get all the Priceboard Task to be updated");
//        System.out.println("Priceboard refillID size in updatePriceboard ===============> "+CommonUtilities.getPriceboardTask_v1_RefillIds().size());
//        System.out.println("Priceboard refillID in updatePriceboard ===============> "+CommonUtilities.getPriceboardTask_v1_RefillIds());
        requestPayload = excelUtils.updatePriceboardTask_v1(sheetName, CommonUtilities.getPriceboardTask_v1_RefillIds().size());
//        List<LinkedHashMap<String, Object>> allUpdatePriceboardTasksDetails = (List<LinkedHashMap<String, Object>>) allUpdatePriceboardTasks.get("refills");
        for (int i=0; i<CommonUtilities.getPriceboardTask_v1_RefillIds().size(); i++){
            requestPayload.get(i).put("taskId", CommonUtilities.getPriceboardTask_v1_RefillIds().get(i));
        }
    }

    @And("Get the refillId to update priceboard task from v1 endpoint")
    public void getTheRefillIdToUpdatePriceboardTask() {
        ExtentReportManager.logInfoDetails("Get the taskId to update priceboard task from v1 endpoint");
        taskDetails = new LinkedHashMap<>();
        taskDetails.put("taskId", CommonUtilities.getPriceboardTaskRefillId());
    }

    @And("Get the refillId to update priceboard task {string} from v1 endpoint")
    public void getTheRefillIdToUpdatePriceboardTask(String taskId) {
        ExtentReportManager.logInfoDetails("Get the taskId to update priceboard task");
        taskDetails = new LinkedHashMap<>();
        if (taskId.equals("null"))
            taskId = null;
        taskDetails.put("taskId", taskId);
    }

    @And("Give the status to update the priceboard task from v1 endpoint {string}")
    public void giveTheStatusToUpdateThePriceboardTask(String status) {
        ExtentReportManager.logInfoDetails("Give the status to update the priceboard task from v1 endpoint");
        taskDetails.put("Status", status);
    }

    @And("Provide the priceboards to be updated from v1 endpoint")
    public void provideThePriceboardsToBeUpdated(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide the priceboards to be updated from v1 endpoint");
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
        taskDetails.put("priceBoards", priceboards1);
    }

    @And("Provide the priceboards to be updated from v1 endpoint as null")
    public void provideThePriceboardsToBeUpdatedAsNull() {
        ExtentReportManager.logInfoDetails("Provide the priceboards to be updated from v1 endpoint as null");
        taskDetails.put("priceBoards", null);
    }

    @And("Provide the priceboards to be updated without priceBoard and pbStatus field from v1 endpoint")
    public void provideThePriceboardsToBeUpdatedWithoutPriceBoardAndPbStatusField() {
        ExtentReportManager.logInfoDetails("Provide the priceboards to be updated without priceBoard and pbStatus field from v1 endpoint");
        List<Map<String, Object>> priceboards = new ArrayList<>();
        taskDetails.put("priceBoards", priceboards);
    }

    @And("Give the comments for updating priceboard task from v1 endpoint {string}")
    public void giveTheCommentsForUpdatingPriceboardTask(String comments) {
        ExtentReportManager.logInfoDetails("Give the comments for updating priceboard task from v1 endpoint");
        taskDetails.put("refillerComments", comments);
    }

    @And("Give the comments for updating priceboard task by auditor from v1 endpoint {string}")
    public void giveTheCommentsForUpdatingPriceboardTaskByAuditor(String comments) {
        ExtentReportManager.logInfoDetails("Give the comments for updating priceboard task by auditor from v1 endpoint");
        taskDetails.put("auditorComments", comments);
    }

    @When("Requester calls the Update Priceboard Task v1 endpoint to update list of all priceboard task")
    public void requesterCallsTheUpdatePriceboardTaskVEndpointToUpdateListOfAllPriceboardTask() {
        ExtentReportManager.logInfoDetails("Requester calls the Update Priceboard Task v1 endpoint to update list of all priceboard task");
        RequestSpecification requestSpecification = RequestGenerator.getRequest(CommonUtilities.genericHeader(), requestPayload).log().all();
        response = requestSpecification.put(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "UPDATE_PRICEBOARD_TASK_v1"));
        response.then().log().all();
        ExtentReportManager.logInfoDetails("Status Code for Updated Priceboard Task is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Update Priceboard Task Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time for Update Priceboard Task is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }

    @When("Requester calls the Update Priceboard Task v1 endpoint to update priceboard task")
    public void requesterCallsTheUpdatePriceboardTaskSEndpointToUpdatePriceboardTask() {
        ExtentReportManager.logInfoDetails("Requester calls the Update Priceboard Task v1 endpoint to update priceboard task");
        requestPayload = new ArrayList<>();
        requestPayload.add(taskDetails);
        RequestSpecification requestSpecification = RequestGenerator.getRequest(CommonUtilities.genericHeader(), requestPayload).log().all();
        response = requestSpecification.put(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "UPDATE_PRICEBOARD_TASK_v1"));
        response.then().log().all();
        ExtentReportManager.logInfoDetails("Status Code for Updated Priceboard Task is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Update Priceboard Task Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time for Update Priceboard Task is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }

    @And("Check the response time for the Priceboard Task updated from v1 endpoint")
    public void checkTheResponseTimeForThePriceboardTaskUpdated() {
        ExtentReportManager.logInfoDetails("Check the response time for the Priceboard Task updated from v1 endpoint");
        GenericSteps.validateTheResponseTime(response.getTimeIn(TimeUnit.MILLISECONDS));
    }

    @And("Verify that priceboard task is updated successfully from v1 endpoint")
    public void verifyThatPriceboardTaskIsUpdatedSuccessfully() {
        ExtentReportManager.logInfoDetails("Verify that priceboard task is updated successfully from v1 endpoint");
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

    @And("Verify that priceboard task is updated successfully by Auditor from v1 endpoint")
    public void verifyThatPriceboardTaskIsUpdatedSuccessfullyByAuditor() {
        ExtentReportManager.logInfoDetails("Verify that priceboard task is updated successfully by Auditor from v1 endpoint");
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


//    @When("Requester calls the Update Refill Task's endpoint to update priceboard task")
//    public void requesterCallsTheUpdateRefillTaskSEndpointToUpdatePriceboardTask() {
//        ExtentReportManager.logInfoDetails("Requester calls the Update Refill Task's endpoint to update priceboard task");
//        RequestSpecification requestSpecification = RequestGenerator.getRequest(CommonUtilities.genericHeader(), requestPayload).log().all();
//        response = requestSpecification.put(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "UPDATE_REFILL_TASK"));
//        response.then().log().all();
//        ExtentReportManager.logInfoDetails("Status Code for Updated Priceboard Task is : "+response.getStatusCode());
//        ExtentReportManager.logInfoDetails("Update Priceboard Task Response Payload is - ");
//        ExtentReportManager.logJson(response.prettyPrint());
//        ExtentReportManager.logInfoDetails("Response Time for Update Priceboard Task is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
//        CommonUtilities.setResponseInstance(response);
//    }
//
//    @When("Requester calls the Update Others Task's endpoint to update priceboard task")
//    public void requesterCallsTheUpdateOthersTaskSEndpointToUpdatePriceboardTask() {
//        ExtentReportManager.logInfoDetails("Requester calls the Update Others Task's endpoint to update priceboard task");
//        RequestSpecification requestSpecification = RequestGenerator.getRequest(CommonUtilities.genericHeader(), requestPayload).log().all();
//        response = requestSpecification.put(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "UPDATE_OTHERS_TASK"));
//        response.then().log().all();
//        ExtentReportManager.logInfoDetails("Status Code for Updated Priceboard Task is : "+response.getStatusCode());
//        ExtentReportManager.logInfoDetails("Update Priceboard Task Response Payload is - ");
//        ExtentReportManager.logJson(response.prettyPrint());
//        ExtentReportManager.logInfoDetails("Response Time for Update Priceboard Task is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
//        CommonUtilities.setResponseInstance(response);
//    }
}
