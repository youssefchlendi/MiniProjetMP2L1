package application.miniprojetmp2l1.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static Connection con = null;

	private static void initConnection() {

		String url = "jdbc:mysql://localhost:3306/mp2l";
		String user = "root";
		String pass = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		if(con == null) {
			initConnection();
		}
		return con;
	}

}
