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

import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import static com.dmartLabs.config.ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH;

public class UpdateFloorWalkSteps {

    private static LinkedHashMap<String, String> requestPayload = new LinkedHashMap<>();
    private Response response;

    @And("Get the floorwalkId to close the Floorwalk")
    public void getTheFloorwalkIdToCloseTheFloorwalk() {
        ExtentReportManager.logInfoDetails("Get the floorwalkId to close the Floorwalk");
        requestPayload = new LinkedHashMap<>();
        requestPayload.put("floor_walk_id", CommonUtilities.getFloorWalkId());
    }

    @And("Give random floorwalkId not generated")
    public void giveFloorwalkIdNotGenerated() {
        ExtentReportManager.logInfoDetails("Give floorwalkId not generated");
        requestPayload.put("floor_walk_id", GenricUtils.generateUUID());
    }

    @And("Give invalid floorwalkId {string}")
    public void giveInvalidFloorwalkId(String floorwalkId) {
        ExtentReportManager.logInfoDetails("Give invalid floorwalkId");
        if (floorwalkId.equals("null"))
            floorwalkId = null;

        requestPayload.put("floor_walk_id", floorwalkId);
    }

    @And("Provide the time of closing the Floorwalk")
    public void provideTheTimeOfClosingTheFloorwalk() {
        ExtentReportManager.logInfoDetails("Provide the time of closing the Floorwalk");
        requestPayload.put("completedAt", GenricUtils.getUTC_Format_CurrentDateTime());
    }

    @And("Give the status for closing the Floorwalk {string}")
    public void giveTheStatusForClosingTheFloorwalk(String status) {
        ExtentReportManager.logInfoDetails("Give the status for closing the Floorwalk");
        requestPayload.put("status", status);
    }

    @And("Give the invalid status for closing the Floorwalk {string}")
    public void giveTheInvalidStatusForClosingTheFloorwalk(String status) {
        ExtentReportManager.logInfoDetails("Give the invalid status for closing the Floorwalk");
        if (status.equals("null"))
            status = null;
        requestPayload.put("status", status);
    }

    @When("Requester call the Update Floorwalk endpoint to close the Floorwalk")
    public void requesterCallTheUpdateFloorwalkEndpointToCloseTheFloorwalk() {
        ExtentReportManager.logInfoDetails("Requester call the Update Floorwalk endpoint to close the Floorwalk");
        RequestSpecification requestSpecification = RequestGenerator.getRequest(CommonUtilities.genericHeader(), requestPayload).log().all();
        response = requestSpecification.put(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "UPDATE_FLOORWALK"));
        response.then().log().all();
        ExtentReportManager.logInfoDetails("Status Code for Update Floorwalk is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Update Floorwalk Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time for Updating Floorwalk is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }

    @And("Check the response time for closing the Floorwalk")
    public void checkTheResponseTimeForClosingTheFloorwalk() {
        ExtentReportManager.logInfoDetails("Check the response time for closing the Floorwalk");
        GenericSteps.validateTheResponseTime(response.getTimeIn(TimeUnit.MILLISECONDS));
    }

    @And("Verify that Floorwalk is closed successfully")
    public void verifyThatFloorwalkIsClosedSuccessfully() {
        ExtentReportManager.logInfoDetails("Verify that Floorwalk is closed successfully");
        if (response.jsonPath().getString("floor_walk_id").equals(requestPayload.get("floor_walk_id"))){
            ExtentReportManager.logPassDetails("floor_walk_id field is passed");
            ExtentReportManager.logInfoDetails("Expected floor_walk_id is "+requestPayload.get("floor_walk_id")+" and the Actual floor_walk_id is "+response.jsonPath().getString("floor_walk_id"));
        } else {
            ExtentReportManager.logFailureDetails("floor_walk_id field is failed");
            ExtentReportManager.logInfoDetails("Expected floor_walk_id is "+requestPayload.get("floor_walk_id")+" but the Actual floor_walk_id is "+response.jsonPath().getString("floor_walk_id"));
        }
        Assert.assertEquals(response.jsonPath().getString("floor_walk_id"), requestPayload.get("floor_walk_id"));

//        if (response.jsonPath().getString("completedAt").equals(requestPayload.get("completedAt"))){
//            ExtentReportManager.logPassDetails("completedAt field is passed");
//            ExtentReportManager.logInfoDetails("Expected completedAt is "+requestPayload.get("completedAt")+" and the Actual completedAt is "+response.jsonPath().getString("completedAt"));
//        } else {
//            ExtentReportManager.logFailureDetails("completedAt field is failed");
//            ExtentReportManager.logInfoDetails("Expected completedAt is "+requestPayload.get("completedAt")+" but the Actual completedAt is "+response.jsonPath().getString("completedAt"));
//        }
//        Assert.assertEquals(response.jsonPath().getString("completedAt"), requestPayload.get("completedAt"));

        if (response.jsonPath().getString("status").equals(requestPayload.get("status"))){
            ExtentReportManager.logPassDetails("status field is passed");
            ExtentReportManager.logInfoDetails("Expected status is "+requestPayload.get("status")+" and the Actual status is "+response.jsonPath().getString("status"));
        } else {
            ExtentReportManager.logFailureDetails("status field is failed");
            ExtentReportManager.logInfoDetails("Expected status is "+requestPayload.get("status")+" but the Actual status is "+response.jsonPath().getString("status"));
        }
        Assert.assertEquals(response.jsonPath().getString("status"), requestPayload.get("status"));
    }

}
