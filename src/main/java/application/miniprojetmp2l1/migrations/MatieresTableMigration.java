package application.miniprojetmp2l1.migrations;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import application.miniprojetmp2l1.helpers.SqlHelpers;

public class MatieresTableMigration implements MigrationInterface {

	@Override
	public void migrate(Boolean remove) throws SQLException {
		boolean tableExists = SqlHelpers.tableExists(cnx, "matieres");
		if (tableExists) {
			if (remove) {
				down();
				up();
			}
		} else {
			up();
		}
	}

	@Override
	public void up() throws SQLException {
		String query = "CREATE TABLE matieres( id VARCHAR(255) PRIMARY KEY NOT NULL, nom VARCHAR(255) NOT NULL)";
		PreparedStatement ps = cnx.prepareStatement(query);
		ps.executeUpdate();
	}

	@Override
	public void down() throws SQLException {
		PreparedStatement ps;
		if(SqlHelpers.tableExists(cnx, "sceances")) {
			String query = "ALTER TABLE sceances " + "DROP FOREIGN KEY fk_matiere; ";
			ps = cnx.prepareStatement(query);
			ps.executeUpdate();
		}
		String query = "DROP TABLE matieres";
		ps = cnx.prepareStatement(query);
		ps.executeUpdate();
	}
	
}
