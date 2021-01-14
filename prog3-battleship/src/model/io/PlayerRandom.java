package model.io;

import java.util.Random;

import model.Board;
import model.CellStatus;
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



public class PlayerRandom implements IPlayer {
	private Random random;
	private String name;
	private CellStatus lastShotStatus;
	
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
		String [] scrafts = {	"ship.Battleship", 
								"ship.Carrier", 
								"ship.Cruiser", 
								"ship.Destroyer", 
								"aircraft.Bomber", 
								"aircraft.Fighter", 
								"aircraft.Transport"};
		Orientation random;
		Coordinate coordinate;
		Craft craft;
		int intentos, limite;
		if(!(b instanceof Board3D)) {
			limite = 4;
		}
		else {
			limite = scrafts.length;
		}
		for(int i = 0; i < limite; i++) {
			intentos = 1;
			random = Orientation.values()[genRandomInt(0, Orientation.values().length)];
			craft = CraftFactory.createCraft(scrafts[i], random);
			while(intentos <= 100){
				coordinate = genRandomCoordinate(b, Craft.BOUNDING_SQUARE_SIZE);
				try {
					b.addCraft(craft, coordinate);
					break;
				} catch (InvalidCoordinateException | OccupiedCoordinateException | NextToAnotherCraftException e) {
					intentos++;	
				}
			}
		}
	}
	
	@Override
	public Coordinate nextShoot(Board b) throws InvalidCoordinateException, CoordinateAlreadyHitException{
		Coordinate c = genRandomCoordinate(b, 0); // la coordenada esta dentro del tablero si o si.
		lastShotStatus = null;
		lastShotStatus = b.hit(c);		
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

	@Override
	public CellStatus getLastShotStatus() {
		return lastShotStatus;
	}
}
