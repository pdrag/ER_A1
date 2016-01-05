package com.ericsson.a1;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EricssonCorporationTest 
{
	ByteArrayOutputStream testOut;
	ByteArrayOutputStream testErr;

	@Before
	public void grabOutput()
	{
		System.setOut(new PrintStream(testOut = new ByteArrayOutputStream()));
		System.setErr(new PrintStream(testErr = new ByteArrayOutputStream()));
	}
	
	@After
	public void restoreOutput()
	{
		System.setOut(null);
		System.setErr(null);
	}
	
	@Test
	// Low wage test
	public void testPrintWeekPay_LowWage1() 
	{
		EricssonCorporation.printWeekPay(6.00, 20);
		assertTrue(testErr.toString().isEmpty());
		assertTrue("Error: Base pay is below Quebec legal limit of $8.00.".equals(testOut.toString()));
	}

	@Test
	// Low wage test - edge case
	public void testPrintWeekPay_LowWage2() 
	{
		EricssonCorporation.printWeekPay(7.99, 50);
		assertTrue(testErr.toString().isEmpty());
		assertTrue("Error: Base pay is below Quebec legal limit of $8.00.".equals(testOut.toString()));
	}

	@Test
	// Too many worked hours
	public void testPrintWeekPay_TooManyHours1() 
	{
		EricssonCorporation.printWeekPay(25.00, 80);
		assertTrue(testErr.toString().isEmpty());
		assertTrue("Error: Hours worked are above the allowed maximum of 60.00 hours.".equals(testOut.toString()));
	}

	@Test
	// Too many worked hours - edge case
	public void testPrintWeekPay_TooManyHours2() 
	{
		EricssonCorporation.printWeekPay(40.00, 60.1);
		assertTrue(testErr.toString().isEmpty());
		assertTrue("Error: Hours worked are above the allowed maximum of 60.00 hours.".equals(testOut.toString()));
	}

	@Test
	// Regular test
	public void testPrintWeekPay_Regular_NoOvertime1() 
	{
		EricssonCorporation.printWeekPay(10.00, 30);
		assertTrue(testErr.toString().isEmpty());
		String response = testOut.toString();
		String expected = "$300.00";
		System.out.println("Expected: " + expected + ", response: " + response);
		assertTrue(expected.equals(response));
	}

	@Test
	// Regular test
	public void testPrintWeekPay_Regular_NoOvertime2() 
	{
		EricssonCorporation.printWeekPay(50.00, 20);
		assertTrue(testErr.toString().isEmpty());
		String response = testOut.toString();
		String expected = "$1000.00";
		System.out.println("Expected: " + expected + ", response: " + response);
		assertTrue(expected.equals(response));
	}

	@Test
	// Regular test - no overtime edge case
	public void testPrintWeekPay_Regular_NoOvertime3() 
	{
		EricssonCorporation.printWeekPay(10.00, 40);
		assertTrue(testErr.toString().isEmpty());
		String response = testOut.toString();
		String expected = "$400.00";
		System.out.println("Expected: " + expected + ", response: " + response);
		assertTrue(expected.equals(response));
	}

	@Test
	// Regular test - overtime edge case low
	public void testPrintWeekPay_Regular_Overtime1() 
	{
		EricssonCorporation.printWeekPay(10.00, 40.1);
		assertTrue(testErr.toString().isEmpty());
		String response = testOut.toString();
		String expected = "$401.50";
		System.out.println("Expected: " + expected + ", response: " + response);
		assertTrue(expected.equals(response));
	}

	@Test
	// Regular test - overtime edge case low
	public void testPrintWeekPay_Regular_Overtime2() 
	{
		EricssonCorporation.printWeekPay(10.00, 50);
		assertTrue(testErr.toString().isEmpty());
		String response = testOut.toString();
		String expected = "$550.00";
		System.out.println("Expected: " + expected + ", response: " + response);
		assertTrue(expected.equals(response));
	}

	@Test
	// Regular test - overtime edge case high
	public void testPrintWeekPay_Regular_Overtime3() 
	{
		EricssonCorporation.printWeekPay(10.00, 60);
		assertTrue(testErr.toString().isEmpty());
		String response = testOut.toString();
		String expected = "$700.00";
		System.out.println("Expected: " + expected + ", response: " + response);
		assertTrue(expected.equals(response));
	}

}
