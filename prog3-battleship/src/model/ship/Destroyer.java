package model.ship;

import model.Orientation;


/**
 * The Class Destroyer.
 */
public class Destroyer extends Ship {
	
	/**
	 * Instantiates a new destroyer.
	 *
	 * @param o the o
	 */
	public Destroyer(Orientation o) {
		super(o, 'Ω', "Destroyer");
		shape = new int[][] {
		      { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
		    	0, 0, 0, 0, 0,
		    	0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
		    	0, 0, 0, 0, 0,	
		    	0, 1, 1, 0, 0,	
		    	0, 0, 0, 0, 0,
		    	0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
		    	0, 0, 1, 0, 0,	
		    	0, 0, 1, 0, 0,	
		    	0, 0, 0, 0, 0,
		    	0, 0, 0, 0, 0},
		      { 0, 0, 0, 0, 0,
		    	0, 0, 0, 0, 0,	
				0, 1, 1, 0, 0,	
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0}};
	}
}
