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
import org.testng.Assert;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.dmartLabs.config.ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH;

public class CreateOthersTaskSteps {

    private static LinkedHashMap<String, Object> requestPayload = new LinkedHashMap<>();
    private Response response;

    @And("Get the unique requestId to create Others Task")
    public void getTheUniqueRequestIdToCreateOthersTask() {
        ExtentReportManager.logInfoDetails("Get the unique requestId to create Others Task");
        requestPayload = new LinkedHashMap<>();
        requestPayload.put("requestId", GenricUtils.generateUUID());
    }

    @And("Get the requestAt time to created Others Task")
    public void getTheRequestAtTimeToCreatedOthersTask() {
        ExtentReportManager.logInfoDetails("Get the requestAt time to created Others Task");
        requestPayload.put("requestedAt", GenricUtils.getUTC_Format_CurrentDateTime());
    }

    @And("Provide article details to create Others task")
    public void provideArticleDetailsToCreateOthersTask(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide article details to create Others task");
        requestPayload.put("articleDetails", dataTable.asMap(String.class, Object.class));
    }

    @And("Give comments for a Others task")
    public void giveRequesterCommentsForATask(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Give requester comments for a Others task");
        requestPayload.putAll(dataTable.asMap(String.class, String.class));
    }

    @And("Provide the quantity for Others task")
    public void provideRequestedQuantityForARefillTask(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide the quantity for Others task");
        requestPayload.putAll(dataTable.asMap(String.class, Integer.class));
    }

