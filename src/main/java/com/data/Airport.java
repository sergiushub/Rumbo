package com.data;

public class Airport {
	
	public String iataCode;
	public String airportName;
	
	public Airport(String iataCode, String airportName) {
		this.iataCode = iataCode;
		this.airportName = airportName;
	}
	
	public String getIataCode() {
		return iataCode;
	}
	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}
	public String getAirportName() {
		return airportName;
	}
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}
}
