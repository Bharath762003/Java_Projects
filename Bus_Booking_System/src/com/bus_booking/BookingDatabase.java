package com.bus_booking;

import java.sql.*;

import java.util.Date;

public class BookingDatabase {
	public int getBooked(int busNo,Date date) throws SQLException {
		String query="select count(passenger_name) from booking where bus_no=? and travel_date=?";
		Connection c=SQLConnection.getConnection();
		PreparedStatement pst=c.prepareStatement(query);
		java.sql.Date sqldate=new java.sql.Date(date.getTime());
		pst.setInt(1, busNo);
		pst.setDate(2, sqldate);
		ResultSet rs=pst.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
	public String Details(int BookingNo) throws SQLException {
		String query="select * from booking where Bookingno="+BookingNo;
		Connection c=SQLConnection.getConnection();
		Statement st=c.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		return "Your Booking No: "+rs.getInt(1)+"\n"+"Passenger Name: "+rs.getString(2)+"\n"+"Bus No: "+rs.getInt(3)+"\n"+"Booked Date: "+rs.getDate(4);
		
	}
	public void addBooking(BookingInfo book) throws SQLException {
		String query="Insert into booking values(?,?,?,?,?,?)";
		Connection c=SQLConnection.getConnection();
		java.sql.Date sqldate=new java.sql.Date(book.date.getTime());
		PreparedStatement pst=c.prepareStatement(query);
		pst.setInt(1, book.BookingNo);
		pst.setString(2, book.passengerName);
		pst.setInt(3, book.busNo);
		pst.setDate(4, sqldate);
		pst.setString(5, book.Source);
		pst.setString(6, book.Destination);
		pst.executeUpdate();
	}
	
	public void cancellation(int bookingno) throws SQLException {
		String query="select * from booking where Bookingno="+bookingno;
		Connection c=SQLConnection.getConnection();
		Statement st=c.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		int book= rs.getInt(1);
		if(bookingno==book) {
			String q="Delete from booking where Bookingno="+bookingno;
			PreparedStatement pst=c.prepareStatement(q);
			pst.executeUpdate();
			System.out.println("\nBooking with Booking Number "+book+" Cancelled Successfully");
		}
	}
	
	public void bookedseats(int busNo,Date date) throws SQLException {
		BusDatabase bd=new BusDatabase();
		BookingDatabase bdd=new BookingDatabase();
		int capacity=bd.getCapacity(busNo);
		int booked=bdd.getBooked(busNo,date);
		System.out.println("The capacity of the Bus "+capacity+" and " +booked +" Seats are  Booked..");
	}
	
}

