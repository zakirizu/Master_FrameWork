package com.cotiviti.hdap.Tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.cotiviti.hdap.CommonUtils.BaseClass;
import com.cotiviti.hdap.CommonUtils.DependencyInjection;

public class NPI {

	DependencyInjection obj;

	public void Create_RG_Sd(DependencyInjection obj) {
		this.obj = obj;
	}

	@Test
	public void msk() {
		WebDriver driver = null;

		driver = BaseClass.initializeDriver();

		// Open the NPI Registry website
		driver.get("https://npiregistry.cms.hhs.gov/results");

		// Wait for the page to load and the NPI Type dropdown to be visible

		driver.findElement(By.id("enumerationType")).click();
		driver.findElement(By.xpath("//*[text()=' Individual']")).click();

		// wait.until(ExpectedConditions.elementToBeClickable(By.id("npiTypeIndividual")));

		// Select the NPI Type as 'Individual' (if it's not selected by default)
		// WebElement npiTypeIndividual =
		// driver.findElement(By.id("npiTypeIndividual"));
		// if (!npiTypeIndividual.isSelected()) {
		// npiTypeIndividual.click();
		// }

		// Enter 'AA' into the Provider First Name field
		WebElement providerFirstName = driver.findElement(By.id("firstName"));
		providerFirstName.sendKeys("AA");

		// Click the "Search" button
		WebElement searchButton = driver.findElement(By.xpath("//*[text()='Search']"));
		searchButton.click();

		// Optionally, wait for the results to appear or perform other actions (like
		// extracting data)
		// wait.until(ExpectedConditions.presenceOfElementLocated(By.id("resultsContainer")));

		/*
		 * You can add more interactions here, e.g., extracting data from the results
		 * table System.out.println("Search completed successfully!");
		 * 
		 * List<WebElement> resultLinks =
		 * driver.findElements(By.xpath("//*[@class='btn btn-link']"));
		 * 
		 * // Print the text of each link
		 * System.out.println("Text of all 'btn btn-link' elements:"); for (WebElement
		 * link : resultLinks) { System.out.println(link.getText()); }
		 * 
		 */
		System.out.println("Search completed successfully!");
		List<String> firstNames = new ArrayList<>();
		List<String> lastNames = new ArrayList<>();
		List<WebElement> resultNames = driver.findElements(By.xpath("//*[@class='btn btn-link']/../../td[2]"));

		// Print the first and last names separately
		// List<WebElement> resultNames =
		// driver.findElements(By.xpath("//*[@class='Name']"));

		// Iterate over the names and separate them into first and last names
		for (WebElement nameElement : resultNames) {
			String fullName = nameElement.getText().trim(); // Get the full name and remove any leading/trailing spaces

			// Split the name into parts based on spaces
			String[] nameParts = fullName.split("\\s+"); // Split by one or more spaces

			if (nameParts.length >= 1) {
				String firstName = nameParts[0]; // First part is the first name
				String lastName = nameParts[nameParts.length - 1]; // Last part is the last name

				// Add first and last names to the respective lists
				firstNames.add(firstName);
				lastNames.add(lastName);
			} else {
				System.out.println("Name format not recognized: " + fullName);
			}
		}

		// Print first names
		System.out.println("First Names:");
		for (String firstName : firstNames) {
			System.out.println(firstName);
		}

		// Print last names
		System.out.println("\nLast Names:");
		for (String lastName : lastNames) {
			System.out.println(lastName);
		}

		// Close the browser
	}
}
