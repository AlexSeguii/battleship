package model.io;

import java.util.Random;

import model.Board;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;
import model.CraftFactory;
import model.Orientation;
import model.aircraft.Board3D;
import model.exceptions.BattleshipException;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.ship.Board2D;


/**
 * The Class PlayerRandom.
 * @auhor Alejandro Seguí Apellániz 48793265F
 * @version 11.0.8
 */
public class PlayerRandom implements IPlayer {
	
	/** The random. */
	private Random random;
	
	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new player random.
	 *
	 * @param name the name
	 * @param seed the seed
	 */
	public PlayerRandom(String name, long seed) {
		this.name = name;
		random = new Random(seed);
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@Override
	public String getName() {
		return name + " (PlayerRandom)";
	}

	/**
	 * Put crafts.
	 *
	 * @param b the b
	 */
	@Override
	public void putCrafts(Board b) {
		int total;
		String[] crafts = {"Battleship", "Carrier", "Cruiser", "Destroyer", "Bomber", "Fighter", "Transport"};
		if((b instanceof Board3D) == false) {
			total = 4;
		}
		else {
			total = crafts.length;
		}
		for(int x=0; x<total;x++) {
			int aux = 1;
			Orientation or = Orientation.values()[genRandomInt(0, Orientation.values().length)];
			Craft craft = CraftFactory.createCraft(crafts[x], or);
			
			while(aux <= 100) {
				Coordinate cord = genRandomCoordinate(b, Craft.BOUNDING_SQUARE_SIZE);
				try {
					b.addCraft(craft, cord);
					break;
				}
				catch(OccupiedCoordinateException | NextToAnotherCraftException | InvalidCoordinateException e) {
					aux++;
				}
			}
		}
	}

	/**
	 * Next shoot.
	 *
	 * @param b the b
	 * @return the coordinate
	 * @throws InvalidCoordinateException the invalid coordinate exception
	 * @throws CoordinateAlreadyHitException the coordinate already hit exception
	 */
	@Override
	public Coordinate nextShoot(Board b) throws InvalidCoordinateException, CoordinateAlreadyHitException {
		Coordinate c = genRandomCoordinate(b, 0);
		b.hit(c);		
		return c;
	}
	
	/**
	 * Gen random int.
	 *
	 * @param min the min
	 * @param max the max
	 * @return the int
	 */
	private int genRandomInt(int min, int max) {
		return random.nextInt(max - min) + min;
	}
	
	/**
	 * Gen random coordinate.
	 *
	 * @param b the b
	 * @param offset the offset
	 * @return the coordinate
	 */
	private Coordinate genRandomCoordinate(Board b, int offset) {
		int i,j,k;
		Coordinate c = null;
		
		i = genRandomInt(-offset, b.getSize());
		j = genRandomInt(-offset, b.getSize());
		if(b instanceof Board2D) {
			c = CoordinateFactory.createCoordinate(i, j);
		}
		else {
			k = genRandomInt(-offset, b.getSize());
			c = CoordinateFactory.createCoordinate(i, j, k);
		}	
		return c;
	}
}
