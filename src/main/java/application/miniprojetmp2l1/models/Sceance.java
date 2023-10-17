package application.miniprojetmp2l1.models;

import java.time.LocalTime;

public class Sceance {

	private Integer id;

	private String jour;

	private LocalTime heureDebut;

	private LocalTime heureFin;

	private Classe classe;

	private Matiere matiere;

	private Enseignant enseignant;

	public Sceance() {
		super();
	}

	public Sceance(Integer id, String jour, LocalTime heureDebut, LocalTime heureFin, Classe classe, Matiere matiere,
			Enseignant enseignant) {
		super();
		this.id = id;
		this.jour = jour;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.classe = classe;
		this.matiere = matiere;
		this.enseignant = enseignant;
	}

	public Sceance(String jour, LocalTime heureDebut, LocalTime heureFin, Classe classe, Matiere matiere,
			Enseignant enseignant) {
		super();
		this.jour = jour;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.classe = classe;
		this.matiere = matiere;
		this.enseignant = enseignant;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Enseignant getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJour() {
		return jour;
	}

	public void setJour(String jour) {
		this.jour = jour;
	}

	public LocalTime getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(LocalTime heureDebut) {
		this.heureDebut = heureDebut;
	}

	public LocalTime getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(LocalTime heureFin) {
		this.heureFin = heureFin;
	}

}
