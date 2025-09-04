package com.cotiviti.hdap.APICommonLibrary;

import org.json.JSONObject;

import com.cotiviti.hdap.CommonLibrary.Constants;

public class createChaseRequest extends apiCommonLib {

	public void createCR() {

		String filePath = Constants.PROJECT_PATH
				+ "\\src\\main\\java\\com\\cotivit\\hdap\\APIPayLoads\\createChaseRequestWithPractitioner.json";
		// Read the file and convert to a JSONObject
		String jsonString = readFile(filePath);
		if (jsonString != null) {
			JSONObject jsonObject = new JSONObject(jsonString);
			System.out.println(jsonString);
			System.out.println(jsonObject.toString(4)); // Pretty print with indentation
		}

	}
}
