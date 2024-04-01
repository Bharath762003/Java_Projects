package com.bus_booking;
import java.sql.*;
public class BusDatabase {
	public void displayBusInfo() throws SQLException {
		String query="Select * from bus";
		Connection c=SQLConnection.getConnection();
		Statement st=c.createStatement();
		ResultSet rs=st.executeQuery(query);
		System.out.println("Bus Details:");
		while(rs.next()) {
			System.out.println("Bus No: "+rs.getInt(1));
			if(rs.getInt(2)==0)
				System.out.println("AC: no ");
			else
				System.out.println("AC: Yes ");
			System.out.println("Capacity: "+rs.getInt(3));
		}
		System.out.println("---------------------------------------------------");
	}
	
	public int getCapacity(int id) throws SQLException {
		String query="Select capacity from bus where id="+id;
		Connection c=SQLConnection.getConnection();
		Statement st=c.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		return rs.getInt(1);
		
	}

}
