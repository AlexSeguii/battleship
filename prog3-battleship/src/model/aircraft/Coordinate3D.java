package model.aircraft;

import java.util.HashSet;
import java.util.Set;

import model.Coordinate;
import model.CoordinateFactory;


/**
 * The Class Coordinate3D.
 */
public class Coordinate3D extends Coordinate {
	
	/**
	 * Instantiates a new coordinate 3 D.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public Coordinate3D(int x, int y, int z) {
		super(3);
		set(0, x);
		set(1, y);
		set(2, z);
	}
	
	/**
	 * Instantiates a new coordinate 3 D.
	 *
	 * @param c the c
	 */
	public Coordinate3D(Coordinate3D c) {
		super(c);
	}
	
	/**
	 * Copy.
	 *
	 * @return the coordinate 3 D
	 */
	public Coordinate3D copy() {
		return new Coordinate3D(this);
	}
	
	/**
	 * Adjacent coordinates.
	 *
	 * @return the sets the
	 */
	public Set<Coordinate> adjacentCoordinates(){
		Set<Coordinate> adyacentes = new HashSet<>();
		
		for(int z = get(2)-1; z <= get(2)+1; z++) {
			for(int y = get(1)-1; y <= get(1)+1; y++) {
				for(int x = get(0)-1; x <= get(0)+1; x++) {
					if(x != get(0) || y != get(1) || z != get(2)) {
						adyacentes.add(CoordinateFactory.createCoordinate(x, y, z));
					}
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
		for (int i = 0; i < 3; i++){
			concatenation.append(get(i));
			if(i < 3 - 1)
				concatenation.append(", ");
		}
		concatenation.append(")");
		return concatenation.toString();
	}
	
}
