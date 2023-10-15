package seeders;

import java.time.LocalTime;
import java.util.List;
import application.Storage;
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
			List<Matiere> matieres = mdao.getAll();
			List<Classe> classes = cdao.getAll();
			List<Enseignant> enseignants = edao.getAll();

			for (Classe classe : classes) {
				for (String jour : Storage.Sceance.days) {
					LocalTime heureDebut = LocalTime.of(8, 0);
					LocalTime heureFin = heureDebut.plusHours(1);

					while (heureFin.getHour() < 13 && heureDebut.getHour() >= 8) {
						// if(heureDebut.getHour() == 11 || heureDebut.getHour() == 12) {
						// heureDebut = heureDebut.plusHours(1);
						// heureFin = heureDebut.plusHours(1);
						// continue;
						// }

						// Sélectionnez aléatoirement une matière et un enseignant
						Matiere matiere = matieres.get((int) (Math.random() * matieres.size()));
						Enseignant enseignant = enseignants.get((int) (Math.random() * enseignants.size()));

						Sceance sceance = new Sceance(jour, heureDebut, heureFin, classe, matiere, enseignant);
						dao.add(sceance);
						heureDebut = heureDebut.plusHours(1);
						heureFin = heureDebut.plusHours(1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
