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

public class CreateRefillTaskSteps_v1 {

//    private static LinkedHashMap<String, Object> requestPayload = new LinkedHashMap<>();
    private static List<LinkedHashMap<String, Object>> requestPayload = new ArrayList<>();
    private static LinkedHashMap<String, Object> taskDetails = new LinkedHashMap<>();
    private LinkedHashMap<String, Object> articleDetails = new LinkedHashMap<>();;
    private Response response;

    @And("Get the unique requestId to create Refill Task from v1 endpoint")
    public void getTheUniqueRequestIdToCreateRefillTask() {
        ExtentReportManager.logInfoDetails("Get the unique requestId to create Refill Task from v1 endpoint");
        taskDetails = new LinkedHashMap<>();
        taskDetails.put("requestId", GenricUtils.generateUUID());
    }

    @And("Get the invalid requestId to verify the functionality of Create Task from v1 endpoint {string}")
    public void getTheInvalidRequestIdToVerifyTheFunctionalityOfCreateTaskFromVEndpoint(String requestId) {
        ExtentReportManager.logInfoDetails("Get the invalid requestId to verify the functionality of Create Task from v1 endpoint");
        taskDetails = new LinkedHashMap<>();
        if (requestId.equals("null"))
            requestId=null;
        taskDetails.put("requestId", requestId);
    }

    @And("Get the requestAt time to created Refill Task from v1 endpoint")
    public void getTheRequestAtTimeToCreatedRefillTask() {
        ExtentReportManager.logInfoDetails("Get the requestAt time to created Refill Task from v1 endpoint");
        taskDetails.put("requestedAt", GenricUtils.getUTC_Format_CurrentDateTime());
    }

    @And("Get the inavlid requestAt time to verify the functionality of Create Task from v1 endpoint {string}")
    public void getTheInavlidRequestAtTimeToVerifyTheFunctionalityOfCreateTaskFromVEndpoint(String requestedAt) {
        ExtentReportManager.logInfoDetails("Get the inavlid requestAt time to verify the functionality of Create Task from v1 endpoint");
        if (requestedAt.equals("null"))
            requestedAt = null;
        taskDetails.put("requestedAt", requestedAt);
    }

    @And("Give the invalid ean numbers to verify the functionality of Create Task from v1 endpoint {string}")
    public void giveTheInvalidEanNumbersToVerifyTheFunctionalityOfCreateTaskFromVEndpoint(String eanNumbers) {
        ExtentReportManager.logInfoDetails("Give the invalid ean numbers to verify the functionality of Create Task from v1 endpoint");
        articleDetails = new LinkedHashMap<>();
        if (eanNumbers.equals("null")) {
            eanNumbers = null;
        }
        articleDetails.put("ean", eanNumbers);
    }

    @And("Give the invalid categoryCode to verify the functionality of Create Task from v1 endpoint {string}")
    public void giveTheInvalidCategoryCodeToVerifyTheFunctionalityOfCreateTaskFromVEndpoint(String categoryCode) {
        ExtentReportManager.logInfoDetails("Give the invalid categoryCode to verify the functionality of Create Task from v1 endpoint");
        articleDetails = new LinkedHashMap<>();
        if (categoryCode.equals("null"))
            categoryCode=null;
        articleDetails.put("categoryCode", categoryCode);
    }

    @And("Give the invalid articleCode to verify the functionality of Create Task from v1 endpoint {string}")
    public void giveTheInvalidArticleCodeToVerifyTheFunctionalityOfCreateTaskFromVEndpoint(String articleCode) {
        ExtentReportManager.logInfoDetails("Give the invalid articleCode to verify the functionality of Create Task from v1 endpoint");
        articleDetails = new LinkedHashMap<>();
        if (articleCode.equals("null"))
            articleCode=null;
        articleDetails.put("articleCode", articleCode);
    }

    @And("Give the invalid articlePrice to verify the functionality of Create Task from v1 endpoint {string}")
    public void giveTheInvalidArticlePriceToVerifyTheFunctionalityOfCreateTaskFromVEndpoint(String articlePrice) {
        ExtentReportManager.logInfoDetails("Give the invalid articlePrice to verify the functionality of Create Task from v1 endpoint");
        articleDetails = new LinkedHashMap<>();
        if (articlePrice.equals("null"))
            articlePrice=null;
        articleDetails.put("articlePrice", articlePrice);
    }

    @And("Give the invalid articleMrp to verify the functionality of Create Task from v1 endpoint {string}")
    public void giveTheInvalidArticleMrpToVerifyTheFunctionalityOfCreateTaskFromVEndpoint(String articleMrp) {
        ExtentReportManager.logInfoDetails("Give the invalid articleMrp to verify the functionality of Create Task from v1 endpoint");
        if (articleMrp.equals("null"))
            articleMrp = null;
        articleDetails.put("articleMrp", articleMrp);
    }

