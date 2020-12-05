package model.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import model.exceptions.io.BattleshipIOException;




/**
 * A factory for creating Player objects.
 * @auhor Alejandro Seguí Apellániz 48793265F
 * @version 11.0.8
 */
public class PlayerFactory {
	
	/**
	 * Creates a new Player object.
	 *
	 * @param name the name
	 * @param s the s
	 * @return the i player
	 * @throws BattleshipIOException the battleship IO exception
	 */
	public static IPlayer createPlayer(String name, String s) throws BattleshipIOException{
		BufferedReader buffer;
		FileReader file;
		IPlayer pl = null;
		long semilla;
		
		if(s.contains(".") || s.contains("/") || s.contains("\\")) {
			try {
				file = new FileReader(s);
				buffer = new BufferedReader(file);
				pl = new PlayerFile(name, buffer);
			}
			catch(FileNotFoundException e) {
				throw new BattleshipIOException("FileNotFoundException ");
			}
		}
		else {
			try {
				semilla = Long.parseLong(s);
				pl = new PlayerRandom(name, semilla);
			}
			catch(NumberFormatException e) {}
		}
		return pl;
	}
}
