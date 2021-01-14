package model.io;

import model.Board;
import model.CellStatus;
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
	public String getName();
	// Cuando sobrescribamos el metodo podremos lanzar cualquier excepcion que herede de BattleshipException.
		// Teoria tip: Cuando sobrescribimos un metodo, en el nuevo metodo no podemos lanzar excepciones que no se
		// lancen en el metodo sobrescrito.
		public void putCrafts(Board b) throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException, BattleshipIOException;
		public Coordinate nextShoot(Board b) throws InvalidCoordinateException, CoordinateAlreadyHitException, BattleshipIOException;
		public CellStatus getLastShotStatus();
}