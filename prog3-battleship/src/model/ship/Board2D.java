package model.ship;

import model.Board;
import model.Coordinate;
import model.CoordinateFactory;
import model.Craft;

/**
 * The Class Board2D.
 */
public class Board2D extends Board {
	
	/**
	 * Instantiates a new board 2 D.
	 *
	 * @param size the size
	 */
	public Board2D(int size) {
		super(size);
	}

	/**
	 * Check coordinate.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	@Override
	public boolean checkCoordinate(Coordinate c) {
		boolean checked = false;
		if(!(c instanceof Coordinate2D)) {
			throw new IllegalArgumentException("error 2DCoordinate");
		}
		if(c.get(0) >= 0 && c.get(0) < getSize() && c.get(1) >= 0 && c.get(1) < getSize()) {
			checked = true;
		}
		return checked;
	}

	/**
	 * Show.
	 *
	 * @param unveil the unveil
	 * @return the string
	 */
	@Override
	public String show(boolean unveil) {
		String str = "";
		
		for(int y = 0; y < getSize(); y++) {
			for(int x = 0; x < getSize(); x++){
				Coordinate cord = CoordinateFactory.createCoordinate(x, y);
				if(unveil == false) {
					if(isSeen(cord) == false) {
						str += NOTSEEN_SYMBOL;
					}
					else {
						Craft barco = getCraft(cord);
						if(barco == null) {
							str += WATER_SYMBOL;
						}
						else {
							if(barco.isShotDown() == true) {
								str += barco.getSymbol();
							}
							else{
								str += HIT_SYMBOL;
							}
						}
					}
				}
				else {
					Craft barco = getCraft(cord);
					if(barco == null) {
						 str += WATER_SYMBOL;
					}
					else {
						if(barco.isHit(cord) == true) {
							str += HIT_SYMBOL;
						}
						else {
							if(barco.isShotDown() == true) {
								str += HIT_SYMBOL;
							}
							else {
								str += barco.getSymbol();
							}
						}
					}
				}
			}
			if(y != getSize() - 1) {
				str += "\n";
			}
		}
		return str;
	}
}
