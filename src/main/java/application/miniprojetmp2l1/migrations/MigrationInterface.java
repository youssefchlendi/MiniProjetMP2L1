package application.miniprojetmp2l1.migrations;

import java.sql.Connection;
import java.sql.SQLException;
import application.miniprojetmp2l1.dal.DatabaseConnection;

public interface MigrationInterface {
	Connection cnx = DatabaseConnection.getConnection();

	void migrate(Boolean remove) throws SQLException;

	void up() throws SQLException;

	void down() throws SQLException;
}
