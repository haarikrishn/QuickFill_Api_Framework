package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.GenricUtils;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.PropertyReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static com.dmartLabs.config.ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH;

public class GenerateTokenStep {
    Response accessTokenresponse;

    @When("Send username and password to get accessToken")
    public String sendUsernameAndPasswordToGetAccessToken() {

        accessTokenresponse  = RequestGenerator.getRequest(GenericSteps.userCredential)
                .when().post(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "AuthenticationEndpoint"));
         String accessToken = accessTokenresponse.jsonPath().get("accessToken");
        ExtentReportManager.logInfoDetails("Send username and password to get accessToken");
        ExtentReportManager.logInfoDetails("Response is " + accessTokenresponse.prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " + accessTokenresponse.getStatusCode());
        CommonUtilities.setResponseInstance(accessTokenresponse);

        CommonUtilities.setSiteId(GenricUtils.getSiteID_FromAccessToken(accessToken));
        CommonUtilities.setRequester(GenricUtils.getRequester_FromAccessToken(accessToken));
        return accessToken;
    }

}
