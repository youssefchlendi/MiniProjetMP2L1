package migrations;

import java.sql.Connection;
import java.sql.SQLException;

import dal.DatabaseConnection;

public interface MigrationInterface {
	Connection cnx = DatabaseConnection.getConnection();

	public void migrate(Boolean remove) throws SQLException;

	public void up() throws SQLException;

	public void down() throws SQLException;
}
