package model;

import java.util.HashSet;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class Ship.
 * @auhor Alejandro Seguí Apellániz 48793265F
 * @version 11.0.8
 */
public class Ship {
	
	/** The Constant BOUNDING_SQUARE_SIZE. */
	private final static int BOUNDING_SQUARE_SIZE = 5;
	
	/** The Constant CRAFT_VALUE. */
	private final static int CRAFT_VALUE = 1;
	
	/** The Constant HIT_VALUE. */
	private final static int HIT_VALUE = -1;
	
	/** The position. */
	private Coordinate position;
	
	/** The orientation. */
	private Orientation orientation;
	
	/** The symbol. */
	private char symbol;
	
	/** The name. */
	private String name;
	
	/** The shape. */
	private int shape[][] = new int[][] {
        { 0, 0, 0, 0, 0,               // NORTH    ·····
          0, 0, 1, 0, 0,               //          ··#··
          0, 0, 1, 0, 0,               //          ··#··
          0, 0, 1, 0, 0,               //          ..#..
          0, 0, 0, 0, 0},              //          ·····

        { 0, 0, 0, 0, 0,               // EAST     ·····
          0, 0, 0, 0, 0,               //          ·····
          0, 1, 1, 1, 0,               //          ·###·
          0, 0, 0, 0, 0,               //          ·····
          0, 0, 0, 0, 0},              //          ·····

        { 0, 0, 0, 0, 0,               // SOUTH    ·····
          0, 0, 1, 0, 0,               //          ··#··
          0, 0, 1, 0, 0,               //          ··#··
          0, 0, 1, 0, 0,               //          ..#..
          0, 0, 0, 0, 0},              //          ·····

        { 0, 0, 0, 0, 0,               // WEST     ·····
          0, 0, 0, 0, 0,               //          ·····
          0, 1, 1, 1, 0,               //          ·###·
          0, 0, 0, 0, 0,               //          ·····
          0, 0, 0, 0, 0}};             //          ·····
          
          
	
	/**
	 * Instantiates a new ship.
	 *
	 * @param orientation the orientation
	 * @param symbol the symbol
	 * @param name the name
	 */
	public Ship(Orientation orientation, char symbol, String name) {
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
		return null;
	}
	
	
	/**
	 * Gets the shape index.
	 *
	 * @param c the c
	 * @return the shape index
	 */
	public int getShapeIndex(Coordinate c) {
		return c.get(1) * BOUNDING_SQUARE_SIZE + c.get(0);
	}
	
	
	/**
	 * Gets the absolute positions.
	 *
	 * @param position the position
	 * @return the absolute positions
	 */
	public Set<Coordinate> getAbsolutePositions(Coordinate position){
		Set<Coordinate> absolute = new HashSet<Coordinate>();
		
		if(position != null) {
			for(int y=0; y < BOUNDING_SQUARE_SIZE; y++) {
				for(int x=0; x < BOUNDING_SQUARE_SIZE; x++) {
					Coordinate aux = new Coordinate(x, y);
					int posShape = getShapeIndex(aux);
					if(shape[orientation.ordinal()][posShape] == HIT_VALUE || shape[orientation.ordinal()][posShape] == CRAFT_VALUE) {
						absolute.add(aux.add(position));
					}
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
	public Set<Coordinate> getAbsolutePositions(){
		Set<Coordinate> s = getAbsolutePositions(position);
		return s;
	}
	
	
	/**
	 * Hit.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	public boolean hit(Coordinate c) {
		Set<Coordinate> absolutas = getAbsolutePositions();
		boolean hited = false;
		
		if(absolutas.contains(c)) {
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
		this.position = new Coordinate(position);
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
		return shape;
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
		int orientacion = orientation.ordinal();
		
		for(int x=0; x < BOUNDING_SQUARE_SIZE; x++) {
			for(int y=0; y < BOUNDING_SQUARE_SIZE; y++) {
				Coordinate aux = new Coordinate(x, y);
				int index = getShapeIndex(aux);
				
				if(shape[orientacion][index] == CRAFT_VALUE) {
					hundido = false;
				}
			}
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
		Set<Coordinate> absolutes = getAbsolutePositions();
		
		if(absolutes.contains(c)) {
			Coordinate relativa = c.subtract(position);
			int poshape = getShapeIndex(relativa);
			
			if(shape[orientation.ordinal()][poshape] == HIT_VALUE) {
				alcanzado = true;
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
				int poshape = getShapeIndex(new Coordinate(x, y));
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
}
