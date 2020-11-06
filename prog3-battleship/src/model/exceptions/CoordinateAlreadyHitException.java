package model.exceptions;

import model.Coordinate;


/**
 * The Class CoordinateAlreadyHitException.
 */
public class CoordinateAlreadyHitException extends BattleshipException {
	
	/**
	 * Instantiates a new coordinate already hit exception.
	 *
	 * @param c the c
	 */
	public CoordinateAlreadyHitException(Coordinate c) {
		super(c);
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return super.getMessage() + " CoordinateAlteadyHit";
	}

}
