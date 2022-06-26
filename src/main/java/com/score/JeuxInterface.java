package com.score;



public interface JeuxInterface 
{
	public void reset();
	public void play(int stepWinner);
	public String voirScores();
	public void setGagnant(int winner);
	public int getGagnant();
	public Joueur getPlayer1();
	public Joueur getPlayer2();

}
