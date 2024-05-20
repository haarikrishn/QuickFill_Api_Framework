package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.GenricUtils;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.ConStants;
import com.dmartLabs.config.PropertyReader;
import com.dmartLabs.pojo.CreateRefillTaskMainPojo;
import com.dmartLabs.pojo.RefillArticleDetails;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CretaeRefillTaskStep {
    public  CreateRefillTaskMainPojo createRefillTaskMainPojo = new CreateRefillTaskMainPojo();
    public  RefillArticleDetails refillArticleDetailspojo = new RefillArticleDetails();
    RequestGenerator requestGenerator = new RequestGenerator();
    Response CreateRefillTaskresponse;


    @When("user calls create task endpoint")
    public void userCallsCreateTaskEndpoint(DataTable dataTable) {
        Map<String, String> createRefillDT = dataTable.asMap(String.class, String.class);
        createRefillTaskMainPojo.setRequestId(GenricUtils.generateUUID());
        createRefillTaskMainPojo.setRequestedAt(GenricUtils.getUTC_Format_CurrentDateTime());

        refillArticleDetailspojo.setArticleName(createRefillDT.get("articleName"));
        refillArticleDetailspojo.setArticlePrice(createRefillDT.get("articlePrice"));
        refillArticleDetailspojo.setEan(createRefillDT.get("ean"));
        refillArticleDetailspojo.setCategoryCode(createRefillDT.get("categoryCode"));
        refillArticleDetailspojo.setArticleCode(createRefillDT.get("articleCode"));
        refillArticleDetailspojo.setArticleMrp(createRefillDT.get("articleMrp"));
        refillArticleDetailspojo.setUom(createRefillDT.get("uom"));
        refillArticleDetailspojo.setCaselot(Integer.parseInt(createRefillDT.get("caselot")));

        createRefillTaskMainPojo.setArticleDetails(refillArticleDetailspojo);

        createRefillTaskMainPojo.setRequestedQuantity(Integer.parseInt(createRefillDT.get("requestedQuantity")));
        createRefillTaskMainPojo.setRequesterComments(createRefillDT.get("requesterComments"));
        createRefillTaskMainPojo.setTaskType(createRefillDT.get("taskType"));


        CreateRefillTaskresponse = requestGenerator.getRequest(CommonUtilities.genericHeader(), createRefillTaskMainPojo).log().all()
                .when().post(PropertyReader.getProperty(ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH, "CreateTaskEndpoint"));
     //   CreateRefillTaskresponse.then().log().all();
        ExtentReportManager.logJson("Response is " + CreateRefillTaskresponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " + CreateRefillTaskresponse.getStatusCode());
        long responseTime = CreateRefillTaskresponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response Time is " + responseTime + " ms");
        CommonUtilities.setResponseInstance(CreateRefillTaskresponse);
    }

    @And("verify that requestPayload is same as responsePayload in Create Task")
    public void verifyThatRequestPayloadIsSameAsResponsePayloadInCreateTask() {
        //expected
        String ExpectedSiteId = CreateRefillTaskresponse.jsonPath().get("SiteId");
        String ExpectedtaskType = CreateRefillTaskresponse.jsonPath().get("taskType");
        String ExpectedStatus = CreateRefillTaskresponse.jsonPath().get("Status");
        String ExpectedEan = CreateRefillTaskresponse.jsonPath().get("articleDetails.ean");
        String ExpectedcategoryCode = CreateRefillTaskresponse.jsonPath().get("articleDetails.categoryCode");
        String Expecteduom = CreateRefillTaskresponse.jsonPath().get("articleDetails.uom");
        int Expectedcaselot= CreateRefillTaskresponse.jsonPath().get("articleDetails.caselot");
        String RefillrefillId = CreateRefillTaskresponse.jsonPath().get("refillId");
        CommonUtilities.setRefillTaskRefillId(RefillrefillId);
        String FloorWalkId = CreateRefillTaskresponse.jsonPath().get("floorWalkId");
        CommonUtilities.setFloorWalkId(FloorWalkId);
      //Actual
        String ActualTaskType = createRefillTaskMainPojo.getTaskType();
        String ActualEan = refillArticleDetailspojo.getEan();
        String ActualcategoryCode = refillArticleDetailspojo.getCategoryCode();
        String Actualuom = refillArticleDetailspojo.getUom();
       int Actualcaselot = refillArticleDetailspojo.getCaselot();
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
        //validating uom(task is refill type)
        if (Expecteduom.equals(Actualuom)) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("Expecteduom is " + Expecteduom + " and the Actualuom is " + Actualuom);
            Assert.assertEquals(Expecteduom, Actualuom);
            System.out.println(Expecteduom + " ===================>" + "Expecteduom validation successful");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("Expecteduom number is " + Expecteduom + " and the Actualuom is " + Actualuom);

        }
        //validating caselot
        if (Expectedcaselot==Actualcaselot){
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("Expectedcaselot is " + Expecteduom + " and the Actualcaselot is " + Actualuom);
            Assert.assertEquals(Expectedcaselot, Actualcaselot);
            System.out.println(Expectedcaselot + " ===================>" + "Expectedcaselot validation successful");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("Expectedcaselot number is " + Expectedcaselot + " and the Actualcaselot is " + Actualcaselot);

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