package com.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

import com.data.Airline;
import com.data.Airport;
import com.data.FlightConnection;
import com.data.SearchResult;

public class SearchEngine {

	public static ArrayList<FlightConnection> flightConnections;
	public static ArrayList<Airline> airlines;
	public static ArrayList<Airport> airports;
	
	public static final long DAYS_PRICE_BOUNDRY_1 = 3;
	public static final long DAYS_PRICE_BOUNDRY_2 = 15;
	public static final long DAYS_PRICE_BOUNDRY_3 = 30;
	
	public static final double PRICE_BONUS_1 = 1.5;
	public static final double PRICE_BONUS_2 = 1.2;
	public static final double PRICE_BONUS_3 = 0.8;
	
	public static final double CHILD_DISCOUNT = 0.67;


	public ArrayList<SearchResult> searchFlight(String origin, String destination, Date departureDate, Integer adultPassenger,
			Integer childPassenger, Integer infantPassenger) throws Exception {

		Date today = new Date();
		double basePrice = 0;
		double finalPrice = 0;
		double infantPrice = 0;
		
		// Comprobamos que se han introducido datos para el aeropuerto origen
		if (origin == null || origin.isEmpty()) {
			throw new Exception("No imput for origin airport.");
		}
		// Comprobamos que se han introducido datos para el aeropuerto destino
		if (destination == null || destination.isEmpty()) {
			throw new Exception("No imput for destination airport.");
		}
		// Comprobamos que la fecha introducida no es anterior a hoy
		if (departureDate == null || departureDate.before(today)) {
			throw new Exception("Date of depature has to greater than today.");
		}
		// Comprobamos que se ha introducido un numero correcto de pasajeros
		if (adultPassenger + childPassenger + infantPassenger <= 0) {
			throw new Exception("The number of passengers has to be greater than 0.");
		}

		// Comprobamos que el aeropuerto origen existe en nuestros datos
		Airport airportOrigin = searchAirport(origin);
		if (airportOrigin == null) {
			throw new Exception("Airport of origin Not Found.");
		}
		// Comprobamos que el aeropuerto destino existe en nuestros datos
		Airport airportDestination = searchAirport(destination);
		if (airportDestination == null) {
			throw new Exception("Airport of destination Not Found.");
		}

		// Comprobamos si existe una conexion entre el aeropuerto de origen y el
		// aeropuerto destino
		ArrayList<FlightConnection> flightConnections = checkFlightConnections(airportOrigin, airportDestination);

		ArrayList<SearchResult> searchResults = null;
		
		//Comprobamos que existe algun vuelo
		if (!flightConnections.isEmpty()) {
			
			//Creamos un nuevo array de resultados de busqueda
			searchResults = new ArrayList<SearchResult>();
						
			//Calculamos el numero de dias de diferencia entre hoy y la fecha de salida
			long daysDifference = checkDaysBetween(today,departureDate);
			
			//Recorremos el array con los vuelos disponibles y calculamos el precio.
			for (int i=0;i<flightConnections.size();i++) {
				
				basePrice = 0;
				finalPrice = 0;
				infantPrice = 0;
				
				FlightConnection flightAux = flightConnections.get(i);
				
				//Calculamos el sumplemento por fecha de vuelo 
				if (daysDifference <= DAYS_PRICE_BOUNDRY_1) {
					basePrice = flightAux.getPrice()*PRICE_BONUS_1;
				} else if (daysDifference > DAYS_PRICE_BOUNDRY_1 && daysDifference <= DAYS_PRICE_BOUNDRY_2) {
					basePrice = flightAux.getPrice()*PRICE_BONUS_2;
				} else if (daysDifference > DAYS_PRICE_BOUNDRY_2 && daysDifference <= DAYS_PRICE_BOUNDRY_3) {
					basePrice = flightAux.getPrice();
				} else if (daysDifference > DAYS_PRICE_BOUNDRY_3) {
					basePrice = flightAux.getPrice()*PRICE_BONUS_3;
				}
				
				//Comprobamos si hay pasajeros tipo Infant
				if (infantPassenger > 0) {
					infantPrice = searchInfantPrice(flightAux);
				}
				
				//Calculamos el precio final
				finalPrice = (basePrice*adultPassenger) + (basePrice*childPassenger*CHILD_DISCOUNT) + (infantPrice*infantPassenger);
				
				//Guardamos los datos en un objeto del tipo SearchResult
				searchResults.add(new SearchResult(flightAux.getAirline(),redondear(finalPrice),redondear(basePrice),redondear(infantPrice)));
			}
			
			
		} else {
			return null;
		}

		return searchResults;
	}
	
	/**
	 * Metodo que redondea a dos decimales
	 * 
	 * @param d Importe a redondar
	 * @return Importe redondeado
	 */
	private double redondear(double d) {
	    
	    d = d * 100;
	    long tmp = Math.round(d);
	    return (double) tmp / 100;
}

	/**
	 * Metodo que calcula el numero de dias entre dos fechas
	 * 
	 * @param today Hoy
	 * @param departureDate Fecha del vuelo
	 * @return numero de dias hasta el vuelo
	 * @throws ParseException 
	 */
	private long checkDaysBetween(Date today, Date departureDate) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date todayNoHours = sdf.parse(sdf.format(new Date()));
		
		return ChronoUnit.DAYS.between(todayNoHours.toInstant(),departureDate.toInstant());
	}

	private double searchInfantPrice(FlightConnection flightAux) {

		String code;
		String airlineIataCode;
		
		for (int i = 0; i < this.airlines.size(); i++) {
			
			//Sacamos el codigo IATA de la areolinea
			airlineIataCode = this.airlines.get(i).getIataCode();
			//Cogemos los dos primeros caracteres del vuelo 
			code = flightAux.getAirline().substring(0, 2);
			
			//Comparamos 
			if (code.compareTo(airlineIataCode)==0) {
				return this.airlines.get(i).getInfantPrice();
			}
		}
		
		return 0;
	}

	/**
	 * Metodo que devuelve los vuelos disponibles entre dos aeropuertos
	 * Si no existe devuelve un Array vacio
	 * 
	 * @param airportOrigin
	 * @param airportDestination
	 * @return
	 */
	private ArrayList<FlightConnection> checkFlightConnections(Airport airportOrigin, Airport airportDestination) {

		ArrayList<FlightConnection> arrayConnections = new ArrayList<FlightConnection>();
		
		//Recorremos el array con los vuelos disponibles
		for (int i = 0; i < this.flightConnections.size(); i++) {
			
			//Comprobamos si el aeropuerto de origen y el aeropuerto destino de alguno de los vuelos coincide con los que buscamos
			if (this.flightConnections.get(i).getDepartureAirport().compareTo(airportOrigin.getIataCode()) == 0
					&& this.flightConnections.get(i).getArrivalAirport()
							.compareTo(airportDestination.getIataCode()) == 0) {
				
				//Si coincide el origen y el destino aniadimos a nuestra lista de vuelos
				arrayConnections.add(flightConnections.get(i));
			}
		}

		return arrayConnections;
	}

	/**
	 * Metodo que devuelve el Objeto Airport o Nulo en caso de no existir en los datos proporcionados
	 * 
	 * @param airportOrigin Codigo del aeropuerto
	 * @return Airport o nulo
	 */
	private Airport searchAirport(String airportOrigin) {

		for (int i = 0; i < this.airports.size(); i++) {
			if (this.airports.get(i).getIataCode().compareTo(airportOrigin) == 0) {
				return this.airports.get(i);
			}
		}

		return null;
	}

}
