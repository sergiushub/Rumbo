package com.data;

public class FlightConnection {
	
	String arrivalAirport;
	String departureAirport;
	String airline;
	Double price;
	
	public FlightConnection(String arrivalAirport, String departureAirport, String airline, Double price) {
		this.arrivalAirport = arrivalAirport;
		this.departureAirport = departureAirport;
		this.airline = airline;
		this.price = price;
	}
	
	public String getArrivalAirport() {
		return arrivalAirport;
	}
	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}
	public String getDepartureAirport() {
		return departureAirport;
	}
	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
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
		return "FlightConnection [arrivalAirport=" + arrivalAirport + ", departureAirport=" + departureAirport
				+ ", airline=" + airline + ", price=" + price + "]";
	}

}
