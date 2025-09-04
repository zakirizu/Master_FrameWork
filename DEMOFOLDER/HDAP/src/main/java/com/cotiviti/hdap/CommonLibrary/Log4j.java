package com.cotiviti.hdap.CommonLibrary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j {
	static Logger mylogger = LogManager.getLogger(Log4j.class.getName());

	public static void main(String[] args) {

		System.out.println("-----Start-----");

		mylogger.info("Info Message");
		mylogger.error("Error Message");
		mylogger.fatal("Fatal Message");
		mylogger.warn("Warn Message");

		System.out.println("----End----");

	}

}
