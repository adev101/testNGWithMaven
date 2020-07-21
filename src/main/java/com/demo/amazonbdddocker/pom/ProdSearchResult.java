package com.demo.amazonbdddocker.pom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.demo.amazonbdddocker.core.TestException;
import com.demo.amazonbdddocker.core.TestFactory;
import com.demo.amazonbdddocker.utilities.TestConfig;



@SuppressWarnings("deprecation")
public class ProdSearchResult {

	static float number=0;
	static int[] arr;
	static String text=null;
	static String price_string=null;
	static String titleText=null;
	static String prdSearched=null;
	ArrayList<String> filter_value=new ArrayList<String>();
	
	@FindBy(xpath="//div[@class='a-section a-spacing-none']/h2//span")
	private List<WebElement> results;
	
	@FindBy(xpath="//span[@class='a-price-whole']")
	private List<WebElement> prices;
	
	@FindBy(xpath="//title[contains(text(),'Amazon')]")
	private WebElement title;
	

	
	
	public ProdSearchResult(){
		PageFactory.initElements(TestFactory.driver, this);
		}
	
	
	public void List() {	
		Iterator<WebElement> it1 =prices.iterator();

		TreeMap<Float, String> tree_map = new TreeMap<Float, String>();
		
		while (it1.hasNext()) {
			price_string = it1.next().getText();
			if (!(price_string=="")) {
				System.out.println(price_string);
				if (price_string.contains(",")) {			
					price_string=price_string.replaceAll(",", "");
					}
				 number = Integer.parseInt(price_string.trim());
				 //text=it2.next().getText();
				 
				 tree_map.put(number, "INR");
			}
		}
		
		System.out.println("Lowest price item is: " + tree_map.firstEntry());
		
		//ExcelInput obj= new ExcelInput();
		//obj.save_results(results);
		
	}
	
	@SuppressWarnings("deprecation")
	public void check_page() {
		try {
			titleText=TestFactory.checkElementText(title);
			prdSearched= TestConfig.getConfigDetails().get("product");
			
			Assert.assertEquals("Amazon.in : "+prdSearched, titleText);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
