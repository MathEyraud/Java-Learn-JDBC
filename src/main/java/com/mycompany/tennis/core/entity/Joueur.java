package com.mycompany.tennis.core.entity;

public class Joueur {
	
	private Long id;
	private String nom;
	private String prenom;
	private Character sexe;
	
	public Joueur() {}
	
	public Joueur(String nom, String prenom, String sexe) {
		this.nom 	= nom;
		this.prenom = prenom;
		this.sexe 	= sexe.charAt(0);
	}
	
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
