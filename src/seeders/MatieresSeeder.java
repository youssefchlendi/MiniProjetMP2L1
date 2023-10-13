package seeders;


import dao.MatieresDao;
import helpers.StringHelpers;
import models.Matiere;

public class MatieresSeeder extends Seeders {

	MatieresDao dao = new MatieresDao();

	@Override
	public void seed() {
		for (int i = 0; i < count; i++) {
			Matiere cls = new Matiere(StringHelpers.generateRandomString(5), StringHelpers.generateRandomString(15));
			try {
				dao.add(cls);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
