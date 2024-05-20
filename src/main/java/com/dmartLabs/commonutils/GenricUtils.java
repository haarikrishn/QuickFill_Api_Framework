package com.dmartLabs.commonutils;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.apache.poi.ss.usermodel.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import rst.pdfbox.layout.text.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public  class GenricUtils extends Constants {
    static Workbook workbook;
    /**
     * random number generator upto 4 digit
     * @return
     */
    public static long getRendomMobileNumber() {
        Random random = new Random();
        // Generate a random integer between 1000 and 9999 (inclusive)
        String num = "5555555"+(random.nextInt(1000));
        return Long.parseLong(num);
    }

    public static String genrateStateRegNumber(){
        String num = ""+GenricUtils.getRendomMobileNumber();
        String randNum = "35060211"+num;
        return randNum;
    }

    public static String encode(String s) {

        return new String(Base64.getEncoder().encode(s.getBytes()));
    }

    public static String getUTC_Format_CurrentDateTime(){
        String currentDateTime = new DateTime().toDateTime(DateTimeZone.UTC).toString();
        if (currentDateTime.contains("000Z")){
            String[] dateTime = currentDateTime.split(".000Z");
            currentDateTime = dateTime[0]+"Z";
        } else if (currentDateTime.contains("00Z")){
            String[] dateTime = currentDateTime.split("00Z");
            currentDateTime = dateTime[0]+"Z";
        } else if (currentDateTime.contains("0Z")){
            String[] dateTime = currentDateTime.split("0Z");
            currentDateTime = dateTime[0]+"Z";
        }

        return currentDateTime;
    }

    public static String getRequester_FromAccessToken(String accessToken) {
        String[] accessTokenArray = accessToken.split(" ");
        String formattedAccessToken = accessTokenArray[1];
        SignedJWT signedJWT = null;
        try {
            signedJWT = SignedJWT.parse(formattedAccessToken);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JWTClaimsSet claims = null;
        try {
            claims = signedJWT.getJWTClaimsSet();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String requester=null;
        try {
            requester = claims.getStringClaim("sub");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return requester;
    }

    public static int getSiteID_FromAccessToken(String accessToken) {
        String[] accessTokenArray = accessToken.split(" ");
        String formattedAccessToken = accessTokenArray[1];
        SignedJWT signedJWT = null;
        try {
            signedJWT = SignedJWT.parse(formattedAccessToken);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JWTClaimsSet claims = null;
        try {
            claims = signedJWT.getJWTClaimsSet();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int siteID = 0;
        try {
            siteID = Integer.parseInt(claims.getStringClaim("site"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return siteID;
    }


    public static JWTClaimsSet decodeAccessToken(String accessToken) {
        String[] accessTokenArray = accessToken.split(" ");
        String formattedAccessToken = accessTokenArray[1];
        SignedJWT signedJWT = null;
        try {
            signedJWT = SignedJWT.parse(formattedAccessToken);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JWTClaimsSet claims = null;
        try {
            claims = signedJWT.getJWTClaimsSet();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return claims;
    }

    public static String[] getRolesFromAccessToken(String accessToken) {
        String[] accessTokenArray = accessToken.split(" ");
        String formattedAccessToken = accessTokenArray[1];
        SignedJWT signedJWT = null;
        try {
            signedJWT = SignedJWT.parse(formattedAccessToken);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JWTClaimsSet claims = null;
        try {
            claims = signedJWT.getJWTClaimsSet();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[] roles = null;
        try {
            roles = claims.getStringArrayClaim("roles");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return roles;
    }


    public static boolean convertStringToBoolean(String value){
        boolean booleanValue = false;
        if (value.equals("true") || value.equals("false")){
            booleanValue = Boolean.parseBoolean(value);
        }
        return booleanValue;
    }

    public static String getFormattedDateTime(String dateFormat){
       return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date())+".747Z";
    }

    public static String generateUUID(){
        return UUID.randomUUID().toString();

    }

    public  static  boolean isInteger(String pageNumber,String pageSize)
    {
       try
       {
           Integer.parseInt(pageNumber);
           Integer.parseInt(pageSize);
           return  true;
       }
       catch (NumberFormatException e)
       {
           return  false;
       }
    }
    public  static  boolean isCharacterPageNumber(String pageNumber)
    {
        return  pageNumber.length()==1 && Character.isLetter(pageNumber.charAt(0));

    }
    public  static  boolean isCharacterPageSize(String pageSize)
    {
        return  pageSize.length()==1 && Character.isLetter(pageSize.charAt(0));

    }

}
