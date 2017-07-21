package com.geolocation.map.exception;

public class Http500Exception extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1411907150824815276L;

	public Http500Exception() {
		super();
	}
	
	Http500Exception(String msg, Throwable th){
		super(msg, th);
	}
	
	Http500Exception(String msg){
		super(msg);
	}
	
	Http500Exception(Throwable th){
		super(th);
	}
}
