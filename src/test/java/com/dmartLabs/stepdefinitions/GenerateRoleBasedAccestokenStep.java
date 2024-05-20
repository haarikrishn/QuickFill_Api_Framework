package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.GenricUtils;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.PropertyReader;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static com.dmartLabs.config.ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH;

public class GenerateRoleBasedAccestokenStep {
    RequestGenerator requestGenerator = new RequestGenerator();
    Response response;
    public static String accessToken;
    public static String refillRequesterAccessToken;
    public static String refillAuditorAccessToken;
    public static String refillerAccessToken;
    public static String bothRoleAccessToken;
    @When("Send username {string} and password {string} to get Access Token from Authentication Endpoint.")
    public void sendUsernameAndPasswordToGetAccessTokenFromAuthenticationEndpoint(String username, String password) {

        ExtentReportManager.logInfoDetails("Send username and password to get accessToken");
        Map<String, String> userCredintials = new HashMap<>();
        userCredintials.put("username", username);
        userCredintials.put("password", password);
//        response = requestGenerator.getRequest(GenericSteps.userCredential).log().all()
        response = requestGenerator.getRequest(userCredintials).log().all()
                .when().post(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "AuthenticationEndpoint"));
        response.then().log().all();
        accessToken = response.jsonPath().get("accessToken");
        ExtentReportManager.logInfoDetails("Response is " + response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " + response.getStatusCode());
        CommonUtilities.setResponseInstance(response);
        String[] roles = GenricUtils.getRolesFromAccessToken(accessToken);
        System.out.println("Length of Roles is ================> "+roles.length);
        System.out.println("Role is =============================> "+roles[0]);

        if (roles.length<2 && roles[0].equals("REFILL REQUESTER")){
            refillRequesterAccessToken = accessToken;
            System.out.println("REFILL REQUESTER Access token is =========> "+refillRequesterAccessToken);
        }
        else if(roles.length<2 && roles[0].equals("REFILL AUDITOR")){
            refillAuditorAccessToken = accessToken;
            System.out.println("REFILL AUDITOR Access token is =========> "+refillAuditorAccessToken);
        }
        else if (roles.length<2 &&roles[0].equals("REFILLER")){
            refillerAccessToken = accessToken;
            System.out.println("REFILLER Access token is =========> "+refillerAccessToken);
        }
        else if (roles[0].equals("REFILL REQUESTER") && roles[1].equals("REFILL AUDITOR")){
            bothRoleAccessToken = accessToken;
            System.out.println("Both Roles Access token is =========> "+bothRoleAccessToken);
        }
    }
}
