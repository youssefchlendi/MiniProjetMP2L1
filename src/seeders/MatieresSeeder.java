package seeders;

import dao.MatieresDao;
import helpers.StringHelpers;
import models.Matiere;

public class MatieresSeeder extends Seeders {

	MatieresDao dao = new MatieresDao();

	@Override
	public void seed() {
		try {
			dao.add(new Matiere("001", "Mathématiques"));
			dao.add(new Matiere("002", "Français"));
			dao.add(new Matiere("003", "Histoire"));
			dao.add(new Matiere("004", "Sciences"));
			dao.add(new Matiere("005", "Anglais"));
			dao.add(new Matiere("006", "Physique"));
			dao.add(new Matiere("007", "Chimie"));
			dao.add(new Matiere("008", "Biologie"));
			dao.add(new Matiere("009", "Géographie"));
			dao.add(new Matiere("010", "Éducation Physique"));
			dao.add(new Matiere("011", "Informatique"));
			dao.add(new Matiere("012", "Arts Plastiques"));
			dao.add(new Matiere("013", "Musique"));
			dao.add(new Matiere("014", "Économie"));
			dao.add(new Matiere("015", "Philosophie"));
			dao.add(new Matiere("016", "Sociologie"));
			dao.add(new Matiere("017", "Psychologie"));
			dao.add(new Matiere("018", "Langues Étrangères"));
			dao.add(new Matiere("019", "Droit"));
			dao.add(new Matiere("020", "Marketing"));
			dao.add(new Matiere("021", "Management"));
			dao.add(new Matiere("022", "Comptabilité"));
			dao.add(new Matiere("023", "Finance"));
			dao.add(new Matiere("024", "Gestion de Projet"));
			dao.add(new Matiere("025", "Ressources Humaines"));
			dao.add(new Matiere("026", "Médecine"));
			dao.add(new Matiere("027", "Dentisterie"));
			dao.add(new Matiere("028", "Pharmacie"));
			dao.add(new Matiere("029", "Vétérinaire"));
			dao.add(new Matiere("030", "Droit"));
			dao.add(new Matiere("031", "Art Culinaire"));
			dao.add(new Matiere("032", "Architecture"));
			dao.add(new Matiere("033", "Géologie"));
			dao.add(new Matiere("034", "Astronomie"));
			dao.add(new Matiere("035", "Psychiatrie"));
			dao.add(new Matiere("036", "Santé Publique"));
			dao.add(new Matiere("037", "Théologie"));
			dao.add(new Matiere("038", "Journalisme"));
			dao.add(new Matiere("039", "Publicité"));
			dao.add(new Matiere("040", "Relations Publiques"));
			dao.add(new Matiere("041", "Cinéma"));
			dao.add(new Matiere("042", "Théâtre"));
			dao.add(new Matiere("043", "Danse"));
			dao.add(new Matiere("044", "Design"));
			dao.add(new Matiere("045", "Mode"));
			dao.add(new Matiere("046", "Écriture Créative"));
			dao.add(new Matiere("047", "Études Religieuses"));
			dao.add(new Matiere("048", "Éthique"));
			dao.add(new Matiere("049", "Environnement"));
			dao.add(new Matiere("050", "Agriculture"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
