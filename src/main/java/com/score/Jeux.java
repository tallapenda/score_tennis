/**
 * 
 */
package com.score;

import java.util.ArrayList;
import java.util.List;

import com.score.Joueur;

/**
 * @author Talla DIOP
 *
 */
public class Jeux {

	private Joueur joueur1;
	private Joueur joueur2;	
	private int winner;
	private List<int[]> scores;
	private int partie_plus = 1;
	private int Set_score_player1 = 0;
	private int Set_score_player2 = 0;
	private String gagnantSetScore = "";
	private int number_game = 6;
	
	//initialiser nom du joueur1 et joueur2
	public Jeux(String nom1, String nom2) 
	{
		this.joueur1 = new Joueur(nom1);
		this.joueur2 = new Joueur(nom2);
		this.reset();
	}

	//initialisation du jeux
	public void reset() {		
		this.joueur1.getScore().initierScore();		
		this.joueur2.getScore().initierScore();
		this.winner = 0;
		this.scores = new ArrayList<int[]>();
		int[] debutScore = {0, 0};
		scores.add(debutScore);		
	}

	// part of games
	public void play(int serviceWinner) 
	{
		// we test we are at the beginning of the game or at the end
		if (this.winner == 1 || this.winner == 2)
			throw new RuntimeException("Le jeu est fini ! (Game is Over !)");

		//we test input data
		if (serviceWinner != 1 && serviceWinner != 2)
			throw new IllegalArgumentException("la value en entré doit être 1 or 2");
		
		
		
		//we test if we are in the game plus case (extension: 3 games)
		if(this.joueur1.getScore().getValue() == 40 && this.joueur2.getScore().getValue() == 40) 
		{
			//--------Stage 1 part of the extension
			//player1
			if (serviceWinner == 1 && partie_plus ==1)
			{
				//on increment score which is always equal to 40
				this.joueur1.getScore().incrementer();
				// give advantage to player 1
				this.joueur1.getScore().avScore();
				// we increment the game
				partie_plus++;
			}
			
			//player2					
			if (serviceWinner == 2 && partie_plus ==1)
			{
				//on increment score which is always equal to 40
				this.joueur2.getScore().incrementer();
				// give advantage to player 1
				this.joueur1.getScore().avScore();
				//on increment
				partie_plus++;
			}
			
			
			//--------Stage 2 part of the extension
			
			//------------DEUCE-----
			
			//we test for player1 in the second slice of the plus game
			if (serviceWinner == 1 && partie_plus ==2)
			{
				if (this.joueur2.getScore().avScore().equals("ADV"))
				{
					// we declare the DEUCE for player1 and for player2
				this.joueur1.getScore().deuceScore();
				this.joueur2.getScore().deuceScore();
				partie_plus++;
				}
				else
				{
					//declare player1 winner
					this.setWinner(1);
					//declare player1 has the advantage
					this.joueur1.getScore().avScore();
				}
			}
			
			//we test for player2 in the second installment of the plus game
			if (serviceWinner == 2 && partie_plus ==2)
			{
				if (this.joueur1.getScore().avScore().equals("ADV"))
				{
					// we declare the DEUCE for player1 and for player2
				this.joueur1.getScore().deuceScore();
				this.joueur2.getScore().deuceScore();
				partie_plus++;
				}
				else
				{
					//declare player2 winner
					this.setWinner(2);
					//declare player2 has the advantage
					this.joueur2.getScore().avScore();
				}
			}
			
			
			//--------Stage 3 part of the extension
			
			//we test if player1 has won the game more
			if (serviceWinner == 1 && partie_plus ==3) 
			{
				//declare player1 winner
				this.setWinner(1);	
				//we give advantage to player1
				this.joueur1.getScore().avScore();
			}
			
			//we test if player2 has won the game more
			if (serviceWinner == 2 && partie_plus ==3) 
			{
				//declare player2 winner
				this.setWinner(2);
				//we give advantage to player2
				this.joueur2.getScore().avScore();
				
			}
			
			
		}
		else
		{
			//-----------SPRINT 1

			// we test if player 1 has won the game
		if (serviceWinner == 1) 
		{
			if (this.joueur1.getScore().getValue() == 40) {
				this.setWinner(1);
				
				//we test the number of games if greater than zero
				if(number_game >0)
				{
					
				//the Set score is awarded to player 1
				Set_score_player1++;
				
				
				//If one player1 reaches the Set Score of 6 and the player2 has a Set Score of 4 or less
				if(Set_score_player1 ==6 && Set_score_player2 <=4)
				{
					//we get name winner set score
					gagnantSetScore = this.setWinnerSteScore(Set_score_player2, this.joueur1.getNom());
					
					//we decrease the number of games
					number_game--;
				}
				
				//If one player1 reaches the Set Score of 6 and the player2 has a Set Score of 5
				if(Set_score_player1 ==6 && Set_score_player2 ==5)
				{
					//the number of games is increased by 1 point
					 number_game = number_game +1;
				}
				
			}
				
			} else {
				this.joueur1.getScore().incrementer();
			}
		} 
		// we test if player 2 has won the game
		else if (serviceWinner == 2) {
			if (this.joueur2.getScore().getValue() == 40) {
				this.setWinner(2);
				
				//we test the number of games if greater than zero
				if(number_game >0)
				{
					
				
				//the Set score is awarded to player 1
				Set_score_player2++;
				
				//If one player2 reaches the Set Score of 6 and the player1 has a Set Score of 4 or less
				if(Set_score_player2 ==6 && Set_score_player1 <=4)
				{
					//we get name winner set score
					gagnantSetScore = this.setWinnerSteScore(Set_score_player2, this.joueur2.getNom());
					
					//we decrease the number of games
					number_game--;
				}
				
				//If one player1 reaches the Set Score of 6 and the player2 has a Set Score of 5
				if(Set_score_player2 ==6 && Set_score_player1 ==5)
				{
					//the number of games is increased by 1 point
					 number_game = number_game +1;
				}
				
				
				}
				
			} else {
				this.joueur2.getScore().incrementer();
			}
		}
		
		}
		
		// update score
		int[] serviceScore = {this.joueur1.getScore().getValue(), this.joueur2.getScore().getValue()};
		this.scores.add(serviceScore);
	}

	
	
	//display scoreboard
	public String showScores() {
		StringBuilder Scoredujeux = new StringBuilder();		
		for (int[] serviceScore : scores) {
			Scoredujeux.append(this.joueur1.getNom() + " : " + serviceScore[0] + "\t-\t" + this.joueur2.getNom() + " : " + serviceScore[1] + "\n");
		}
		return Scoredujeux.toString() + " "+gagnantSetScore;
	}

	
	//set winner 
	public void setWinner(int winner) {
		if (winner != 1 && winner != 2)
			throw new IllegalArgumentException("the entry value for a winner must be 1 or 2");

		this.winner = winner;
	}

	//method returning winner
	public int getWinner() {
		return this.winner;
	}

	//return joueur1
	public Joueur getPlayer1() {
		return this.joueur1;
	}
	
	//return joueur2
	public Joueur getPlayer2() {
		return this.joueur2;
	}	
	
	
	   //set winner set score
		public String setWinnerSteScore(int num_set_score, String player) 
		{

			return "Player " + player +" wins the game " +num_set_score;
		}
	

}
