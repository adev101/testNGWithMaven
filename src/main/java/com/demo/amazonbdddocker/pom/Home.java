package com.demo.amazonbdddocker.pom;

import java.awt.Desktop.Action;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.demo.amazonbdddocker.core.TestException;

import com.demo.amazonbdddocker.core.TestFactory;

@SuppressWarnings("deprecation")
public class Home extends TestFactory{
	
	
	@FindBy(xpath="//a[@data-nav-ref='nav_ya_signin']//span")
	private WebElement linkSignin;
	
	@FindBy(xpath="//a[@data-nav-ref='nav_signin']/span[text()='Sign in']")
	private WebElement btnSignin;
	
	@FindBy(xpath="//a[@data-nav-role='signin']")
	private WebElement linkUserCheck;
	
	@FindBy(xpath="//span[text()='Your Account']")
	private WebElement linkUserAccount;
	
	@FindBy(xpath="//input[@id='twotabsearchtextbox']")
	private WebElement searchBox;
	
	@FindBy(xpath="//a[@id='nav-link-accountList']")
	private WebElement accountLink;
	
	@FindBy(xpath="//*[@href]")
	private List<WebElement> allLinks;
	
	
	public Home(){
		PageFactory.initElements(TestFactory.driver, this);
		}
	
	public void hoverSignIn() {
		try {
			Thread.sleep(2000);
			TestFactory.hoverOnElement(linkSignin);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void hoverUser() {
		try {
			Actions act= new Actions(driver);
			act.moveToElement(linkUserCheck).build().perform();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	public void searchProd(String prdName) {
		try {
			TestFactory.elementSendKeys(searchBox, prdName);
			TestFactory.elementSendKeys(searchBox, Keys.ENTER);
		} catch (TestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getTitle() {
		try {
			String url=null;
			url=TestFactory.checkURL();
			Assert.assertEquals("https://www.amazon.in/", url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void get_all_links() {
		try {
			System.out.println("Total links available: "+ allLinks.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void check_broken_links() {
		try {
			TestFactory.checkBrokenLinks(allLinks);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
