package migrations;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import helpers.SqlHelpers;

public class EnseignantsTableMigration implements MigrationInterface {

	@Override
	public void migrate(Boolean remove) throws SQLException {
		Boolean tableExists = SqlHelpers.tableExists(cnx, "enseignants");
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
		String query = "CREATE TABLE enseignants( matricule VARCHAR(255) PRIMARY KEY NOT NULL, nom VARCHAR(255) NOT NULL, contact VARCHAR(255) NOT NULL)";
		PreparedStatement ps = cnx.prepareStatement(query);
		ps.executeUpdate();
	}

	@Override
	public void down() throws SQLException {
		PreparedStatement ps;
		if(SqlHelpers.tableExists(cnx, "sceances")) {
			String query = "ALTER TABLE sceances " + "DROP FOREIGN KEY fk_enseignant;";
			ps = cnx.prepareStatement(query);
			ps.executeUpdate();
		}
		String query = "DROP TABLE enseignants;";
		ps = cnx.prepareStatement(query);
		ps.executeUpdate();
	}

}
