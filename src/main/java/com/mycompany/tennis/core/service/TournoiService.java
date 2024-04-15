package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;

public class TournoiService {
	
	/* 
	 * ATTRIBUTS
	 */
	private TournoiRepositoryImpl tournoiRepository;
	
	/*
	 * CONSTRUCTEURS
	 */
	public TournoiService() {
		this.tournoiRepository = new TournoiRepositoryImpl();
	}
	
	/** 
	 * METHODES
	 */
	public void createTournoi(Tournoi tournoi) {
		tournoiRepository.create(tournoi);
	}	
}
