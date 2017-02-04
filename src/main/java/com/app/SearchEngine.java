package com.app;

import java.util.Date;

public class SearchEngine {

	public double searchFlight(String airportOrigin, String airportDestination, Date departureDate,
			Integer adultPassenger, Integer childPassenger, Integer infantPassenger) throws Exception {
		
		//Comprobamos que el aeropuerto de Origen existe
		if (!airportOrigin.isEmpty()) {
			throw new Exception();
		}
		
		return 1;
	}
	
	
}
