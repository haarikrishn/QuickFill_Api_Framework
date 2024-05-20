package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.GenricUtils;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.ConStants;
import com.dmartLabs.config.PropertyReader;
import com.dmartLabs.pojo.PriceBoardArticleDetailsPojo;
import com.dmartLabs.pojo.priceBoardMainPojo;
import com.dmartLabs.pojo.priceBoardSubpojo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import rst.pdfbox.layout.text.Constants;
import  static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class CreatePriceBoardTaskStep extends Constants implements ConStants {
  public   priceBoardMainPojo priceboardmainPojo =new priceBoardMainPojo();

   public List<priceBoardSubpojo>priceboardsubpojoList=new ArrayList<>();
 // public   priceBoardSubpojo priceboardsubpojo=new priceBoardSubpojo();

public  PriceBoardArticleDetailsPojo  articleDetails=new PriceBoardArticleDetailsPojo();
RequestGenerator requestGenerator=new RequestGenerator();

    Response createpriceBoardResponse;
    public  static  List<Map<String, String>> priceboardDT;
//cretepriceboard task DT
    @When("user calls create priceBoard taskDT endpoint")
    public void userCallsCreatePriceBoardTaskDTEndpoint(DataTable dataTable) {
      priceboardDT = dataTable.asMaps();
        priceboardmainPojo.setRequestId(GenricUtils.generateUUID());
        int priceboardDTCount = priceboardDT.size();
        for(int i=0;i<priceboardDT.size();i++)
        {
            Map<String, String> prieboardOnebyOne = priceboardDT.get(i);
            priceBoardSubpojo priceboardsubpojo=new priceBoardSubpojo();
            priceboardsubpojo.setPriceBoard(prieboardOnebyOne.get("priceBoard"));
            priceboardsubpojo.setPbStatus(Boolean.parseBoolean(prieboardOnebyOne.get("pbStatus")));
            priceboardsubpojoList.add(priceboardsubpojo);
        }
        priceboardmainPojo.setPriceBoards(priceboardsubpojoList);
        priceboardmainPojo.setRequestedAt(GenricUtils.getUTC_Format_CurrentDateTime());
    }

    @And("pass article details to createPriceboardtaskDT End point")
    public void passArticleDetailsToCreatePriceboardtaskDTEndPoint(DataTable dataTable) {
        Map<String, String> priceboardDT = dataTable.asMap(String.class, String.class);
        priceboardmainPojo.setPriceBoards(priceboardsubpojoList);
        priceboardmainPojo.setRequestedAt(GenricUtils.getUTC_Format_CurrentDateTime());

        articleDetails.setArticleName(priceboardDT.get("articleName"));
        articleDetails.setArticlePrice(priceboardDT.get("articlePrice"));
        articleDetails.setEan(priceboardDT.get("ean"));
        articleDetails.setCategoryCode(priceboardDT.get("categoryCode"));
        articleDetails.setArticleCode(priceboardDT.get("articleCode"));
        articleDetails.setArticleMrp(priceboardDT.get("articleMrp"));
        priceboardmainPojo.setArticleDetails(articleDetails);
       priceboardmainPojo.setRequesterComments(priceboardDT.get("requesterComments"));
        priceboardmainPojo.setTaskType(priceboardDT.get("taskType"));

        createpriceBoardResponse = requestGenerator.getRequest(CommonUtilities.genericHeader(), priceboardmainPojo).log().all()
                .when().post(PropertyReader.getProperty(ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH, "CreateTaskEndpoint"));
        //createpriceBoardResponse.then().log().all();
        ExtentReportManager.logJson("Response is " + createpriceBoardResponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " + createpriceBoardResponse.getStatusCode());
        long responseTime = createpriceBoardResponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response Time is " + responseTime + " ms");
        CommonUtilities.setResponseInstance(createpriceBoardResponse);

    }
//===================================================================================================================
    //create price board task

    @When("user calls create priceBoard task endpoint")
    public void userCallsCreatePriceBoardTaskEndpoint(DataTable dataTable) {
        Map<String, String> prieboardDT = dataTable.asMap(String.class, String.class);
       priceboardmainPojo.setRequestId(GenricUtils.generateUUID());
       //set priceboard list of object
        priceBoardSubpojo priceboardsubpojo1=new priceBoardSubpojo();
        priceboardsubpojo1.setPriceBoard(prieboardDT.get("priceBoard1"));
        priceboardsubpojo1.setPbStatus(false);
        priceboardsubpojoList.add(priceboardsubpojo1);

        priceBoardSubpojo priceboardsubpojo2=new priceBoardSubpojo();
        priceboardsubpojo2.setPriceBoard(prieboardDT.get("priceBoard2"));
        priceboardsubpojo2.setPbStatus(false);
        priceboardsubpojoList.add(priceboardsubpojo2);

        priceBoardSubpojo priceboardsubpojo3=new priceBoardSubpojo();
        priceboardsubpojo3.setPriceBoard(prieboardDT.get("priceBoard3"));
        priceboardsubpojo3.setPbStatus(false);
        priceboardsubpojoList.add(priceboardsubpojo3);

        priceBoardSubpojo priceboardsubpojo4=new priceBoardSubpojo();
        priceboardsubpojo4.setPriceBoard(prieboardDT.get("priceBoard4"));
        priceboardsubpojo4.setPbStatus(false);
        priceboardsubpojoList.add(priceboardsubpojo4);
priceboardmainPojo.setPriceBoards(priceboardsubpojoList);

priceboardmainPojo.setRequestedAt(GenricUtils.getUTC_Format_CurrentDateTime());

        articleDetails.setArticleName(prieboardDT.get("articleName"));
        articleDetails.setArticlePrice(prieboardDT.get("articlePrice"));
        articleDetails.setEan(prieboardDT.get("ean"));
        articleDetails.setCategoryCode(prieboardDT.get("categoryCode"));
        articleDetails.setArticleCode(prieboardDT.get("articleCode"));
        articleDetails.setArticleMrp(prieboardDT.get("articleMrp"));

priceboardmainPojo.setArticleDetails(articleDetails);
priceboardmainPojo.setRequesterComments(prieboardDT.get("requesterComments"));

       createpriceBoardResponse = requestGenerator.getRequest(CommonUtilities.genericHeader(), priceboardmainPojo).log().all()
                .when().post(PropertyReader.getProperty(ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH, "CreateTaskEndpoint"));
        createpriceBoardResponse.then().log().all();
        ExtentReportManager.logJson("Response is " + createpriceBoardResponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " + createpriceBoardResponse.getStatusCode());
        long responseTime = createpriceBoardResponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response Time is " + responseTime + " ms");
        CommonUtilities.setResponseInstance(createpriceBoardResponse);


    }
    @And("verify that requestPayload is same as responsePayload in Create PRICEBOARD Task")
    public void verifyThatRequestPayloadIsSameAsResponsePayloadInCreatePRICEBOARDTask() {
        String ExpectedSiteId = createpriceBoardResponse.jsonPath().get("SiteId");
        String ExpectedtaskType = createpriceBoardResponse.jsonPath().get("taskType");
        String ExpectedStatus = createpriceBoardResponse.jsonPath().get("Status");
        String ExpectedEan = createpriceBoardResponse.jsonPath().get("articleDetails.ean");
        String ExpectedcategoryCode = createpriceBoardResponse.jsonPath().get("articleDetails.categoryCode");
        String PriceBoardrefillId = createpriceBoardResponse.jsonPath().get("refillId");
        CommonUtilities.setPriceRefillId(PriceBoardrefillId);

        //Actual
        String ActualTaskType = priceboardmainPojo.getTaskType();
        String ActualEan = articleDetails.getEan();
        String ActualcategoryCode = articleDetails.getCategoryCode();

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
