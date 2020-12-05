package model.exceptions;

import model.Coordinate;


/**
 * The Class CoordinateException.
 * @auhor Alejandro Seguí Apellániz 48793265F
 * @version 11.0.8
 */
public class CoordinateException extends BattleshipException {
	
	/** The c. */
	private Coordinate c;
	
	/**
	 * Instantiates a new coordinate exception.
	 *
	 * @param c the c
	 */
	public CoordinateException(Coordinate c) {
		this.c = c;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return "ERROR: " + c.toString();
	}
}
