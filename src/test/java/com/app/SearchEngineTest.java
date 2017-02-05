package com.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.data.Airline;
import com.data.Airport;
import com.data.FlightConnection;
import com.data.SearchResult;

public class SearchEngineTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void TestNoInputOriginAirport1() throws Exception {
		
		SearchEngine sEngine = new SearchEngine();
		
		thrown.expect(Exception.class);
		thrown.expectMessage("No imput for origin airport.");
		sEngine.searchFlight(null, "XXX", new Date(), 1, 0, 0);
	}
	
	@Test
	public void TestNoInputOriginAirport2() throws Exception {
		
		SearchEngine sEngine = new SearchEngine();
		
		thrown.expect(Exception.class);
		thrown.expectMessage("No imput for origin airport.");
		sEngine.searchFlight("", "XXX", new Date(), 1, 0, 0);
	}
	
	@Test
	public void TestNoInputDestinationAirport1() throws Exception {
		
		SearchEngine sEngine = new SearchEngine();
		
		thrown.expect(Exception.class);
		thrown.expectMessage("No imput for destination airport.");
		sEngine.searchFlight("XXX", null, new Date(), 1, 0, 0);
	}

	@Test
	public void TestNoInputDestinationAirport2() throws Exception {
		
		SearchEngine sEngine = new SearchEngine();
		
		thrown.expect(Exception.class);
		thrown.expectMessage("No imput for destination airport.");
		sEngine.searchFlight("XXX", "", new Date(), 1, 0, 0);
	}
	
	@Test
	public void TestDateNoImput() throws Exception {
		
		SearchEngine sEngine = new SearchEngine();
		
		thrown.expect(Exception.class);
		thrown.expectMessage("No imput for departure date.");
		sEngine.searchFlight("XXX", "XXX", null, 1, 0, 0);
	}
	
	@Test
	public void TestDateBefore() throws Exception {
		
		SearchEngine sEngine = new SearchEngine();
		
		thrown.expect(Exception.class);
		thrown.expectMessage("Date of depature has to be equal or greater than today.");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, -1);		
		sEngine.searchFlight("XXX", "XXX", calendar.getTime(), 1, 0, 0);
	}
	
	@Test
	public void TestNumberOfPassengers() throws Exception {
		
		SearchEngine sEngine = new SearchEngine();
		
		thrown.expect(Exception.class);
		thrown.expectMessage("The number of passengers has to be greater than 0.");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, 2);		
		sEngine.searchFlight("XXX", "XXX", calendar.getTime(), 0, 0, 0);
	}
	
	@Test
	public void TestAirportOriginExists() throws Exception {
		
		SearchEngine sEngine = new SearchEngine();
		
		thrown.expect(Exception.class);
		thrown.expectMessage("Airport of origin Not Found.");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, 2);		
		sEngine.searchFlight("XXX", "MAD", calendar.getTime(), 1, 0, 0);
	}
	
	@Test
	public void TestAirportDestinationExists() throws Exception {
		
		SearchEngine sEngine = new SearchEngine();
		
		thrown.expect(Exception.class);
		thrown.expectMessage("Airport of destination Not Found.");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, 2);		
		sEngine.searchFlight("MAD", "XXX", calendar.getTime(), 1, 0, 0);
	}
	
	/**
	 * Test 1 - 1 adulto de Amasterdam a Francia dentro de 31 dias
	 */
	@Test
	public void test1() {

		SearchEngine sEngine = new SearchEngine();
		ArrayList<SearchResult> sResult = new ArrayList<SearchResult>();
		
		try {
			
			//Calculamos 31 dias a partir de hoy
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_YEAR, 31);			 
			
			sResult = sEngine.searchFlight("AMS", "FRA", calendar.getTime(), 1, 0, 0);

			assertEquals("Test 1 - Opcion 1 - Vuelo", "TK2372", sResult.get(0).getFlightNumber());
			assertEquals("Test 1 - Opcion 1 - Precio", 157.6, sResult.get(0).getTotalPrice(), 0);
			
			assertEquals("Test 1 - Opcion 2 - Vuelo", "TK2659", sResult.get(1).getFlightNumber());
			assertEquals("Test 1 - Opcion 2 - Precio", 198.4 ,sResult.get(1).getTotalPrice(), 0);
			
			assertEquals("Test 1 - Opcion 3 - Vuelo", "LH5909", sResult.get(2).getFlightNumber());
			assertEquals("Test 1 - Opcion 3 - Precio", 90.4, sResult.get(2).getTotalPrice(), 0);
			
		} catch (ParseException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Test 2 - 2 adultos, 1 niño y 1 bebé de Londres a Estambul dentro de 15 dias 
	 */
	@Test
	public void test2() {

		SearchEngine sEngine = new SearchEngine();
		ArrayList<SearchResult> sResult = new ArrayList<SearchResult>();
		
		try {
			
			//Calculamos 15 dias a partir de hoy
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_YEAR, 15);			 
			
			sResult = sEngine.searchFlight("LHR", "IST", calendar.getTime(), 2, 1, 1);

			assertEquals("Test 2 - Opcion 1 - Vuelo","TK8891", sResult.get(0).getFlightNumber());
			assertEquals("Test 2 - Opcion 1 - Precio", 806, sResult.get(0).getTotalPrice(), 0);
			
			assertEquals("Test 2 - Opcion 2 - Vuelo", "LH1085", sResult.get(1).getFlightNumber());
			assertEquals("Test 2 - Opcion 2 - Precio", 481.19, sResult.get(1).getTotalPrice(), 0);
			
			
		} catch (ParseException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Test 3 - 1 adultos y 2 niños de Barcelona a Madrid dentro de 2 dias 
	 */
	@Test
	public void test3() {

		SearchEngine sEngine = new SearchEngine();
		ArrayList<SearchResult> sResult = new ArrayList<SearchResult>();
		
		try {
			
			//Calculamos 15 dias a partir de hoy
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_YEAR, 2);			 
			
			sResult = sEngine.searchFlight("BCN", "MAD", calendar.getTime(), 1, 2, 0);

			assertEquals("Test 3 - Opcion 1 - Vuelo","IB2171", sResult.get(0).getFlightNumber());
			assertEquals("Test 3 - Opcion 1 - Precio", 909.09, sResult.get(0).getTotalPrice(), 0);
			
			assertEquals("Test 3 - Opcion 2 - Vuelo", "LH5496", sResult.get(1).getFlightNumber());
			assertEquals("Test 3 - Opcion 2 - Precio", 1028.43, sResult.get(1).getTotalPrice(), 0);
			
			
		} catch (ParseException e) {
			fail(e.getMessage());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	/**
	 * Test 4 - 1 adultos de Paris a Frankfurt dentro de 2 dias 
	 */
	@Test
	public void test4() {

		SearchEngine sEngine = new SearchEngine();
		ArrayList<SearchResult> sResult = new ArrayList<SearchResult>();
		
		try {
			
			//Calculamos 15 dias a partir de hoy
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_YEAR, 2);			 
			
			sResult = sEngine.searchFlight("CDG", "FRA", calendar.getTime(), 1, 0, 0);

			assertNull("Test 4 - No hay vuelos", sResult);
			
		} catch (ParseException e) {
			fail(e.getMessage());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	@BeforeClass
	public static void inputData() {

		String line;
		FileReader f;
		List<FlightConnection> flightConnections = new ArrayList<FlightConnection>();
		List<Airport> airports = new ArrayList<Airport>();
		List<Airline> airlines = new ArrayList<Airline>();
		try {
			f = new FileReader("src/test/resources/data/FlightConnectionPrice.dat");
			BufferedReader b = new BufferedReader(f);
			while ((line = b.readLine()) != null) {
				String[] lineData = line.split(",");

				flightConnections
						.add(new FlightConnection(lineData[0], lineData[1], lineData[2], Double.valueOf(lineData[3])));
			}

			f = new FileReader("src/test/resources/data/Airlines.dat");
			b = new BufferedReader(f);
			while ((line = b.readLine()) != null) {
				String[] lineData = line.split(",");

				airlines.add(new Airline(lineData[0], lineData[1], Double.valueOf(lineData[2])));
			}

			f = new FileReader("src/test/resources/data/Airports.dat");
			b = new BufferedReader(f);
			while ((line = b.readLine()) != null) {
				String[] lineData = line.split(",");

				airports.add(new Airport(lineData[0], lineData[1]));
			}

			SearchEngine.flightConnections = (ArrayList<FlightConnection>) flightConnections;
			SearchEngine.airlines = (ArrayList<Airline>) airlines;
			SearchEngine.airports = (ArrayList<Airport>) airports;

			b.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}