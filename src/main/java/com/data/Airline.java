package com.data;

public class Airline {
	
	public String iataCode;
	public String airlineName;
	public Double infantPrice;
	
	public Airline(String iataCode, String airlineName, Double infantPrice) {
		this.iataCode = iataCode;
		this.airlineName = airlineName;
		this.infantPrice = infantPrice;
	}
	
	public String getIataCode() {
		return iataCode;
	}
	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	public Double getInfantPrice() {
		return infantPrice;
	}
	public void setInfantPrice(Double infantPrice) {
		this.infantPrice = infantPrice;
	}

	@Override
	public String toString() {
		return "Airline [iataCode=" + iataCode + ", airlineName=" + airlineName + ", infantPrice=" + infantPrice + "]";
	}

}
