package model.io;

import java.awt.Color;
import java.io.File;
import java.util.Objects;

import model.Board;
import model.Game;
import model.exceptions.io.BattleshipIOException;
import model.io.gif.AnimatedGIF;
import model.io.gif.FrameGIF;


/**
 * The Class VisualiserGIF.
 * @auhor Alejandro Seguí Apellániz 48793265F
 * @version 11.0.8
 */
public class VisualiserGIF implements IVisualiser {
	
	/** The agif. */
	private AnimatedGIF agif;
	
	/** The game. */
	private Game game;	
	
	/**
	 * Instantiates a new visualiser GIF.
	 *
	 * @param g the g
	 */
	public VisualiserGIF(Game g) {
		Objects.requireNonNull(g);
		game = g;
		agif = new AnimatedGIF();
	}
	
	/**
	 * Show.
	 */
	@Override
	public void show() {
		//FrameGIF frame = new FrameGIF(w, h*2+1);
		int w,h;
		String s1 = game.getBoard1().show(false);
		String s2 = game.getBoard2().show(false);
		w = 0;
		while(s1.charAt(w) != '\n') {
			w++;
		}
		h = game.getBoard1().getSize();
		FrameGIF frame = new FrameGIF(w, h*2 + 1);
		int x = 0; //fil
		int y = 0; //col
		
		for(int i = 0; i < s1.length(); i++) {
			if(s1.charAt(i) == '\n') {
				x++;
				y=0;
			}
			else {
				try {
					switch(s1.charAt(i)) {
						case Board.WATER_SYMBOL:
							frame.printSquare(y, x, Color.BLUE);
							break;
						case Board.Board_SEPARATOR:
							frame.printSquare(y, x, Color.ORANGE);
							break;
						case Board.NOTSEEN_SYMBOL:
							frame.printSquare(y, x, Color.LIGHT_GRAY);
							break;
						case Board.HIT_SYMBOL:
							frame.printSquare(y, x, Color.RED);
							break;
						default :
							frame.printSquare(y, x, Color.RED);
							break;
					}
				}
				catch(BattleshipIOException e) {
				}
				y++;
				}
		}
				x++;
				y = 0;
				for(int i=0; i<w; i++) {
					try {
						frame.printSquare(y, x, Color.DARK_GRAY);
					}
					catch (BattleshipIOException e) {}		
					y++;
				}
				x++;
				y = 0;
				for(int i = 0; i < s2.length(); i++) {
					if(s2.charAt(i) == '\n') {
						x++;
						y = 0;
					}
					else {
						try {
							switch(s2.charAt(i)) {
								case Board.WATER_SYMBOL:
									frame.printSquare(y, x, Color.BLUE);
									break;
								case Board.Board_SEPARATOR:
									frame.printSquare(y, x, Color.ORANGE);
									break;
								case Board.NOTSEEN_SYMBOL:
									frame.printSquare(y, x, Color.LIGHT_GRAY);
									break;
								case Board.HIT_SYMBOL:
									frame.printSquare(y, x, Color.RED);
									break;
								default:
									frame.printSquare(y, x, Color.RED);
									break;
							}
						}
						catch(BattleshipIOException e) {}
						y++;
					}
				}
				try {
					agif.addFrame(frame);
				}
				catch (BattleshipIOException e) {
				}
	}

	/**
	 * Close.
	 */
	@Override
	public void close() {
		try {
			agif.saveFile(new File("files/output.gif"));
		}
		catch (BattleshipIOException e) {
		}

	}

}
