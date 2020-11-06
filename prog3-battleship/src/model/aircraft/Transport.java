package model.aircraft;

import model.Orientation;


/**
 * The Class Transport.
 */
public class Transport extends Aircraft {
	
	/**
	 * Instantiates a new transport.
	 *
	 * @param o the o
	 */
	public Transport(Orientation o) {
		super(o, '⇋', "Transport");
		shape = new int[][] {
		      { 0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 1, 1, 1, 0,	
		    	1, 0, 1, 0, 1,
		    	0, 0, 1, 0, 0},
		      { 0, 1, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	1, 1, 1, 1, 1,	
		    	0, 0, 1, 0, 0,
		    	0, 1, 0, 0, 0},
		      { 0, 0, 1, 0, 0,
		    	1, 0, 1, 0, 1,	
		    	0, 1, 1, 1, 0,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 1, 0, 0},
		      { 0, 0, 0, 1, 0,
		    	0, 0, 1, 0, 0,	
		    	1, 1, 1, 1, 1,	
		    	0, 0, 1, 0, 0,
		    	0, 0, 0, 1, 0}};
	}
}
