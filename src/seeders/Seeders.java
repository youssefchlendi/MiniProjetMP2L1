package seeders;

import java.sql.Connection;

import dal.DatabaseConnection;

public abstract class Seeders {
	Connection cnx = DatabaseConnection.getConnection();
	
	static int count = 5;
	
	public abstract void seed();
}
