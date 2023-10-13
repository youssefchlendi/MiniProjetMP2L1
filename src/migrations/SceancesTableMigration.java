package migrations;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import helpers.SqlHelpers;

public class SceancesTableMigration implements MigrationInterface {

	@Override
	public void migrate(Boolean remove) throws SQLException {
		Boolean tableExists = SqlHelpers.tableExists(cnx, "sceances");
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
		String query = "CREATE TABLE sceances ("
				+ "			id INT AUTO_INCREMENT PRIMARY KEY,"
				+ "			heure_debut DATETIME NOT NULL,"
				+ "			heure_fin DATETIME NOT NULL,"
				+ "			id_enseignant VARCHAR(255),"
				+ "			id_matiere VARCHAR(255),"
				+ "			id_classe VARCHAR(255),"
				+ "			CONSTRAINT fk_enseignant FOREIGN KEY (id_enseignant) REFERENCES enseignants(matricule) ON DELETE CASCADE,"
				+ "			CONSTRAINT fk_matiere FOREIGN KEY (id_matiere) REFERENCES matieres(id) ON DELETE CASCADE,"
				+ "			CONSTRAINT fk_classe FOREIGN KEY (id_classe) REFERENCES classes(matricule) ON DELETE CASCADE"
				+ ");";
		PreparedStatement ps = cnx.prepareStatement(query);
		ps.executeUpdate();
	}

	@Override
	public void down() throws SQLException {
		String query = "DROP TABLE sceances";
		PreparedStatement ps = cnx.prepareStatement(query);
		ps.executeUpdate();
	}
	
}
