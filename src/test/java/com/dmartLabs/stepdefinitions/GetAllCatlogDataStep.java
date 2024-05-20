package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.ConStants;
import com.dmartLabs.config.PropertyReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import rst.pdfbox.layout.text.Constants;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.dmartLabs.config.ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH;


public class GetAllCatlogDataStep extends Constants implements ConStants {
    RequestGenerator requestGenerator = new RequestGenerator();
    Response GetAllCatlogDataesponse;
    int firstMax;
    int Secondmax;
    @When("user calls GetAllCatlogData end point")
    public void userCallsGetAllCatlogDataEndPoint() {
        GetAllCatlogDataesponse = requestGenerator.getRequestGet(CommonUtilities.genericHeader()).log().all()
                .when().get(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "GetAllItemsCatalogEndPoint"));
        GetAllCatlogDataesponse.then().log().all();

        ExtentReportManager.logJson("Response is " + GetAllCatlogDataesponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " + GetAllCatlogDataesponse.getStatusCode());
        long responseTime = GetAllCatlogDataesponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response Time is " + responseTime + " ms");
        CommonUtilities.setResponseInstance(GetAllCatlogDataesponse);
    }

    @And("verify that GetAllCatlogData ResponseBody")
    public void verifyThatGetAllCatlogDataResponseBody() {
        //  Object all = GetAllCatlogDataesponse.jsonPath().get();

        List<Map<String, Integer>> CatalogListResponse = GetAllCatlogDataesponse.jsonPath().get("items");
        ArrayList<Integer> AllMillisAl = new ArrayList<Integer>();
        // System.out.println(CatalogListResponse.size() + "==================================>total count");
        for (int i = 0; i < CatalogListResponse.size(); i++) {
            Integer AllMillis = CatalogListResponse.get(i).get("updatedOnMillis");
            AllMillisAl.add(AllMillis);
        }
        int[] a = new int[AllMillisAl.size()];
        for (int i = 0; i < AllMillisAl.size(); i++) {
            a[i] = AllMillisAl.get(i);
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] < a[j]) {
                    int  temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
            System.out.print(a[i]+" ");//descending order
        }

        System.out.println(" ==========================================================");
        System.out.println(a[0]+"==================================first max");
        System.out.println(a[1]+"=====================================second max");
        firstMax = a[0];
        Secondmax=a[1];

        CommonUtilities.setFirstmaxmaxupdatedOnMillis(firstMax);
        CommonUtilities.setSeondmaxupdatedOnMillis(Secondmax);
        ExtentReportManager.logInfoDetails("MaxMilliseconds is "+firstMax);

        System.out.println(CommonUtilities.getFirstmaxmaxupdatedOnMillis() + "===================================>"+"MaxMilliseconds");
        int ActualTotalCount = CatalogListResponse.size();
        int ExpectedTotalCount = GetAllCatlogDataesponse.jsonPath().get("totalCount");
        //validating no.of records
        if (ExpectedTotalCount == ActualTotalCount) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("ExpectedTotalCount is " + ExpectedTotalCount + " and the ActualTotalCount is " + ActualTotalCount);
            Assert.assertEquals(ExpectedTotalCount, ActualTotalCount);
            System.out.println(ExpectedTotalCount + " ===================>" + "ExpectedTotalCount validation successful");
            ExtentReportManager.logInfoDetails(ExpectedTotalCount+" "+"no.of records validation successful");
        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("ExpectedTotalCount is " + ExpectedTotalCount + " and the ActualTotalCount is " + ActualTotalCount);
            System.out.println(ExpectedTotalCount + " ===================>" + "ExpectedTotalCount validation fail");

        }

    }


    //=================================================================================================
