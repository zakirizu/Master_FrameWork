package com.cotiviti.hdap.APIPayLoads;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;

public class NpiRegistryData {

    public static void main(String[] args) {
        // Prepare the JSON request data
    	String organizationName = "John";
        String body = "{\r\n"
                + "    \"organizationName\": \""+organizationName+"\"\r\n"
          + "}";

        // Base URL for the API
        String url = "https://npiregistry.cms.hhs.gov/RegistryBack/search";

        // Send the POST request using RestAssured
        Response response = RestAssured.given()
                .baseUri(url)  // Set the base URL
                .contentType(ContentType.JSON)  // Set the content type to JSON
                .accept(ContentType.JSON)  // Set the accept header to JSON
                .body(body)  // Attach the request body
                .post();  // Send the POST request

        // Check the response status code
        if (response.statusCode() == 200) {
            // If response is OK, print the response
            String rsBody = response.getBody().asPrettyString();
            System.out.println("Response: " + rsBody);

            // Parse the response JSON
            JSONArray jsonResponse = new JSONArray(rsBody);

            // Create an Excel workbook and sheet
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("NPI Data");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("NPI Number");
            headerRow.createCell(1).setCellValue("TIN");
            headerRow.createCell(2).setCellValue("Organization Name");
            headerRow.createCell(3).setCellValue("Address Line 1");
            headerRow.createCell(4).setCellValue("City");
            headerRow.createCell(5).setCellValue("State");
            headerRow.createCell(6).setCellValue("Postal Code");
            headerRow.createCell(7).setCellValue("Phone Number");
            headerRow.createCell(8).setCellValue("Used");

            // Loop through the JSON array and write data to Excel
            for (int i = 0; i < jsonResponse.length(); i++) {
                JSONObject npiData = jsonResponse.getJSONObject(i);
                JSONObject basic = npiData.getJSONObject("basic");
                JSONObject primaryAddress = npiData.getJSONObject("primaryAddress");

                // Create a new row for each NPI entry
                Row row = sheet.createRow(i + 1);

                // Set NPI Number
                String npiNumber = npiData.getString("number");
                row.createCell(0).setCellValue(npiNumber);

                // Set TIN (Remove last digit of NPI)
                String tin = removeLastDigit(npiNumber);
                row.createCell(1).setCellValue(tin);

                // Set Organization Name
                row.createCell(2).setCellValue(basic.getString("orgName"));

                // Set Address Line 1
                row.createCell(3).setCellValue(primaryAddress.getString("addressLine1"));

                // Set City
                row.createCell(4).setCellValue(primaryAddress.getString("city"));

                // Set State
                row.createCell(5).setCellValue(primaryAddress.getString("state"));

                // Set Postal Code
                String rawZip = primaryAddress.getString("postalCode");
                if (rawZip != null && rawZip.length() == 9) {
                    rawZip = rawZip.substring(0, 5) + "-" + rawZip.substring(5);
                }
                row.createCell(6).setCellValue(rawZip);
                
             //   row.createCell(6).setCellValue(primaryAddress.getString("postalCode"));

                // Format Phone Number as (800) 111-2222
                String phoneNumber = primaryAddress.getString("teleNumber");
                String formattedPhone = formatPhoneNumber(phoneNumber);
                row.createCell(7).setCellValue(formattedPhone);
            }

            // Write the Excel file to disk
            try (FileOutputStream fileOut = new FileOutputStream("NPI_Data.xlsx")) {
                workbook.write(fileOut);
                System.out.println("Data has been written to NPI_Data.xlsx");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            // If the response is not OK, print the error code and response
            System.out.println("Request failed with response code: " + response.statusCode());
            System.out.println("Response: " + response.getBody().asString());
        }
    }

    // Method to remove the last digit from NPI to create TIN
    private static String removeLastDigit(String npiNumber) {
        if (npiNumber.length() > 1) {
            return npiNumber.substring(0, npiNumber.length() - 1);
        } else {
            return npiNumber;  // If the NPI is only one digit long, return it as is
        }
    }

    // Method to format phone number to (800) 111-2222
    private static String formatPhoneNumber(String phoneNumber) {
        // Remove non-numeric characters from the phone number
        String cleaned = phoneNumber.replaceAll("[^\\d]", "");

        // Check if the cleaned number has 10 digits
        if (cleaned.length() == 10) {
            // Format the phone number as (800) 111-2222
            return String.format("(%s) %s-%s",
                    cleaned.substring(0, 3),
                    cleaned.substring(3, 6),
                    cleaned.substring(6));
        } else {
            return phoneNumber;  // If the phone number doesn't have 10 digits, return it as is
        }
    }
}
