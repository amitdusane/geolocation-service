package com.geolocation.map.exception;

public class Http404Exception extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1411907150824815276L;

	public Http404Exception() {
		super();
	}
	
	Http404Exception(String msg, Throwable th){
		super(msg, th);
	}
	
	Http404Exception(String msg){
		super(msg);
	}
	
	Http404Exception(Throwable th){
		super(th);
	}
}
