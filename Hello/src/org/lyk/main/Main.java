package org.lyk.main;

import java.sql.Connection;

import dbc.DatabaseConnection;
 
 
public class Main
{
	public static void main(String[] args) throws Exception
	{ 
		DatabaseConnection dbc = new DatabaseConnection();
		Connection conn = dbc.getConnection();
		
		
		System.out.println("///Main done~~");
	} 
}
