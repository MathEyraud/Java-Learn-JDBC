package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;

public class MatchService {
	
	/* 
	 * ATTRIBUTS
	 */
	private MatchRepositoryImpl matchRepository;
	private ScoreRepositoryImpl scoreRepository;
	
	/*
	 * CONSTRUCTEURS
	 */
	public MatchService() {
		this.matchRepository = new MatchRepositoryImpl();
		this.scoreRepository = new ScoreRepositoryImpl();
	}
	
	/** 
	 * METHODES
	 */
	public void createMatch(Match match) {
		matchRepository.create(match);
		scoreRepository.create(match.getScore());
	}	
}
