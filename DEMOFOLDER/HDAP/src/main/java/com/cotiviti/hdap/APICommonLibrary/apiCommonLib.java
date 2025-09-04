package com.cotiviti.hdap.APICommonLibrary;

import static io.restassured.RestAssured.given;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;

import com.cotiviti.hdap.APIPayLoads.CancelChaseRequestPage;
import com.cotiviti.hdap.APIPayLoads.ChaseRequestWithProviderPayLoad;
import com.cotiviti.hdap.BaseClass.BaseTest;
import com.cotiviti.hdap.CommonUtils.PropertiesFileReader;
import com.fasterxml.jackson.databind.JsonNode;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;;

/**
 * @author dinesh.neeli
 *
 */
public class apiCommonLib extends BaseTest {
	

	 private Set<String> usedDates1 = new HashSet<>();
	    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	static Response response = null;

	public apiCommonLib() {
		super();
	}

	public static Set<String> usedDates = new HashSet<>();
	public String env = System.getProperty("env");

	/**
	 * @author dinesh.neeli
	 */
	public String generateBearerToken(ConcurrentHashMap<String, String> dataMap) {
		String env = System.getProperty("env");
		String barerToken = "";
		try {
			switch (env) {
			case "UAT":
				barerToken = new apiAuthorization().createUATAuth();
				break;
			case "QA":
				barerToken = new apiAuthorization().createQAAuth();
				break;
			case "DEV":
				barerToken = new apiAuthorization().createQAAuth();
				break;
			default:
				break;
			}
			dataMap.put("barerToken", barerToken);
		} catch (Exception e) {
			Assert.fail(e.getLocalizedMessage());
		}
		return barerToken;
	}

	/**
	 * @author shankar.kudupudi
	 * @Reviewer dinesh.neeli
	 */

	public void processDataAndGenerateOutput(String inputFilePath, String outputFilePath, String authToken) {

		boolean reTry = false;
		int retryRowIndex = -1; // Variable to store the row index to start retrying from
		try {
			// Load the input Excel file
			FileInputStream fis = new FileInputStream(inputFilePath);
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet inputSheet = workbook.getSheetAt(0); // Assuming input data is in the first sheet

			// Create a BufferedWriter for the output CSV file
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));

			writer.write(
					"IntendedUse,AuditType,ChartType,DateOfServiceStart,DateOfServiceEnd,FirstName,LastName,FacitlityName,FTIN,FNPI,FAddress1,FAddress2,FCity,FState,FZip,PLastName,PFirstName,PTIN,PNPI,PAddress1,PAddress2,PCity,PState,PZip,DOB\n");

			ConcurrentHashMap<String, String> dataMap = new ConcurrentHashMap<>();
			ConcurrentHashMap<String, Integer> columnIndexMap = new ConcurrentHashMap<>();
			Row headerRow = inputSheet.getRow(0);
			int i = 1; // Start from 1, since row 0 is the header row

			// Map the header columns to indices
			for (Cell cell : headerRow) {
				String columnName = cell.getStringCellValue().trim();
				columnIndexMap.put(columnName, cell.getColumnIndex());
			}

