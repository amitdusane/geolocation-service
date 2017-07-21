package com.geolocation.map.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.geolocation.map.exception.Http400Exception;
import com.geolocation.map.service.GeocodingService;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

@RestController
@RequestMapping(value = "/maps/api/geocode")
public class GeocodingController extends AbstractRestController {

	@Autowired
	private GeocodingService service;

	@RequestMapping(value = "/address/{address}")
	public GeocodingResult[] getLocation(@PathVariable("address") String address)
			throws ApiException, InterruptedException, IOException {

		return service.getLocation(address);
	}

	@RequestMapping(value = "/latlng/{latlng}")
	public GeocodingResult[] getReverseLocation(@PathVariable("latlng") String rawLatlng)
			throws ApiException, InterruptedException, IOException {

		LatLng latlng = null;

		if (rawLatlng != null && rawLatlng.length() > 0 && rawLatlng.split(",").length > 1) {
			String[] latlngArray = rawLatlng.split(",");
			latlng = new LatLng(Double.valueOf(latlngArray[0]), Double.valueOf(latlngArray[1]));

		} else {
			throw new Http400Exception("Invalid latitude longitude inputs");
		}

		return service.getReverseLocation(latlng);

	}

}
