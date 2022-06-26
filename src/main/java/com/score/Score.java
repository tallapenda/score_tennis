package com.score;

import java.util.stream.IntStream;

import com.score.InterfaceScore;



public class Score implements InterfaceScore {
	private int value;
	private String av;
	
	/**
	 * Constructor
	 */
	public Score() {
		this.initierScore();
	}
	
	//score initialization
	public void initierScore() {
        	this.value = 0;
    }
	
	//Incrementation score
	public void incrementer() {
		//table de valeurs
		int[] tableValues = {0, 15, 30, 40};
		
		//we test the input value
		if (!IntStream.of(tableValues).anyMatch(x -> x == this.value)) {
			throw new RuntimeException("score value non supportée");
		}
		
    	if (this.value == 0 || this.value == 15) {
    		this.value += 15;
    	} else if (this.value == 30 || this.value == 40) {//the score value at 30 and 40 is the same
    		this.value = 40;
    	}		
	}

	
	//methode getValue
	public int getValue() {
		return this.value;
	}
	
	
	//avantage score
		public String avScore() {
	        	return this.av = "ADV";
	    }
		
		//DEUCE score
				public String  deuceScore() {
			        	return this.av = "DEUCE";
			    }
}
