package com.savio.Springbootcsvreading;

public class CSVResponseMessage {

	private String message;
	private String data;
	

	  public CSVResponseMessage(String message, String data) {
	    this.message = message;
	    this.data = data;
	  }

	  public String getMessage() {
	    return message;
	  }

	  public void setMessage(String message) {
	    this.message = message;
	  }

	public String getdata() {
		return data;
	}

	public void setdata(String data) {
		this.data = data;
	}

}