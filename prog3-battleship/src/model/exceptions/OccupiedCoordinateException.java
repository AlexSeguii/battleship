package model.exceptions;

import model.Coordinate;


/**
 * The Class OccupiedCoordinateException.
 * @auhor Alejandro Seguí Apellániz 48793265F
 * @version 11.0.8
 */
public class OccupiedCoordinateException extends CoordinateException {
	
	/**
	 * Instantiates a new occupied coordinate exception.
	 *
	 * @param c the c
	 */
	public OccupiedCoordinateException(Coordinate c) {
		super(c);
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return super.getMessage() + " Occupied Coordinate";
	}
}
