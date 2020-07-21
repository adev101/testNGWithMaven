package com.demo.amazonbdddocker.core;

public class TestException extends Exception{
	private String fieldname;
	
	public TestException(String fieldname, String errormessage) {
		super(errormessage);
		this.fieldname=fieldname;
	}
	
	public TestException(String errormessage) {
		super(errormessage);
	}
	
	public void setfieldname(String fieldname) {
		this.fieldname= fieldname;
	}
	
	public String getfieldname() {
		return this.fieldname;
	}
}