			// Use a do-while loop so we can retry when needed
			do {
				reTry = false; // Reset reTry at the beginning of each attempt

				for (; i < inputSheet.getPhysicalNumberOfRows(); i++) { // Start from current value of i, or
																		// retryRowIndex
					Row inputRow = inputSheet.getRow(i);

					String rgId = ExcelReader.getCellValueByColumnName(inputRow, columnIndexMap, "RGID");
					String factId = ExcelReader.getCellValueByColumnName(inputRow, columnIndexMap, "FACTID");
					String practId = ExcelReader.getCellValueByColumnName(inputRow, columnIndexMap, "PRACTID");
					String accountID = ExcelReader.getCellValueByColumnName(inputRow, columnIndexMap, "ACCOUNTID");
					String subAccountID = ExcelReader.getCellValueByColumnName(inputRow, columnIndexMap,
							"SUBACCOUNTID");
					String providerType = ExcelReader.getCellValueByColumnName(inputRow, columnIndexMap,
							"PROVIDERTYPE");
					String run = ExcelReader.getCellValueByColumnName(inputRow, columnIndexMap, "RUN");

					// Initialize the data map for this row
					if (reTry)
						run = "Y";

					if (run.equalsIgnoreCase("Y")) {
						// Process the data for RG, Fact, and Pract
						requestGroupDataExtractor rgProcessor = new requestGroupDataExtractor();
						dataMap = rgProcessor.getRGData(dataMap, rgId, authToken);

						facitilityDataExtractor factProcessor = new facitilityDataExtractor();
						dataMap = factProcessor.getFacitilityData(dataMap, factId, authToken);

						practitionerDataExtractor practProcessor = new practitionerDataExtractor();
						dataMap = practProcessor.getPractitionerData(dataMap, practId, authToken);

					}
					String[] generatedName = generateUKName();
					String firstName = generatedName[0];
					String lastName = generatedName[1];
					String startDate = generateUniqueStartDate(0, 0);
					//String endDate = generateUniqueEndDate(startDate);
					dataMap.put("RGstartDate", startDate); // startDate in yyyy-MM-dd format
				//	dataMap.put("RGendDate", endDate);
					dataMap.put("RGfirstName", firstName);
					dataMap.put("RGlastName", lastName);
					// Generate unique DOB in the format YYYY-MM-DD
					String dob = generateDOB(inputRow.getRowNum());
					dataMap.put("dob", dob);
					dataMap = setCotivitiClaimNumber(dataMap);

					String payLoad = ChaseRequestWithProviderPayLoad.chaseRequestPayLoad(dataMap.get("RGProjectTypes"),
							dataMap.get("RGAuditTypes"), dataMap.get("RGChartTypes"),
							dataMap.get("CotivitiClaimNumber"), dataMap.get("CotivitiClaimNumber"),
							dataMap.get("RGstartDate"), dataMap.get("RGendDate"), dataMap.get("RGfirstName"),
							dataMap.get("RGlastName"), dataMap.get("dob"), dataMap.get("Fname"), dataMap.get("Fstreet"),
							dataMap.get("Fcity"), dataMap.get("Fstate"), dataMap.get("FpostalCode"),
							dataMap.get("Fnpi"), dataMap.get("Ftin"), dataMap.get("Fphone"), dataMap.get("PfirstName"),
							dataMap.get("PlastName"), dataMap.get("Pstreet"), dataMap.get("Pcity"),
							dataMap.get("Pstate"), dataMap.get("PpostalCode"), dataMap.get("Pnpi"), dataMap.get("Ptin"),
							accountID, subAccountID, providerType ,dataMap.get("ProspectiveClaimFlag"));


					writer.write("\"" + dataMap.get("RGProjectTypes") + "\",\"" + dataMap.get("RGAuditTypes") + "\",\""
							+ dataMap.get("RGChartTypes") + "\",\"" + dataMap.get("RGstartDate") + "\",\""
							+ dataMap.get("RGendDate") + "\",\"" + dataMap.get("RGfirstName") + "\",\""
							+ dataMap.get("RGlastName") + "\",\"" + dataMap.get("Fname") + "\",\"" + dataMap.get("Ftin")
							+ "\",\"" + dataMap.get("Fnpi") + "\",\"" + dataMap.get("Fstreet") + "\",\"" + "" + "\",\""
							+ dataMap.get("Fcity") + "\",\"" + dataMap.get("Fstate") + "\",\""
							+ dataMap.get("FpostalCode") + "\",\"" + dataMap.get("PfirstName") + "\",\""
							+ dataMap.get("PlastName") + "\",\"" + dataMap.get("Ptin") + "\",\"" + dataMap.get("Pnpi")
							+ "\",\"" + dataMap.get("Pstreet") + "\",\"" + "" + "\",\"" + dataMap.get("Pcity") + "\",\""
							+ dataMap.get("Pstate") + "\",\"" + dataMap.get("PpostalCode") + "\",\"" + dob + "\"\n");

					String apiUrl = PropertiesFileReader.getAPIProperty(env + ".ChaseRequestEndPoint");

					// Fetch response from the API
					Response jsonResponse = sendPostRequest(apiUrl, payLoad, authToken);
					int statusCode = jsonResponse.getStatusCode();

					if (statusCode == 401) {
						authToken = generateBearerToken(dataMap);
						// Set the retry row index and break out of the loop to retry from that row
						retryRowIndex = i;
						reTry = true;
						break; // Break out of the for loop to reattempt the process from the failed row
					}

					System.err.println("Data extraction process completed for RG : " + rgId + " Facility : " + factId
							+ ", Practitioner : " + practId + " iteration :" + (i + 1));
				}
				// If retry is triggered, continue from the failed row index
			} while (reTry); // Repeat if retry is needed

