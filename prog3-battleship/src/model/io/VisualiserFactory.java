package model.io;

import model.Game;


/**
 * A factory for creating Visualiser objects.
 * @auhor Alejandro Seguí Apellániz 48793265F
 * @version 11.0.8
 */
public class VisualiserFactory {
	
	/**
	 * Creates a new Visualiser object.
	 *
	 * @param n the n
	 * @param g the g
	 * @return the i visualiser
	 */
	public static IVisualiser createVisualiser(String n, Game g) {
		switch(n) {
			case "Console":
				return new VisualiserConsole(g);
			case "GIF":
				return new VisualiserGIF(g);
			default:
				return null;
		}
	}
}
