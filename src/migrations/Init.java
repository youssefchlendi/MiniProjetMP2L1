package migrations;

import java.sql.SQLException;

import seeders.EnseignantsSeeder;

public class Init {
	public void run(Boolean seed) {
		EnseignantsTableMigration ensmgr = new EnseignantsTableMigration();
		try {
			ensmgr.migrate(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (seed) {
			EnseignantsSeeder ensSeeder = new EnseignantsSeeder();

			ensSeeder.seed();
		}
	}
}
