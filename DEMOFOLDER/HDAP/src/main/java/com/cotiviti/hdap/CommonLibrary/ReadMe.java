package com.cotiviti.hdap.CommonLibrary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReadMe {
	static Logger myLogger = LogManager.getLogger(ReadMe.class.getName());

	/*
	 * L0G4J
	 * =============================================================================
	 * =============================================================================
	 * =========== 1. We need to create myLogger Object in the below format 2.
	 * static Logger myLogger = LogManager.getLogger(<ClassName>.class.getName());
	 * --> The above peace of code will print the logs in console and as well in the
	 * Log4J Report which is present under the folder with name "log4j2_Reports".
	 * Also note if the file size is more than 10MB this will create a new folder.
	 * 3. Also, we can control what is getting logged by using the xml file. We have
	 * placed the XML File under "Resources" folder. Please refer online log4j for
	 * clear understanding. For Now you can use the update the values form
	 * "debug"->"trace" in xml file.
	 ******************************************************************************************************************************************************************************/

}
