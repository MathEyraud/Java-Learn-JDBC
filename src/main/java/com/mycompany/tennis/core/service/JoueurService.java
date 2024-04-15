package com.mycompany.tennis.core.service;

import java.util.List;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;

public class JoueurService {
	
	/* 
	 * ATTRIBUTS
	 */
	private JoueurRepositoryImpl joueurRepository;
	
	/*
	 * CONSTRUCTEURS
	 */
	public JoueurService() {
		this.joueurRepository = new JoueurRepositoryImpl();
	}
	
	/** 
	 * METHODES
	 */
	public void createJoueur(Joueur joueur) {
		joueurRepository.create(joueur);
	}
	
	public void updateJoueur(Joueur joueur) {
		joueurRepository.update(joueur);
	}
	
	public void deleteJoueur(long idJoueur) {
		joueurRepository.delete(idJoueur);
	}
	
	public List<Joueur> getJoueurs() {
		return joueurRepository.getJoueurs();
	}
	
	public Joueur getJoueurById(long idJoueur) {
		return joueurRepository.getJoueurById(idJoueur);
	}
	
}
