package model.exceptions;

import model.Coordinate;


/**
 * The Class BattleshipException.
 */
public abstract class BattleshipException extends Exception {
	
	/** The c. */
	private Coordinate c;
	
	/**
	 * Instantiates a new battleship exception.
	 *
	 * @param c the c
	 */
	public BattleshipException(Coordinate c) {
		this.c = c;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return "Error: " + c.toString();
	}
}
