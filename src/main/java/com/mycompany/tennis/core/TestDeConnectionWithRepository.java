package com.mycompany.tennis.core;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;

public class TestDeConnectionWithRepository {
	
	public static void main(String... args){
		
		// Création du repository
		JoueurRepositoryImpl joueurRepository 	= new JoueurRepositoryImpl();
		
		// -------------------------------- //
		// Récupération de tous les joueurs //
		// -------------------------------- //
		// Création de la liste pour stocker les joueurs
		List<Joueur> joueurs = new ArrayList<Joueur>();
		// récupération des joueurs
		joueurs = joueurRepository.getJoueurs();
		//Affichage des joueurs
		for (Joueur joueur : joueurs) {
			System.out.println(joueur);
		}
		
		// ---------------------------------- //
		// Récupération du joueur avec l'ID 2 //
		// ---------------------------------- //
		Joueur joueur1 = joueurRepository.getJoueurById(2L);
		System.out.println(joueur1);
		
		// ---------------------------- //
		// Création d'un nouveau joueur //
		// ---------------------------- //
		/*Joueur newJoueur1 = new Joueur("Ey","Math","H");
		System.out.println(newJoueur1);
		joueurRepository.create(newJoueur1);
		System.out.println(newJoueur1);*/

		// ------------------------ //
		// Modification d'un joueur //
		// ------------------------ //
		// Récupération du joueur
		/*Joueur joueur2 = joueurRepository.getJoueurById(46L);
		System.out.println(joueur2);
		
		// Modification des informations
		joueur2.setPrenom("Michel");
		joueur2.setNom("Delacourt");
		joueur2.setSexe('H');
		
		// Modification on DB
		joueurRepository.update(joueur2);*/
		
		// ----------------------- //
		// Suppression d'un joueur //
		// ----------------------- //
		/*joueurRepository.delete(47L);*/
		
	}
	
}
