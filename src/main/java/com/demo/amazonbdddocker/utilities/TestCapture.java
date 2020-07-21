package com.demo.amazonbdddocker.utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class TestCapture {

	public static void captureScreenshot(WebDriver driver, String screenshotname ) {
		
		String path=System.getProperty("user.dir");
		
		TakesScreenshot ts= (TakesScreenshot) driver;
		File file= ts.getScreenshotAs(OutputType.FILE);
	
		try {
			Files.copy(file, new File(path+"/AmazonBDD/"+ screenshotname+".jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
