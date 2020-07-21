package com.demo.amazonbdddocker.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class TestConfig {

	public static HashMap<String,String> prjProperties= new HashMap<String,String>();
	

	public static HashMap<String,String> getConfigDetails() throws IOException {
		try {

			
			String userDir=System.getProperty("user.dir");
			File file= new File(userDir+"/src/test/resources/configuration/config.properties");
			FileInputStream fis= new FileInputStream(file);
			
			Properties prop= new Properties();
			
			prop.load(fis);
			String value=null;
			
			for (String key: prop.stringPropertyNames()) {
				value=prop.getProperty(key);
				prjProperties.put(key, value);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prjProperties;
	}
}
