package model.exceptions.io;


import model.exceptions.BattleshipException;


/**
 * The Class BattleshipIOException.
 * @auhor Alejandro Seguí Apellániz 48793265F
 * @version 11.0.8
 */
public class BattleshipIOException extends BattleshipException {
	
	/** The message. */
	private String message;
	
	/**
	 * Instantiates a new battleship IO exception.
	 *
	 * @param m the m
	 */
	public BattleshipIOException(String m) {
		message = m;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return "IO exception: " + message;
	}

}
