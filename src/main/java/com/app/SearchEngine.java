package com.app;

import java.util.ArrayList;
import java.util.Date;

import com.data.Airline;
import com.data.Airport;
import com.data.FlightConnection;

public class SearchEngine {
	
	public static ArrayList<FlightConnection> flightConnections;
	public static ArrayList<Airline> airlines;
	public static ArrayList<Airport> airports;	

	public double searchFlight(Airport airportOrigin, Airport airportDestination, Date departureDate,
			Integer adultPassenger, Integer childPassenger, Integer infantPassenger) throws Exception {
		
		//Comprobamos que el aeropuerto de Origen existe
		if (airportOrigin == null) {
			throw new Exception();
		}
		
		return 1;
	}
	
	
}
