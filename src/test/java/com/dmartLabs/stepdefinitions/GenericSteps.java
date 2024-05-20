package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.GenricUtils;
import com.dmartLabs.config.PropertyReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import io.restassured.module.jsv.JsonSchemaValidator;
import rst.pdfbox.layout.text.Constants;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.dmartLabs.config.ConStants.JSON_SCHEMA_VALIDATION_PATH;

public class GenericSteps extends Constants {

    public static String username;
    public static String password;
    public static Map<String, String> userCredential;
    public static Map<String, String> requestId;
    public static Map<String, String> requestedAt;
    public static LinkedHashMap<String, Object> articleDetails;
    public static Map<String, String> requesterComments;
    public static Map<String, String> taskType;

    @Given("Send Generic data using Feature File")
    public void sendGenericDataUsingFeatureFile(DataTable dataTable) {

        userCredential = dataTable.asMap(String.class, String.class);
        username = userCredential.get("username");
        password = userCredential.get("password");

    }

    @Given("Give Username and Password to get Access Token")
    public void giveUsernameAndPasswordForAccessToken(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Give Username and Password for Access Token");
        userCredential = dataTable.asMap(String.class, String.class);

    }

    @Then("verify that status code be equal to {string}")
    public void verifyThatStatusCodeBeEqualTo(String expectedStatusCode) {
        ExtentReportManager.logInfoDetails("Verify that status code be equal to "+expectedStatusCode);

        int statucode = Integer.parseInt(String.valueOf(CommonUtilities.getResponseInstance().getStatusCode()));
        if (statucode == Integer.parseInt(expectedStatusCode)) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("Expected Status Code is "+statucode+ " and the Actual Status Code is " +CommonUtilities.getResponseInstance().getStatusCode());
        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("Expected status code is " + expectedStatusCode + " and Actual status code is " + CommonUtilities.getResponseInstance().getStatusCode());
        }

        CommonUtilities.getResponseInstance().then().assertThat().statusCode(Integer.parseInt(expectedStatusCode));
    }

    @Then("Verify that status code be equal to {string}")
    public void verifyThatStatusCodeBeEqual(String expectedStatusCode) {
        ExtentReportManager.logInfoDetails("Verify that status code be equal to "+expectedStatusCode);

        int statuscode = Integer.parseInt(String.valueOf(CommonUtilities.getResponseInstance().getStatusCode()));
        if (statuscode == Integer.parseInt(expectedStatusCode)) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("Expected Status Code is "+statuscode+ " and the Actual Status Code is " +CommonUtilities.getResponseInstance().getStatusCode());
        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("Expected status code is " + expectedStatusCode + " but the Actual status code is " + CommonUtilities.getResponseInstance().getStatusCode());
        }

        CommonUtilities.getResponseInstance().then().assertThat().statusCode(Integer.parseInt(expectedStatusCode));
    }



    @And("verify that schema should be equal {string} for Create Task")
    public void verifyThatSchemaShouldBeEqualForCreateTask(String schemaFile) {
        ExtentReportManager.logInfoDetails("verify that schema should be equal "+schemaFile+"for Create Task");
        CommonUtilities.getResponseInstance().then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(PropertyReader.fileReaders(JSON_SCHEMA_VALIDATION_PATH, schemaFile)));
        JsonSchemaValidator schema = JsonSchemaValidator.matchesJsonSchema(PropertyReader.fileReaders(JSON_SCHEMA_VALIDATION_PATH, schemaFile));
        ExtentReportManager.logInfoDetails(" Schema is pass "+ schema);
//        System.out.println("===========================>"+"schema validation successful");

    }

    public static void validateTheResponseTime(long responseTime) {
        if (responseTime<500){
            ExtentReportManager.logPassDetails("Response time Passed");
            ExtentReportManager.logInfoDetails("Response Time is "+responseTime+" ms");
        } else {
            ExtentReportManager.logFailureDetails("Response time Failed");
            ExtentReportManager.logInfoDetails("Response Time is "+responseTime+" ms");
        }
    }

    @And("Get the unique requestId for a Task to be created")
    public void getTheUniqueRequestIdForATaskToBeCreated() {
        ExtentReportManager.logInfoDetails("Get the unique requestId for a Task to be created");
        requestId = new HashMap<>();
        requestId.put("requestId", GenricUtils.generateUUID());
    }

    @And("Get the requestAt time for a Task to be created")
    public void getTheRequestAtTimeForATaskToBeCreated() {
        ExtentReportManager.logInfoDetails("Get the requestAt time for a Task to be created");
        requestedAt = new HashMap<>();
        requestedAt.put("requestedAt", GenricUtils.getUTC_Format_CurrentDateTime());
    }

//    @And("Provide article details to create task")
//    public void provideArticleDetailsToCreateRefillTask(DataTable dataTable) {
//        ExtentReportManager.logInfoDetails("Provide article details to create Refill task");
//        articleDetails = new LinkedHashMap<>();
//        articleDetails.putAll(dataTable.asMap(String.class, Object.class));
//        if (articleDetails.containsKey("caselot")) {
//            int caselot = Integer.parseInt(String.valueOf(articleDetails.get("caselot")));
//            articleDetails.put("caselot", caselot);
//        }
//    }
//
//    @And("Give requester comments for a task")
//    public void giveRequesterCommentsForATask(DataTable dataTable) {
//        ExtentReportManager.logInfoDetails("Give requester comments for a task");
//        requesterComments = new HashMap<>();
//        requesterComments = dataTable.asMap(String.class, String.class);
//    }

    @And("Give task type for a new task to be created")
    public void giveTaskTypeForANewTaskToBeCreated(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Give task type for a new task to be created");
        taskType = dataTable.asMap(String.class, String.class);
    }

    @And("Verify that Create Task schema should be equal to {string}")
    public void verifyThatCreateTaskSchemaShouldBeEqualTo(String schemaFileName) {
        ExtentReportManager.logInfoDetails("Verify that Create Task schema should be equal to "+schemaFileName);
        CommonUtilities.getResponseInstance().then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(PropertyReader.fileReaders(JSON_SCHEMA_VALIDATION_PATH, schemaFileName)));
        JsonSchemaValidator schema = JsonSchemaValidator.matchesJsonSchema(PropertyReader.fileReaders(JSON_SCHEMA_VALIDATION_PATH, schemaFileName));
        ExtentReportManager.logInfoDetails(" Schema is pass "+ schema);
    }
}
