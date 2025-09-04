package com.cotiviti.hdap.TestRunner;

import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cotiviti.hdap.CommonUtils.ExcelTagReader;

import io.cucumber.testng.*;

/*dryRun if True -> It will only check whether there are associated steps definitions or not
If true only checks if all the Features has corresponding StepDefinitions or Not. If False then runs the actual code in the step
*/
@CucumberOptions(features = "C:\\Users\\dinesh.neeli\\git\\hdap_automation\\HDAP\\src\\main\\java\\com\\cotiviti\\hdap\\FeatureFiles\\sample.feature", glue = "com.cotiviti.hdap.StepDefinitions", plugin = {
		"pretty", "html:target/cucumber-report.html" }, tags = "" // Will be set dynamically
)
public class myRunner {

	@DataProvider(name = "tagProvider", parallel = false)
	public Object[][] provideTags() {
		String excelFilePath = "C:\\Users\\dinesh.neeli\\git\\hdap_automation\\HDAP\\TestData\\Tags.xlsx";
		List<String> tags = ExcelTagReader.getTagExpression(excelFilePath);
		System.out.println("Tags read from Excel: " + tags);
		// Check if the list is empty or null
		if (tags == null || tags.isEmpty()) {
			System.out.println("No tags found. Please check the Excel file.");
			return new Object[0][0]; // Return an empty array if no tags are found
		}

		// Using Java 11+ Stream API syntax
		return tags.stream().map(tag -> new Object[] { tag }).toArray(Object[][]::new); // This works in Java 11 and
																						// above
	}

	@Test(dataProvider = "tagProvider")
	public void runCucumberForEachTag(String tag) {
		String sanitizedTag = tag.trim().replaceAll("[\\r\\n]+", "");
		System.setProperty("cucumber.filter.tags", sanitizedTag);

		// Dynamic report path per tag
		String reportPath = "html:target/reports/report-" + sanitizedTag.replaceAll("[^a-zA-Z0-9]", "") + ".html";
		System.setProperty("cucumber.plugin", "pretty," + reportPath);

		System.out.println("Running Cucumber for tag: " + sanitizedTag);
		TestNGCucumberRunner runner = new TestNGCucumberRunner(myRunner.class);
		try {
			Object[][] scenarios = runner.provideScenarios();
			System.out.println("Scenarios found: " + scenarios.length);

			for (Object[] scenario : runner.provideScenarios()) {
				PickleWrapper pickleWrapper = (PickleWrapper) scenario[0];
				runner.runScenario(pickleWrapper.getPickle());
			}
		} finally {
			runner.finish();
		}
	}
}