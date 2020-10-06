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
	
	/** The position. */
	private Coordinate position;
	
	/** The orientation. */
	private Orientation orientation;
	
	/** The bounding square size. */
	private static int BOUNDING_SQUARE_SIZE = 5;
	
	/** The hit value. */
	private static int HIT_VALUE = -1;
	
	/** The craft value. */
	private static int CRAFT_VALUE = 1;
	
	/** The symbol. */
	private char symbol;
	
	/** The name. */
	private String name;
	
	/** The shape. */
	private int[][] shape;
	
	
	/**
	 * Instantiates a new ship.
	 *
	 * @param o the o
	 * @param s the s
	 * @param n the n
	 */
	//SIN this
	public Ship(Orientation o, char s, String n) {
		this.o = orientation;
		this.s = symbol;
		this.n = name;
	}
	
	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Coordinate getPosition() {
		return position.copy();
	}
	
	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(Coordinate position) {
		this.position = position;
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
	 * Gets the shape.
	 *
	 * @return the shape
	 */
	public int [][] getShape(){
		
	}
	
	/**
	 * Gets the shape index.
	 *
	 * @param c the c
	 * @return the shape index
	 */
	public int getShapeIndex(Coordinate c) {
		int pos;
		
		pos = c.get(1) * BOUNDING_SQUARE_SIZE + c.get(0);
		return pos;
	}
	
	/**
	 * Gets the absolute positions.
	 *
	 * @param c the c
	 * @return the absolute positions
	 */
	public Set<Coordinate> getAbsolutePositions(Coordinate c){
		Set<Coordinate>  
	}
	
	/**
	 * Gets the absolute positions.
	 *
	 * @return the absolute positions
	 */
	public Set<Coordinate> getAbsolutePositions(){
		
	}
	
	/**
	 * Hit.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	public boolean hit(Coordinate c) {
		
	}
	
	/**
	 * Checks if is shot down.
	 *
	 * @return true, if is shot down
	 */
	public boolean isShotDown() {
		
	}
	
	/**
	 * Checks if is hit.
	 *
	 * @param c the c
	 * @return true, if is hit
	 */
	public boolean isHit(Coordinate c) {
		
	}

}
