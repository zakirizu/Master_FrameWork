package com.cotiviti.hdap.CommonUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertiesFileReader {
	static FileInputStream fis;
	static String locationPath;
	static String value = null;

	public static void main(String[] args) throws IOException {
		String test = getUIProperty("highLightElement");
		System.out.println(test);
	}

	public static String getAPIProperty(String key) {
		try {

			locationPath = System.getProperty("user.dir") + "/src/main/java/com/cotiviti/hdap/Resources/propertyFile_API.properties";
			FileInputStream fis = new FileInputStream(locationPath);
			Properties prop = new Properties();
			prop.load(fis);
			value = prop.getProperty(key);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			System.out.println("user is not able to find the file at location: " + locationPath);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			// fis.close();
		}
		return value;

	}

	public static String getUIProperty(String key) {
		try {

			locationPath = System.getProperty("user.dir")
					+ "/src/main/java/com/cotiviti/hdap/Resources/propertyFile_UI.properties";
			FileInputStream fis = new FileInputStream(locationPath);
			Properties prop = new Properties();
			prop.load(fis);
			value = prop.getProperty(key);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			System.out.println("user is not able to find the file at location: " + locationPath);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			// fis.close();
		}
		return value;

	}

}
