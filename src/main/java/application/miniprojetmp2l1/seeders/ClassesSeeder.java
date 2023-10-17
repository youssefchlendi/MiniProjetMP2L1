package application.miniprojetmp2l1.seeders;

import application.miniprojetmp2l1.dao.ClassesDao;
import application.miniprojetmp2l1.models.Classe;

public class ClassesSeeder extends Seeders {

	ClassesDao dao = new ClassesDao();

	@Override
	public void seed() {
		try {
			dao.add(new Classe("1A", "Première Année A"));
			dao.add(new Classe("2A", "Deuxième Année A"));
			dao.add(new Classe("3A", "Troisième Année A"));
			dao.add(new Classe("4A", "Quatrième Année A"));
			dao.add(new Classe("5A", "Cinquième Année A"));
			dao.add(new Classe("6A", "Sixième Année A"));
			dao.add(new Classe("7A", "Septième Année A"));
			dao.add(new Classe("8A", "Huitième Année A"));
			dao.add(new Classe("9A", "Neuvième Année A"));
			dao.add(new Classe("10A", "Dixième Année A"));
			dao.add(new Classe("11A", "Onzième Année A"));
			dao.add(new Classe("12A", "Douzième Année A"));
			dao.add(new Classe("13A", "Treizième Année A"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
