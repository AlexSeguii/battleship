package model.exceptions;

import model.Coordinate;


/**
 * The Class OccupiedCoordinateException.
 */
public class OccupiedCoordinateException extends BattleshipException {
	
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
