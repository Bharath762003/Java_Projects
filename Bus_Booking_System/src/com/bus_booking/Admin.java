package com.bus_booking;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

class AdminDatabase{
	public void displayUser() throws SQLException {
		String query="Select * from users";
		Connection c=SQLConnection.getConnection();
		Statement st=c.createStatement();
		ResultSet rs=st.executeQuery(query);
		System.out.println("Users Details:");
		while(rs.next()) {
			System.out.println("Username: "+rs.getString(1));
			System.out.println("Password: "+rs.getString(2));
			System.out.println("Email: "+rs.getString(3));
			System.out.println("PhoneNumber: "+rs.getString(4));
			System.out.println();
		}
		System.out.println("---------------------------------------------------");
	}
	public String remove(int busNo) throws SQLException {
		String query="Delete from bus where id="+busNo;
		Connection c=SQLConnection.getConnection();
		PreparedStatement pst=c.prepareStatement(query);
		pst.executeUpdate();
		return "The Bus No "+busNo+" is removed ";
	}
	
	public String add(int busNo,int ac,int capacity) throws SQLException {
		String query ="insert into bus (id,ac,capacity) values(?,?,?)";
		Connection c=SQLConnection.getConnection();
		PreparedStatement pst=c.prepareStatement(query);
		pst.setInt(1,busNo);
		pst.setInt(2, ac);
		pst.setInt(3, capacity);
		pst.executeUpdate();
		return "Bus No "+busNo+" is Added with an Capacity of "+capacity+" .";
		
	}
	public String register(int aid,String name,String password,String phone) throws SQLException {
		String query="insert into admin (aid,name,password,phone_number) values (?,?,?,?)";
		Connection c=SQLConnection.getConnection();
		PreparedStatement pst=c.prepareStatement(query);
		pst.setInt(1, aid);
		pst.setString(2, name);
		pst.setString(3, password);
		pst.setString(4, phone);
		pst.executeUpdate();
		return "Registered Successfully";
	}
	public void login(int id,String password) throws SQLException {
		String query="Select aid,password,name from admin";
		Connection c=SQLConnection.getConnection();
		PreparedStatement pst = c.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        
        while (rs.next()) {
		int aid=rs.getInt(1);
		String pword=rs.getString("password");
		String adminname=rs.getString("name");
			if(id==aid && password.equals(pword)) {
				Scanner scan=new Scanner(System.in);
				System.out.println("Login Successfully \nWelcome to Bus Booking Website"+"\n"+adminname+" ,as a Admin you can Manage buses");
				int input = 4;
                AdminDatabase ad=new AdminDatabase();
                while (true) {

                    System.out.println("Choose the below Options \n1. To View User Details \n2. To Add Bus " +
                            "\n3. To Remove Bus \n4. Exit");
                    input = scan.nextInt();
                    switch (input) {
                        case 1:
                            System.out.println("Here You can see User details");
                            ad.displayUser();
                            break;
                        case 2:
                            System.out.println("Enter Bus No, press 1 for AC and 0 for Non AC, Capacity:");
                
                            int busNo = scan.nextInt();
                            int ac = scan.nextInt();
                            int capacity = scan.nextInt();
                            System.out.println(ad.add(busNo, ac, capacity));
                            break;
                        case 3:
                            System.out.println("Enter Bus No to remove:");
                            int removeBusNo = scan.nextInt();
                            System.out.println(ad.remove(removeBusNo));
                            break;
                        case 4:
                            System.out.println("Exit from Admin Login");
                            return;
                        default:
                            System.out.println("Enter the above options only");
                            break;
                    }
                }
                
			}
        }
	}
}

public class Admin {

private static int id=101;
	
	public static void adminregister() throws SQLException {
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter Admin Secret Code to Register");
		int secretcode=scan.nextInt();
		scan.nextLine();
		if(secretcode==id) {
			Random random = new Random();
			int aid=random.nextInt(100) + 1;
			System.out.println("Enter Username");
			String name=scan.nextLine();
			System.out.println("Enter your password");
			String password=scan.nextLine();
			System.out.println("Enter the Phone No: ");
			String phone =scan.nextLine();
			System.out.println("Admin Account Created. You can Login with this Admin id: "+aid);
			AdminDatabase adb=new AdminDatabase();
			adb.register(aid,name, password, phone);
		}
		else {
			System.out.println("Enter the Correct secret code");
		}
	}
	public static  void adminlogin() throws SQLException {
		Scanner scan=new Scanner(System.in);

            System.out.print("Enter the Admin Id No: ");
            int adminId = scan.nextInt();
            scan.nextLine(); 
            
            System.out.print("Enter the Password: ");
            String adminPassword = scan.nextLine();
            AdminDatabase adb=new AdminDatabase();
			adb.login(adminId, adminPassword);
            
    }
	
	public void admin() throws SQLException {
		int i=3;
		Scanner scan=new Scanner(System.in);
		boolean m=false;
		while(true) {
			System.out.println("Choose the below Options\n1.Admin Registration \n2.Admin Login \n3.exit");
			i=scan.nextInt();
			switch(i) {
			case 1:
				adminregister();
				break;
			case 2:
				adminlogin();
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

//	public static void main(String args[]) throws SQLException {
//		adminlogin();
//	}
}
