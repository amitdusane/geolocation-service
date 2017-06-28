package com.proxybid.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.maps.GeoApiContext;

@SpringBootApplication
@EnableWebMvc
public class GeolocationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeolocationServiceApplication.class, args);
	}
	
	@Bean
	@Autowired
	public GeoApiContext service(@Value("${google.api.key}") String apiKey) {
		GeoApiContext geoApiContext = new GeoApiContext();
		geoApiContext.setApiKey(apiKey);
	    return geoApiContext;
	}
}
