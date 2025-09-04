package com.cotiviti.hdap.TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/*dryRun if True -> It will only check whether there are associated steps definitions or not
If true only checks if all the Features has corresponding StepDefinitions or Not. If False then runs the actual code in the step
*/
@CucumberOptions(features = "C:\\Users\\dinesh.neeli\\git\\hdap_automation\\HDAP\\src\\main\\java\\com\\cotiviti\\hdap\\FeatureFiles\\CreateChaseRequest.feature", glue = "com.cotiviti.hdap.StepDefinitions", plugin = {
		"pretty", "html:target/cucumber-report.html" }, tags = "@ChaseRequest" // Will be set dynamically
)
public class myRunner2 extends AbstractTestNGCucumberTests {

}