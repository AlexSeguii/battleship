package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class Coordinate
 * @auhor Alejandro Seguí Apellániz 48793265F
 * @version 11.0.8
 */
public class Coordinate {
	
	
	/** The components. */
	private int[] components;
	
	/**
	 * Instantiates a new coordinate.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Coordinate(int x, int y) {
		components = new int [2];
		components[0] = x;
		components[1] = y;
	}
	
	/**
	 * Instantiates a new coordinate.
	 *
	 * @param c the c
	 */
	public Coordinate(Coordinate c) {
		int dim = c.components.length;
		components = new int [dim];
		
		for(int i = 0; i < dim; i++) {
			components[i] = c.components[i];
		}
	}
	
	/**
	 * Sets the.
	 *
	 * @param component the component
	 * @param value the value
	 */
	protected void set(int component, int value) {
		if (component >= 0 && component < components.length) {
			components[component] = value;
		}
		else
			System.err.println("Error in Coordinate.set, component " + component + " is out of range");
	}
	
	/**
	 * Gets the.
	 *
	 * @param component the component
	 * @return the int
	 */
	public int get(int component) {
		if(component >= 0 && component < components.length) {
			return components[component];
		}
		else 
			System.err.println("Error in Coordinate.get, component " + component + " is out of range");
		
		return -1;
	}
	
	/**
	 * Adds the.
	 *
	 * @param c the c
	 * @return the coordinate
	 */
	public Coordinate add(Coordinate c) {
		Coordinate total = new Coordinate(this);
		
		for(int i = 0; i<c.components.length; i++) {
			total.set(i, total.get(i) + c.get(i));
		}
		return total;
	}
	
	/**
	 * Subtract.
	 *
	 * @param c the c
	 * @return the coordinate
	 */
	public Coordinate subtract(Coordinate c) {
		Coordinate total = new Coordinate(this);
		
		for(int i = 0; i < c.components.length; i++) {
			total.set(i, total.get(i) - c.get(i));
		}
		return total;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		StringBuilder concatenation = new StringBuilder();
		concatenation.append("(");
		for (int i = 0; i < components.length; i++){
			concatenation.append(components[i]);
			if(i < components.length - 1)
				concatenation.append(", ");
		}
		concatenation.append(")");
		return concatenation.toString();
	}
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(components);
		return result;
	}
	
	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (!Arrays.equals(components, other.components))
			return false;
		return true;
	}
	
	/**
	 * Copy.
	 *
	 * @return the coordinate
	 */
	public Coordinate copy() {
		return new Coordinate(this);
	}
	
	/**
	 * Adjacent coordinates.
	 *
	 * @return the sets the
	 */
	public Set<Coordinate> adjacentCoordinates() {
		Set<Coordinate> adyacentes = new HashSet<Coordinate>();
		
		for(int i = components[0] - 1; i <= components[0] + 1; i++) {
			for(int j = components[1] - 1; j <= components [1] + 1; j++) {
				if(i != components[0] && j != components[1]) {
					adyacentes.add(new Coordinate(i,j));
				}
			}
		}
		return adyacentes;
	}

}








