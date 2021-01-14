package model.score;

import model.Craft;
import model.io.IPlayer;

public class CraftScore extends Score<Craft>{
	public CraftScore(IPlayer p) {
		super(p);
	}

	/**
	 * En funcion del tipo del barco se añade mas o menos puntuacion.
	 * 
	 * @param sc Barco a puntuar
	 */
	@Override
	public void score(Craft sc) {
		score += sc.getValue();
	}
}
