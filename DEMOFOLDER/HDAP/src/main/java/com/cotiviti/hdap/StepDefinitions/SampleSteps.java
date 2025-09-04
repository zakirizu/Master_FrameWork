package com.cotiviti.hdap.StepDefinitions;

import io.cucumber.java.en.Given;

public class SampleSteps {

	@Given("I run a smoke test")
	public void I_run_a_smoke_test() {
		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
		System.err.println("Smoke executed");
	}

	@Given("I run a regression test")
	public void I_run_a_regression_test() {
		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
		System.err.println("Regression executed");
	}
}