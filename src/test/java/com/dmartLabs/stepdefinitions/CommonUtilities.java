package com.dmartLabs.stepdefinitions;


import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.ConStants;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class CommonUtilities implements ConStants {
    RequestGenerator requestGenerator = new RequestGenerator();
    public static String accessToken;
    Response response;
    static ThreadLocal<Response> response2 = new ThreadLocal<>();
    static ThreadLocal<Map> mapData = new ThreadLocal<>();
    static ThreadLocal<String> token = new ThreadLocal<>();
    static ThreadLocal<Integer> threadSafeRequestedQuantity = new ThreadLocal<>();
    static  ThreadLocal<Integer> threadSafemaxupdatedOnMillis=new ThreadLocal<>();
    static  ThreadLocal<String> threadSafeRequestId=new ThreadLocal<>();
    static  ThreadLocal<String> threadSafeRefillTaskRefillId=new ThreadLocal<>();
    static  ThreadLocal<String> threadSafePriceboardTaskRefillId=new ThreadLocal<>();
    static  ThreadLocal<String> threadSafeOthersTaskRefillId=new ThreadLocal<>();
    static  ThreadLocal<String> threadSafeFloorWalkId=new ThreadLocal<>();
    static ThreadLocal<String> threadSafeRefillId = new ThreadLocal<>();
    static  ThreadLocal<String>threadsafePriceRefillId=new ThreadLocal<>();
    static ThreadLocal<String>threadsafeothersrefillId=new ThreadLocal<>();
    static ThreadLocal<Integer>threadsafeSiteId=new ThreadLocal<>();
    static ThreadLocal<String>threadsafeRequester=new ThreadLocal<>();
    static  ThreadLocal<Integer> threadSafeFirstmaxupdatedOnMillis=new ThreadLocal<>();
    static  ThreadLocal<Integer> threadSafeSeondmaxupdatedOnMillis=new ThreadLocal<>();
    static  ThreadLocal<String>threadSafeEan=new ThreadLocal<>();
    static  ThreadLocal<LinkedHashMap<String, Object>> threadSafeRequestPayload = new ThreadLocal<>();
    static ThreadLocal<List<String>> threadSafe_v1_refillIds = new ThreadLocal<>();
    static ThreadLocal<List<String>> threadSafeRefillTask_v1_RefillId = new ThreadLocal<>();
    static ThreadLocal<List<String>> threadSafePriceboardTask_v1_RefillId = new ThreadLocal<>();
    static ThreadLocal<List<String>> threadSafeOthersTask_v1_RefillId = new ThreadLocal<>();

    public static void setRefillTask_v1_RefillIds(List<String> refillTask_v1_refillIds) {
        threadSafeRefillTask_v1_RefillId.set(refillTask_v1_refillIds);
    }

    public static List<String> getRefillTask_v1_RefillIds() {
        return threadSafeRefillTask_v1_RefillId.get();
    }

    public static void setPriceboardTask_v1_RefillIds(List<String> priceboardTask_v1_refillIds) {
        threadSafePriceboardTask_v1_RefillId.set(priceboardTask_v1_refillIds);
    }

    public static List<String> getPriceboardTask_v1_RefillIds() {
        return threadSafePriceboardTask_v1_RefillId.get();
    }

    public static void setOthersTask_v1_RefillIds(List<String> othersTask_v1_refillIds) {
        threadSafeOthersTask_v1_RefillId.set(othersTask_v1_refillIds);
    }

    public static List<String> getOthersTask_v1_RefillIds() {
        return threadSafeOthersTask_v1_RefillId.get();
    }

    public static void setRequestId(String requestId) {
        threadSafeRequestId.set(requestId);
    }

    public static void getAllTasksRefillId() {
        threadSafe_v1_refillIds.get();
    }

    public static void setAllTasksRefillId(List<String> v1_refillIds) {
        threadSafe_v1_refillIds.set(v1_refillIds);
    }

    public static String getRequestId() {
     return threadSafeRequestId.get();
    }

    public static void setSiteId(int siteID_fromAccessToken) {
        CommonUtilities.threadsafeSiteId.set(siteID_fromAccessToken);
    }

    public static int getSiteId(){
        return threadsafeSiteId.get();
    }

    public static void setRequester(String requester_fromAccessToken) {
        CommonUtilities.threadsafeRequester.set(requester_fromAccessToken);
    }

    public static String getRequester(){
        return threadsafeRequester.get();
    }

    public static void setRefillId(String refillId) {
        threadSafeRefillId.set(refillId);
    }

    public static void setRequestedQuantity(int requestedQuantity) {
        threadSafeRequestedQuantity.set(requestedQuantity);
    }

    public static int getRequestedQuantity(){
        return threadSafeRequestedQuantity.get();
    }

    public static String getRefillId() {
        return threadSafeRefillId.get();
    }

    public static String getRefillTaskRefillId()
    {
        return threadSafeRefillTaskRefillId.get();
    }
    public  static  void setRefillTaskRefillId(String RefillrefillId ) {
        threadSafeRefillTaskRefillId.set(RefillrefillId);
    }

    public static void setPriceboardTaskRefillId(String refillId) {
        threadSafePriceboardTaskRefillId.set(refillId);
    }

    public static String getPriceboardTaskRefillId() {
        return threadSafePriceboardTaskRefillId.get();
    }

    public static void setOthersTaskRefillId(String refillId) {
        threadSafeOthersTaskRefillId.set(refillId);
    }

    public static String getOthersTaskRefillId() {
        return threadSafeOthersTaskRefillId.get();
    }

    public static int getFirstmaxmaxupdatedOnMillis()
    {
        return threadSafeFirstmaxupdatedOnMillis.get();
    }
    public  static  void setFirstmaxmaxupdatedOnMillis(int FirstmaxmaxupdatedOnMillis )
    {
        CommonUtilities.threadSafeFirstmaxupdatedOnMillis.set(FirstmaxmaxupdatedOnMillis);
    }

    public static int getSeondmaxupdatedOnMillis()
    {
        return threadSafeSeondmaxupdatedOnMillis.get();
    }
    public  static  void setSeondmaxupdatedOnMillis(int SeondmaxupdatedOnMillis )
    {
        CommonUtilities.threadSafeSeondmaxupdatedOnMillis.set(SeondmaxupdatedOnMillis);
    }

    public static String getEan() {

        return threadSafeEan.get();
    }
    public static void setEan(String ean) {

        threadSafeEan.set(ean);
    }

    //=============================================================================================


    //==========================================================================
    public static String getFloorWalkId()
    {
        return threadSafeFloorWalkId.get();
    }
    public  static  void setFloorWalkId(String FloorWalkId )
    {
        CommonUtilities.threadSafeFloorWalkId.set(FloorWalkId);
    }


    //==========================================================================
    //threadsafePriceRefillId
    public static String getPriceRefillId()
    {
        return threadsafePriceRefillId.get();
    }
    public  static  void setPriceRefillId(String PriceRefillId )
    {
        CommonUtilities.threadsafePriceRefillId.set(PriceRefillId);
    }
    //==========================================================================
    //threadsafeothersrefillId
    public static String getothersrefillId()
    {
        return threadsafeothersrefillId.get();
    }

    public  static  void setothersrefillId(String othersrefillId )
    {
        CommonUtilities.threadsafeothersrefillId.set(othersrefillId);
    }

    //=====================================================================
   public static String getJsonPath(Response response, String key) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }

    public static String getAccessTokenFromRequestSpecificaton(RequestSpecification requestSpecification) {
        QueryableRequestSpecification header = SpecificationQuerier.query(requestSpecification);
        return header.getHeaders().get("Authorization").toString();
    }

    public static String getAcessToken() {

        return token.get();
    }
    public static void setToken(String setToken) {

        token.set((String) setToken);
    }
    public static Response getResponseInstance() {

        return response2.get();
    }

    public static void setResponseInstance(Response setInstanceResponse) {
        response2.set((Response) setInstanceResponse);
    }

    public static Map<String,String> genericHeader() {
        Map<String,String> header = new HashMap<>();
        GenerateTokenStep generateAccessTokenSteps = new GenerateTokenStep();
        accessToken = generateAccessTokenSteps.sendUsernameAndPasswordToGetAccessToken();
        CommonUtilities.setToken(accessToken);
        header.put("Authorization", CommonUtilities.getAcessToken());
        return header;
    }


    public static void setTask(LinkedHashMap<String, Object> task) {
        threadSafeRequestPayload.set(task);
    }

    public static LinkedHashMap<String, Object> getTask(){
        return threadSafeRequestPayload.get();
    }

}
