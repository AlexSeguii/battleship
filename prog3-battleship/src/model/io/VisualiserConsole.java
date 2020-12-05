package model.io;

import java.util.Objects;

import model.Game;

/**
 * The Class VisualiserConsole.
 * @auhor Alejandro Seguí Apellániz 48793265F
 * @version 11.0.8
 */
public class VisualiserConsole implements IVisualiser {
	
	/** The game. */
	private Game game;
	
	/**
	 * Instantiates a new visualiser console.
	 *
	 * @param g the g
	 */
	public VisualiserConsole(Game g) {
		Objects.requireNonNull(g);
		game = g;
	}

	/**
	 * Show.
	 */
	@Override
	public void show() {
		System.out.println(game.toString());
	}

	/**
	 * Close.
	 */
	@Override
	public void close() {}

}
