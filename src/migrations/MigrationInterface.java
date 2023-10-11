package migrations;

import java.sql.SQLException;

public interface MigrationInterface {
	public void migrate(Boolean remove) throws SQLException;
	public void up() throws SQLException;
	public void down() throws SQLException;
}
