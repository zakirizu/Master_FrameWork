package test_Framework;

import org.apache.logging.log4j.*;

public class Test_Log4j2 {
	
static	Logger logger = LogManager.getLogger(Test_Log4j2.class.getName());
		
			
	public static void main(String[] args) {
		
        logger.trace("This is a TRACE message");
        logger.debug("This is a DEBUG message");
        logger.info("This is an INFO message");
        logger.warn("This is a WARN message");
        logger.error("This is an ERROR message");
        logger.fatal("This is a FATAL message");

        System.out.println("Check the console and app.log file for logs.");
		
	}

}
