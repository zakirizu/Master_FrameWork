package com.cotiviti.hdap.TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/*dryRun if True -> It will only check whether there are associated steps definitions or not
If true only checks if all the Features has corresponding StepDefinitions or Not. If False then runs the actual code in the step
*/
@CucumberOptions(features = "src/main/java/com/cotiviti/hdap/FeatureFiles", glue = "com/cotiviti/hdap/StepDefinitions", monochrome = true, dryRun = false, tags = "@CreateRGZR1", plugin = {
		"pretty", "html:TestResults_CucumberReports/cucumber.html", "json:TestResults_cucumberReports/cucumber.json" })
public class RunnerTestNG extends AbstractTestNGCucumberTests {

}
