package model.exceptions;

import model.Coordinate;


/**
 * The Class InvalidCoordinateException.
 */
public class InvalidCoordinateException extends BattleshipException {
	
	/**
	 * Instantiates a new invalid coordinate exception.
	 *
	 * @param c the c
	 */
	public InvalidCoordinateException(Coordinate c) {
		super(c);
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return super.getMessage() + " InvalidCoordinate";
	}

}
