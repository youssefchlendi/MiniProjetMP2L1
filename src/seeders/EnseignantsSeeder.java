package seeders;

import java.sql.SQLException;

import dao.EnseignantDao;
import helpers.StringHelpers;
import models.Enseignant;

public class EnseignantsSeeder extends Seeders {

	EnseignantDao dao = new EnseignantDao();

	@Override
	public void seed() {
		for (int i = 0; i < count; i++) {
			Enseignant ens = new Enseignant(StringHelpers.generateRandomString(5),
					StringHelpers.generateRandomString(15), StringHelpers.generateRandomString(25));
			try {
				dao.add(ens);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
