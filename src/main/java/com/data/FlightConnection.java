package com.data;

public class FlightConnection {
	
	String departureAirport;
	String arrivalAirport;
	String airline;
	Double price;
	
	public FlightConnection(String departureAirport, String arrivalAirport, String airline, Double price) {
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.airline = airline;
		this.price = price;
	}
	
	public String getDepartureAirport() {
		return departureAirport;
	}
	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}
	public String getArrivalAirport() {
		return arrivalAirport;
	}
	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "FlightConnection [departureAirport=" + departureAirport + ", arrivalAirport=" + arrivalAirport
				+ ", airline=" + airline + ", price=" + price + "]";
	}

}