//    GetCatlogDataByMaxMilliSeconds
    // ====================================
    HashMap<String, Integer> queryParam = new HashMap<>();
    Response GetCatlogDataByMaxMilliSecondsResponse;
    int pagenumber;
    int pagesize;
    Integer Allprice;
    Integer AllSellingPrice;

    @And("pass the data to GetCatlogDataByMaxMilliSeconds")
    public void passTheDataToGetCatlogDataByMaxMilliSeconds(DataTable dataTable) {
        Map<String, String> GetCatlogDataByMaxMilliSecondsDT = dataTable.asMap(String.class, String.class);
        pagenumber = Integer.parseInt(GetCatlogDataByMaxMilliSecondsDT.get("pageNumber"));
        pagesize = Integer.parseInt(GetCatlogDataByMaxMilliSecondsDT.get("pageSize"));
        int maxmilseconds = CommonUtilities.getFirstmaxmaxupdatedOnMillis();
        queryParam.put("pageNumber", pagenumber);
        queryParam.put("pageSize", pagesize);
        queryParam.put("updatedOnMillis", maxmilseconds-1);
    }

    @When("user calls GetCatlogDataByMaxMilliSeconds end point")
    public void userCallsGetCatlogDataByMaxMilliSecondsEndPoint() {
        GetCatlogDataByMaxMilliSecondsResponse = RequestGenerator.GetRequestWithQueryParam(CommonUtilities.genericHeader(), queryParam).log().all().
                when().get(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "GetAllItemsCatalogEndPoint"));
        GetCatlogDataByMaxMilliSecondsResponse.then().log().all();
        ExtentReportManager.logJson("Response is " + GetCatlogDataByMaxMilliSecondsResponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " + GetCatlogDataByMaxMilliSecondsResponse.getStatusCode());
        long responseTime = GetCatlogDataByMaxMilliSecondsResponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response Time is " + responseTime + " ms");
        CommonUtilities.setResponseInstance(GetCatlogDataByMaxMilliSecondsResponse);

    }

    @And("verify that GetCatlogDataByMaxMilliSeconds ResponseBody")
    public void verifyThatGetCatlogDataByMaxMilliSecondsResponseBody() {

        List<Map<String, String>> MaxCatalogListResponse = GetCatlogDataByMaxMilliSecondsResponse.jsonPath().get("items");
        for(int i=0;i<MaxCatalogListResponse.size();i++)
        {
            String AllEan = MaxCatalogListResponse.get(i).get("ean");
            CommonUtilities.setEan(AllEan);
        }


        for (int i = 0; i < MaxCatalogListResponse.size(); i++) {

            Allprice =Integer.parseInt(MaxCatalogListResponse.get(i).get("price"));
            AllSellingPrice = Integer.parseInt(MaxCatalogListResponse.get(i).get("sellingPrice"));

        }
        System.out.println(Allprice +"======================================>Changed price");
        System.out.println(AllSellingPrice+"=================================>Changed SellingPrice");
        ExtentReportManager.logInfoDetails("Changed price in data base is "+Allprice);
        ExtentReportManager.logInfoDetails("Changed selling  price in data base is "+AllSellingPrice);

        // System.out.println(MaxCatalogListResponse.size() + "==================================>total count");
        int expectedPageSize = GetCatlogDataByMaxMilliSecondsResponse.jsonPath().get("pageSize");
        int expectedPageNumber = GetCatlogDataByMaxMilliSecondsResponse.jsonPath().get("pageNumber");
        int MaxExpectedTotalCount = GetCatlogDataByMaxMilliSecondsResponse.jsonPath().get("totalCount");
        //  Object expectEan = GetCatlogDataByMaxMilliSecondsResponse.jsonPath().get("ean");

        int MaxActualTotalCount = MaxCatalogListResponse.size();


        if (expectedPageSize == pagesize) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("expectedPageSize is " + expectedPageSize + " and the actual pagesize is " + pagesize);
            Assert.assertEquals(expectedPageSize, pagesize);
            System.out.println(expectedPageSize + " ===================>" + "expectedPageSize validation successful");
        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("expectedPageSize is " + expectedPageSize + " and the actual pagesize is " + pagesize);
            System.out.println(expectedPageSize + " ===================>" + "expectedPageSize validation fail");

        }
        if (expectedPageNumber == pagenumber) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("expectedPageNumber is " + expectedPageNumber + " and the actual pagenumber is " + pagenumber);
            Assert.assertEquals(expectedPageNumber, pagenumber);
            System.out.println(expectedPageNumber + " ===================>" + "pagenumber validation successful");
        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("expectedPageNumber is " + expectedPageSize + " and the actual pagenumber is " + pagenumber);
            System.out.println(expectedPageNumber + " ===================>" + "pagenumber validation fail");

        }
