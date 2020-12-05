package model.exceptions;

import model.Coordinate;


/**
 * The Class CoordinateAlreadyHitException.
 * @auhor Alejandro Seguí Apellániz 48793265F
 * @version 11.0.8
 */
public class CoordinateAlreadyHitException extends CoordinateException {
	
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
