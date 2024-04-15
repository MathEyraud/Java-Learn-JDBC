package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;

public class EpreuveService {
	
	/* 
	 * ATTRIBUTS
	 */
	private EpreuveRepositoryImpl epreuveRepository;
	
	/*
	 * CONSTRUCTEURS
	 */
	public EpreuveService() {
		this.epreuveRepository = new EpreuveRepositoryImpl();
	}
	
	/** 
	 * METHODES
	 */
	public void createEpreuve(Epreuve epreuve) {
		epreuveRepository.create(epreuve);
	}	
	public Epreuve getEpreuveById(long idEpreuve) {
		return epreuveRepository.getEpreuveById(idEpreuve);
	}

}
