package com.mycompany.tennis.core.entity;

public class Tournoi {
	
	/* 
	 * ATTRIBUTS
	 */
	private Long id;
	private String nom;
	private String code;
	
	/** 
	 * METHODES
	 */
	@Override
    public String toString() {
        return "Tournoi{" 	+
                "id=" 		+ this.id 	+ " / " +
                "Nom="		+ this.nom 	+ " / " +
                "Code=" 	+ this.code	+
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
