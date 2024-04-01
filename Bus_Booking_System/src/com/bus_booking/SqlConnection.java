package com.bus_booking;
import java.sql.*;
public class SqlConnection {

	private static final String url="jdbc:mysql://localhost:3306/bus_booking";
	private static final String userName="root";
	private static final String password="Bha@7603";
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,userName,password);
	}
}