			// Close the writer
			writer.close();
			workbook.close();

			System.out.println("Data successfully extracted and written to " + outputFilePath);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}

	}

	/**
	 * @author dinesh.neeli
	 * @param rowNum
	 * @return
	 */
	public String generateDOB(int rowNum) {
		// Define the specific years you want to cycle through
		int[] years = { 1999, 1997, 1996 };

		// Calculate the index for the year based on the row number, cycling through the
		// years
		int year = years[rowNum % years.length];

		// Calculate the month and day based on the row number to make each date unique
		int month = (rowNum % 12) + 1; // Cycle through months (1-12)
		int day = (rowNum % 28) + 1; // Cycle through days (1-28 to ensure valid dates)

		// Construct the date using the year, month, and day
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day); // Months in Calendar are 0-based (0 = January)

		// Format the date to "yyyy-MM-dd"
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(calendar.getTime());
	}

	/**
	 * 
	 * @param jsonNode
	 * @return
	 */
	public String extractType(JsonNode jsonNode) {
		try {
			if (jsonNode.isArray() && jsonNode.size() > 0) {
				for (JsonNode node : jsonNode) {
					String type = node.path("Type").asText();
					if (!type.isEmpty()) {
						return type; // Return the first non-empty type found
					}
				}
			}
		} catch (Exception e) {
			Assert.fail(e.getLocalizedMessage());
		}
		return ""; // If no match, return an empty string
	}

	/**
	 * 
	 * @param nameNode
	 * @return
	 */
	public String extractName(JsonNode nameNode) {
		if (nameNode.has("NamePart") && nameNode.has("Ranges")) {
			String namePart = nameNode.path("NamePart").asText();
			JsonNode rangesNode = nameNode.path("Ranges");
			if (rangesNode.isArray() && rangesNode.size() > 0) {
				JsonNode range = rangesNode.get(0); // Taking the first range
				String startChar = range.path("startChar").asText();
				String endChar = range.path("endChar").asText();
				return namePart + " (" + startChar + "-" + endChar + ")";
			}
		}
		return "";
	}

	public ConcurrentHashMap<String, String> extractDateRangeMap(ConcurrentHashMap<String, String> dateMap,
			JsonNode jsonNode) {
		try {
			if (jsonNode.isArray() && jsonNode.size() > 0) {
				for (JsonNode node : jsonNode) {
					String startDate = node.path("StartDate").asText("");
					String endDate = node.path("EndDate").asText("");
					if (!startDate.isEmpty() && !endDate.isEmpty()) {
						dateMap.put("ExtRGStartDate", startDate);
						dateMap.put("ExtRGEndDate", endDate);
					}
				}
			}
		} catch (Exception e) {
			Assert.fail("Error extracting date range: " + e.getMessage());
		}
		return dateMap;
	}

	public int extractYear(String dateStr) {
		int year = 0;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(dateStr, formatter);
			year = date.getYear();
		} catch (Exception e) {
			System.err.println("Invalid date: " + e.getMessage());
		}
		return year;
	}
	
	//unique start date generation method by shankar.kudupudi
	
	    // Generates a unique random date between startDateStr and endDateStr (inclusive)
	    public String generateUniqueDate(String startDateStr, String endDateStr) {
	        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
	        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

	        if (startDate.isAfter(endDate)) {
	            throw new IllegalArgumentException("Start date must be before or equal to end date.");
	        }

	        long daysBetween = endDate.toEpochDay() - startDate.toEpochDay();
	        String newDate;

	        do {
	            long randomOffset = (long) (Math.random() * (daysBetween + 1));
	            LocalDate randomDate = startDate.plusDays(randomOffset);
	            newDate = randomDate.format(formatter);
	        } while (usedDates1.contains(newDate));

	        usedDates1.add(newDate);
	        return newDate;
	    }

	    // Generates a unique start date between overallStartDate and overallEndDate
	    public String generateUniqueStartDate(String overallStartDate, String overallEndDate) {
	        return generateUniqueDate(overallStartDate, overallEndDate);
	    }

	    // Generates a unique end date that is after startDate and before or equal to overallEndDate
	    public String generateUniqueEndDate(String startDate, String overallEndDate) {
	        return generateUniqueDate(startDate, overallEndDate);
	    }


	/**
	 * 
	 * @return
	 */
	public String generateUniqueStartDate(int startYear, int endYear) {

		if (startYear <= 0 || endYear <= 0) {
			startYear = 2001;
			endYear = 2024;
		}
		String startDate = "";
		try {
			do {
				startDate = generateRandomDate(startYear, endYear); // Generate a random start date between 2000 and
																	// 2024
			} while (usedDates.contains(startDate)); // Ensure uniqueness
			usedDates.add(startDate); // Add the new start date to the set
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getLocalizedMessage());
		}
		return startDate;
	}

	/**
	 * 
	 * @param startDate
	 * @return
	 */
	public String generateUniqueEndDate(String startDate  ,int endYear ) {
		String endDate;
		int startYear = Integer.parseInt(startDate.split("-")[0]);
	//	int endYear = 2030;

		// Generate a random end date that is after the start date
		do {
			endDate = generateRandomEndDate(startYear, endYear);
		} while (usedDates.contains(endDate) || endDate.compareTo(startDate) <= 0); // Ensure uniqueness

		usedDates.add(endDate); // Add the new end date to the set
		return endDate;
	}

	/**
	 * 
	 * @param startYear
	 * @param endYear
	 * @return
	 */
	public String generateRandomDate(int startYear, int endYear) {
		Random rand = new Random();
		int year = rand.nextInt(endYear - startYear + 1) + startYear; // Random year between startYear and endYear
		int dayOfYear = rand.nextInt(365) + 1; // Random day in the year

		Calendar cal = Calendar.getInstance();
		cal.set(year, Calendar.JANUARY, 1);
		cal.add(Calendar.DAY_OF_YEAR, dayOfYear - 1); // Adjust to get the correct date

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Format as year-month-day
		return sdf.format(cal.getTime());
	}

	
	public String generateRandomEndDate(int startYear, int endYear) {
		    int adjustedEndYear = endYear - 1;

		    if (startYear > adjustedEndYear) {
		        throw new IllegalArgumentException("Invalid year range: startYear must be less than or equal to (endYear - 1)");
		    }

		    Random rand = new Random();
		    int year = rand.nextInt(adjustedEndYear - startYear + 1) + startYear;

		    Calendar cal = Calendar.getInstance();
		    cal.set(year, Calendar.JANUARY, 1);
		    int daysInYear = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
		    int dayOfYear = rand.nextInt(daysInYear) + 1;

		    cal.set(Calendar.DAY_OF_YEAR, dayOfYear);

		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    return sdf.format(cal.getTime());
		}


	/**
	 * 
	 * @return
	 */
	public String[] generateUKName() {
		String firstName = "";
		String lastName = "";
		try {
			List<String> firstNames = Arrays.asList("Oliver", "George", "Harry", "Jack", "Jacob", "Charlie", "Thomas",
					"James", "William", "Joshua");
			List<String> lastNames = Arrays.asList("Smith", "Jones", "Taylor", "Brown", "Williams", "Wilson", "Evans",
					"Thomas", "Johnson", "Roberts");

			Random random = new Random();
			firstName = firstNames.get(random.nextInt(firstNames.size()));
			lastName = lastNames.get(random.nextInt(lastNames.size()));
		} catch (Exception e) {
			Assert.fail(e.getLocalizedMessage());
		}

		return new String[] { firstName, lastName };
	}

	/**
	 * 
	 * @param apiUrl
	 * @param authToken
	 * @return
	 */
	public String sendGetRequest(String apiUrl, String authToken) {
		String response = "";
		try {
			response = given().baseUri(apiUrl).header("Content-Type", "application/json")
					.header("Authorization", authToken).when().get().then().statusCode(200).extract().asString();

		} catch (Exception e) {
			Assert.fail(e.getLocalizedMessage());
		}
		return response;
	}
	
	public Response sendGetRequestResponse(String apiUrl, String authToken) {
	    Response response = null;
	    try {
	        response = given()
	            .baseUri(apiUrl)
	            .header("Content-Type", "application/json")
	            .header("Authorization", authToken)
	            .when()
	            .get();
	    } catch (Exception e) {
	        Assert.fail(e.getLocalizedMessage());
	    }
	    return response;
	}

	public String sendGetRequestwithPostMethod(String apiUrl, String authToken) {
		String response = "";
		try {
			response = given().baseUri(apiUrl).header("Content-Type", "application/json")
					.header("Authorization", authToken).when().post().then().statusCode(200).extract().asString();
		} catch (Exception e) {
			Assert.fail(e.getLocalizedMessage());
		}
		return response;
	}

	public Response sendPostRequest(String apiUrl, String reqBody, String authToken) {

		try {
			response = given().header("Content-Type", "application/json").header("Authorization", authToken)
					.body(reqBody).queryParam("", "").when().post(apiUrl).then().log().body(true).extract().response();
			Thread.sleep(3000);
		} catch (Exception e) {
			// Assert.fail(e.getLocalizedMessage());
		}
		return response;
	}

	public Response sendPostRequestWithStatusValidation(String apiUrl, String reqBody, String authToken) {

		try {
			response = given().header("Content-Type", "application/json").header("Authorization", authToken)
					.body(reqBody).queryParam("", "").when().post(apiUrl).then().log().body(true).extract().response();
		} catch (Exception e) {
			// Assert.fail(e.getLocalizedMessage());
		}
		return response;
	}

	public void sendPostRequestWithSchemavalidation(String jsonFile) throws IOException {

		String filePath = System.getProperty("user.dir");
		System.out.println(filePath);
		File file = new File(filePath + "/src/main/java/com/cotiviti/hdap/jsonSchemaValidationFiles/" + jsonFile);
		try {
			response.then().body(JsonSchemaValidator.matchesJsonSchema(file));

		} catch (Exception e) {
			Assert.fail(e.getLocalizedMessage());
		}

	}

	public Response sendPutRequest(String apiUrl, String json, String authToken) {
		// String response="";
		try {
			response = given().baseUri(apiUrl).header("Content-Type", "application/json")
					.header("Authorization", authToken).body(json).when().put();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return response;
	}

	public void statuscodeValidator(String expectedStatusCode) {
		String ActualStatuscode = String.valueOf(response.getStatusCode());
		Assert.assertEquals(ActualStatuscode, expectedStatusCode);

	}

	public void validateResponse(String expectedresponse) {

		String ActualResponse = String.valueOf(response.then().log().body(true).extract().response().asString());
		if (ActualResponse.contains(expectedresponse)) {
			System.out.println("Response Message validated Successfully");
		} else {
			Assert.assertEquals(expectedresponse, ActualResponse);
		}

	}

	public static int getPostRequestStatusCode(String apiUrl, String reqBody, String authToken) {
		Response response = null;
		int statuscode = 0;
		try {
			response = given().header("Content-Type", "application/json").header("Authorization", authToken)
					.body(reqBody).queryParam("", "").when().post(apiUrl);
			statuscode = response.getStatusCode();
		} catch (Exception e) {
			// Assert.fail(e.getLocalizedMessage());
		}
		return statuscode;
	}
//	/**
//	 * 
//	 * @param address
//	 * @return
//	 */
//	public String[] parseAddress(String address) {
//		String[] addressParts = null;
//		try {
//			addressParts = new String[10];
//			// Regular expression to split the address
//			// This assumes address format: street, city, state, postal code
//			// Example: "03354 Brown Landing Apt. 514, West Dustinfort, FM, 54477"
//			String[] splitAddress = address.split(", ");
//			if (splitAddress.length == 4) {
//				addressParts[0] = splitAddress[0]; // Street
//				addressParts[1] = splitAddress[1]; // City
//				addressParts[2] = splitAddress[2]; // State
//				addressParts[3] = splitAddress[3]; // Postal Code
//			}
//		} catch (Exception e) {
//			Assert.fail(e.getLocalizedMessage());
//		}
//		return addressParts;
//	}

	/**
	 * 
	 * @param address
	 * @return
	 */
	public String[] parseAddress(String address) {
		String[] addressParts = null;
		try {
			// Split the address using ", " as delimiter
			String[] splitAddress = address.split(", ");
			addressParts = new String[splitAddress.length];
			for (int i = 0; i < splitAddress.length; i++) {
				String temp = splitAddress[i];
				addressParts[i] = temp;
			}
		} catch (Exception e) {
			Assert.fail(e.getLocalizedMessage());
		}
		return addressParts;
	}

	/**
	 * @author dinesh.neeli
	 * @param filePath
	 * @return
	 */
	public static String readFile(String filePath) {
		StringBuilder content = new StringBuilder();

		try {
			// Read the file content into a String
			BufferedReader reader = Files.newBufferedReader(Paths.get(filePath));
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line).append("\n");
			}
			reader.close();
		} catch (Exception e) {
			System.out.println("Error reading the file: " + e.getMessage());
			return null;
		}

		return content.toString();
	}

	public ConcurrentHashMap<String, String> setCotivitiClaimNumber(ConcurrentHashMap<String, String> dataMap) {
		long currentTimeMillis = System.currentTimeMillis();
		long timestamp = currentTimeMillis / 1000;
//		System.out.println("Timestamp: " + timestamp);
		dataMap.put("CotivitiClaimNumber", Long.toString(timestamp));
		executionMap.put("CotivitiClaimNumber", Long.toString(timestamp));
		return dataMap;

	}

	/**
	 * @author dinesh.neeli
	 * @param testData
	 * @param sheetName
	 * @param tcName
	 * @param
	 * @return
	 */
	public ConcurrentHashMap<String, String> getTestCaseData(ConcurrentHashMap<String, String> testData,
			String sheetName, String tcName, ITestContext context) {
		ExcelReader reader = new ExcelReader();
		String suiteName = context.getCurrentXmlTest().getSuite().getName();
		try {
			String filepath = reader.getDataPath(context);
			setup(filepath, sheetName);
			testData = reader.getData(sheetName, tcName, filepath);
			testData.put("SuiteName", suiteName);
		} catch (Exception e) {
			Assert.fail(e.getLocalizedMessage());
		}
		return testData;
	}

