package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class Board.
 * @auhor Alejandro Seguí Apellániz 48793265F
 * @version 11.0.8
 */
public class Board {
	
	/** The Constant HIT_SYMBOL. */
	public final static char HIT_SYMBOL = '•';
	
	/** The Constant WATER_SYMBOL. */
	public final static char WATER_SYMBOL = ' ';
	
	/** The Constant NOTSEEN_SYMBOL. */
	public final static char NOTSEEN_SYMBOL = '?';
	
	/** The Constant MAX_BOARD_SIZE. */
	private final static int MAX_BOARD_SIZE = 20;
	
	/** The Constant MIN_BOARD_SIZE. */
	private final static int MIN_BOARD_SIZE = 5;
	
	/** The size. */
	private int size;
	
	/** The num crafts. */
	private int numCrafts;
	
	/** The destroyed crafts. */
	private int destroyedCrafts;
	
	/** The seen. */
	private Set<Coordinate> seen;
	
	/** The board. */
	private Map<Coordinate, Ship> board;
	
	/**
	 * Instantiates a new board.
	 *
	 * @param size the size
	 */
	public Board(int size) {
		if(size > MAX_BOARD_SIZE || size < MIN_BOARD_SIZE ) {
			System.err.println("El valor de size no es válido");
			this.size = MIN_BOARD_SIZE;
		}
		else {
			this.size = size;
		}
		
		board = new HashMap<Coordinate, Ship>();
		seen = new HashSet<Coordinate>();
		
		destroyedCrafts = 0;
		numCrafts = 0;
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
	 * Check coordinate.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	public boolean checkCoordinate(Coordinate c) {
		boolean esta = false;
		
		if(c.get(0) >= 0 && c.get(0) <= size-1 && c.get(1) >= 0 && c.get(1) <= size-1) {
			esta = true;
		}
		return esta;
	}
	
	/**
	 * Adds the ship.
	 *
	 * @param ship the ship
	 * @param position the position
	 * @return true, if successful
	 */
	public boolean addShip(Ship ship, Coordinate position) {
		boolean added = true;
		Set<Coordinate> occupied = ship.getAbsolutePositions(position);
		
		for(Coordinate c : occupied) {
			if(checkCoordinate(c) == false) {
				added = false;
				break;
			}
		}
		
		if(added == false) {
			System.err.println("Alguna de las posiciones del barco está fuera del tablero");
		}
		else {
			for(Coordinate c : occupied) {
				if(board.containsKey(c)) {
					added = false;
					break;
				}
			}
			if(added == false) {
				System.err.println("Alguna de las posiciones del barco ya está ocupada");
			}
			else {
				Set<Coordinate> adyacentes = getNeighborhood(ship, position);
				for(Coordinate c : adyacentes) {
					if(board.containsKey(c)) {
						added = false;
						break;
					}
				}
				if(added) {
					numCrafts++;
					for(Coordinate c : occupied) {
						board.put(c, ship);
					}
					ship.setPosition(position);
				}
				else {
					System.err.println("Alguna de las posiciones alrededor del barco está ocupada");
				}
			}
		}
		return added;
	}
	
	/**
	 * Gets the ship.
	 *
	 * @param c the c
	 * @return the ship
	 */
	public Ship getShip(Coordinate c) {
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
	 */
	public CellStatus hit(Coordinate c) {
		CellStatus enumerado;
		Ship barco;
		Set<Coordinate> adyacentes;
		
		if(checkCoordinate(c) == false || board.containsKey(c) == false) {
			enumerado = CellStatus.WATER;
			if(checkCoordinate(c)) {
				seen.add(c);
			}
			else {
				System.err.println("Coordenada está fuera del tablero");
			}
		}
		else {
			seen.add(c);
			barco = getShip(c);
			if(barco.isShotDown()) {
				enumerado = CellStatus.DESTROYED;
			}
			else {
				boolean hited = barco.hit(c);
				if(hited == true) {
					enumerado = CellStatus.HIT;
					if(barco.isShotDown()) {
						destroyedCrafts++;
						enumerado = CellStatus.DESTROYED;
						adyacentes = getNeighborhood(barco);
						seen.addAll(adyacentes);
					}
				}
				else {
					enumerado = CellStatus.WATER;
				}
			}
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
	 * @param ship the ship
	 * @param position the position
	 * @return the neighborhood
	 */
	public Set<Coordinate> getNeighborhood(Ship ship, Coordinate position){
		Set<Coordinate> vecinas = new HashSet<Coordinate>();
		Set<Coordinate> ady;
		Set<Coordinate> ocuppied = ship.getAbsolutePositions(position);
		
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
	 * @param ship the ship
	 * @return the neighborhood
	 */
	public Set<Coordinate> getNeighborhood(Ship ship){
		return getNeighborhood(ship, ship.getPosition());
	}
	
	/**
	 * Show.
	 *
	 * @param unveil the unveil
	 * @return the string
	 */
	public String show(boolean unveil) {
		String str = "";
		
		for(int y = 0; y < size; y++) {
			for(int x = 0; x < size; x++){
				Coordinate cord = new Coordinate(x, y);
				if(unveil == false) {
					if(!seen.contains(cord)) {
						str += NOTSEEN_SYMBOL;
					}
					else {
						Ship barco = board.get(cord);
						if(barco == null) {
							str += WATER_SYMBOL;
						}
						else {
							if(barco.isShotDown() == true) {
								str += barco.getSymbol();
							}
							else{
								str += HIT_SYMBOL;
							}
						}
					}
				}
				else {
					if(board.containsKey(cord) == false) {
						str += WATER_SYMBOL;
					}
					else {
						Ship barco = board.get(cord);
						if(barco.isHit(cord)) {
							str += HIT_SYMBOL;
						}
						else{
							if(barco.isShotDown()) {
								str += HIT_SYMBOL;
							}
							else {
								str += barco.getSymbol();
							}
						}
					}
				}
			}
			if(y != size - 1) {
				str += "\n";
			}
		}
		return str;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return "Board " + size + "; crafts: " + numCrafts + "; destroyed: " + destroyedCrafts;
	}
	
	
}
