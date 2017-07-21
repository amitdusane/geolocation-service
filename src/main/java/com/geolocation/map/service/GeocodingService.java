package com.geolocation.map.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

@Service
public class GeocodingService extends AbstractRestService{

	
	public GeocodingResult[] getLocation(String address) throws ApiException, InterruptedException, IOException{
		return GeocodingApi.geocode(geoApiContext, address).await();		
	}
	
	public GeocodingResult[] getReverseLocation(LatLng latlng) throws ApiException, InterruptedException, IOException{
		return GeocodingApi.reverseGeocode(geoApiContext, latlng).await();
	}
	
}
