package com.app;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.data.Airline;
import com.data.Airport;
import com.data.FlightConnection;

public class SearchEngineTest {

	@Test
	public void test() {

		SearchEngine sEngine = new SearchEngine();
		inputData(sEngine);

		String helloWorld = new String("Hello, World!");
		assertEquals(helloWorld, "Hello, World!");
	}

	private void inputData(SearchEngine sEngine) {

		String line;
		FileReader f;
		List flightConnections = new ArrayList<FlightConnection>();
		List airports = new ArrayList<Airport>();
		List airlines = new ArrayList<Airline>();
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

			sEngine.flightConnections = (ArrayList<FlightConnection>) flightConnections;
			sEngine.airlines = (ArrayList<Airline>) airlines;
			sEngine.airports = (ArrayList<Airport>) airports;

			for (int i = 0; i < sEngine.flightConnections.size(); i++) {

				System.out.println(sEngine.flightConnections.get(i).toString());

			}

			for (int i = 0; i < sEngine.airlines.size(); i++) {

				System.out.println(sEngine.airlines.get(i).toString());

			}

			for (int i = 0; i < sEngine.airports.size(); i++) {

				System.out.println(sEngine.airports.get(i).toString());

			}

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
