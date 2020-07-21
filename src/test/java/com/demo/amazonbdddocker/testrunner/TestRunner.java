package com.demo.amazonbdddocker.testrunner;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.demo.amazonbdddocker.core.TestFactory;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;


@CucumberOptions(features= {"src/test/resources/feature"}
,glue= {"com.demo.amazonbdddocker.teststeps"}
,tags= {"@test"}
,plugin= {"html:target/cucumber-html-report","json:target/cucumber.json"})



public class TestRunner {
	
	private TestNGCucumberRunner testNGCucumberRunner;
	
	@BeforeClass(alwaysRun=true)
	public void setup() throws Exception {
		testNGCucumberRunner= new TestNGCucumberRunner(this.getClass());
	}
	
	@Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
	public void getFeature(CucumberFeatureWrapper cucumberFeature) {
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}
	
	@DataProvider
	public Object[][] features() {
	    return testNGCucumberRunner.provideFeatures();
	}
	
	@AfterClass
	public static void cleanup() {
		TestFactory.driver.close();
		TestFactory.driver.quit();
	}
}
