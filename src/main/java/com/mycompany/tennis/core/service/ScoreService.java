package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;

public class ScoreService {
	
	/* 
	 * ATTRIBUTS
	 */
	private ScoreRepositoryImpl scoreRepository;
	
	/*
	 * CONSTRUCTEURS
	 */
	public ScoreService() {
		this.scoreRepository = new ScoreRepositoryImpl();
	}
	
	/** 
	 * METHODES
	 */
	public void createScore(Score score) {
		scoreRepository.create(score);
	}	
}
