package model.aircraft;

import model.Craft;
import model.Orientation;

/**
 * The Class Aircraft.
 * @auhor Alejandro Seguí Apellániz 48793265F
 * @version 11.0.8
 * 
 */
public abstract class Aircraft extends Craft {
	
	/**
	 * Instantiates a new aircraft.
	 *
	 * @param orientation the orientation
	 * @param symbol the symbol
	 * @param name the name
	 */
	public Aircraft(Orientation orientation, char symbol, String name) {
		super(orientation, symbol, name);
	}
}
