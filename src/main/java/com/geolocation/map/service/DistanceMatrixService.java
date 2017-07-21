package com.geolocation.map.service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
import com.google.maps.model.Unit;

@Service
public class DistanceMatrixService extends AbstractRestService{
	
	public DistanceMatrix getDistance(String[] origins, String[] destinations) throws ApiException, InterruptedException, IOException{		
		//DistanceMatrixApi.getDistanceMatrix(geoApiContext, origins, destinations).await();
		return DistanceMatrixApi.newRequest(geoApiContext).units(Unit.IMPERIAL).origins(new LatLng[]{new LatLng(18.499302, 73.821355)}).destinations(new LatLng[]{new LatLng(18.495945,73.820046), new LatLng(18.498610,73.813373)}).await();
	}	
	
}
