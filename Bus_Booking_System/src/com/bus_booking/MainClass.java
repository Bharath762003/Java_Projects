package com.bus_booking;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MainClass {

	static void mainmethod() throws SQLException {
		int i=3;
		Scanner scan=new Scanner(System.in);
		boolean m=false;
		while(true) {
			System.out.println("Choose the below Options\n1.Admin \n2.Users \n3.exit");
			i=scan.nextInt();
			switch(i) {
			case 1:
				Admin a=new Admin();
				a.admin();
				break;
			case 2:
				User u=new User();
				u.main();
				break;
			case 3:
				System.out.println("Exit");
				return;
			
			default:
	            System.out.println("Invalid choice. Please enter a valid option.");
	            break;
			}
		}
		}
		
	public static void main(String[] args) throws SQLException {
		mainmethod();

	}

}
