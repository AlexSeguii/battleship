package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;


/**
 * The Class Board.
 */
public abstract class Board {
	
	/** The Constant Board_SEPARATOR. */
	public static final char Board_SEPARATOR = '|';
	
	/** The Constant HIT_SYMBOL. */
	public static final char HIT_SYMBOL = '•';
	
	/** The Constant WATER_SYMBOL. */
	public static final char WATER_SYMBOL = ' ';
	
	/** The Constant NOTSEEN_SYMBOL. */
	public static final char NOTSEEN_SYMBOL = '?';
	
	/** The Constant MAX_BOARD_SIZE. */
	private static final int MAX_BOARD_SIZE = 20;
	
	/** The Constant MIN_BOARD_SIZE. */
	private static final int MIN_BOARD_SIZE = 5;
	
	/** The board. */
	private Map<Coordinate, Craft> board;
	
	/** The seen. */
	private Set<Coordinate> seen;
	
	/** The num crafts. */
	private int numCrafts;
	
	/** The destroyed crafts. */
	private int destroyedCrafts;
	
	/** The size. */
	private int size;

	/**
	 * Instantiates a new board.
	 *
	 * @param size the size
	 */
	public Board(int size) {
		if(size > MAX_BOARD_SIZE || size < MIN_BOARD_SIZE) {
			throw new IllegalArgumentException("Wrong board size");
		}
		else {
			this.size = size;
		}
		
		board = new HashMap<Coordinate, Craft>();
		seen = new HashSet<Coordinate>();
		numCrafts = 0;
		destroyedCrafts = 0;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Adds the craft.
	 *
	 * @param craft the craft
	 * @param position the position
	 * @return true, if successful
	 * @throws InvalidCoordinateException the invalid coordinate exception
	 * @throws OccupiedCoordinateException the occupied coordinate exception
	 * @throws NextToAnotherCraftException the next to another craft exception
	 */
	public boolean addCraft(Craft craft, Coordinate position) throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException {
		boolean added = false;
		Set<Coordinate> occupied = craft.getAbsolutePositions(position);
		
		added = true;
		for(Coordinate c : occupied) {
			if(checkCoordinate(c) == false) {
				throw new InvalidCoordinateException(c);
			}
		}
		added = true;
		for(Coordinate c : occupied) {
			if(board.containsKey(c)) {
				throw new OccupiedCoordinateException(c);
			}
		}
		added = true;
		for(Coordinate c : getNeighborhood(craft, position)) {
			if(board.containsKey(c)) {
				throw new NextToAnotherCraftException(c);
			}
		}
		numCrafts++;
		for(Coordinate c : occupied) {
			board.put(c, craft);
		}
		craft.setPosition(position.copy());
	
		return added;
	}
	
	/**
	 * Gets the craft.
	 *
	 * @param c the c
	 * @return the craft
	 */
	public Craft getCraft(Coordinate c) {
		return board.get(c);
	}
	
	/**
	 * Checks if is seen.
	 *
	 * @param c the c
	 * @return true, if is seen
	 */
	public boolean isSeen(Coordinate c) {
		return seen.contains(c);
	}
	
	/**
	 * Hit.
	 *
	 * @param c the c
	 * @return the cell status
	 * @throws InvalidCoordinateException the invalid coordinate exception
	 * @throws CoordinateAlreadyHitException the coordinate already hit exception
	 */
	public CellStatus hit(Coordinate c) throws InvalidCoordinateException, CoordinateAlreadyHitException {
		CellStatus enumerado;
		
		//Coordenada se encuentra en el tablero
		if(checkCoordinate(c)) {
			if(board.containsKey(c) == false) {
				enumerado = CellStatus.WATER;
				seen.add(c.copy());
			}
			else {
				seen.add(c.copy());
				Craft craft = board.get(c);
				if(craft.isHit(c) == true) {
					throw new CoordinateAlreadyHitException(c);
				}
				craft.hit(c);
				if(craft.isShotDown() == false) {
					enumerado = CellStatus.HIT;
				}
				else {
					enumerado = CellStatus.DESTROYED;
					destroyedCrafts++;
					for(Coordinate o : getNeighborhood(craft)) {
						seen.add(o.copy());
					}
				}
			}
		}
		else {
			throw new InvalidCoordinateException(c);
		}
		return enumerado;
	}
	
	/**
	 * Are all crafts destroyed.
	 *
	 * @return true, if successful
	 */
	public boolean areAllCraftsDestroyed() {
		return numCrafts == destroyedCrafts;
	}
	
	/**
	 * Gets the neighborhood.
	 *
	 * @param craft the craft
	 * @param position the position
	 * @return the neighborhood
	 */
	public Set<Coordinate> getNeighborhood(Craft craft, Coordinate position){
		Set<Coordinate> vecinas = new HashSet<Coordinate>();
		Set<Coordinate> ady;
		Set<Coordinate> ocuppied = craft.getAbsolutePositions(position);
		Objects.requireNonNull(craft);
		Objects.requireNonNull(position);
		
		for(Coordinate c : ocuppied) {
			ady = c.adjacentCoordinates();
			for(Coordinate a : ady) {
				if(checkCoordinate(a) && ocuppied.contains(a) == false) {
					vecinas.add(a);
				}
			}
		}
		return vecinas;
	}
	
	/**
	 * Gets the neighborhood.
	 *
	 * @param craft the craft
	 * @return the neighborhood
	 */
	public Set<Coordinate> getNeighborhood(Craft craft){
		return getNeighborhood(craft, craft.getPosition());
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return "Board " + size + "; crafts: " + numCrafts + "; destroyed: " + destroyedCrafts;
	}
	
	/**
	 * Check coordinate.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	abstract public boolean checkCoordinate(Coordinate c);
	
	/**
	 * Show.
	 *
	 * @param unveil the unveil
	 * @return the string
	 */
	public abstract String show(boolean unveil);

}