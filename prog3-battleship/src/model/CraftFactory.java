package model;

import model.aircraft.Bomber;
import model.aircraft.Fighter;
import model.aircraft.Transport;
import model.ship.Battleship;
import model.ship.Carrier;
import model.ship.Cruiser;
import model.ship.Destroyer;


/**
 * A factory for creating Craft objects.
 * @auhor Alejandro Seguí Apellániz 48793265F
 * @version 11.0.8
 */
public class CraftFactory {
	
	/**
	 * Creates a new Craft object.
	 *
	 * @param type the type
	 * @param orientation the orientation
	 * @return the craft
	 */
	public static Craft createCraft(String type, Orientation orientation) {
		Craft nave = null;
		switch(type) {
			case "Fighter":
				nave = new Fighter(orientation);
				break;
				
			case "Bomber":
				nave = new Bomber(orientation);
				break;
				
			case "Transport":
				nave = new Transport(orientation);
				break;
				
			case "Carrier":
				nave = new Carrier(orientation);
				break;
				
			case "Battleship":
				nave = new Battleship(orientation);
				break;
				
			case "Cruiser":
				nave = new Cruiser(orientation);
				break;
				
			case "Destroyer":
				nave = new Destroyer(orientation);
				break;
		
		}
		return nave;
	}

}
