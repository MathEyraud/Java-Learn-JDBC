package com.mycompany.tennis.core.entity;

public class Match {
	
	/* 
	 * ATTRIBUTS
	 */
	private Long id;
	private Epreuve epreuve;
	private Joueur vainqueur;
	private Joueur finaliste;
	
	private Score score;
	
	/*
	 * CONSTRUCTEURS
	 */
	public Match(Epreuve epreuve, Joueur vainqueur, Joueur finaliste) {
		this.epreuve 	= epreuve;
		this.vainqueur 	= vainqueur;
		this.finaliste 	= finaliste;
	}
	
	/** 
	 * METHODES
	 */
	@Override
    public String toString() {
        return "Match{" +
                "id=" 			+ this.id 				+ " / " +
                "id Epreuve=" 	+ this.epreuve.getId() 	+ " / " +
                "id Vainqueur=" + this.vainqueur.getId()+ " / " +
                "id Finaliste=" + this.finaliste.getId()+ " / " +
                "Score=" 		+ this.score 			+
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
	public Epreuve getEpreuve() {
		return epreuve;
	}
	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}
	public Joueur getVainqueur() {
		return vainqueur;
	}
	public void setVainqueur(Joueur vainqueur) {
		this.vainqueur = vainqueur;
	}
	public Joueur getFinaliste() {
		return finaliste;
	}
	public void setFinaliste(Joueur finaliste) {
		this.finaliste = finaliste;
	}
	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}
	
}
