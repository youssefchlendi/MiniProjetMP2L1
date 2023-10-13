package migrations;

import java.sql.SQLException;

import seeders.ClassesSeeder;
import seeders.EnseignantsSeeder;

public class Init {
	public void run(Boolean seed) {
		EnseignantsTableMigration ensmgr = new EnseignantsTableMigration();
		ClassesTableMigration clsmgr = new ClassesTableMigration();
		try {
			ensmgr.migrate(true);
			clsmgr.migrate(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (seed) {
			EnseignantsSeeder ensSeeder = new EnseignantsSeeder();
			ClassesSeeder clsSeeder = new ClassesSeeder();
			
			ensSeeder.seed();
			clsSeeder.seed();
			
		}
	}
}
