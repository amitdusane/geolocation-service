package com.proxybid.map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.proxybid.map.exception.Http400Exception;
import com.proxybid.map.exception.Http404Exception;
import com.proxybid.map.exception.RestAPIException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public RestAPIException exceptionHandler(Exception ex){
		log.error("Exception", ex);
		return new RestAPIException(ex, "Something went wrong at server!");		
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(Http404Exception.class)
	public RestAPIException handleResourceNotFound(Http404Exception ex, HttpServletRequest request,
			HttpServletResponse resp) {

		log.info("Converting Data store exceptions to rest response " + ex.getMessage());
		//cntService.increment("com.amitdusane.http.404.count");
		return new RestAPIException(ex, "Sorry we couldn't find the requested resource: "+ request.getRequestURL() );

	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Http400Exception.class)
	public RestAPIException handleResourceNotFound(Http400Exception ex, WebRequest request,
			HttpServletResponse resp) {

		log.info("Converting Data store exceptions to rest response " + ex.getMessage());
		//cntService.increment("com.amitdusane.http.400.count");
		return new RestAPIException(ex, "You messed up!");

	}

	
}
