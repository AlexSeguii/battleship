package model.ship;

import model.Orientation;


/**
 * The Class Carrier.
 */
public class Carrier extends Ship {
	
	/**
	 * Instantiates a new carrier.
	 *
	 * @param o the o
	 */
	public Carrier(Orientation o) {
		super(o, '®', "Carrier");
		shape = new int[][] {
		      { 0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0},
		      { 0, 0, 0, 0, 0,
		        0, 0, 0, 0, 0,	
		        1, 1, 1, 1, 1,	
		        0, 0, 0, 0, 0,
		        0, 0, 0, 0, 0},
		      { 0, 0, 1, 0, 0,
		        0, 0, 1, 0, 0,	
		        0, 0, 1, 0, 0,	
		        0, 0, 1, 0, 0,
		        0, 0, 1, 0, 0},
		      { 0, 0, 0, 0, 0,
		        0, 0, 0, 0, 0,	
		        1, 1, 1, 1, 1,	
		        0, 0, 0, 0, 0,
		        0, 0, 0, 0, 0}};
	}
}