    @And("Give the invalid articleName to verify the functionality of Create Task from v1 endpoint {string}")
    public void giveTheInvalidArticleNameToVerifyTheFunctionalityOfCreateTaskFromVEndpoint(String articleName) {
        ExtentReportManager.logInfoDetails("Give the invalid articleName to verify the functionality of Create Task from v1 endpoint");
        articleDetails = new LinkedHashMap<>();
        if (articleName.equals("null"))
            articleName=null;
        articleDetails.put("articleName", articleName);
    }

    @And("Provide article details to create Refill task from v1 endpoint")
    public void provideArticleDetailsToCreateRefillTask(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide article details to create Refill task from v1 endpoint");
        articleDetails = new LinkedHashMap<>();
        articleDetails.putAll(dataTable.asMap(String.class, Object.class));
        if (articleDetails.containsKey("caselot")) {
            int caselot = Integer.parseInt(String.valueOf(articleDetails.get("caselot")));
            articleDetails.put("caselot", caselot);
        }
        taskDetails.put("articleDetails", articleDetails);
    }

    @And("Provide the quantity for a Refill task to be created from v1 endpoint")
    public void provideRequestedQuantityForARefillTask(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide the quantity for a Refill task to be created from v1 endpoint");
        taskDetails.putAll(dataTable.asMap(String.class, Integer.class));
    }

    @And("Give requester comments for a Refill task to be created from v1 endpoint")
    public void giveRequesterCommentsForATask(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Give requester comments for a Refill task to be created from v1 endpoint");
        taskDetails.putAll(dataTable.asMap(String.class, String.class));
    }

    @And("Give the invalid task type for a new task to be created from v1 endpoint {string}")
    public void giveTheInvalidTaskTypeForANewTaskToBeCreatedFromVEndpoint(String taskType) {
        ExtentReportManager.logInfoDetails("Give the invalid task type for a new task to be created from v1 endpoint");
        if (taskType.equals("null"))
            taskType=null;
        taskDetails.put("taskType", taskType);
    }

    @And("Check the response time for the Refill Task created with v1 endpoint")
    public void checkTheResponseTimeForTheRefillTaskCreatedWithV3Endpoint() {
        ExtentReportManager.logInfoDetails("Check the response time for the Refill Task created with v1 endpoint");
        GenericSteps.validateTheResponseTime(response.getTimeIn(TimeUnit.MILLISECONDS));
    }


    @When("Requester call the Create Task v1 endpoint to create Refill task")
    public void requesterCallTheCreateTaskV1EndpointToCreateRefillTask() {
        ExtentReportManager.logInfoDetails("Requester call the Create Task v1 endpoint to create Refill task");
        if (GenericSteps.taskType!=null){
            taskDetails.putAll(GenericSteps.taskType);
        }
        GenericSteps.taskType = null;
        requestPayload = new ArrayList<>();
        requestPayload.add(taskDetails);
        RequestSpecification requestSpecification = RequestGenerator.getRequest(CommonUtilities.genericHeader(), requestPayload).log().all();
        response = requestSpecification.post(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "CREATE_TASK_v1"));
        response.then().log().all();
        if (response.statusCode()==201) {
            List<LinkedHashMap<String, String>> deserializedResponse = response.as(new TypeRef<List<LinkedHashMap<String, String>>>() {});
//            List<String> v3_RefillIds = new ArrayList<>();
            for (LinkedHashMap<String, String> resp:deserializedResponse){
               CommonUtilities.setRefillTaskRefillId(resp.get("taskId"));
//                v3_RefillIds.add(resp.get("refillId"));
            }
//            CommonUtilities.setAllTasksRefillId(v3_RefillIds);
            CommonUtilities.setFloorWalkId(deserializedResponse.get(0).get("floorWalkId"));
        }

        ExtentReportManager.logInfoDetails("Status Code for newly created Task is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Create Task Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time for newly created Task is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }

    @And("Verify that Refill Task is created successfully with v1 endpoint")
    public void verifyThatRefillTaskIsCreatedSuccessfully() {
        ExtentReportManager.logInfoDetails("Verify that Refill Task is created successfully with v1 endpoint");
        List<LinkedHashMap<String, Object>> deserializedResponse = response.as(new TypeRef<List<LinkedHashMap<String, Object>>>() {});
//        List<LinkedHashMap<String, Object>> reqPayload = (List<LinkedHashMap<String, Object>>) requestPayload.get("refills");

        for (int i=0; i<deserializedResponse.size(); i++){
            if (deserializedResponse.get(i).get("requestId").equals(requestPayload.get(i).get("requestId"))){
                ExtentReportManager.logPassDetails("requestId field of Refill Task is passed");
                ExtentReportManager.logInfoDetails("Excpected requestId is "+requestPayload.get(i).get("requestId")+" and the Actual requestId is "+deserializedResponse.get(i).get("requestId"));
            } else {
                ExtentReportManager.logFailureDetails("requestId field of Refill Task number is failed");
                ExtentReportManager.logInfoDetails("Excpected requestId is "+requestPayload.get(i).get("requestId")+" but the Actual requestId is "+deserializedResponse.get(i).get("requestId"));
            }
            Assert.assertEquals(deserializedResponse.get(i).get("requestId"), requestPayload.get(i).get("requestId"));
        }
    }

}
