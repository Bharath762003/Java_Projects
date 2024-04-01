package com.bus_booking;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class UserDatabase{
	public String register(String name,String password,String email,String phone) throws SQLException {
		String query="insert into users (username,password,email,phone_number) values (?,?,?,?)";
		Connection c=SqlConnection.getConnection();
		PreparedStatement pst=c.prepareStatement(query);
		pst.setString(1, name);
		pst.setString(2, password);
		pst.setString(3, email);
		pst.setString(4, phone);
		pst.executeUpdate();
		return "Registered Successfully";
	}
	
	public void login(String email,String password) throws SQLException {
		String query="Select email,password,username from users";
		Connection c=SqlConnection.getConnection();
		PreparedStatement pst = c.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        boolean login=false;
        while (rs.next()) {
		String mail=rs.getString("email");
		String pword=rs.getString("password");
		String user=rs.getString("username");
			if(email.equals(mail) && password.equals(pword)) {
				Scanner scan=new Scanner(System.in);
					System.out.println("Login Successfully \nWelcome to Bus Booking Website"+"\n"+user+" ,Book Bus Tickets");
					login=true;
					BusDatabase bd=new BusDatabase();
					try {
						bd.displayBusInfo();
						
						int userinput=5;
						
							
						while(true) {
							System.out.println("Choose 1 to Book Bus \nChoose 2 to Check Booking Details \nChoose 3 to Cancel \nChoose 4 to Available Seats \nChoose 5 to exit \n---------------------------------------------------");
							System.out.print("Enter Your Choice: ");
							userinput=scan.nextInt();
						if(userinput==1) {
								BookingInfo book=new BookingInfo();
								if(book.isAvailable()) {
									Date Current=new Date();
									if(book.date.after(Current))
									{
										BookingDatabase booking=new BookingDatabase();
										booking.addBooking(book);
										System.out.println("Bus Booked on "+book.date+" ,Your Booking Number is "+book.BookingNo);
										System.out.println("---------------------------------------------------");
									}
										
								}
								else {
									System.err.println("Bus is full try other bus or date");
								}
							}
						else if(userinput==2) {
							System.out.print("Enter your BookingNo: ");
							int input=scan.nextInt();
							System.out.println();
							try {
								BookingDatabase booking=new BookingDatabase();
								System.out.println(booking.Details(input));
								System.out.println("---------------------------------------------------");
							}
							catch(Exception e) {
								System.err.println("Invalid BookingNo.Enter Correct Booking Number");
								System.out.println("---------------------------------------------------");
							}	
						}
						else if(userinput==3) {
							try {
							System.out.print("Your have choosed to cancel \nEnter Your Booking Id: ");
							int input=scan.nextInt();
							BookingDatabase booking=new BookingDatabase();
							booking.cancellation(input);
							System.out.println("Cancelled Successfully");
							System.out.println("---------------------------------------------------");
							}
							catch(Exception e){
								System.err.println("Enter the Correct Booking No");
							}
							
						}
						else if(userinput==4) {
							System.out.print("Please Provide below Information \nEnter the Bus No Your Going to Book: ");
							int input=scan.nextInt();
							Date date = null;
							System.out.print("Enter the Travel Date: ");
							String dateInput=scan.next();
							SimpleDateFormat dateformat=new SimpleDateFormat("dd-MM-yyyy");
							try {
								date=dateformat.parse(dateInput);
							} catch (ParseException e) {
								e.printStackTrace();
							}	
							System.out.println();
							BookingDatabase booking=new BookingDatabase();
							booking.bookedseats(input,date);
							System.out.println("---------------------------------------------------");
						}
						else if(userinput==5) {
							System.out.println("You are Exit from Booking Tickets");
							return;
						}
						else {
							System.out.println("Choose the below given Options");
							System.out.println("---------------------------------------------------");
						}
						}
//						scan.close();
					}
					catch(Exception e) {
						System.out.println(e);
					}
					break;
			}
		
        }
        if(!login) {
        	System.out.println("Invalid email or password");
        }
	}
}

public class User {

public void main() throws SQLException {
		
		Scanner scan = new Scanner(System.in);
		
        while (true) {
            System.out.println("Choose the below Options \n1. Login\n2. Register\n3. Exit");
            int input = scan.nextInt();
            scan.nextLine(); // Consume newline character

            switch (input) {
                case 1:
                    System.out.println("Enter MailId: ");
                    String email = scan.nextLine();
                    System.out.println("Enter the Password: ");
                    String password = scan.nextLine();
                    UserDatabase db = new UserDatabase();
                    db.login(email, password);
                    break;
                case 2:
                    System.out.println("Enter the Username: ");
                    String name = scan.nextLine();
                    System.out.print("Enter password: ");
                    password = scan.nextLine();
                    
                    String eemail = "";
                    boolean evalid = false;
                    while (!evalid) {
                        try {
                            System.out.print("Enter email Address: ");
                            eemail = scan.nextLine();
                            if (eemail.contains("@")) {
                                evalid = true;
                            } else {
                                System.out.println("Enter a valid email address containing '@'");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    String phoneNumber = "";
                    boolean pvalid = false;
                    while (!pvalid) {
                        try {
                            System.out.print("Enter phone number: ");
                            phoneNumber = scan.nextLine();
                            if (phoneNumber.length() == 10) {
                                pvalid = true;
                            } else {
                                System.out.println("Enter Valid Mobile Number (10 digits)");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    UserDatabase d = new UserDatabase();
                    System.out.println(d.register(name, password, eemail, phoneNumber));
                    break;
                case 3:
                    System.out.println("Exit from  User Login ");
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        }
	
	}

}

