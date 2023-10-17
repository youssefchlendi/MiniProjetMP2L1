package application.miniprojetmp2l1.models;

public class Matiere {
	
	private String id;
	
	private String nom;
	
	public Matiere() {
		super();
	}

	public Matiere(String id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
