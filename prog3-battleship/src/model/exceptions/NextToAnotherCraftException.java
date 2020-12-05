package model.exceptions;

import model.Coordinate;


/**
 * The Class NextToAnotherCraftException.
 * @auhor Alejandro Seguí Apellániz 48793265F
 * @version 11.0.8
 */
public class NextToAnotherCraftException extends CoordinateException {
	
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
