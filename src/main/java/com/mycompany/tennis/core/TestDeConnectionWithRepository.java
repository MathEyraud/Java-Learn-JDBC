package com.mycompany.tennis.core;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;

public class TestDeConnectionWithRepository {
	
	public static void main(String... args){
		
		// Création du repository
		JoueurRepositoryImpl joueurRepository = new JoueurRepositoryImpl();
		
		// ---------------------------------- //
		// Récupération du joueur avec l'ID 2 //
		// ---------------------------------- //
		/*Joueur joueur1 = joueurRepository.getJoueurById(2L);
		System.out.println(joueur1.getNom() + " " + joueur1.getPrenom());*/
		
		// ---------------------------- //
		// Création d'un nouveau joueur //
		// ---------------------------- //
		/*Joueur newJoueur1 = new Joueur("Ey","Math","H");
		joueurRepository.create(newJoueur1);*/

		// ---------------------- //
		// Modification du joueur //
		// ---------------------- //
		// Récupération du joueur
		/*Joueur joueur2 = joueurRepository.getJoueurById(46L);
		System.out.println("Récupération : " + joueur2.getNom() + " " + joueur2.getPrenom() + " " + joueur2.getSexe());
		
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
		
		// -------------------------------- //
		// Récupération de tous les joueurs //
		// -------------------------------- //
		/*List<Joueur> joueurs = new ArrayList<Joueur>();
		joueurs = joueurRepository.getJoueurs();
		for (Joueur joueur : joueurs) {
			System.out.println(joueur.getId() + " " + joueur.getNom() + " " + joueur.getPrenom() + " " + joueur.getSexe());
		}*/
	}
	
}
