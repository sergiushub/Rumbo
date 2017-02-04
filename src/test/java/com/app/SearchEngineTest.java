package com.app;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class SearchEngineTest {

	@Test
	public void test() {

		inputData();

		String helloWorld = new String("Hello, World!");
		System.out.println("Hello!");
		assertEquals(helloWorld, "Hello, World!");
	}

	private void inputData() {
		String cadena;
		FileReader f;
		try {
			f = new FileReader("../test/resources/com/data/FlightConnectionPrice.dat");
			BufferedReader b = new BufferedReader(f);
			while ((cadena = b.readLine()) != null) {
				System.out.println(cadena);
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
