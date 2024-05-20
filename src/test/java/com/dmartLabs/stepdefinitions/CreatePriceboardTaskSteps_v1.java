package com.dmartLabs.stepdefinitions;

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

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.dmartLabs.config.ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH;

public class CreatePriceboardTaskSteps_v1 {

//    private static LinkedHashMap<String, Object> requestPayload = new LinkedHashMap<>();
    private static List<LinkedHashMap<String, Object>> requestPayload = new ArrayList<>();
    private static LinkedHashMap<String, Object> taskDetails = new LinkedHashMap<>();
    private Response response;

    @And("Get the unique requestId to create Priceboard Task from v1 endpoint")
    public void getTheUniqueRequestIdToCreatePriceboardTask() {
        ExtentReportManager.logInfoDetails("Get the unique requestId to create Priceboard Task from v1 endpoint");
        taskDetails = new LinkedHashMap<>();
        taskDetails.put("requestId", GenricUtils.generateUUID());
    }

    @And("Get the requestAt time to created Priceboard Task from v1 endpoint")
    public void getTheRequestAtTimeToCreatedRefillTask() {
        ExtentReportManager.logInfoDetails("Get the requestAt time to created Priceboard Task from v1 endpoint");
        taskDetails.put("requestedAt", GenricUtils.getUTC_Format_CurrentDateTime());
    }

    @And("Provide the priceboards for a Priceboard task to be created from v1 endpoint")
    public void provideThePriceboardForAPriceboardTask(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide the priceboards for a Priceboard task to be created from v1 endpoint");
        List<Map<String, Object>> priceboards = new ArrayList<>(dataTable.asMaps(String.class, Object.class));
        List<Map<String, Object>> priceboards1 = new ArrayList<>();
        for (Map<String, Object> priceboard:priceboards){
            Map<String, Object> priceboard1 = new HashMap<>();
            priceboard1.put("priceBoard", priceboard.get("priceBoard"));

            if (priceboard.get("pbStatus").equals("false"))
                priceboard1.put("pbStatus", false);
            else if (priceboard.get("pbStatus").equals("true"))
                priceboard1.put("pbStatus", true);

            priceboards1.add(priceboard1);
        }
        taskDetails.put("priceBoards", priceboards1);
    }

    @And("Provide empty priceboards for a Priceboard task in v1 endpoint")
    public void provideEmptyPriceboardsForAPriceboardTask() {
        ExtentReportManager.logInfoDetails("Provide empty priceboards for a Priceboard task in v1 endpoint");
        List<Map<String, Object>> priceboards1 = new ArrayList<>();
        taskDetails.put("priceBoards", priceboards1);
    }

    @And("Provide only pbStatus or priceboard in priceBoards for a Priceboard task to be created from v1 endpoint")
    public void provideOnlyPbStatusInPriceBoardsForAPriceboardTask(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide only pbStatus or priceboard in priceBoards for a Priceboard task to be created from v1 endpoint");
        List<Map<String, Object>> priceBoards = dataTable.asMaps(String.class, Object.class);
        taskDetails.put("priceBoards", priceBoards);
    }

    @And("Provide article details to create Priceboard task from v1 endpoint")
    public void provideArticleDetailsToCreatePriceboardTask(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide article details to create Priceboard task from v1 endpoint");
        taskDetails.put("articleDetails", dataTable.asMap(String.class, Object.class));
    }

    @And("Give requester comments for a Priceboard task from v1 endpoint")
    public void giveRequesterCommentsForATask(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Give requester comments for a Priceboard task from v1 endpoint");
        taskDetails.putAll(dataTable.asMap(String.class, String.class));
    }

//    @And("Provide the quantity for a Priceboard task")
//    public void provideRequestedQuantityForARefillTask(DataTable dataTable) {
//        ExtentReportManager.logInfoDetails("Provide the quantity for a Priceboard task");
//        requestPayload.putAll(dataTable.asMap(String.class, Integer.class));
//    }

    @When("Requester call the Create Task v1 endpoint to create Priceboard task")
    public void requesterCallTheCreateTaskEndpointToCreateRefillTask() {
        if (GenericSteps.taskType!=null){
            taskDetails.putAll(GenericSteps.taskType);
        }
        requestPayload.add(taskDetails);
//        requestPayload.put("refills", refills);
        ExtentReportManager.logInfoDetails("Requester call the Create Task v1 endpoint to create Priceboard task");
        RequestSpecification requestSpecification = RequestGenerator.getRequest(CommonUtilities.genericHeader(), requestPayload).log().all();
        response = requestSpecification.post(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "CREATE_TASK_v1"));
        response.then().log().all();
        if (response.statusCode()==201) {
            List<LinkedHashMap<String, String>> deserializedResponse = response.as(new TypeRef<List<LinkedHashMap<String, String>>>() {});
//            List<String> v3_RefillIds = new ArrayList<>();
            for (LinkedHashMap<String, String> resp:deserializedResponse){
                CommonUtilities.setPriceboardTaskRefillId(resp.get("taskId"));
//                v3_RefillIds.add(resp.get("refillId"));
            }
//            CommonUtilities.setAllTasksRefillId(v3_RefillIds);
            CommonUtilities.setFloorWalkId(deserializedResponse.get(0).get("floorWalkId"));
        }
        ExtentReportManager.logInfoDetails("Status Code for newly created Priceboard Task is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Create Priceboard Task Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time for newly created Priceboard Task is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }

    @And("Check the response time for the Priceboard Task created from v1 endpoint")
    public void checkTheResponseTimeForThePriceboardTaskCreated() {
        ExtentReportManager.logInfoDetails("Check the response time for the Priceboard Task created from v1 endpoint");
        GenericSteps.validateTheResponseTime(response.getTimeIn(TimeUnit.MILLISECONDS));
    }

    @And("Verify that Priceboard Task is created successfully from v1 endpoint")
    public void verifyThatPriceboardTaskIsCreatedSuccessfullyFromVEndpoint() {
        List<LinkedHashMap<String, Object>> deserializedResponse = response.as(new TypeRef<List<LinkedHashMap<String, Object>>>() {});
//        List<LinkedHashMap<String, Object>> reqPayload = (List<LinkedHashMap<String, Object>>) requestPayload.get("refills");
        for (int i=0; i<deserializedResponse.size(); i++){
            if (deserializedResponse.get(i).get("requestId").equals(requestPayload.get(i).get("requestId"))){
                ExtentReportManager.logPassDetails("requestId field of Priceboard Task is passed");
                ExtentReportManager.logInfoDetails("Excpected requestId is "+requestPayload.get(i).get("requestId")+" and the Actual requestId is "+deserializedResponse.get(i).get("requestId"));
            } else {
                ExtentReportManager.logFailureDetails("requestId field of Priceboard Task is failed");
                ExtentReportManager.logInfoDetails("Excpected requestId is "+requestPayload.get(i).get("requestId")+" but the Actual requestId is "+deserializedResponse.get(i).get("requestId"));
            }
            Assert.assertEquals(deserializedResponse.get(i).get("requestId"), requestPayload.get(i).get("requestId"));
        }
    }

}
