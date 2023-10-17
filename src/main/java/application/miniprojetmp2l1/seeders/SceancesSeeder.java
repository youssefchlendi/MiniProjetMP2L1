package application.miniprojetmp2l1.seeders;

import java.time.LocalTime;
import java.util.List;
import application.miniprojetmp2l1.Storage;
import application.miniprojetmp2l1.dao.ClassesDao;
import application.miniprojetmp2l1.dao.EnseignantDao;
import application.miniprojetmp2l1.dao.MatieresDao;
import application.miniprojetmp2l1.dao.SceancesDao;
import application.miniprojetmp2l1.models.Classe;
import application.miniprojetmp2l1.models.Enseignant;
import application.miniprojetmp2l1.models.Matiere;
import application.miniprojetmp2l1.models.Sceance;

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
