package seeders;

import java.time.LocalDateTime;
import java.util.List;

import dao.ClassesDao;
import dao.EnseignantDao;
import dao.MatieresDao;
import dao.SceancesDao;
import models.Classe;
import models.Enseignant;
import models.Matiere;
import models.Sceance;

public class SceancesSeeder extends Seeders {

	SceancesDao dao = new SceancesDao();
	MatieresDao mdao = new MatieresDao();
	ClassesDao cdao = new ClassesDao();
	EnseignantDao edao = new EnseignantDao();

	@Override
	public void seed() {
		try {
			for (int i = 0; i < count; i++) {
				List<Matiere> matieres = mdao.getAll();
				List<Classe> classes = cdao.getAll();
				List<Enseignant> enseignants = edao.getAll();
				
				Sceance cls = new Sceance(LocalDateTime.now(), LocalDateTime.now(), classes.get(i),matieres.get(i), enseignants.get(i));
				dao.add(cls);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
