package model.ship;

import model.Craft;
import model.Orientation;


/**
 * The Class Ship.
 * @auhor Alejandro Seguí Apellániz 48793265F
 * @version 11.0.8
 */
public abstract class Ship extends Craft {
	
	/**
	 * Instantiates a new ship.
	 *
	 * @param orientation the orientation
	 * @param symbol the symbol
	 * @param name the name
	 */
	public Ship(Orientation orientation, char symbol, String name) {
		super(orientation, symbol, name);
	}
}
