package com.demo.amazonbdddocker.teststeps;

import com.demo.amazonbdddocker.pom.Home;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;

import com.demo.amazonbdddocker.utilities.TestConfig;
import com.demo.amazonbdddocker.core.TestFactory;


public class HomeSteps {

	static String prdName;
	
	@Given("Amazon home page is launched$")
	public void launch_home_page() {
	    String browser;
		try {
			browser = TestConfig.getConfigDetails().get("browser");
			TestFactory.getURL(browser);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	@When("^user is at home page$")
	public void user_is_at_home_page() {
		TestFactory.navigateToHome();
		Home obj= new Home();
		obj.getTitle();
				
	}

	
	@When("^user searches for the product$")
	public void search_product() {
		try {
			
			prdName=TestConfig.getConfigDetails().get("product");

			Home objHome= new Home();
			objHome.searchProd(prdName);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	

	@When("^User fetches all links on the page$")
	public void fetch_link() {
		try {
			Home obj= new Home();
			obj.get_all_links();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Then("^user validates if any link is broken and reports$")
	public void validate_link() {
		try {
			Home obj= new Home();
			obj.check_broken_links();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    
    
    @Then("^Product not found message is displayed$")
    public void display_message() {
    	
    }
	
}
