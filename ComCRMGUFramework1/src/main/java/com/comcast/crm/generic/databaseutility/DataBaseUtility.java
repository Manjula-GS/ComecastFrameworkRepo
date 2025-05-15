package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
	
	Connection con;
	
	public void getDBConnection(String url, String usn, String psw) throws SQLException
	{
	try{
		Driver drive=new Driver();
		DriverManager.registerDriver(drive);
		
		con=DriverManager.getConnection(url, usn, psw);
	}
	catch(Exception e) {
		
	}		
	}
	public void getDBConnection() throws SQLException
	{
	try{
		Driver drive=new Driver();
		DriverManager.registerDriver(drive);
		
		con=DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninzahrm", "root", "root");
	}
	catch(Exception e) {
		
	}		
	}
	public void closeDBConnection() throws SQLException
	{
		try {
			con.close();
		}catch(Exception e) {
			
		}
		
	}
	ResultSet result=null;
	public ResultSet executeSelectQuery(String query)
	{
		try {
			Statement stat = con.createStatement();
			result=stat.executeQuery(query);			
			
		}catch(Exception e) {
			
		}
		return result;
	}
	public int executeNonSelectQuery(String query)
	{
		int result=0;
		try {
			Statement stat = con.createStatement();
			result=stat.executeUpdate(query);
			
		}catch(Exception e) {
			
		}
		return result;
		
	}
	

}
