package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import model.exceptions.CoordinateAlreadyHitException;

/**
 * The Class Craft.
 * @auhor Alejandro Seguí Apellániz 48793265F
 * @version 11.0.8
 */
public abstract class Craft {
	
	/** The Constant BOUNDING_SQUARE_SIZE. */
	public static final int BOUNDING_SQUARE_SIZE = 5;
	
	// indica que aun no le hemos dado
	private static final int CRAFT_VALUE = 1;
	
	// indica que has disparado sobre esa celda
	private static final int HIT_VALUE = -1;
	
	/** The position. */
	private Coordinate position;
	
	/** The orientation. */
	private Orientation orientation;
	
	/** The symbol. */
	private char symbol;
	
	/** The name. */
	private String name;
	
	/** The shape. */
	protected int shape[][];

	/**
	 * Instantiates a new craft.
	 *
	 * @param orientation the orientation
	 * @param symbol the symbol
	 * @param name the name
	 */
	public Craft(Orientation orientation, char symbol, String name) {
		this.orientation = orientation;
		this.symbol = symbol;
		this.name = name;
		
		position = null;
	}

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Coordinate getPosition() {
		if(position != null) {
			return position.copy();
		}
		else {
			return null;
		}
	}

	/**
	 * Gets the shape index.
	 *
	 * @param c the c
	 * @return the shape index
	 */
	public int getShapeIndex(Coordinate c) {
		Objects.requireNonNull(c);
		int indice = 0;
		indice = c.get(0) + c.get(1) * BOUNDING_SQUARE_SIZE;
		return indice;
	}

	/**
	 * Gets the absolute positions.
	 *
	 * @param position the position
	 * @return the absolute positions
	 */
	public Set<Coordinate> getAbsolutePositions(Coordinate position) {
		Objects.requireNonNull(position);
		Set<Coordinate> absolute = new HashSet<Coordinate>();
		int x, y;
		
		if(position != null) {
			for(int k = 0; k < BOUNDING_SQUARE_SIZE * BOUNDING_SQUARE_SIZE; k++) {
				y = k / BOUNDING_SQUARE_SIZE;
				x = k % BOUNDING_SQUARE_SIZE;
				if(shape[orientation.ordinal()][k] == HIT_VALUE || shape[orientation.ordinal()][k] == CRAFT_VALUE ) {
					absolute.add(position.add(CoordinateFactory.createCoordinate(x, y)));
				}
			}
		}
		return absolute;
	}

	/**
	 * Gets the absolute positions.
	 *
	 * @return the absolute positions
	 */
	public Set<Coordinate> getAbsolutePositions() {
		Set<Coordinate> s = getAbsolutePositions(position);
		return s;
	}

	/**
	 * Hit.
	 *
	 * @param c the c
	 * @return true, if successful
	 * @throws CoordinateAlreadyHitException the coordinate already hit exception
	 */
	public boolean hit(Coordinate c) throws CoordinateAlreadyHitException {
		boolean hited = false;
		Objects.requireNonNull(c);
		
		Set<Coordinate> absolutas = getAbsolutePositions();
		if(absolutas.contains(c)) {
			if(isHit(c)) {
				throw new CoordinateAlreadyHitException(c);
			}
			Coordinate relativa;
			relativa = c.subtract(position);
			
			int poshape = getShapeIndex(relativa);
			
			if(shape[orientation.ordinal()][poshape] == CRAFT_VALUE) {
				shape[orientation.ordinal()][poshape] = HIT_VALUE;
				hited = true;
			}
		}
	
		return hited;
	}

	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(Coordinate position) {
		this.position = position.copy();
	}

	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the shape.
	 *
	 * @return the shape
	 */
	public int [][] getShape() {
		return shape.clone();
	}

	
	/**
	 * Gets the orientation.
	 *
	 * @return the orientation
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public char getSymbol() {
		return symbol;
	}

	/**
	 * Checks if is shot down.
	 *
	 * @return true, if is shot down
	 */
	public boolean isShotDown() {
		boolean hundido = true;
		
		if(position != null) { 
			for(int i = 0; i < shape[orientation.ordinal()].length && hundido == true; i++) {
				if(shape[orientation.ordinal()][i] == CRAFT_VALUE) {
					hundido = false;
				}
			}
		}
		else {
			hundido = false;
		}
		return hundido;
	}

	/**
	 * Checks if is hit.
	 *
	 * @param c the c
	 * @return true, if is hit
	 */
	public boolean isHit(Coordinate c) {
		boolean alcanzado = false;
		
		if(position != null) {
			Set<Coordinate> absolutes = getAbsolutePositions();
			if(absolutes.contains(c)) {
				Coordinate relativa = c.subtract(position);
				int poshape = getShapeIndex(relativa);
				
				if(shape[orientation.ordinal()][poshape] == HIT_VALUE) {
					alcanzado = true;
				}
			}
		}
		return alcanzado;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		StringBuilder sb ;
		sb = new StringBuilder();
		
		sb.append(name + " (" + orientation.toString() + ")\n");
		sb.append(" ");
		
		for(int i=1; i <= BOUNDING_SQUARE_SIZE; i++) {
			sb.append("-");
		}
		
		sb.append("\n");
		for(int y=0; y < BOUNDING_SQUARE_SIZE; y++) {
			sb.append("|");
			for(int x=0; x < BOUNDING_SQUARE_SIZE; x++) {
				int poshape = getShapeIndex(CoordinateFactory.createCoordinate(x, y));
				if(shape[orientation.ordinal()][poshape] == CRAFT_VALUE) {
					sb.append(symbol);
				}
				else {
					if(shape[orientation.ordinal()][poshape] == HIT_VALUE) {
						sb.append(Board.HIT_SYMBOL);
					}
					else {
						sb.append(Board.WATER_SYMBOL);
					}
				}
			}
			sb.append("|\n");
		}
		
		sb.append(" ");
		
		for(int j=1; j <= BOUNDING_SQUARE_SIZE; j++) {
			sb.append("-");
		}
		return sb.toString();
	}
	public abstract int getValue();
}