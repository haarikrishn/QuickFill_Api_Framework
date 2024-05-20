package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExcelUtils;
import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.GenricUtils;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.PropertyReader;
import io.cucumber.datatable.DataTable;
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

public class CreateTaskSteps_v1 {

    private List<LinkedHashMap<String, Object>> requestPayload;
    private Response response;
    private ExcelUtils excelUtils;

    @And("Give the Excel file to get the request payload {string}")
    public void giveTheExcelFileToGetTheRequestPayload(String excelFile) {
        ExtentReportManager.logInfoDetails("Give the Excel file to get the request payload");
        excelUtils = new ExcelUtils(excelFile);
    }

    @And("Give the sheet name {string} and the number of tasks to be created {int}")
    public void giveTheSheetNameAndTheNumberOfTasksToBeCreated(String sheetName, int totalTasks) {
        ExtentReportManager.logInfoDetails("Sheet name is "+sheetName+" and the number of tasks to be created is "+totalTasks);
        requestPayload = excelUtils.createTask_v1(sheetName, totalTasks);

    }

    @When("Requester call the Create Task v1 endpoint to create new tasks")
    public void requesterCallTheCreateTaskV1EndpointToCreateTask() {
        ExtentReportManager.logInfoDetails("Requester call the Create Task v1 endpoint to create new tasks");
        RequestSpecification requestSpecification = RequestGenerator.getRequest(CommonUtilities.genericHeader(), requestPayload).log().all();
        response = requestSpecification.post(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "CREATE_TASK_v1"));
        response.then().log().all();
        if (response.statusCode()==201) {
            List<LinkedHashMap<String, String>> deserializedResponse = response.as(new TypeRef<List<LinkedHashMap<String, String>>>() {});
            List<String> v1_RefillIds = new ArrayList<>();
            for (LinkedHashMap<String, String> resp:deserializedResponse){
                v1_RefillIds.add(resp.get("taskId"));
            }
            CommonUtilities.setAllTasksRefillId(v1_RefillIds);
            CommonUtilities.setFloorWalkId(deserializedResponse.get(0).get("floorWalkId"));
        }

        ExtentReportManager.logInfoDetails("Status Code for newly created Task is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Create Task Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time for newly created Task is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }

    @And("Verify that Task is created successfully with v1 endpoint")
    public void verifyThatRefillTaskIsCreatedSuccessfullyForV() {
        ExtentReportManager.logInfoDetails("Verify that Task is created successfully with v1 endpoint");
        List<LinkedHashMap<String, Object>> deserializedResponse = response.as(new TypeRef<List<LinkedHashMap<String, Object>>>() {});
        //List<LinkedHashMap<String, Object>> reqPayload = (List<LinkedHashMap<String, Object>>) requestPayload.get("refills");
        List<String> refillTask_v1_RefillIds = new ArrayList<>();
        List<String> priceboardTask_v1_RefillIds = new ArrayList<>();
        List<String> othersTask_v1_RefillIds = new ArrayList<>();
        for (int i=0; i<deserializedResponse.size(); i++){
            if (deserializedResponse.get(i).get("requestId").equals(requestPayload.get(i).get("requestId"))){
                ExtentReportManager.logPassDetails("requestId field of task number "+(i+1)+" is passed");
                ExtentReportManager.logInfoDetails("Excpected requestId is "+requestPayload.get(i).get("requestId")+" and the Actual requestId is "+deserializedResponse.get(i).get("requestId"));
            } else {
                ExtentReportManager.logFailureDetails("requestId field of task number "+(i+1)+" is failed");
                ExtentReportManager.logInfoDetails("Excpected requestId is "+requestPayload.get(i).get("requestId")+" but the Actual requestId is "+deserializedResponse.get(i).get("requestId"));
            }
            Assert.assertEquals(deserializedResponse.get(i).get("requestId"), requestPayload.get(i).get("requestId"));

            if (requestPayload.get(i).get("taskType").equals("REFILL"))
                refillTask_v1_RefillIds.add((String) deserializedResponse.get(i).get("taskId"));
            else if (requestPayload.get(i).get("taskType").equals("PRICEBOARD"))
                priceboardTask_v1_RefillIds.add((String) deserializedResponse.get(i).get("taskId"));
            else if (requestPayload.get(i).get("taskType").equals("OTHERS"))
                othersTask_v1_RefillIds.add((String) deserializedResponse.get(i).get("taskId"));
        }
        CommonUtilities.setRefillTask_v1_RefillIds(refillTask_v1_RefillIds);
        CommonUtilities.setPriceboardTask_v1_RefillIds(priceboardTask_v1_RefillIds);
        CommonUtilities.setOthersTask_v1_RefillIds(othersTask_v1_RefillIds);
    }

    @And("Check the response time for the task created")
    public void checkTheResponseTimeForTheTaskCreated() {
        ExtentReportManager.logInfoDetails("Check the response time for the task created");
        GenericSteps.validateTheResponseTime(response.getTimeIn(TimeUnit.MILLISECONDS));
    }

}
