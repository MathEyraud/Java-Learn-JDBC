package com.mycompany.tennis.core.entity;

public class Epreuve {
	
	/* 
	 * ATTRIBUTS
	 */
	private Long 		id;
	private Short 		annee;
	private Character 	typeEpreuve;
	private Tournoi 	tournoi;
	
	//TODO: Supprimer cette id pour prendre l'objet
	private Long 		idTournoi;
	
	/** 
	 * METHODES
	 */
	//TODO: Supprimer l'id pour mettre uniquement l'objet tournoi.
	@Override
    public String toString() {
        return "Epreuve{" +
                "id=" 				+ this.id 			+ " / " +
                "Annee=" 			+ this.annee 		+ " / " +
                "Type d'épreuve=" 	+ this.typeEpreuve	+ " / " +
                "id Tournoi=" 		+ this.idTournoi	+ " / " +
                "Tournoi=" 			+ this.tournoi		+
                '}';
    }
	
	/** 
	 * GETTERS/SETTERS
	 * @return
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Short getAnnee() {
		return annee;
	}

	public void setAnnee(Short annee) {
		this.annee = annee;
	}

	public Character getTypeEpreuve() {
		return typeEpreuve;
	}

	public void setTypeEpreuve(Character typeEpreuve) {
		this.typeEpreuve = typeEpreuve;
	}

	public Tournoi getTournoi() {
		return tournoi;
	}

	public void setTournoi(Tournoi tournoi) {
		this.tournoi = tournoi;
	}

	public Long getIdTournoi() {
		return idTournoi;
	}

	public void setIdTournoi(Long idTournoi) {
		this.idTournoi = idTournoi;
	}
	
	

}
