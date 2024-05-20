package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.ConStants;
import com.dmartLabs.config.PropertyReader;
import com.dmartLabs.pojo.priceBoardSubpojo;
import com.dmartLabs.pojo.updatePriceBoardPojoByAuditor;
import com.dmartLabs.pojo.updatePriceBoardPojoByRefiller;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.dmartLabs.stepdefinitions.CreatePriceBoardTaskStep.priceboardDT;

public class UpdatePriceBoardTaskStep {
    RequestGenerator requestGenerator = new RequestGenerator();
    public static String ActualpriceRefillId;
    public List<priceBoardSubpojo> priceboardsubpojoList = new ArrayList<>();
    updatePriceBoardPojoByRefiller updatePriceBoardMainPojo = new updatePriceBoardPojoByRefiller();
    Response UpdatePriceBoardResponse;
    public String ActualRefillId;
    Map<String, String> updatePriceDT;

    //update task by refiller
    @When("user calls create UpdatePriceBoardTaskDT  endpoint")
    public void userCallsCreateUpdatePriceBoardTaskDTEndpoint(DataTable dataTable) {
        ActualpriceRefillId = CommonUtilities.getPriceRefillId();
        updatePriceBoardMainPojo.setRefillId(ActualpriceRefillId);
        List<Map<String, String>> updatepriceboardListDT = dataTable.asMaps();
        int priceboardDTCount = updatepriceboardListDT.size();
        for (int i = 0; i < updatepriceboardListDT.size(); i++) {
            Map<String, String> prieboardOnebyOne = updatepriceboardListDT.get(i);
            priceBoardSubpojo priceboardsubpojo = new priceBoardSubpojo();
            priceboardsubpojo.setPriceBoard(prieboardOnebyOne.get("priceBoard"));
            priceboardsubpojo.setPbStatus(Boolean.parseBoolean(prieboardOnebyOne.get("pbStatus")));
            priceboardsubpojoList.add(priceboardsubpojo);
        }
        updatePriceBoardMainPojo.setPriceBoards(priceboardsubpojoList);
    }
    @And("user pass the data to  create UpdatePriceBoardTask  endpoint")
    public void userPassTheDataToCreateUpdatePriceBoardTaskEndpoint(DataTable dataTable) {

        updatePriceDT = dataTable.asMap(String.class, String.class);
              updatePriceBoardMainPojo.setStatus(updatePriceDT.get("status"));
        updatePriceBoardMainPojo.setRefillerComments(updatePriceDT.get("refillerComments"));

        UpdatePriceBoardResponse = requestGenerator.getRequest(CommonUtilities.genericHeader(), updatePriceBoardMainPojo).log().all()
                .when().put(PropertyReader.getProperty(ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH, "UpdatePriceBoardEndpoint"));
      //  UpdatePriceBoardResponse.then().log().all();
        ExtentReportManager.logJson("Response is " + UpdatePriceBoardResponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " + UpdatePriceBoardResponse.getStatusCode());
        long responseTime = UpdatePriceBoardResponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response Time is " + responseTime + " ms");
        CommonUtilities.setResponseInstance(UpdatePriceBoardResponse);
    }

    @And("verify that requestPayload is same as responsePayload in UpdatePriceBoardTask")
    public void verifyThatRequestPayloadIsSameAsResponsePayloadInUpdatePriceBoardTask() {
        String ExpectedPriceRefillId = UpdatePriceBoardResponse.jsonPath().get("refillId");
        List<Map<String,String>> updatePriceListResponse=UpdatePriceBoardResponse.jsonPath().get("priceBoards");
   for(int i=0;i<updatePriceListResponse.size();i++)
   {
       if(updatePriceListResponse.get(i).get("priceBoard").equals(priceboardDT.get(i).get("priceBoard")))
       {
           System.out.println("priceboard"+i+"==================>"+"validation successful");
           ExtentReportManager.logInfoDetails("priceboard"+i+"==================>"+"validation successful");
       }
       else
       {
           System.out.println("priceboard validation fail");
       }
//       if(updatePriceListResponse.get(i).get("pbStatus").equals(Boolean.parseBoolean(priceboardDT.get(i).get("priceBoard"))
//       {
//           System.out.println("pbStatus"+i+"==================>"+"validation successful");
//           ExtentReportManager.logInfoDetails("pbStatus"+i+"==================>"+"validation successful");
//       }
//       else
//       {
//           System.out.println("priceboard validation fail");
//       }

   }
        if (ExpectedPriceRefillId.equals(ActualpriceRefillId)) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("ExpectedPriceRefillId is " + ExpectedPriceRefillId + " and the ActualStatus is " + ActualpriceRefillId);
            Assert.assertEquals(ExpectedPriceRefillId, ActualpriceRefillId);
            System.out.println(ExpectedPriceRefillId + " ===================>" + "ExpectedPriceRefillId validation successful");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("ExpectedPriceRefillId  is " + ExpectedPriceRefillId + " and the ActualStatus is " + ActualpriceRefillId);
        }
    }
    //===============================================================================================
    //update task by auditor

    updatePriceBoardPojoByAuditor updatePriceBoardMainPojo1 = new updatePriceBoardPojoByAuditor();


    @When("user calls create UpdatePriceBoardTaskDTByAuditor  endpoint")
    public void userCallsCreateUpdatePriceBoardTaskDTByAuditorEndpoint(DataTable dataTable) {

        updatePriceBoardMainPojo1.setRefillId(ActualpriceRefillId);
        List<Map<String, String>> updatepriceboardListDT = dataTable.asMaps();
        int priceboardDTCount = updatepriceboardListDT.size();
        for (int i = 0; i < updatepriceboardListDT.size(); i++) {
            Map<String, String> prieboardOnebyOne = updatepriceboardListDT.get(i);
            priceBoardSubpojo priceboardsubpojo = new priceBoardSubpojo();
            priceboardsubpojo.setPriceBoard(prieboardOnebyOne.get("priceBoard"));
            priceboardsubpojo.setPbStatus(Boolean.parseBoolean(prieboardOnebyOne.get("pbStatus")));
            priceboardsubpojoList.add(priceboardsubpojo);
        }
        updatePriceBoardMainPojo1.setPriceBoards(priceboardsubpojoList);


    }
    @And("user pass the data to  UpdatePriceBoardTaskDTByAuditor endpoint")
    public void userPassTheDataToUpdatePriceBoardTaskDTByAuditorEndpoint(DataTable dataTable) {

        Map<String, String> updatePriceByAuditorDT = dataTable.asMap(String.class, String.class);
        updatePriceBoardMainPojo1.setStatus(updatePriceByAuditorDT.get("status"));
        updatePriceBoardMainPojo1.setAuditorComments(updatePriceByAuditorDT.get("auditorComments"));

        UpdatePriceBoardResponse = requestGenerator.getRequest(CommonUtilities.genericHeader(), updatePriceBoardMainPojo1).log().all()
                .when().put(PropertyReader.getProperty(ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH, "UpdatePriceBoardEndpoint"));
        //  UpdatePriceBoardResponse.then().log().all();
        ExtentReportManager.logJson("Response is " + UpdatePriceBoardResponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " + UpdatePriceBoardResponse.getStatusCode());
        long responseTime = UpdatePriceBoardResponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response Time is " + responseTime + " ms");
        CommonUtilities.setResponseInstance(UpdatePriceBoardResponse);
    }

}