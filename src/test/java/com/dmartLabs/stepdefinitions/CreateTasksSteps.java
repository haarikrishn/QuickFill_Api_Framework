package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.GenricUtils;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.PropertyReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.dmartLabs.config.ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH;

public class CreateTasksSteps {

    private LinkedHashMap<String, Object> requestPayload;
    private Response response;

    @And("Get the unique requestId to create a task")
    public void getTheUniqueRequestIdToCreateATask() {
        ExtentReportManager.logInfoDetails("Get the unique requestId to create a task");
        requestPayload = new LinkedHashMap<>();
        requestPayload.put("requestId", GenricUtils.generateUUID());
    }

    @And("Get the requestAt time to created a task")
    public void getTheRequestAtTimeToCreatedATask() {
        ExtentReportManager.logInfoDetails("Get the requestAt time to created a task");
        requestPayload.put("requestedAt", GenricUtils.getUTC_Format_CurrentDateTime());
    }

    @And("Provide the priceboards to create a Priceboard task")
    public void provideThePriceboardsToCreateAPriceboardTask(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide the priceboards to create a Priceboard task");
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
        requestPayload.put("priceBoards", priceboards1);
    }

    @And("Provide the details for a task to be created {string} {string} {string} {string} {string} {string} {string} {string}")
    public void provideTheDetailsForATaskToBeCreated(String articleName, String articlePrice, String ean, String categoryCode, String articleCode, String articleMrp, String requesterComments, String taskType) {
        ExtentReportManager.logInfoDetails("Provide the details for a task to be created");
        LinkedHashMap<String, Object> articleDetails = new LinkedHashMap<>();
        if (articleName.equals("null"))
            articleName = null;
        else if (articlePrice.equals("null"))
            articlePrice = null;
        else if (ean.equals("null"))
            ean = null;
        else if (categoryCode.equals("null"))
            categoryCode = null;
        else if (articleCode.equals("null"))
            articleCode = null;
        else if (articleMrp.equals("null"))
            articleMrp = null;
        else if (requesterComments.equals("null"))
            requesterComments = null;
        else if (taskType.equals("null"))
            taskType = null;

        articleDetails.put("articleName", articleName);
        articleDetails.put("articlePrice", articlePrice);
        articleDetails.put("ean", ean);
        articleDetails.put("categoryCode", categoryCode);
        articleDetails.put("articleCode", articleCode);
        articleDetails.put("articleMrp", articleMrp);

        requestPayload.put("articleDetails", articleDetails);
        if ((requesterComments==null || !requesterComments.equals("")))
            requestPayload.put("requesterComments", requesterComments);
        requestPayload.put("taskType", taskType);
    }

    @And("Provide the details for a task to be created {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string}")
    public void provideTheDetailsForATaskToBeCreated(String articleName, String articlePrice, String ean, String categoryCode, String articleCode, String articleMrp, String uom, String caselot, String requestedQuantity, String requesterComments, String taskType) {
        ExtentReportManager.logInfoDetails("Provide the details for a task to be created");
        LinkedHashMap<String, Object> articleDetails = new LinkedHashMap<>();
        if (articleName.equals("null"))
            articleName = null;
        else if (articlePrice.equals("null"))
            articlePrice = null;
        else if (ean.equals("null"))
            ean = null;
        else if (categoryCode.equals("null"))
            categoryCode = null;
        else if (articleCode.equals("null"))
            articleCode = null;
        else if (articleMrp.equals("null"))
            articleMrp = null;
        else if (uom.equals("null"))
            uom = null;
        else if (caselot.equals("null"))
            caselot = null;
        else if (requestedQuantity.equals("null"))
            requestedQuantity = null;
        else if (requesterComments.equals("null"))
            requesterComments = null;
        else if (taskType.equals("null"))
            taskType = null;

        articleDetails.put("articleName", articleName);
        articleDetails.put("articlePrice", articlePrice);
        articleDetails.put("ean", ean);
        articleDetails.put("categoryCode", categoryCode);
        articleDetails.put("articleCode", articleCode);
        articleDetails.put("articleMrp", articleMrp);
        articleDetails.put("uom", uom);
        if (caselot!=null)
            articleDetails.put("caselot", Integer.parseInt(caselot));
        else
            articleDetails.put("caselot", caselot);
        requestPayload.put("articleDetails", articleDetails);
        if (requestedQuantity!=null)
            requestPayload.put("requestedQuantity", Integer.parseInt(requestedQuantity));
        else
            requestPayload.put("requestedQuantity", requestedQuantity);
        if ((requesterComments==null || !requesterComments.equals("")))
            requestPayload.put("requesterComments", requesterComments);
        requestPayload.put("taskType", taskType);
    }

    @When("Requester call the Create Task endpoint to create task")
    public void requesterCallTheCreateTaskEndpointToCreateTask() {
        if (GenericSteps.taskType!=null){
            requestPayload.put("taskType", GenericSteps.taskType);
        }
        ExtentReportManager.logInfoDetails("Requester call the Create Task endpoint to create task");
        RequestSpecification requestSpecification = RequestGenerator.getRequest(CommonUtilities.genericHeader(), requestPayload).log().all();
        response = requestSpecification.post(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "CREATE_TASK"));
        response.then().log().all();
        if (response.statusCode()==201) {
            CommonUtilities.setRequestId(response.jsonPath().getString("requestId"));
            CommonUtilities.setRefillId(response.jsonPath().getString("refillId"));
            CommonUtilities.setFloorWalkId(response.jsonPath().getString("floorWalkId"));
        }
        ExtentReportManager.logInfoDetails("Status Code for newly created Task is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Create Task Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time for newly created Task is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }

    @And("Get the invalid requestId {string}")
    public void getTheInvalidRequestId(String requestId) {
        ExtentReportManager.logInfoDetails("Get the invalid requestId");
        if (requestId.equals("null"))
            requestId = null;
        requestPayload = new LinkedHashMap<>();
        requestPayload.put("requestId", requestId);
    }

    @And("Get the requestAt time to created Task")
    public void getTheRequestAtTimeToCreatedTask() {
        ExtentReportManager.logInfoDetails("Get the requestAt time to created Task");
        requestPayload.put("requestedAt", GenricUtils.getUTC_Format_CurrentDateTime());
    }

    @And("Provide article details to create task")
    public void provideArticleDetailsToCreateTask(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide article details to create task");
        LinkedHashMap<String, Object> articleDetails = new LinkedHashMap<>();
        articleDetails.putAll(dataTable.asMap(String.class, Object.class));
        if (articleDetails.containsKey("caselot")) {
            int caselot = Integer.parseInt(String.valueOf(articleDetails.get("caselot")));
            articleDetails.put("caselot", caselot);
        }
        requestPayload.put("articleDetails", articleDetails);
    }


    @And("Give comments for a task")
    public void giveCommentsForATask(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Give comments for a task");
        requestPayload.putAll(dataTable.asMap(String.class, String.class));
    }


    @And("Get the invalid requestedAt time {string}")
    public void getTheInvalidRequestedAtTime(String requestedAt) {
        ExtentReportManager.logInfoDetails("Get the invalid requestedAt time");
        if (requestedAt.equals("null"))
            requestedAt = null;

        requestPayload.put("requestedAt", requestedAt);
    }

}
