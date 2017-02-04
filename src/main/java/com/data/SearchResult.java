package com.data;

public class SearchResult {
	
	String flightNumber;
	double totalPrice;
	double basePrice;
	double infantPrice;
	
	public SearchResult(String flightNumber, double totalPrice, double basePrice, double infantPrice) {
		this.flightNumber = flightNumber;
		this.totalPrice = totalPrice;
		this.basePrice = basePrice;
		this.infantPrice = infantPrice;
	}
	
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	public double getInfantPrice() {
		return infantPrice;
	}
	public void setInfantPrice(double infantPrice) {
		this.infantPrice = infantPrice;
	}

	@Override
	public String toString() {
		return "SearchResult [flightNumber=" + flightNumber + ", totalPrice=" + totalPrice + ", basePrice=" + basePrice
				+ ", infantPrice=" + infantPrice + "]";
	}

}
