package com.geolocation.map.exception;

public class RestAPIException {
	
	private final String errorDetail;
	private final String errorMessage;
	
	public RestAPIException(Exception ex, String errorDetail) {
		super();
		this.errorDetail = errorDetail;
		this.errorMessage = ex.getLocalizedMessage();
	}
	
	public String getErrorDetail() {
		return errorDetail;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	
	

}
