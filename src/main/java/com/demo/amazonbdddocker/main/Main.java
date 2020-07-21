package com.demo.amazonbdddocker.main;



public class Main {

	static String path=null;
	

	public static void main(String[] args) throws Throwable {
	    	
		path=System.getProperty("user.dir");
		//String[] arguments = {"--plugin", "html:build/reports/cucumber", "-g","com.demo.amazonbdddocker.teststeps", path+"\\src\\test\\resources\\feature", "--tags ","@test"};
		String[] arguments = {"--plugin", "html:build/reports/cucumber", "--glue","com.demo.amazonbdddocker.teststeps", "src/test/resources/feature", "--tags ","@test"};
	    
			cucumber.api.cli.Main.main(arguments);
	    }
	}