//	public void cancelCRdata(String inputFilePath, String sheetName, String authToken) {
//
//		try {
//			// Load the input Excel file
//			FileInputStream fis = new FileInputStream(inputFilePath);
//			Workbook workbook = new XSSFWorkbook(fis);
//			Sheet inputSheet = workbook.getSheet(sheetName); // Assuming input data is in the first sheet
//
//			// Create a BufferedWriter for the output CSV file
//			ConcurrentHashMap<String, String> dataMap = new ConcurrentHashMap<>();
//			ConcurrentHashMap<String, Integer> columnIndexMap = new ConcurrentHashMap<>();
//			Row headerRow = inputSheet.getRow(0);
//
//			// Map the header columns to indices
//			for (Cell cell : headerRow) {
//				String columnName = cell.getStringCellValue().trim();
//				columnIndexMap.put(columnName, cell.getColumnIndex());
//			}
//			for (Row inputRow : inputSheet) {
//				if (inputRow.getRowNum() == 0) {
//					continue; // Skip the header row
//				}
//
//				String claimNumber = ExcelReader.getCellValueByColumnName(inputRow, columnIndexMap, "ClaimNumber");
//				String projectType = ExcelReader.getCellValueByColumnName(inputRow, columnIndexMap, "ProjectType");
//				String chaseReqID = ExcelReader.getCellValueByColumnName(inputRow, columnIndexMap, "ChaseRequestID");
//
//				String jsonResponse = new CancelChaseRequestPage().cancelChaseRequests(projectType, claimNumber,
//						chaseReqID, authToken);
//				System.err.println("Chase request cancelled Successfully : " + chaseReqID);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println(e.toString());
//		}
//	}
//	

	public void cancelCRdata(String inputFilePath, String sheetName, String authToken) {

		boolean reTry = false;
		int retryRowIndex = -1;
		try {
			// Load the input Excel file
			FileInputStream fis = new FileInputStream(inputFilePath);
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet inputSheet = workbook.getSheet(sheetName); // Assuming input data is in the first sheet

			// Create a BufferedWriter for the output CSV file
			ConcurrentHashMap<String, String> dataMap = new ConcurrentHashMap<>();
			ConcurrentHashMap<String, Integer> columnIndexMap = new ConcurrentHashMap<>();
			Row headerRow = inputSheet.getRow(0);

			// Map the header columns to indices
			for (Cell cell : headerRow) {
				String columnName = cell.getStringCellValue().trim();
				columnIndexMap.put(columnName, cell.getColumnIndex());
			}
			int i = 1;

			do {
				reTry = false; // Reset reTry at the beginning of each attempt

				for (; i < inputSheet.getPhysicalNumberOfRows(); i++) {

					Row inputRow = inputSheet.getRow(i);

					String claimNumber = ExcelReader.getCellValueByColumnName(inputRow, columnIndexMap, "ClaimNumber");
					String projectType = ExcelReader.getCellValueByColumnName(inputRow, columnIndexMap, "ProjectType");
					String chaseReqID = ExcelReader.getCellValueByColumnName(inputRow, columnIndexMap,
							"ChaseRequestID");

					Response response = new CancelChaseRequestPage().cancelChaseRequests(projectType, claimNumber,
							chaseReqID, authToken);
					int statusCode = response.getStatusCode();
					if (statusCode == 401) {
						authToken = generateBearerToken(dataMap);
						// Set the retry row index and break out of the loop to retry from that row
						retryRowIndex = i;
						reTry = true;
						break; // Break out of the for loop to reattempt the process from the failed row
					}
					System.err.println("Chase request cancelled Successfully : " + chaseReqID);
				}
			} while (reTry);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
	}

	public void ChaseRequestSearch(String inputFilePath, String outputFilePath, String sheetName, String authToken) {
		// Send POST request and capture response
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
		try {
			String apiUrl = PropertiesFileReader.getAPIProperty(env + ".ChaseRequestSearchEndPoint");

			String payLoad = "{\r\n" + "    \"RequestGroupID\": \"RG-40382236123155\"\r\n" + "}";

			// Fetch response from the API
			Response response = sendPostRequest(apiUrl, payLoad, authToken);
			String jsonResponse = response.getBody().asString();

			// Parse the JSON response
			JSONObject jsonObject = new JSONObject(jsonResponse);
			JSONArray results = jsonObject.getJSONArray("Results");
			authToken = generateBearerToken(map);
			// Create output file stream and workbook instance
			try (FileOutputStream fileOut = new FileOutputStream(outputFilePath);
					Workbook workbook = new XSSFWorkbook()) {

				// Create sheet and header row
				Sheet sheet = workbook.createSheet(sheetName);
				Row headerRow = sheet.createRow(0);
				headerRow.createCell(0).setCellValue("ClaimNumber");
				headerRow.createCell(1).setCellValue("ProjectType");
				headerRow.createCell(2).setCellValue("ChaseRequestID");

				// Loop through the results and capture the relevant fields
				for (int i = 0; i < results.length(); i++) {
					JSONObject result = results.getJSONObject(i).getJSONObject("Result");
					JSONObject chaseRequest = result.getJSONObject("ChaseRequest");
					JSONObject claimDetails = chaseRequest.getJSONObject("ClaimDetails");
					String claimNumber = claimDetails.getString("ClientClaimNumber");
					String projectType = chaseRequest.getJSONObject("ChaseRequestHeader").getString("ProjectType");
					String chaseRequestId = chaseRequest.getString("RequestID");

					// Store the captured data into Excel sheet
					Row row = sheet.createRow(i + 1);
					row.createCell(0).setCellValue(claimNumber);
					row.createCell(1).setCellValue(projectType);
					row.createCell(2).setCellValue(chaseRequestId);
				}

				// Write the workbook to the output file
				workbook.write(fileOut);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void assertReportFailure(Exception e) {
		if (e != null) {
			Assert.fail(e.getLocalizedMessage());
		}
	}

	public String getCurrentDateTime() {
		// Get the current date and time
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYMdHHmmSSS");
		return sdf.format(now).toString();
	}

	public String getCurrentDateTime(String format) {
		// Get the current date and time
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(now).toString();
	}

	public String createrandomNumber() {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmssSSS");
		String formattedDate = dateFormat.format(currentDate).toString();

		return formattedDate;
	}

	public int randomNumbers(int i) {

		Random random = new Random();
		int n = random.nextInt(i);
		return n;
	}

	public String cotivitiClaim() {
		long currentTimeMillis = System.currentTimeMillis();
		long timestamp = currentTimeMillis / 1000;
		System.out.println("Timestamp: " + timestamp);
		String CotivitiClaimNumber = Long.toString(timestamp);
		return CotivitiClaimNumber;
	}
}
