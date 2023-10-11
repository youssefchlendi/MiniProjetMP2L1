package migrations;

import java.sql.SQLException;

public class Init {
	public void run() {
		EnseignantsTableMigration ensmgr = new EnseignantsTableMigration();
		try {
			ensmgr.migrate(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
