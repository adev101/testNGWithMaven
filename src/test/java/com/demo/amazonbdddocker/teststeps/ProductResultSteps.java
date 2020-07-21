package com.demo.amazonbdddocker.teststeps;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.demo.amazonbdddocker.pom.ProdSearchResult;


public class ProductResultSteps {
		
		
		@Then("^Lists result with minimum price on first page$")
		public void list_all_results() {
			try {
				Thread.sleep(5000);
				ProdSearchResult obj= new ProdSearchResult();
				obj.List();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Given("^User is at search result page$")
		public void check_page() {
			ProdSearchResult obj= new ProdSearchResult();
			obj.check_page();
		}
		
		


}
