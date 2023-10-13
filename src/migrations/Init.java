package migrations;

import java.sql.SQLException;

import seeders.ClassesSeeder;
import seeders.EnseignantsSeeder;
import seeders.MatieresSeeder;

public class Init {
	public void run(Boolean seed) {
		EnseignantsTableMigration ensmgr = new EnseignantsTableMigration();
		ClassesTableMigration clsmgr = new ClassesTableMigration();
		MatieresTableMigration mtrmgr = new MatieresTableMigration();
		SceancesTableMigration scsmgr = new SceancesTableMigration();
		try {
			ensmgr.migrate(true);
			clsmgr.migrate(true);
			mtrmgr.migrate(true);
			scsmgr.migrate(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (seed) {
			EnseignantsSeeder ensSeeder = new EnseignantsSeeder();
			ClassesSeeder clsSeeder = new ClassesSeeder();
			MatieresSeeder mtrSeeder = new MatieresSeeder();
			
			ensSeeder.seed();
			clsSeeder.seed();
			mtrSeeder.seed();
		}
	}
}
