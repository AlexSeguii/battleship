package model;
import java.util.*;

/**
 * The Class Coordinate.
 * @auhor Alejandro Seguí Apellániz 48793265F
 * @version 11.0.8
 */
public abstract class Coordinate {
	/** The components. */
	private int[] components;
	
	/**
	 * Instantiates a new coordinate.
	 *
	 * @param dim the dim
	 */
	protected Coordinate(int dim) {
		components = new int[dim];
	}
	
	/**
	 * Instantiates a new coordinate.
	 *
	 * @param c the c
	 */
	protected Coordinate(Coordinate c) {
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
	public void set(int component, int value) {
		if (component >= 0 && component < components.length) {
			components[component] = value;
		}
		else
			throw new IllegalArgumentException("Componente fuera de rango");
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
			throw new IllegalArgumentException("Componente fuera de rango");
		
	}
	
	/**
	 * Adds the.
	 *
	 * @param c the c
	 * @return the coordinate
	 */
	public Coordinate add(Coordinate c) {
		Objects.requireNonNull(c);
		Coordinate total = copy();
		
		for(int i = 0; i < total.components.length && i < c.components.length; i++) {
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
		Objects.requireNonNull(c);
		Coordinate total = copy();
		
		for(int i = 0; i < total.components.length && i < c.components.length; i++) {
			total.set(i, total.get(i) - c.get(i));
		}
		return total;
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
	public abstract Coordinate copy();
	
	/**
	 * Adjacent coordinates.
	 *
	 * @return the sets the
	 */
	public abstract Set<Coordinate> adjacentCoordinates();

}








