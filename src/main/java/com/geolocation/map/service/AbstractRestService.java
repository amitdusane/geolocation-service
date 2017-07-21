package com.geolocation.map.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.maps.GeoApiContext;

public class AbstractRestService {
	
	public final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	GeoApiContext geoApiContext;
	
	
}
