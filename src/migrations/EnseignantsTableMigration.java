package migrations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dal.DatabaseConnection;
import helpers.SqlHelpers;

public class EnseignantsTableMigration implements MigrationInterface {
	Connection cnx = DatabaseConnection.getConnection();

	@Override
	public void migrate(Boolean remove) throws SQLException{
		Boolean tableExists = SqlHelpers.tableExists(cnx, "enseignants");
		if(tableExists) {
			if(remove) {
				down();
				up();
			}
		}else {
			up();
		}
		
	}

	@Override
	public void up() throws SQLException {
		String query = "CREATE TABLE enseignants( matricule VARCHAR(255) PRIMARY KEY NOT NULL, nom VARCHAR(255) NOT NULL, contact VARCHAR(255) NOT NULL)";
		PreparedStatement ps = cnx.prepareStatement(query);
		ps.executeUpdate();
	}

	@Override
	public void down() throws SQLException {
		String query = "DROP TABLE enseignants";
		PreparedStatement ps = cnx.prepareStatement(query);
		ps.executeUpdate();
	}
}
