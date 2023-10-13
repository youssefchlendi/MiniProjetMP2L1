package models;

import java.time.LocalDateTime;

public class Sceance {
	
	private Integer id;
	
	private LocalDateTime heureDebut;
	
	private LocalDateTime heureFin;
	
	private Classe classe;
	
	private Matiere matiere;
	
	private Enseignant enseignant;
	
	
	public Sceance() {
		super();
	}

	public Sceance(Integer id, LocalDateTime heureDebut, LocalDateTime heureFin, Classe classe, Matiere matiere,
			Enseignant enseignant) {
		super();
		this.id = id;
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

	public LocalDateTime getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(LocalDateTime heureDebut) {
		this.heureDebut = heureDebut;
	}

	public LocalDateTime getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(LocalDateTime heureFin) {
		this.heureFin = heureFin;
	}
	
}