//validating MaxMilliseconds records
        if (MaxExpectedTotalCount == MaxActualTotalCount) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("MaxExpectedTotalCount is " + MaxExpectedTotalCount + " and the MaxActualTotalCount " + MaxActualTotalCount);
            Assert.assertEquals(MaxExpectedTotalCount, MaxActualTotalCount);
            System.out.println(MaxExpectedTotalCount + " ===================>" + "MaxExpectedTotalCount validation successful");
        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("MaxExpectedTotalCount is " + MaxExpectedTotalCount + " and MaxActualTotalCount is " + MaxActualTotalCount);
            System.out.println(MaxExpectedTotalCount + " ===================>" + "MaxExpectedTotalCount validation fail");
        }
        System.out.println(CommonUtilities.getEan()+"===================>"+"last updated ean number");
        ExtentReportManager.logInfoDetails(CommonUtilities.getEan()+"===================>"+"last updated ean number");
    }
    //==========================================================================================================================
    //passing second max value
    @And("pass the SecondMaxMilliSeconds to GetCatlogDataByMaxMilliSeconds")
    public void passTheSecondMaxMilliSecondsToGetCatlogDataByMaxMilliSeconds(DataTable dataTable) {
        Map<String, String> SecondMaxMilliSecondsDT = dataTable.asMap(String.class, String.class);
        pagenumber = Integer.parseInt(SecondMaxMilliSecondsDT.get("pageNumber"));
        pagesize = Integer.parseInt(SecondMaxMilliSecondsDT.get("pageSize"));
        int Secondmaxmilseconds = CommonUtilities.getSeondmaxupdatedOnMillis();
        queryParam.put("pageNumber", pagenumber);
        queryParam.put("pageSize", pagesize);
        queryParam.put("updatedOnMillis", Secondmaxmilseconds);
    }
//=================================================================================================
    //get catlog data by ean

    public   HashMap<String, String> queryParamEan = new HashMap<>();
    Response GetEanResponse;

    @And("pass the data to GetCatlogDataByEan")
    public void passTheDataToGetCatlogDataByEan() {
        String CommonEanValue = CommonUtilities.getEan();
        queryParamEan.put("ean",CommonEanValue);
    }

    @When("user calls GetCatlogDataByEan end point")
    public void userCallsGetCatlogDataByEanEndPoint() {
        GetEanResponse = RequestGenerator.GetRequestWithQueryParamString(CommonUtilities.genericHeader(), queryParamEan).log().all().
                when().get(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "GetItemByEan"));
        GetEanResponse.then().log().all();
        ExtentReportManager.logJson("Response is " + GetEanResponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " + GetEanResponse.getStatusCode());
        long responseTime = GetEanResponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response Time is " + responseTime + " ms");
        CommonUtilities.setResponseInstance(GetEanResponse);

    }
    //============================================================================================================================
//get catlog data by old ean

    @And("pass the data to GetCatlogDataByOldEan")
    public void passTheDataToGetCatlogDataByOldEan(DataTable dataTable) {
        Map<String, String> EanDT = dataTable.asMap(String.class, String.class);
        String eanValue =(EanDT.get("ean"));
        queryParamEan.put("ean",eanValue);
    }
    @And("Validate responsebody of catlog ean")
    public void validateResponsebodyOfCatlogEan() {
        String ExpectedsellingPrice = GetEanResponse.jsonPath().get("sellingPrice");
        String Expectedprice=  GetEanResponse.jsonPath().get("price");
        String  Expectedean=   GetEanResponse.jsonPath().get("ean");
        String ExpectedarticleCode=  GetEanResponse.jsonPath().get("articleCode");
        String ExpectedsiteId=  GetEanResponse.jsonPath().get("siteId");
        Object ExpectedupdatedOnMillis = GetEanResponse.jsonPath().get("updatedOnMillis");
        ExtentReportManager.logInfoDetails("ExpectedsellingPrice is " +"==================>"+ ExpectedsellingPrice);
        ExtentReportManager.logInfoDetails("Expectedprice is "+"==================>" + Expectedprice);
        ExtentReportManager.logInfoDetails("Expectedean is "+"==================>" + Expectedean);
        ExtentReportManager.logInfoDetails("ExpectedarticleCode is "+"==================>" + ExpectedarticleCode);
        ExtentReportManager.logInfoDetails("ExpectedsiteId is "+"==================>" + ExpectedsiteId);
        ExtentReportManager.logInfoDetails("last updated on  "+"==================>" + ExpectedupdatedOnMillis);
        System.out.println("ExpectedsellingPrice is "+"==================>" + ExpectedsellingPrice);
        System.out.println("Expectedprice is "+"==================>" + Expectedprice);
        System.out.println("Expectedean is " +"==================>"+ Expectedean);
        System.out.println("ExpectedarticleCode is "+"==================>" + ExpectedarticleCode);
        System.out.println("ExpectedsiteId is "+"==================>" + ExpectedsiteId);
        System.out.println("last updated on "+"==================>" + ExpectedupdatedOnMillis+"========>"+"milli seconds");



    }
}
