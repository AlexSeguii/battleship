package model.score;

import java.util.Objects;

import model.io.IPlayer;

public abstract class Score<T> implements Comparable<Score<T>>{
	protected int score;
	private IPlayer player;
	public Score(IPlayer player) {
		Objects.requireNonNull(player);
		this.player = player;
		score = 0;
	}
	
	public int getScore() {
		return score;
	}
	
	public String toString() {
		return player.getName() + ": " + score;
	}
	/*
		this.compareTo(otro)
			< 0		this es menor que otro
			> 0		this es mayor que otro
			= 0		this y otro son iguales
	  
	 */
	@Override
	public int compareTo(Score<T> arg0) {
		if(getScore() > arg0.getScore()) {
			return -1;
		}
		
		else {
			if(getScore() < arg0.getScore()) {
				return 1;
			}
			
			else {
				return player.getName().compareTo(arg0.player.getName());
			}
		}	
	}
	public abstract void score(T sc);
}
