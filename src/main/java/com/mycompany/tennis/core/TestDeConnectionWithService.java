package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.service.EpreuveService;
import com.mycompany.tennis.core.service.JoueurService;
import com.mycompany.tennis.core.service.MatchService;
import com.mycompany.tennis.core.service.ScoreService;

public class TestDeConnectionWithService {
	
	public static void main(String... args){
		
		// Création du service
		JoueurService 	joueurService 	= new JoueurService();
		MatchService 	matchService 	= new MatchService();
		EpreuveService 	epreuveService 	= new EpreuveService();
						
		// ---------------------------- //
		// Création d'un nouveau joueur //
		// ---------------------------- //
		/*// Création du joueur
		Joueur newJoueur = new Joueur("Michel", "Ribz", "H");
		
		// Affichage du joueur avant l'ajout dans la DB (Sans id)
		System.out.println(newJoueur);
		
		// Ajout du joueur dans la DB
		joueurService.createJoueur(newJoueur);
		
		// Affichage du joueur après l'ajout dans la DB (Avec id)
		System.out.println(newJoueur);*/
		
		// --------------------------- //
		// Création d'un nouveau match //
		// --------------------------- //
		// Création des données du match (Epreuve (+tournoi) & Joueurs)
		
		// Récupération de l'épreuve
		Epreuve epreuve = epreuveService.getEpreuveById(10L);
		System.out.println(epreuve);
		
		// Récupération des joueurs
		Joueur vainqueur = joueurService.getJoueurById(32L);
		System.out.println(vainqueur);
		
		Joueur finaliste = joueurService.getJoueurById(34L);
		System.out.println(finaliste);
		
		// Création du score
		Score score = new Score((byte)3,(byte)4,(byte)6);
		
		// Création du match
		Match match = new Match(epreuve, vainqueur, finaliste);
		match.setScore(score);
		score.setMatch(match);
		
		// Affichage du match avant l'ajout dans la DB (Sans id)
		System.out.println(match);
		
		// Ajout du match dans la DB
		matchService.createMatch(match);
		
		// Affichage du match après l'ajout dans la DB (Avec id)
		System.out.println(match);
		
	}
	
}
