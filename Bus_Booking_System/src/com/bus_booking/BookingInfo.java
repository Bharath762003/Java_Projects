package com.bus_booking;

import java.sql.SQLException;
import java.text.*;
import java.util.*;

public class BookingInfo {

	String passengerName;
	int busNo;
	Date date;
	int BookingNo;
	String Source;
	String Destination;
	BookingInfo(){
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter Name of Passenger: ");
		passengerName=scan.next();
		System.out.println("Enter Bus No: "); 
		busNo=scan.nextInt();
		System.out.println("Enter the date in this format dd-mm-yyyy");
		
		SimpleDateFormat dateformat=new SimpleDateFormat("dd-MM-yyyy");
		boolean valid=false;
		while(!valid) {
			try {
				String dateInput=scan.next();
				date=dateformat.parse(dateInput);
				Date currentDate=new Date();
				if(date !=null && date.before(currentDate)) {
					System.out.println("You cannot book for the past Date");
				}
				else {
					valid=true;
				}
				
			} catch (ParseException e) {
				System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy format:");
			}	
		}
		
		
			
			
		Random random = new Random();
		BookingNo=random.nextInt(100) + 1;
		scan.nextLine();
		System.out.println("Enter the Source City ");
		Source=scan.nextLine();
		System.out.println("Enter the Destination City");
		Destination=scan.nextLine();
	}
	
	public boolean isAvailable() throws SQLException  {
		BusDatabase bd=new BusDatabase();
		BookingDatabase bdd=new BookingDatabase();
		int capacity=bd.getCapacity(busNo);
		
		int booked=bdd.getBooked(busNo,date);
	
		return capacity>booked;
	}
}
