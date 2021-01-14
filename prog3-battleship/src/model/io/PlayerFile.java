package model.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

import model.Board;
import model.CellStatus;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;
import model.CraftFactory;
import model.Orientation;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;


public class PlayerFile implements IPlayer {
	private CellStatus lastShotStatus;
	private BufferedReader br;
	private String name;
	
	public PlayerFile(String name, BufferedReader reader) {
		Objects.requireNonNull(reader);
		this.name = name;
		br = reader;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@Override
	public String getName() {
		return name + " (PlayerFile)";
	}
	
	@Override
	public void putCrafts(Board b) throws InvalidCoordinateException, OccupiedCoordinateException, NextToAnotherCraftException, BattleshipIOException {
		Craft craft;
		String line;
		Coordinate pos;
		String[] tr;
		
		craft = null;
		pos = null;
		
		Objects.requireNonNull(b);
		try {
			line = br.readLine();
			while(line != null && line.equals("exit") == false &&  line.equals("endput") == false) {
				try {
					tr = line.split("\\s+"); 
					if(tr[0].equals("put")) {
						if(tr.length<3) {
							throw new BattleshipIOException("error en longitud");
						}
						craft = CraftFactory.createCraft(tr[1], Orientation.valueOf(tr[2]));
						switch(tr.length) {
							case 5:
								pos = CoordinateFactory.createCoordinate(Integer.parseInt(tr[3]), Integer.parseInt(tr[4]));
								break;
							case 6:
								pos = CoordinateFactory.createCoordinate(Integer.parseInt(tr[3]), Integer.parseInt(tr[4]), Integer.parseInt(tr[5]));						
								break;
							default:
								throw new BattleshipIOException("Error en los argumentos para put");
						}					
					}
					else {
						throw new BattleshipIOException("error el comando no es correcto");					
					}
				}
				catch(IllegalArgumentException e) {
					throw new BattleshipIOException(e.getMessage() + "se esta leyendo el fichero");
				}
				b.addCraft(craft, pos);
				line = br.readLine();
			}
		}
		catch(IOException e) {
			throw new BattleshipIOException("no pasa");
		}
	}

	@Override
	public Coordinate nextShoot(Board b) throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException {
		int i,j,k;
		String[] tr;
		String line;
		Coordinate c = null;
		
		lastShotStatus = null;
		try {
			line = br.readLine();
			if(line != null) {
				tr = line.split("\\s+");
				if(!tr[0].equals("shoot") && !tr[0].equals("exit")) {
					throw new BattleshipIOException("Error ");
				}
				else {
					if(tr[0].equals("shoot") == true) {
						switch(tr.length) {
							case 3:
								i = Integer.parseInt(tr[1]);
								j = Integer.parseInt(tr[2]);
								c = CoordinateFactory.createCoordinate(i, j);
								break;
							case 4:
								i = Integer.parseInt(tr[1]);
								j = Integer.parseInt(tr[2]);
								k = Integer.parseInt(tr[3]);
								c = CoordinateFactory.createCoordinate(i, j, k);
								break;
							default:
								throw new BattleshipIOException("Error");
						}
						lastShotStatus = b.hit(c);
					}
				}
			}
			
		}
		catch(IllegalArgumentException e) {
			throw new BattleshipIOException(e.getMessage());
		}
		catch (IOException e) {
			throw new BattleshipIOException("ERROR");
		}
		return c;
	}
	public CellStatus getLastShotStatus() {
		return lastShotStatus;
	}

}
