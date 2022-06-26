package com.score;




public class Joueur implements JoueurInterface {
	private String nom;
	private Score score;

	//constructeur
	public Joueur(String name) {
		this.nom = name;
		this.score = new Score();
	}

	//return player name
	public String getNom() {
		return this.nom;
	}

	//return player score
	public Score getScore() {
		return this.score;
	}
}
