package model.aircraft;

import model.Craft;
import model.Orientation;

/**
 * The Class Aircraft.
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
