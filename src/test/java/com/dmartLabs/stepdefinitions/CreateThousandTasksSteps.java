package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExcelUtils;
import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.GenricUtils;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.ConStants;
import com.dmartLabs.config.PropertyReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CreateThousandTasksSteps {

    private List<LinkedHashMap<String, Object>> allTasks;
    private GenericSteps genericSteps;
    private CreateThousandTasksSteps createThousandTasksSteps;
    private Response responses;
    private RequestGenerator requestGenerator;

    @And("Get all task from Excel file")
    public void getAllTaskFromExcelFile(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Get all task from Excel file");
        Map<String, String> excelFileDetails = dataTable.asMap(String.class, String.class);
        ExcelUtils excelUtils = new ExcelUtils(excelFileDetails.get("fileName"));
        allTasks = excelUtils.getTaskAllRequestPayload(excelFileDetails.get("sheetName"));
    }

    @When("User calls create task endpoint to create one thousand tasks")
    public void userCallsCreateTaskEndpointToCreateOneThousandTasks() {
        ExtentReportManager.logInfoDetails("User calls create task endpoint to create one thousand tasks");
        requestGenerator = new RequestGenerator();
        genericSteps = new GenericSteps();
        createThousandTasksSteps = new CreateThousandTasksSteps();
        for (LinkedHashMap<String, Object> task:allTasks){
            task.put("requestId", GenricUtils.generateUUID());
            task.put("requestedAt", GenricUtils.getUTC_Format_CurrentDateTime());
            responses = requestGenerator.getRequest(CommonUtilities.genericHeader(), task).log().all()
                    .when().post(PropertyReader.getProperty(ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH, "CREATE_TASK"));
            responses.then().log().all();
            long responseTime = responses.getTimeIn(TimeUnit.MILLISECONDS);
            CommonUtilities.setResponseInstance(responses);
            CommonUtilities.setTask(task);
            genericSteps.verifyThatStatusCodeBeEqualTo("201");
            genericSteps.validateTheResponseTime(responseTime);
            createThousandTasksSteps.verifyThatRequestPayloadIsSameAsResponsePayload(responses);
            if (task.get("taskType").equals("PRICE BOARD")){
                genericSteps.verifyThatSchemaShouldBeEqualForCreateTask("CreatePriceBoardTaskSchema.json");
            } else if (task.get("taskType").equals("REFILL")){
                genericSteps.verifyThatSchemaShouldBeEqualForCreateTask("CreateRefillTask.json");
            } else {
                genericSteps.verifyThatSchemaShouldBeEqualForCreateTask("CreateOthersTaskSchema.json");
            }
        }
    }

    @And("verify that requestPayload is same as responsePayload")
    public void verifyThatRequestPayloadIsSameAsResponsePayload(Response responses) {
        ExtentReportManager.logInfoDetails("verify that requestPayload is same as responsePayload");

        LinkedHashMap<String, Object> response = responses.as(new TypeRef<LinkedHashMap<String, Object>>() {});
        LinkedHashMap<String, Object> taskRequestPayload = CommonUtilities.getTask();
        if (response.get("SiteId").equals((Object) String.valueOf(CommonUtilities.getSiteId()))){
            ExtentReportManager.logPassDetails("SiteId field is Passed");
            ExtentReportManager.logInfoDetails("Expected siteId is "+CommonUtilities.getSiteId()+" and the Actual siteId is "+response.get("SiteId"));
        } else {
            ExtentReportManager.logFailureDetails("SiteId field is Failed");
            ExtentReportManager.logInfoDetails("Expected siteId is "+CommonUtilities.getSiteId()+" and the Actual siteId is "+response.get("SiteId"));
        }
        Assert.assertEquals(response.get("SiteId"), (Object) String.valueOf(CommonUtilities.getSiteId()));

        if (response.get("requestId").equals(taskRequestPayload.get("requestId"))){
            ExtentReportManager.logPassDetails("requestId field is Passed");
            ExtentReportManager.logInfoDetails("Expected requestId is "+taskRequestPayload.get("requestId")+" and the Actual requestId is "+response.get("requestId"));
        } else {
            ExtentReportManager.logPassDetails("requestId field is Failed");
            ExtentReportManager.logFailureDetails("Expected requestId is "+taskRequestPayload.get("requestId")+" and the Actual requestId is "+response.get("requestId"));
        }
        Assert.assertEquals(response.get("requestId"), taskRequestPayload.get("requestId"));

        if (response.get("taskType").equals(taskRequestPayload.get("taskType"))){
            ExtentReportManager.logPassDetails("taskType field is Passed");
            ExtentReportManager.logInfoDetails("Expected taskType is "+taskRequestPayload.get("taskType")+" and the Actual taskType is "+response.get("taskType"));
        } else {
            ExtentReportManager.logFailureDetails("taskType field is Failed");
            ExtentReportManager.logInfoDetails("Expected taskType is "+taskRequestPayload.get("taskType")+" and the Actual taskType is "+response.get("taskType"));
        }
        Assert.assertEquals(response.get("taskType"), taskRequestPayload.get("taskType"));

        Map<String, Object> actualArticleDetails = (Map<String, Object>) response.get("articleDetails");
        Map<String, Object> expectedArticleDetails = (Map<String, Object>) taskRequestPayload.get("articleDetails");
        verifyArticleDetailsField(actualArticleDetails, expectedArticleDetails);

        if (response.get("Status").equals("OPEN")){
            ExtentReportManager.logPassDetails("Status field is Passed");
            ExtentReportManager.logInfoDetails("Expected Status is OPEN and the Actual Status is "+response.get("Status"));
        } else {
            ExtentReportManager.logFailureDetails("Status field is Failed");
            ExtentReportManager.logInfoDetails("Expected Status is OPEN and the Actual Status is "+response.get("Status"));
        }
        Assert.assertEquals(response.get("Status"), "OPEN");

        if (response.get("requestedAt").equals(taskRequestPayload.get("requestedAt"))){
            ExtentReportManager.logPassDetails("requestedAt field is Passed");
            ExtentReportManager.logInfoDetails("Expected requestedAt is "+taskRequestPayload.get("requestedAt")+" and the Actual requestedAt is "+response.get("requestedAt"));
        } else {
            ExtentReportManager.logFailureDetails("requestedAt field is Failed");
            ExtentReportManager.logInfoDetails("Expected requestedAt is "+taskRequestPayload.get("requestedAt")+" and the Actual requestedAt is "+response.get("requestedAt"));
        }
        Assert.assertEquals(response.get("requestedAt"), taskRequestPayload.get("requestedAt"));

        if (response.containsKey("requestedQuantity")) {
            if (response.get("requestedQuantity").equals(taskRequestPayload.get("requestedQuantity"))){
                ExtentReportManager.logPassDetails("requestedQuantity field is Passed");
                ExtentReportManager.logInfoDetails("Expected requestedQuantity is "+taskRequestPayload.get("requestedQuantity")+" and the Actual requestedQuantity is "+response.get("requestedQuantity"));
            } else {
                ExtentReportManager.logFailureDetails("requestedQuantity field is Failed");
                ExtentReportManager.logInfoDetails("Expected requestedQuantity is "+taskRequestPayload.get("requestedQuantity")+" and the Actual requestedQuantity is "+response.get("requestedQuantity"));
            }
            Assert.assertEquals(response.get("requestedQuantity"), taskRequestPayload.get("requestedQuantity"));
        }

        if (response.get("taskType").equals("OTHERS")) {
            if (response.get("requesterComments").equals(taskRequestPayload.get("requesterComments"))){
                ExtentReportManager.logPassDetails("requesterComments field is Passed");
                ExtentReportManager.logInfoDetails("Expected requesterComments is "+taskRequestPayload.get("requesterComments")+" and the Actual requesterComments is "+response.get("requesterComments"));
            } else {
                ExtentReportManager.logFailureDetails("requesterComments field is Failed");
                ExtentReportManager.logInfoDetails("Expected requesterComments is "+taskRequestPayload.get("requesterComments")+" and the Actual requesterComments is "+response.get("requesterComments"));
            }
            Assert.assertEquals(response.get("requesterComments"), taskRequestPayload.get("requesterComments"));
        }

        if (response.containsKey("priceBoards")){
            List<Map<String, Object>> actualPriceboards = (List<Map<String, Object>>) response.get("priceBoards");
            List<Map<String, Object>> expectedPriceBoards = (List<Map<String, Object>>) taskRequestPayload.get("priceBoards");
            verifyPriceboardField(actualPriceboards, expectedPriceBoards);
        }

    }

    private void verifyArticleDetailsField(Map<String, Object> actualArticleDetails, Map<String, Object> expectedArticleDetails) {
        if (actualArticleDetails.get("ean").equals(expectedArticleDetails.get("ean"))){
            ExtentReportManager.logPassDetails("ean field is Passed");
            ExtentReportManager.logInfoDetails("Expected ean is "+expectedArticleDetails.get("ean")+" and the Actual ean is "+actualArticleDetails.get("ean"));
        } else {
            ExtentReportManager.logFailureDetails("ean field is Failed");
            ExtentReportManager.logInfoDetails("Expected ean is "+expectedArticleDetails.get("ean")+" and the Actual ean is "+actualArticleDetails.get("ean"));
        }
        Assert.assertEquals(actualArticleDetails.get("ean"), expectedArticleDetails.get("ean"));

        if (actualArticleDetails.get("articleCode").equals(expectedArticleDetails.get("articleCode"))){
            ExtentReportManager.logPassDetails("articleCode field is Passed");
            ExtentReportManager.logInfoDetails("Expected articleCode is "+expectedArticleDetails.get("articleCode")+" and the Actual articleCode is "+actualArticleDetails.get("articleCode"));
        } else {
            ExtentReportManager.logFailureDetails("articleCode field is Failed");
            ExtentReportManager.logInfoDetails("Expected articleCode is "+expectedArticleDetails.get("articleCode")+" and the Actual articleCode is "+actualArticleDetails.get("articleCode"));
        }
        Assert.assertEquals(actualArticleDetails.get("articleCode"), expectedArticleDetails.get("articleCode"));

        if (actualArticleDetails.get("articleName").equals(expectedArticleDetails.get("articleName"))){
            ExtentReportManager.logPassDetails("articleName field is Passed");
            ExtentReportManager.logInfoDetails("Expected articleName is "+expectedArticleDetails.get("articleName")+" and the Actual articleName is "+actualArticleDetails.get("articleName"));
        } else {
            ExtentReportManager.logFailureDetails("articleName field is Failed");
            ExtentReportManager.logInfoDetails("Expected articleName is "+expectedArticleDetails.get("articleName")+" and the Actual articleName is "+actualArticleDetails.get("articleName"));
        }
        Assert.assertEquals(actualArticleDetails.get("articleName"), expectedArticleDetails.get("articleName"));

        if (actualArticleDetails.get("articlePrice").equals(expectedArticleDetails.get("articlePrice"))){
            ExtentReportManager.logPassDetails("articlePrice field is Passed");
            ExtentReportManager.logInfoDetails("Expected articlePrice is "+expectedArticleDetails.get("articlePrice")+" and the Actual articlePrice is "+actualArticleDetails.get("articlePrice"));
        } else {
            ExtentReportManager.logFailureDetails("articlePrice field is Failed");
            ExtentReportManager.logInfoDetails("Expected articlePrice is "+expectedArticleDetails.get("articlePrice")+" and the Actual articlePrice is "+actualArticleDetails.get("articlePrice"));
        }
        Assert.assertEquals(actualArticleDetails.get("articlePrice"), expectedArticleDetails.get("articlePrice"));

        if (actualArticleDetails.get("articleMrp").equals(expectedArticleDetails.get("articleMrp"))){
            ExtentReportManager.logPassDetails("articleMrp field is Passed");
            ExtentReportManager.logInfoDetails("Expected articleMrp is "+expectedArticleDetails.get("articleMrp")+" and the Actual articleMrp is "+actualArticleDetails.get("articleMrp"));
        } else {
            ExtentReportManager.logFailureDetails("articleMrp field is Failed");
            ExtentReportManager.logInfoDetails("Expected articleMrp is "+expectedArticleDetails.get("articleMrp")+" and the Actual articleMrp is "+actualArticleDetails.get("articleMrp"));
        }
        Assert.assertEquals(actualArticleDetails.get("articleMrp"), expectedArticleDetails.get("articleMrp"));

        if (actualArticleDetails.get("categoryCode").equals(expectedArticleDetails.get("categoryCode"))){
            ExtentReportManager.logPassDetails("categoryCode field is Passed");
            ExtentReportManager.logInfoDetails("Expected categoryCode is "+expectedArticleDetails.get("categoryCode")+" and the Actual categoryCode is "+actualArticleDetails.get("categoryCode"));
        } else {
            ExtentReportManager.logFailureDetails("categoryCode field is Failed");
            ExtentReportManager.logInfoDetails("Expected categoryCode is "+expectedArticleDetails.get("categoryCode")+" and the Actual categoryCode is "+actualArticleDetails.get("categoryCode"));
        }
        Assert.assertEquals(actualArticleDetails.get("categoryCode"), expectedArticleDetails.get("categoryCode"));

        if (actualArticleDetails.get("articlePrice").equals(expectedArticleDetails.get("articlePrice"))){
            ExtentReportManager.logPassDetails("articlePrice field is Passed");
            ExtentReportManager.logInfoDetails("Expected articlePrice is "+expectedArticleDetails.get("articlePrice")+" and the Actual articlePrice is "+actualArticleDetails.get("articlePrice"));
        } else {
            ExtentReportManager.logFailureDetails("articlePrice field is Failed");
            ExtentReportManager.logInfoDetails("Expected articlePrice is "+expectedArticleDetails.get("articlePrice")+" and the Actual articlePrice is "+actualArticleDetails.get("articlePrice"));
        }
        Assert.assertEquals(actualArticleDetails.get("articlePrice"), expectedArticleDetails.get("articlePrice"));
    }

    private void verifyPriceboardField(List<Map<String, Object>> actualPriceboards, List<Map<String, Object>> expectedPriceBoards) {
        if (actualPriceboards.size()==expectedPriceBoards.size()){
            for (int i=0; i<actualPriceboards.size(); i++){
                Object actualPriceboardName = actualPriceboards.get(i).get("priceBoard");
                Object actualPbStatus = actualPriceboards.get(i).get("pbStatus");
                Object expectedPriceBoardName = expectedPriceBoards.get(i).get("priceBoard");
                Object expectedPbStatus = expectedPriceBoards.get(i).get("pbStatus");

                if (actualPriceboardName.equals(expectedPriceBoardName) && actualPbStatus.equals(expectedPbStatus)){
                    ExtentReportManager.logPassDetails("priceBoards field is Passed");
                    ExtentReportManager.logInfoDetails("Expected priceboard is "+expectedPriceBoardName+" and the Actual priceboard is "+actualPriceboardName);
                    ExtentReportManager.logInfoDetails("Expected pbStatus is "+expectedPbStatus+" and the Actual pbStatus is "+actualPbStatus);
                } else {
                    ExtentReportManager.logFailureDetails("priceBoards field is Failed");
                    ExtentReportManager.logInfoDetails("Expected priceboard is "+expectedPriceBoardName+" and the Actual priceboard is "+actualPriceboardName);
                    ExtentReportManager.logInfoDetails("Expected pbStatus is "+expectedPbStatus+" and the Actual pbStatus is "+actualPbStatus);
                }
                Assert.assertEquals(actualPriceboardName, expectedPriceBoardName);
                Assert.assertEquals(actualPbStatus, expectedPbStatus);
            }
        } else {
            ExtentReportManager.logFailureDetails("priceBoards field is Failed");
        }
    }

}
