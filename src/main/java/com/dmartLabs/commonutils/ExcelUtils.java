package com.dmartLabs.commonutils;


import com.dmartLabs.config.ConStants;
import org.apache.poi.ss.usermodel.*;
import rst.pdfbox.layout.text.Constants;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ExcelUtils extends Constants {

    static FileInputStream fis;
    static Workbook workbook;
    static DataFormatter df;
    static FileOutputStream fos;
    static ExcelUtils excelUtils;

    public ExcelUtils(String fileName) {
        try {
            fis = new FileInputStream(ConStants.EXCEL_FILE_PATH + fileName);
            workbook = WorkbookFactory.create(fis);
            df = new DataFormatter();
//            fos = new FileOutputStream(ConStants.EXCEL_FILE_PATH + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * @param mobNumber
     * @throws IOException
     */
    public void setMobileNumberDirect(String mobNumber) {

        Sheet sheet = workbook.getSheet("Sheet1");
        int temp = 1;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {

            for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
                String key = df.formatCellValue(sheet.getRow(i).getCell(j));
                if (key.equalsIgnoreCase("Mobile Number")) {
                    System.out.println(key + " is a key");
                    Row row = sheet.getRow(temp);
                    Cell cell = row.createCell(j);
                    cell.setCellValue(mobNumber);
                    System.out.println(key + " data is updated");
                } else if (key.equalsIgnoreCase(null)) {
                    break;
                }
            }
        }

        try {
            workbook.write(fos);
            fos.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param regNumber
     * @throws IOException
     */
    public void setStateRegNumberDirect(String regNumber) {

        Sheet sheet = workbook.getSheet("Sheet1");
        int temp = 1;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {

            for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
                String key = df.formatCellValue(sheet.getRow(i).getCell(j));
                if (key.equalsIgnoreCase("State Reg Number")) {
                    System.out.println(key + " is a key");
                    Row row = sheet.getRow(temp);
                    Cell cell = row.createCell(j);
                    cell.setCellValue(regNumber);
                    System.out.println(key + " data is updated");
                } else if (key.equalsIgnoreCase(null)) {
                    break;
                }
            }
        }
        try {
            workbook.write(fos);
            fos.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param keyData
     * @param value
     * @throws IOException
     */
    public void setValueBasedOnKey(String keyData, String value) {

        Sheet sheet = workbook.getSheet("Sheet1");
        int temp = 1;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {

            for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
                String key = df.formatCellValue(sheet.getRow(i).getCell(j));
                if (key.equalsIgnoreCase(keyData)) {
                    System.out.println(key + " is a key");
                    Row row = sheet.getRow(temp);
                    Cell cell = row.createCell(j);
                    cell.setCellValue(value);
                    System.out.println(key + " data is updated");
                } else if (key.equalsIgnoreCase(null)) {
                    break;
                }
            }
        }
    }


    /**
     *
     * @param keyData
     * @return
     */
    public String getExcellDataBasedOnKey(String keyData) {

        Sheet sheet = workbook.getSheet("Sheet1");
        int temp = 1;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {

            for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
                String key = df.formatCellValue(sheet.getRow(i).getCell(j));
                if (key.equalsIgnoreCase(keyData)) {
                    System.out.println(key + " is a key");
                    System.out.println(df.formatCellValue(sheet.getRow(temp).getCell(j)) + " data is get");
                   return df.formatCellValue(sheet.getRow(temp).getCell(j));
                } else if (key.equalsIgnoreCase(null)) {
                    break;
                }
            }
        }
        return null;
    }

    /**
     * @return
     */
    public String getExcelKeyData(int columnNumber) {

        Sheet sheet = workbook.getSheet("Sheet1");
        int temp = 0;
        System.out.println(df.formatCellValue(sheet.getRow(temp).getCell(columnNumber)) + " data is get");
        return df.formatCellValue(sheet.getRow(temp).getCell(columnNumber));
    }



    public String getExcellDataBasedOnRowAndKeyWise(int row, String keyData) {

        Sheet sheet = workbook.getSheet("Sheet1");
//        int temp = row;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {

            for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
                String key = df.formatCellValue(sheet.getRow(i).getCell(j));
                if (key.equalsIgnoreCase(keyData)) {
                    System.out.println(key + " is a key");
                    try {
                        System.out.println(df.formatCellValue(sheet.getRow(row).getCell(j)) + " data is get");
                        return df.formatCellValue(sheet.getRow(row).getCell(j));
                    }catch (IndexOutOfBoundsException e){
                        e.printStackTrace();
                    }

                } else if (key.equalsIgnoreCase(null)) {
                    break;
                }
            }
        }
        return null;
    }

    /**
     *
     */
    public static void closeWorkbook() {
        try {
//            workbook.write(fos);
            fis.close();
//            fos.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<LinkedHashMap<String, Object>> getTaskAllRequestPayload(String sheetName) {

        Sheet sheet = workbook.getSheet(sheetName);

        int totalRows = sheet.getPhysicalNumberOfRows();

        List<LinkedHashMap<String, Object>> allRequestPayloads = new ArrayList<>();

        LinkedHashMap<String, Object> requestPayload;

        List<String> allKeys = new ArrayList<>();


        for (int i=0;i<sheet.getRow(0).getPhysicalNumberOfCells();i++){
            allKeys.add(sheet.getRow(0).getCell(i).getStringCellValue());
        }

        LinkedHashMap<String, Object> articleDetails;
        Map<String, Object> priceBoard;

        for (int i=1;i<sheet.getPhysicalNumberOfRows();i++) {
            requestPayload = new LinkedHashMap<>();
            requestPayload.put("requestId", "");
            requestPayload.put("requestedAt", "");
            articleDetails = new LinkedHashMap<>();
            List<Map<String, Object>> priceBoards = new ArrayList<>();
            if (sheet.getRow(i).getCell(0).getStringCellValue().equals("end")){
                break;
            }
            else{
                Row row = sheet.getRow(i);
                for (int j = 0; j < sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
                    String values = df.formatCellValue(row.getCell(j));

                    if (!(values.equals(""))) {

                        if (values.equals("\"\"")) {
                            values = "";
                        }

                        if (values.equals("null")) {
                            values = null;
                        }

                        if (!allKeys.get(j).contains("priceBoard") && !allKeys.get(j).contains("pbStatus") &&
                                !allKeys.get(j).contains("requestedQuantity") && !allKeys.get(j).contains("requesterComments") &&
                                    !allKeys.get(j).contains("taskType")) {
                            if (allKeys.get(j).equals("caselot")){
                                articleDetails.put(allKeys.get(j), Integer.parseInt(values));
                            } else {
                                articleDetails.put(allKeys.get(j), values);
                            }
                            requestPayload.put("articleDetails",articleDetails);
                        } else if (allKeys.get(j).contains("priceBoard")) {
                            priceBoard = new HashMap<>();
                            priceBoard.put(allKeys.get(j), values);
                            priceBoard.put(allKeys.get(++j), Boolean.parseBoolean(df.formatCellValue(row.getCell(j))));
                            priceBoards.add(priceBoard);
                            requestPayload.put("priceBoards", priceBoards);
                        } else {
                            if (allKeys.get(j).equals("requestedQuantity")){
                                requestPayload.put(allKeys.get(j), Integer.parseInt(values));
                            } else {
                                requestPayload.put(allKeys.get(j), values);
                            }
                        }
                    }
                }
            }
            allRequestPayloads.add(requestPayload);
        }
        System.out.println();

        try {
            ExcelUtils.closeWorkbook();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allRequestPayloads;
    }

    public LinkedHashMap<String, Object> createTask_v1(String sheetName) {

        Sheet sheet = workbook.getSheet(sheetName);

        int totalRows = sheet.getPhysicalNumberOfRows();

        LinkedHashMap<String, Object> tasks = new LinkedHashMap<>();

        List<LinkedHashMap<String, Object>> allRequestPayloads = new ArrayList<>();

        LinkedHashMap<String, Object> requestPayload;

        List<String> allKeys = new ArrayList<>();


        for (int i=0;i<sheet.getRow(0).getPhysicalNumberOfCells();i++){
            allKeys.add(sheet.getRow(0).getCell(i).getStringCellValue());
        }

        LinkedHashMap<String, Object> articleDetails;
        Map<String, Object> priceBoard;

        for (int i=1;i<sheet.getPhysicalNumberOfRows();i++) {
            requestPayload = new LinkedHashMap<>();
            requestPayload.put("requestId", GenricUtils.generateUUID());
            requestPayload.put("requestedAt", GenricUtils.getUTC_Format_CurrentDateTime());
            articleDetails = new LinkedHashMap<>();
            List<Map<String, Object>> priceBoards = new ArrayList<>();
            if (sheet.getRow(i).getCell(0).getStringCellValue().equals("end")){
                break;
            }
            else{
                Row row = sheet.getRow(i);
                for (int j = 0; j < sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
                    String values = df.formatCellValue(row.getCell(j));

                    if (!(values.equals(""))) {

                        if (values.equals("\"\"")) {
                            values = "";
                        }

                        if (values.equals("null")) {
                            values = null;
                        }

                        if (!allKeys.get(j).contains("priceBoard") && !allKeys.get(j).contains("pbStatus") &&
                                !allKeys.get(j).contains("requestedQuantity") && !allKeys.get(j).contains("requesterComments") &&
                                !allKeys.get(j).contains("taskType")) {
                            if (allKeys.get(j).equals("caselot")){
                                articleDetails.put(allKeys.get(j), Integer.parseInt(values));
                            } else {
                                articleDetails.put(allKeys.get(j), values);
                            }
                            requestPayload.put("articleDetails",articleDetails);
                        } else if (allKeys.get(j).contains("priceBoard")) {
                            priceBoard = new HashMap<>();
                            priceBoard.put(allKeys.get(j), values);
                            priceBoard.put(allKeys.get(++j), Boolean.parseBoolean(df.formatCellValue(row.getCell(j))));
                            priceBoards.add(priceBoard);
                            requestPayload.put("priceBoards", priceBoards);
                        } else {
                            if (allKeys.get(j).equals("requestedQuantity")){
                                requestPayload.put(allKeys.get(j), Integer.parseInt(values));
                            } else {
                                requestPayload.put(allKeys.get(j), values);
                            }
                        }
                    }
                }
            }
            allRequestPayloads.add(requestPayload);
        }
        tasks.put("refills", allRequestPayloads);

        try {
            ExcelUtils.closeWorkbook();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public List<LinkedHashMap<String, Object>> createTask_v1(String sheetName, int requiredTasks) {

        Sheet sheet = workbook.getSheet(sheetName);

        int totalRows = sheet.getPhysicalNumberOfRows();

//        LinkedHashMap<String, Object> tasks = new LinkedHashMap<>();

        List<LinkedHashMap<String, Object>> allRequestPayloads = new ArrayList<>();

        LinkedHashMap<String, Object> requestPayload;

        List<String> allKeys = new ArrayList<>();


        for (int i=0;i<sheet.getRow(0).getPhysicalNumberOfCells();i++){
            allKeys.add(sheet.getRow(0).getCell(i).getStringCellValue());
        }

        LinkedHashMap<String, Object> articleDetails;
        Map<String, Object> priceBoard;

        for (int i=1;i<sheet.getPhysicalNumberOfRows();i++) {
            requestPayload = new LinkedHashMap<>();
            requestPayload.put("requestId", GenricUtils.generateUUID());
            requestPayload.put("requestedAt", GenricUtils.getUTC_Format_CurrentDateTime());
            articleDetails = new LinkedHashMap<>();
            List<Map<String, Object>> priceBoards = new ArrayList<>();
            if (sheet.getRow(i).getCell(0).getStringCellValue().equals("end") || i==requiredTasks+1){
                break;
            }
            else{
                Row row = sheet.getRow(i);
                for (int j = 0; j < sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
                    String values = df.formatCellValue(row.getCell(j));

                    if (!(values.equals(""))) {

                        if (values.equals("\"\"")) {
                            values = "";
                        }

                        if (values.equals("null")) {
                            values = null;
                        }

                        if (!allKeys.get(j).contains("priceBoard") && !allKeys.get(j).contains("pbStatus") &&
                                !allKeys.get(j).contains("requestedQuantity") && !allKeys.get(j).contains("requesterComments") &&
                                !allKeys.get(j).contains("taskType")) {
                            if (allKeys.get(j).equals("caselot")){
                                articleDetails.put(allKeys.get(j), Integer.parseInt(values));
                            } else {
                                articleDetails.put(allKeys.get(j), values);
                            }
                            requestPayload.put("articleDetails",articleDetails);
                        } else if (allKeys.get(j).contains("priceBoard")) {
                            priceBoard = new HashMap<>();
                            priceBoard.put(allKeys.get(j), values);
                            priceBoard.put(allKeys.get(++j), Boolean.parseBoolean(df.formatCellValue(row.getCell(j))));
                            priceBoards.add(priceBoard);
                            requestPayload.put("priceBoards", priceBoards);
                        } else {
                            if (allKeys.get(j).equals("requestedQuantity")){
                                requestPayload.put(allKeys.get(j), Integer.parseInt(values));
                            } else {
                                requestPayload.put(allKeys.get(j), values);
                            }
                        }
                    }
                }
            }
            allRequestPayloads.add(requestPayload);
        }
//        tasks.put("refills", allRequestPayloads);

        try {
            ExcelUtils.closeWorkbook();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allRequestPayloads;
    }

    public List<LinkedHashMap<String, Object>> updateRefillTask_v1(String sheetName) {

        Sheet sheet = workbook.getSheet(sheetName);

        int totalRows = sheet.getPhysicalNumberOfRows();

        List<LinkedHashMap<String, Object>> allRequestPayloads = new ArrayList<>();

        LinkedHashMap<String, Object> requestPayload;

        List<String> allKeys = new ArrayList<>();


        for (int i=0;i<sheet.getRow(0).getPhysicalNumberOfCells();i++){
            allKeys.add(sheet.getRow(0).getCell(i).getStringCellValue());
        }

        for (int i=1;i<sheet.getPhysicalNumberOfRows();i++) {
            requestPayload = new LinkedHashMap<>();
            requestPayload.put("taskId", "");
            if (sheet.getRow(i).getCell(0).getStringCellValue().equals("end")){
                break;
            }
            else{
                Row row = sheet.getRow(i);
                for (int j = 0; j < sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
                    String values = df.formatCellValue(row.getCell(j));

                    if (!(values.equals(""))) {
                        if (allKeys.get(j).equals("refilledQuantity"))
                            requestPayload.put(allKeys.get(j),Integer.parseInt(values));
                        else
                            requestPayload.put(allKeys.get(j), values);
                    }
                }
            }
            allRequestPayloads.add(requestPayload);
        }

        try {
            ExcelUtils.closeWorkbook();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allRequestPayloads;
    }

    public List<LinkedHashMap<String, Object>> updateRefillTask_v1(String sheetName, int requiredUpdateRefillTasks) {

        Sheet sheet = workbook.getSheet(sheetName);

        int totalRows = sheet.getPhysicalNumberOfRows();

        List<LinkedHashMap<String, Object>> allRequestPayloads = new ArrayList<>();

        LinkedHashMap<String, Object> requestPayload;

        List<String> allKeys = new ArrayList<>();


        for (int i=0;i<sheet.getRow(0).getPhysicalNumberOfCells();i++){
            allKeys.add(sheet.getRow(0).getCell(i).getStringCellValue());
        }

        for (int i=1;i<sheet.getPhysicalNumberOfRows();i++) {
            requestPayload = new LinkedHashMap<>();
            requestPayload.put("taskId", "");
            if (sheet.getRow(i).getCell(0).getStringCellValue().equals("end") || i==requiredUpdateRefillTasks+1){
                break;
            }
            else{
                Row row = sheet.getRow(i);
                for (int j = 0; j < sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
                    String values = df.formatCellValue(row.getCell(j));

                    if (!(values.equals(""))) {
                        if (allKeys.get(j).equals("refilledQuantity"))
                            requestPayload.put(allKeys.get(j),Integer.parseInt(values));
                        else
                            requestPayload.put(allKeys.get(j), values);
                    }
                }
            }
            allRequestPayloads.add(requestPayload);
        }

        try {
            ExcelUtils.closeWorkbook();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allRequestPayloads;
    }

    public List<LinkedHashMap<String, Object>> updatePriceboardTask_v1(String sheetName) {

        Sheet sheet = workbook.getSheet(sheetName);

        int totalRows = sheet.getPhysicalNumberOfRows();

        List<LinkedHashMap<String, Object>> allRequestPayloads = new ArrayList<>();

        LinkedHashMap<String, Object> requestPayload;

        List<String> allKeys = new ArrayList<>();


        for (int i=0;i<sheet.getRow(0).getPhysicalNumberOfCells();i++){
            allKeys.add(sheet.getRow(0).getCell(i).getStringCellValue());
        }

        Map<String, Object> priceBoard;

        for (int i=1;i<sheet.getPhysicalNumberOfRows();i++) {
            requestPayload = new LinkedHashMap<>();
            requestPayload.put("taskId", "");
            List<Map<String, Object>> priceBoards = new ArrayList<>();
            if (sheet.getRow(i).getCell(0).getStringCellValue().equals("end")){
                break;
            }
            else{
                Row row = sheet.getRow(i);
                for (int j = 0; j < sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
                    String values = df.formatCellValue(row.getCell(j));

                    if (!(values.equals(""))) {
                        if (allKeys.get(j).contains("priceBoard")) {
                            priceBoard = new HashMap<>();
                            priceBoard.put(allKeys.get(j), values);
                            priceBoard.put(allKeys.get(++j), Boolean.parseBoolean(df.formatCellValue(row.getCell(j))));
                            priceBoards.add(priceBoard);
                            requestPayload.put("priceBoards", priceBoards);
                        } else {
                                requestPayload.put(allKeys.get(j), values);
                        }
                    }
                }
            }
            allRequestPayloads.add(requestPayload);
        }

        try {
            ExcelUtils.closeWorkbook();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allRequestPayloads;
    }

    public List<LinkedHashMap<String, Object>> updatePriceboardTask_v1(String sheetName, int requiredUpdatePriceboardTasks) {

        Sheet sheet = workbook.getSheet(sheetName);

        int totalRows = sheet.getPhysicalNumberOfRows();

        List<LinkedHashMap<String, Object>> allRequestPayloads = new ArrayList<>();

        LinkedHashMap<String, Object> requestPayload;

        List<String> allKeys = new ArrayList<>();


        for (int i=0;i<sheet.getRow(0).getPhysicalNumberOfCells();i++){
            allKeys.add(sheet.getRow(0).getCell(i).getStringCellValue());
        }

        Map<String, Object> priceBoard;

        for (int i=1;i<sheet.getPhysicalNumberOfRows();i++) {
            requestPayload = new LinkedHashMap<>();
            requestPayload.put("taskId", "");
            List<Map<String, Object>> priceBoards = new ArrayList<>();
            if (sheet.getRow(i).getCell(0).getStringCellValue().equals("end") || i==requiredUpdatePriceboardTasks+1){
                break;
            }
            else{
                Row row = sheet.getRow(i);
                for (int j = 0; j < sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
                    String values = df.formatCellValue(row.getCell(j));

                    if (!(values.equals(""))) {
                        if (allKeys.get(j).contains("priceBoard")) {
                            priceBoard = new HashMap<>();
                            priceBoard.put(allKeys.get(j), values);
                            priceBoard.put(allKeys.get(++j), Boolean.parseBoolean(df.formatCellValue(row.getCell(j))));
                            priceBoards.add(priceBoard);
                            requestPayload.put("priceBoards", priceBoards);
                        } else {
                            requestPayload.put(allKeys.get(j), values);
                        }
                    }
                }
            }
            allRequestPayloads.add(requestPayload);
        }

        try {
            ExcelUtils.closeWorkbook();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allRequestPayloads;
    }

    public List<LinkedHashMap<String, Object>> updateOthersTask_v1(String sheetName) {

        Sheet sheet = workbook.getSheet(sheetName);

        int totalRows = sheet.getPhysicalNumberOfRows();

        List<LinkedHashMap<String, Object>> allRequestPayloads = new ArrayList<>();

        LinkedHashMap<String, Object> requestPayload;

        List<String> allKeys = new ArrayList<>();


        for (int i=0;i<sheet.getRow(0).getPhysicalNumberOfCells();i++){
            allKeys.add(sheet.getRow(0).getCell(i).getStringCellValue());
        }


        for (int i=1;i<sheet.getPhysicalNumberOfRows();i++) {
            requestPayload = new LinkedHashMap<>();
            requestPayload.put("taskId", "");
            List<Map<String, Object>> priceBoards = new ArrayList<>();
            if (sheet.getRow(i).getCell(0).getStringCellValue().equals("end")){
                break;
            }
            else{
                Row row = sheet.getRow(i);
                for (int j = 0; j < sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
                    String values = df.formatCellValue(row.getCell(j));

                    if (!(values.equals(""))) {
                        requestPayload.put(allKeys.get(j), values);
                    }
                }
            }
            allRequestPayloads.add(requestPayload);
        }

        try {
            ExcelUtils.closeWorkbook();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allRequestPayloads;
    }

    public List<LinkedHashMap<String, Object>> updateOthersTask_v1(String sheetName, int requiredUpdateOthersTasks) {

        Sheet sheet = workbook.getSheet(sheetName);

        int totalRows = sheet.getPhysicalNumberOfRows();

        List<LinkedHashMap<String, Object>> allRequestPayloads = new ArrayList<>();

        LinkedHashMap<String, Object> requestPayload;

        List<String> allKeys = new ArrayList<>();


        for (int i=0;i<sheet.getRow(0).getPhysicalNumberOfCells();i++){
            allKeys.add(sheet.getRow(0).getCell(i).getStringCellValue());
        }


        for (int i=1;i<sheet.getPhysicalNumberOfRows();i++) {
            requestPayload = new LinkedHashMap<>();
            requestPayload.put("taskId", "");
            List<Map<String, Object>> priceBoards = new ArrayList<>();
            if (sheet.getRow(i).getCell(0).getStringCellValue().equals("end") || i==requiredUpdateOthersTasks+1){
                break;
            }
            else{
                Row row = sheet.getRow(i);
                for (int j = 0; j < sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
                    String values = df.formatCellValue(row.getCell(j));

                    if (!(values.equals(""))) {
                        requestPayload.put(allKeys.get(j), values);
                    }
                }
            }
            allRequestPayloads.add(requestPayload);
        }

        try {
            ExcelUtils.closeWorkbook();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allRequestPayloads;
    }

    public static void main(String[] args) {
        ExcelUtils excelUtils = new ExcelUtils("QuickFill_TestData.xlsx");
        List<LinkedHashMap<String, Object>> allTasks = excelUtils.updateOthersTask_v1("UpdateOthersTasksByRefiller");
        System.out.println(allTasks);
//        for (LinkedHashMap<String, Object> task : allTasks) {
//            System.out.println(task);
//            System.out.println();
//        }

    }

}



