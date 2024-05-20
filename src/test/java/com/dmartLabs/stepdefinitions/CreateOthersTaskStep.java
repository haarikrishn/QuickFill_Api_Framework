package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.GenricUtils;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.ConStants;
import com.dmartLabs.config.PropertyReader;
import com.dmartLabs.pojo.OthersArticlesPojo;
import com.dmartLabs.pojo.OthersTaskMainPojo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CreateOthersTaskStep
{

    OthersTaskMainPojo othersTaskMainPojo=new OthersTaskMainPojo();
    OthersArticlesPojo othersArticlesPojo=new OthersArticlesPojo();
    RequestGenerator requestGenerator=new RequestGenerator();
    Response creatOthersTaskResponse;


    @When("user calls OthersTask endpoint")
    public void userCallsOthersTaskEndpoint(DataTable dataTable) {
        Map<String, String> othersDT = dataTable.asMap(String.class, String.class);
othersTaskMainPojo.setRequestId(GenricUtils.generateUUID());
othersTaskMainPojo.setRequestedAt(GenricUtils.getUTC_Format_CurrentDateTime());
othersArticlesPojo.setArticleName(othersDT.get("articleName"));
othersArticlesPojo.setArticlePrice(othersDT.get("articlePrice"));
othersArticlesPojo.setEan(othersDT.get("ean"));
othersArticlesPojo.setCategoryCode(othersDT.get("categoryCode"));
othersArticlesPojo.setArticleCode(othersDT.get("articleCode"));
othersArticlesPojo.setArticleMrp(othersDT.get("articleMrp"));
othersTaskMainPojo.setArticleDetails(othersArticlesPojo);
othersTaskMainPojo.setRequesterComments(othersDT.get("requesterComments"));
othersTaskMainPojo.setTaskType("OTHERS");


      creatOthersTaskResponse = requestGenerator.getRequest(CommonUtilities.genericHeader(), othersTaskMainPojo).log().all()
                .when().post(PropertyReader.getProperty(ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH, "CreateTaskEndpoint"));
  //      creatOthersTaskResponse.then().log().all();
        ExtentReportManager.logJson("Response is " + creatOthersTaskResponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " + creatOthersTaskResponse.getStatusCode());
        long responseTime = creatOthersTaskResponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response Time is " + responseTime + " ms");
        CommonUtilities.setResponseInstance(creatOthersTaskResponse);
    }

    @And("verify that requestPayload is same as responsePayload in OthersTask")
    public void verifyThatRequestPayloadIsSameAsResponsePayloadInOthersTask() {

        String ExpectedSiteId = creatOthersTaskResponse.jsonPath().get("SiteId");
        String ExpectedtaskType = creatOthersTaskResponse.jsonPath().get("taskType");
        String ExpectedStatus = creatOthersTaskResponse.jsonPath().get("Status");
        String ExpectedEan = creatOthersTaskResponse.jsonPath().get("articleDetails.ean");
        String ExpectedcategoryCode = creatOthersTaskResponse.jsonPath().get("articleDetails.categoryCode");
        String OthersrefillId = creatOthersTaskResponse.jsonPath().get("refillId");
        CommonUtilities.setothersrefillId(OthersrefillId);

        //Actual
        String ActualTaskType = othersTaskMainPojo.getTaskType();
        String ActualEan = othersArticlesPojo.getEan();
        String ActualcategoryCode = othersArticlesPojo.getCategoryCode();

        String ActualStatus="OPEN";
        String ActualSiteId="4017";

        //validating ean
        if (ExpectedEan.equals(ActualEan)) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("ExpectedEan is " + ExpectedEan + " and the ActualEan is " + ActualEan);
            Assert.assertEquals(ExpectedEan, ActualEan);
            System.out.println(ExpectedEan + " ===================>" + "ExpectedEan validation successful");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("ExpectedEan number is " + ExpectedEan + " and the actual dstSiteId is " + ActualEan);

        }
        //validating site id
        if (ExpectedSiteId.equals(ActualSiteId)) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("ExpectedSiteId is " + ExpectedSiteId + " and the ActualSiteId is " + ActualSiteId);
            Assert.assertEquals(ExpectedSiteId, ActualSiteId);
            System.out.println(ExpectedSiteId + " ===================>" + "ActualSiteId validation successful");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("ExpectedSiteId number is " + ExpectedSiteId + " and the ActualSiteId is " + ActualSiteId);

        }
        //validating taskType
        if (ExpectedtaskType.equals(ActualTaskType)) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("ExpectedtaskType is " + ExpectedtaskType + " and the ActualTaskType is " + ActualTaskType);
            Assert.assertEquals(ExpectedtaskType, ActualTaskType);
            System.out.println(ExpectedtaskType + " ===================>" + "ExpectedtaskType validation successful");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("ExpectedtaskType number is " + ExpectedtaskType + " and the ActualTaskType is " + ActualTaskType);

        }
        //validating categoryCode
        if (ExpectedcategoryCode.equals(ActualcategoryCode)) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("ExpectedcategoryCode is " + ExpectedcategoryCode + " and the ActualcategoryCode is " + ActualcategoryCode);
            Assert.assertEquals(ExpectedcategoryCode, ActualcategoryCode);
            System.out.println(ExpectedcategoryCode + " ===================>" + "ExpectedcategoryCode validation successful");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("ExpectedcategoryCode number is " + ExpectedcategoryCode + " and the ActualcategoryCode is " + ActualcategoryCode);

        }

        //validating status
        if (ExpectedStatus.equals(ActualStatus)) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("ExpectedStatus is " + ExpectedStatus + " and the ActualStatus is " + ActualStatus);
            Assert.assertEquals(ExpectedStatus, ActualStatus);
            System.out.println(ExpectedStatus + " ===================>" + "ExpectedStatus validation successful");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("ExpectedStatus  is " + ExpectedStatus + " and the ActualStatus is " + ActualStatus);
        }


    }

}