    @And("Provide the priceboards for Others task")
    public void provideThePriceboardsForATask(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide the priceboards for Others task");
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

    @When("Requester call the Create Task endpoint to create Others task")
    public void requesterCallTheCreateTaskEndpointToCreateOthersTask() {
        ExtentReportManager.logInfoDetails("Requester call the Create Task endpoint to create Others task");
        if (GenericSteps.taskType!=null){
            requestPayload.putAll(GenericSteps.taskType);
        }

        RequestSpecification requestSpecification = RequestGenerator.getRequest(CommonUtilities.genericHeader(), requestPayload).log().all();
        response = requestSpecification.post(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "CREATE_TASK"));
        response.then().log().all();
        if (response.statusCode()==201) {
            CommonUtilities.setRequestId(response.jsonPath().getString("requestId"));
            CommonUtilities.setOthersTaskRefillId(response.jsonPath().getString("refillId"));
            CommonUtilities.setFloorWalkId(response.jsonPath().getString("floorWalkId"));
        }
        ExtentReportManager.logInfoDetails("Status Code for newly created Others Task is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Create Others Task Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time for newly created Others Task is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }

    @And("Check the response time for the Others Task created")
    public void checkTheResponseTimeForTheOthersTaskCreated() {
        ExtentReportManager.logInfoDetails("Check the response time for the Others Task created");
        GenericSteps.validateTheResponseTime(response.getTimeIn(TimeUnit.MILLISECONDS));
    }

    @And("Verify that Others Task is created successfully")
    public void verifyThatOthersTaskIsCreatedSuccessfully() {
        ExtentReportManager.logInfoDetails("Verify that Priceboard Task is created successfully");
        if (response.jsonPath().getString("SiteId").equals(String.valueOf(CommonUtilities.getSiteId()))){
            ExtentReportManager.logPassDetails("SiteId field is passed");
            ExtentReportManager.logInfoDetails("Expected SiteId is "+CommonUtilities.getSiteId()+" and the Actual SiteId is "+response.jsonPath().getString("SiteId"));
        } else {
            ExtentReportManager.logFailureDetails("SiteId field is failed");
            ExtentReportManager.logInfoDetails("Expected SiteId is "+CommonUtilities.getSiteId()+" but the Actual SiteId is "+response.jsonPath().getString("SiteId"));
        }
        Assert.assertEquals(response.jsonPath().getString("SiteId"), String.valueOf(CommonUtilities.getSiteId()));


        if (response.jsonPath().get("taskType").equals(requestPayload.get("taskType"))){
            ExtentReportManager.logPassDetails("taskType field is passed");
            ExtentReportManager.logInfoDetails("Expected taskType is "+requestPayload.get("taskType")+" and the Actual taskType is "+response.jsonPath().get("taskType"));
        } else {
            ExtentReportManager.logFailureDetails("taskType field is failed");
            ExtentReportManager.logInfoDetails("Expected taskType is "+requestPayload.get("taskType")+" but the Actual taskType is "+response.jsonPath().get("taskType"));
        }
        Assert.assertEquals(response.jsonPath().get("taskType"), requestPayload.get("taskType"));

        ExtentReportManager.logInfoDetails("articleDetails field validation -");
        Map<String, Object> actualArticleDetails = (Map<String, Object>) response.jsonPath().get("articleDetails");
        Map<String, Object> expectedArticleDetails = (Map<String, Object>) requestPayload.get("articleDetails");

        if (actualArticleDetails.get("ean").equals(expectedArticleDetails.get("ean"))){
            ExtentReportManager.logPassDetails("ean field is passed");
            ExtentReportManager.logInfoDetails("Expected ean is "+expectedArticleDetails.get("ean")+" and the Actual ean is "+actualArticleDetails.get("ean"));
        } else {
            ExtentReportManager.logFailureDetails("ean field is failed");
            ExtentReportManager.logInfoDetails("Expected ean is "+expectedArticleDetails.get("ean")+" but the Actual ean is "+actualArticleDetails.get("ean"));
        }
        Assert.assertEquals(actualArticleDetails.get("ean"), expectedArticleDetails.get("ean"));

        if (actualArticleDetails.get("articleCode").equals(expectedArticleDetails.get("articleCode"))){
            ExtentReportManager.logPassDetails("articleCode field is passed");
            ExtentReportManager.logInfoDetails("Expected articleCode is "+expectedArticleDetails.get("articleCode")+" and the Actual articleCode is "+actualArticleDetails.get("articleCode"));
        } else {
            ExtentReportManager.logFailureDetails("articleCode field is failed");
            ExtentReportManager.logInfoDetails("Expected articleCode is "+expectedArticleDetails.get("articleCode")+" but the Actual articleCode is "+actualArticleDetails.get("articleCode"));
        }
        Assert.assertEquals(actualArticleDetails.get("articleCode"), expectedArticleDetails.get("articleCode"));

        if (actualArticleDetails.get("articleName").equals(expectedArticleDetails.get("articleName"))){
            ExtentReportManager.logPassDetails("articleName field is passed");
            ExtentReportManager.logInfoDetails("Expected articleName is "+expectedArticleDetails.get("articleName")+" and the Actual articleName is "+actualArticleDetails.get("articleName"));
        } else {
            ExtentReportManager.logFailureDetails("articleName field is failed");
            ExtentReportManager.logInfoDetails("Expected articleName is "+expectedArticleDetails.get("articleName")+" but the Actual articleName is "+actualArticleDetails.get("articleName"));
        }
        Assert.assertEquals(actualArticleDetails.get("articleName"), expectedArticleDetails.get("articleName"));

        if (actualArticleDetails.get("articlePrice").equals(expectedArticleDetails.get("articlePrice"))){
            ExtentReportManager.logPassDetails("articlePrice field is passed");
            ExtentReportManager.logInfoDetails("Expected articlePrice is "+expectedArticleDetails.get("articlePrice")+" and the Actual articlePrice is "+actualArticleDetails.get("articlePrice"));
        } else {
            ExtentReportManager.logFailureDetails("articlePrice field is failed");
            ExtentReportManager.logInfoDetails("Expected articlePrice is "+expectedArticleDetails.get("articlePrice")+" but the Actual articlePrice is "+actualArticleDetails.get("articlePrice"));
        }
        Assert.assertEquals(actualArticleDetails.get("articlePrice"), expectedArticleDetails.get("articlePrice"));

        if (actualArticleDetails.get("articleMrp").equals(expectedArticleDetails.get("articleMrp"))){
            ExtentReportManager.logPassDetails("articleMrp field is passed");
            ExtentReportManager.logInfoDetails("Expected articlePrice is "+expectedArticleDetails.get("articleMrp")+" and the Actual articleMrp is "+actualArticleDetails.get("articleMrp"));
        } else {
            ExtentReportManager.logFailureDetails("articleMrp field is failed");
            ExtentReportManager.logInfoDetails("Expected articlePrice is "+expectedArticleDetails.get("articleMrp")+" but the Actual articleMrp is "+actualArticleDetails.get("articleMrp"));
        }
        Assert.assertEquals(actualArticleDetails.get("articleMrp"), expectedArticleDetails.get("articleMrp"));

        if (actualArticleDetails.get("categoryCode").equals(expectedArticleDetails.get("categoryCode"))){
            ExtentReportManager.logPassDetails("categoryCode field is passed");
            ExtentReportManager.logInfoDetails("Expected categoryCode is "+expectedArticleDetails.get("categoryCode")+" and the Actual categoryCode is "+actualArticleDetails.get("categoryCode"));
        } else {
            ExtentReportManager.logFailureDetails("categoryCode field is failed");
            ExtentReportManager.logInfoDetails("Expected categoryCode is "+expectedArticleDetails.get("categoryCode")+" but the Actual categoryCode is "+actualArticleDetails.get("categoryCode"));
        }
        Assert.assertEquals(actualArticleDetails.get("categoryCode"), expectedArticleDetails.get("categoryCode"));

        if (actualArticleDetails.get("uom").equals("")){
            ExtentReportManager.logPassDetails("uom field is passed");
            ExtentReportManager.logInfoDetails("Expected uom is \"\" and the Actual uom is \"\" ");
        } else {
            ExtentReportManager.logFailureDetails("uom field is failed");
            ExtentReportManager.logInfoDetails("Expected uom is \"\" but the Actual uom is "+actualArticleDetails.get("uom"));
        }
        Assert.assertEquals(actualArticleDetails.get("uom"), "");

        if (actualArticleDetails.get("caselot").equals(0)){
            ExtentReportManager.logPassDetails("caselot field is passed");
            ExtentReportManager.logInfoDetails("Expected caselot is 0 and the Actual caselot is "+actualArticleDetails.get("caselot"));
        } else {
            ExtentReportManager.logFailureDetails("caselot field is failed");
            ExtentReportManager.logInfoDetails("Expected caselot is 0 but the Actual caselot is "+actualArticleDetails.get("caselot"));
        }
        Assert.assertEquals(actualArticleDetails.get("caselot"), 0);

        if (response.jsonPath().getString("requester").equals(CommonUtilities.getRequester())){
            ExtentReportManager.logPassDetails("requester field is passed");
            ExtentReportManager.logInfoDetails("Expected requester is "+CommonUtilities.getRequester()+" and the Actual requester is "+response.jsonPath().getString("requester"));
        } else {
            ExtentReportManager.logFailureDetails("requester field is failed");
            ExtentReportManager.logInfoDetails("Expected requester is "+CommonUtilities.getRequester()+" but the Actual requester is "+response.jsonPath().getString("requester"));
        }
        Assert.assertEquals(response.jsonPath().getString("requester"), CommonUtilities.getRequester());

        if (response.jsonPath().getString("Status").equals("OPEN")){
            ExtentReportManager.logPassDetails("Status field is passed");
            ExtentReportManager.logInfoDetails("Expected Status is OPEN and the Actual requester is "+response.jsonPath().getString("Status"));
        } else {
            ExtentReportManager.logFailureDetails("Status field is failed");
            ExtentReportManager.logInfoDetails("Expected Status is OPEN but the Actual requester is "+response.jsonPath().getString("Status"));
        }
        Assert.assertEquals(response.jsonPath().getString("Status"), "OPEN");

        if (response.jsonPath().get("requestedAt").equals(requestPayload.get("requestedAt"))){
            ExtentReportManager.logPassDetails("requestedAt field is passed");
            ExtentReportManager.logInfoDetails("Expected requestedAt is "+requestPayload.get("requestedAt")+" and the Actual requestedAt is "+response.jsonPath().get("requestedAt"));
        } else {
            ExtentReportManager.logFailureDetails("requestedAt field is failed");
            ExtentReportManager.logInfoDetails("Expected requestedAt is "+requestPayload.get("requestedAt")+" but the Actual requestedAt is "+response.jsonPath().get("requestedAt"));
        }
        Assert.assertEquals(response.jsonPath().get("requestedAt"), requestPayload.get("requestedAt"));

        if (response.jsonPath().get("requesterComments").equals(requestPayload.get("requesterComments"))){
            ExtentReportManager.logPassDetails("requesterComments field is passed");
            ExtentReportManager.logInfoDetails("Expected requesterComments is "+requestPayload.get("requesterComments")+" and the Actual requesterComments is "+response.jsonPath().get("requesterComments"));
        } else {
            ExtentReportManager.logFailureDetails("requesterComments field is failed");
            ExtentReportManager.logInfoDetails("Expected requesterComments is "+requestPayload.get("requesterComments")+" but the Actual requesterComments is "+response.jsonPath().get("requesterComments"));
        }
        Assert.assertEquals(response.jsonPath().get("requesterComments"), requestPayload.get("requesterComments"));
    }

}
