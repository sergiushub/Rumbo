package com.app;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.data.FlightConnection;

public class SearchEngineTest {

	@Test
	public void test() {

		inputData();

		String helloWorld = new String("Hello, World!");
		System.out.println("Hello!");
		assertEquals(helloWorld, "Hello, World!");
	}

	private void inputData() {
		String line;
		FileReader f;
		List flightConnections = new ArrayList<FlightConnection>();
		try {
			f = new FileReader("src/test/resources/data/FlightConnectionPrice.dat");
			BufferedReader b = new BufferedReader(f);
			while ((line = b.readLine()) != null) {
				String[] lineData = line.split(",");
				
				flightConnections.add(new FlightConnection(lineData[0],lineData[1],lineData[2],Double.valueOf(lineData[3])));
			}
			
			for(int i=0; i< flightConnections.size(); i++) {
				
				System.out.println(flightConnections.get(i).toString());
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
