package com.mycompany.tennis.core.entity;

public class Joueur {
	
	/** 
	 * ATTRIBUTS
	 */
	private Long id;
	private String nom;
	private String prenom;
	private Character sexe;
	
	/** 
	 * CONSTRUCTEUR
	 */
	public Joueur() {}
	
	public Joueur(String nom, String prenom, String sexe) {
		this.nom 	= nom;
		this.prenom = prenom;
		this.sexe 	= sexe.charAt(0);
	}
	
	/** 
	 * METHODES
	 */
	@Override
    public String toString() {
        return "Joueur{" +
                "id=" 		+ this.id 		+ " / " +
                "nom=" 		+ this.nom 		+ " / " +
                "prenom=" 	+ this.prenom 	+ " / " +
                "sexe=" 	+ this.sexe 	+
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
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Character getSexe() {
		return sexe;
	}
	public void setSexe(Character sexe) {
		this.sexe = sexe;
	}
	
	
	

}
