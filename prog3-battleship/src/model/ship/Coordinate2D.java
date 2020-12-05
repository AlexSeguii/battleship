package model.ship;

import java.util.HashSet;
import java.util.Set;

import model.Coordinate;
import model.CoordinateFactory;

/**
 * The Class Coordinate2D.
 */
public class Coordinate2D extends Coordinate {
	
	/**
	 * Instantiates a new coordinate 2 D.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Coordinate2D(int x, int y) {
		super(2);
		set(0, x);
		set(1, y);
	}
	
	/**
	 * Instantiates a new coordinate 2 D.
	 *
	 * @param c the c
	 */
	public Coordinate2D(Coordinate2D c) {
		super(c);
	}
	
	/**
	 * Copy.
	 *
	 * @return the coordinate 2 D
	 */
	public Coordinate2D copy() {
		return new Coordinate2D(this);
	}
	
	/**
	 * Adjacent coordinates.
	 *
	 * @return the sets the
	 */
	public Set<Coordinate> adjacentCoordinates(){
		Set<Coordinate> adyacentes = new HashSet<>();
		
		for(int y = get(1)-1; y <= get(1)+1; y++) {
			for(int x = get(0)-1; x <= get(0)+1; x++) {
				if(x != get(0) || y != get(1)) {
					adyacentes.add(CoordinateFactory.createCoordinate(x, y));
				}
			}
		}
		return adyacentes;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		StringBuilder concatenation = new StringBuilder();
		concatenation.append("(");
		for (int i = 0; i < 2; i++){
			concatenation.append(get(i));
			if(i < 2 - 1)
				concatenation.append(", ");
		}
		concatenation.append(")");
		return concatenation.toString();
	}
}
