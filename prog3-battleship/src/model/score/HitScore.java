package model.score;

import model.CellStatus;
import model.io.IPlayer;

public class HitScore extends Score<CellStatus>{
	public HitScore(IPlayer p) {
		super(p);
	}
	// me pasan el estado de la celda donde he disparado
	// y en funcion del estado pues aumento la puntuacion
	public void score(CellStatus arg0) {
		if(arg0 == CellStatus.DESTROYED || arg0 == CellStatus.HIT ) {
			score++;
		}
	}
}
