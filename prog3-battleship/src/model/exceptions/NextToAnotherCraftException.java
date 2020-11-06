package model.exceptions;

import model.Coordinate;


/**
 * The Class NextToAnotherCraftException.
 */
public class NextToAnotherCraftException extends BattleshipException {
	
	/**
	 * Instantiates a new next to another craft exception.
	 *
	 * @param c the c
	 */
	public NextToAnotherCraftException(Coordinate c) {
		super(c);
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return super.getMessage() + " NextToAnotherCraft";
	}
}
