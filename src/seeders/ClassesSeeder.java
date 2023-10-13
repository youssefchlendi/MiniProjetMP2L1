package seeders;


import dao.ClassesDao;
import helpers.StringHelpers;
import models.Classe;

public class ClassesSeeder extends Seeders {

	ClassesDao dao = new ClassesDao();

	@Override
	public void seed() {
		for (int i = 0; i < count; i++) {
			Classe cls = new Classe(StringHelpers.generateRandomString(5), StringHelpers.generateRandomString(15));
			try {
				dao.add(cls);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
