package models;

import java.time.LocalDateTime;

public class Sceance {
	
	private String id;
	
	private LocalDateTime heureDebut;
	
	private LocalDateTime heureFin;
	
	public Sceance() {
		super();
	}

	public Sceance(String id, LocalDateTime heureDebut, LocalDateTime heureFin) {
		super();
		this.id = id;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
