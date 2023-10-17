package application.miniprojetmp2l1.seeders;

import java.sql.Connection;
import application.miniprojetmp2l1.dal.DatabaseConnection;

public abstract class Seeders {
	Connection cnx = DatabaseConnection.getConnection();
	
	static int count = 5;
	
	public abstract void seed();
}
