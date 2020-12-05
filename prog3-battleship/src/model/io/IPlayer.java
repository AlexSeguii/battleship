package model.io;

import model.Board;
import model.Coordinate;
import model.exceptions.BattleshipException;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;


/**
 * The Interface IPlayer.
 * @auhor Alejandro Seguí Apellániz 48793265F
 * @version 11.0.8
 */
public interface IPlayer {
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	String getName();
	
	/**
	 * Put crafts.
	 *
	 * @param b the b
	 * @throws InvalidCoordinateException the invalid coordinate exception
	 * @throws BattleshipIOException the battleship IO exception
	 * @throws NextToAnotherCraftException the next to another craft exception
	 * @throws OccupiedCoordinateException the occupied coordinate exception
	 */
	void putCrafts(Board b) throws InvalidCoordinateException,BattleshipIOException,NextToAnotherCraftException,OccupiedCoordinateException;
	
	/**
	 * Next shoot.
	 *
	 * @param b the b
	 * @return the coordinate
	 * @throws InvalidCoordinateException the invalid coordinate exception
	 * @throws BattleshipIOException the battleship IO exception
	 * @throws CoordinateAlreadyHitException the coordinate already hit exception
	 */
	Coordinate nextShoot(Board b) throws InvalidCoordinateException,BattleshipIOException,CoordinateAlreadyHitException;
}