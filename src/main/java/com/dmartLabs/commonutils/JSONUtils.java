package com.dmartLabs.commonutils;
import org.json.JSONObject;
import org.json.JSONTokener;
import rst.pdfbox.layout.text.Constants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static com.dmartLabs.config.ConStants.JSON_FILE_PATH;

public class JSONUtils extends Constants  {
    public  static File getRequestPayLoadAsJsonFile(String filePath)
    {
        File jsonFile = new File(filePath);
        return jsonFile;
    }
    public  static JSONObject getRequestPayloadAsObjectFromJsonFile(String jsonFileName)
    {
        File jsonFile = new File(JSON_FILE_PATH+jsonFileName);
        FileReader fileReader = null;
        try {
        fileReader = new FileReader(jsonFile);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        JSONTokener jsonTokener = new JSONTokener(fileReader);//converting file to object
        JSONObject jsonObject = new JSONObject(jsonTokener);//converting to jsonobject
        return jsonObject ;
    }

}
//public class Main {
//    public static void main(String[] args) {
//        // Create a JSON parser
//        JSONParser parser = new JSONParser();
//
//        try {
//            // Read the JSON file and parse it
//            Object obj = parser.parse(new FileReader("data.json"));
//
//            // Convert the parsed object to a JSONObject
//            JSONObject jsonObject = (JSONObject) obj;
//
//            // Get the value of the "name" field
//            String name = (String) jsonObject.get("name");
//            System.out.println(name);