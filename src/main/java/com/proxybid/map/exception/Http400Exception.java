package com.proxybid.map.exception;

public class Http400Exception extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1411907150824815276L;

	public Http400Exception() {
		super();
	}
	
	public Http400Exception(String msg, Throwable th){
		super(msg, th);
	}
	
	public Http400Exception(String msg){
		super(msg);
	}
	
	public Http400Exception(Throwable th){
		super(th);
	}
}
