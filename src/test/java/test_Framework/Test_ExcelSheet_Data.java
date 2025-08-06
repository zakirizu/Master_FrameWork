package test_Framework;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import utils.ExcelFileReader;

public class Test_ExcelSheet_Data {

	public static void main(String[] args) throws IOException {
	HashMap<String, String> hmap = ExcelFileReader.getExcelData("alpha","t2c2");
	
	for (Entry<String, String> entry : hmap.entrySet()) {
	    System.out.println("Key: " + entry.getKey() + " | Value: " + entry.getValue());
	}

	}

}
