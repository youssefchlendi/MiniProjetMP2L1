package application.miniprojetmp2l1.models;

public class Classe {
	
	private String matricule;
	
	private String nom;
	
	public Classe() {
		super();
	}

	public Classe(String matricule, String nom) {
		super();
		this.matricule = matricule;
		this.nom = nom;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
