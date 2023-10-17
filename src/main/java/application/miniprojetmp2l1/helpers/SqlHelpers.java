package application.miniprojetmp2l1.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlHelpers {
	public static boolean tableExists(Connection connection, String tableName) throws SQLException {
		String query = "SELECT * FROM information_schema.tables WHERE table_schema = ? AND table_name = ? LIMIT 1;";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, "mp2l");
		ps.setString(2, tableName);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			return true;
		}
		
		return false;
	}
}
