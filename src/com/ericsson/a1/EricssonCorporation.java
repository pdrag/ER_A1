package com.ericsson.a1;

public class EricssonCorporation 
{
	// legal minimum base pay in Quebec
	public static final double MIN_BASE_PAY = 8.00; 
	
	// maximum work hours allowed by company'a policy
	public static final double MAX_HOURS_PER_WEEK = 60.0; 
	
	// regular business week hours
	public static final double REGULAR_HOURS_PER_WEEK = 40.0; 
	
	
	static void printWeekPay(double basePay, double hoursWorked)
	{
		double pay;
		
		if (basePay<0)
		{
			// throw new IllegalArgumentException("Base pay is negative.");
			System.out.print("Error (Illegal argument): Base pay is negative.");
			return;
		}
		
		if (hoursWorked<0)
		{
			// throw new IllegalArgumentException("Hours worked is negative.");
			System.out.print("Error (Illegal argument): Hours worked is negative.");
			return;
		}
		
		if (basePay<MIN_BASE_PAY)
		{
			System.out.printf("Error: Base pay is below Quebec legal limit of $%.2f.", MIN_BASE_PAY);
			return;
		}
		
		if (hoursWorked>MAX_HOURS_PER_WEEK)
		{
			System.out.printf("Error: Hours worked are above the allowed maximum of %.2f hours.", MAX_HOURS_PER_WEEK);
			return;
		}
		
		// Check if there is overtime
		if (hoursWorked>REGULAR_HOURS_PER_WEEK)
		{
			// Regular hours
			pay = basePay * REGULAR_HOURS_PER_WEEK;	
			// adding overtime
			pay += basePay * (hoursWorked-REGULAR_HOURS_PER_WEEK) * 1.5;	
		}
		else
		{
			pay = basePay * hoursWorked;
		}
		
		System.out.printf("$%.2f",pay);
	}
	
	
	
	public static void main(String[] args) 
	{
		System.out.print("Employee-1: ");
		printWeekPay(7.50, 35);
		System.out.println();

		System.out.print("Employee-2: ");
		printWeekPay(8.20, 47);
		System.out.println();

		System.out.print("Employee-3: ");
		printWeekPay(10.00, 73);
		System.out.println();
	}

}
