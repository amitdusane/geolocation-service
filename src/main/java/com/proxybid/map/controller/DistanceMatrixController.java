package com.proxybid.map.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.proxybid.map.exception.Http400Exception;
import com.proxybid.map.service.DistanceMatrixService;
import com.proxybid.map.service.GeocodingService;

@RestController
@RequestMapping(value = "/maps/api/distance")
public class DistanceMatrixController extends AbstractRestController {

	@Autowired
	private DistanceMatrixService service;

	@Autowired
	private GeocodingService geoCodingService;

	@RequestMapping(method = RequestMethod.GET)
	public DistanceMatrix getFilteredLocations(@RequestParam(required=false,name="address") String address, @RequestParam(required=false,name="latlng") String latlng, @RequestParam(required=false,name="radius") String radius, @RequestParam(required=false,name="travelMode") TravelMode mode)
			throws ApiException, InterruptedException, IOException {

		validateInputs(address, latlng, radius, mode);

		LatLng origin = getLatlng(address, latlng);

		String[] destinations = getDestinations();
		
		String originStr = origin.toString();
		
		String[] origins = new String[]{originStr};
		
		DistanceMatrix distanceMatrix = service.getDistance(origins, destinations);
		
		if(distanceMatrix != null){
			DistanceMatrixRow[] rows = distanceMatrix.rows;
			for(DistanceMatrixRow row : rows){
				DistanceMatrixElement[] elements = row.elements;
				for(DistanceMatrixElement element : elements){
					if(321869 < element.distance.inMeters){
						//TODO: handle it properly
						System.out.println("Found found---> ");
					}
					
				}
			}
		}
		
		GeocodingResult[] location = geoCodingService.getLocation(address);
		return distanceMatrix;
	}

	private String[] getDestinations() {
		
		return new String[]{"18.499302,73.821355","18.495945,73.820046","18.498610,73.813373","18.505854,73.817664","18.502843,73.821977"};
	}

	private LatLng getLatlng(String address, String latlng) throws ApiException, InterruptedException, IOException {

		LatLng finalLatlng = null;

		if (address != null && !address.isEmpty()) {
			GeocodingResult[] results = geoCodingService.getLocation(address);
			if (results != null && results.length > 0) {

				GeocodingResult geocodingResult = results[0];
				finalLatlng = geocodingResult.geometry.location != null ? geocodingResult.geometry.location : null;
			}
		} else if (latlng != null && !latlng.isEmpty()) {
			String[] split = latlng.split(",");
			if(split != null && split.length > 0){
				finalLatlng = new LatLng(Double.valueOf(split[0]), Double.valueOf(split[1]));
			}
			
		}

		return finalLatlng;
	}

	/**
	 * Validates inputs for distance api
	 * 
	 * @param address
	 * @param latlng
	 * @param radius
	 * @param mode
	 */
	private void validateInputs(String address, String latlng, String radius, TravelMode mode) {

		if (address == null && latlng == null) {
			throw new Http400Exception("Either Address or Latitude/Lognitude should be provided");
		} else if (address != null && latlng != null) {
			throw new Http400Exception("Either Address or Latitude/Lognitude should be provided not the both");
		}

	}

	/*
	 * @RequestMapping(value = "/address/{address}") public GeocodingResult[]
	 * getLocation(@PathVariable("address") String address) throws ApiException,
	 * InterruptedException, IOException {
	 * 
	 * return service.getLocation(address); }
	 * 
	 * @RequestMapping(value = "/latlng/{latlng}") public GeocodingResult[]
	 * getReverseLocation(@PathVariable("latlng") String rawLatlng) throws
	 * ApiException, InterruptedException, IOException {
	 * 
	 * LatLng latlng = null;
	 * 
	 * if (rawLatlng != null && rawLatlng.length() > 0 &&
	 * rawLatlng.split(",").length > 1) { String[] latlngArray =
	 * rawLatlng.split(","); latlng = new LatLng(Double.valueOf(latlngArray[0]),
	 * Double.valueOf(latlngArray[1]));
	 * 
	 * } else { throw new Http400Exception("Invalid latitude longitude inputs");
	 * }
	 * 
	 * return service.getReverseLocation(latlng);
	 * 
	 * }
	 */

}
