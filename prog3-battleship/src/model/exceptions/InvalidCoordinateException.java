package model.exceptions;

import model.Coordinate;


/**
 * The Class InvalidCoordinateException.
 * @auhor Alejandro Seguí Apellániz 48793265F
 * @version 11.0.8
 */
public class InvalidCoordinateException extends CoordinateException {
	
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
