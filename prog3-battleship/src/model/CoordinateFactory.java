package model;

import model.aircraft.Coordinate3D;
import model.ship.Coordinate2D;


public class CoordinateFactory {
	public static Coordinate createCoordinate(int... coords) {
		Coordinate c = null;
		if(coords.length > 3|| coords.length < 2 ) {
			throw new IllegalArgumentException("Error en las coordenadas");
		}
		else {
			if(coords.length == 2) {
				c = new Coordinate2D(coords[0], coords[1]);
			}
			else {
				c = new Coordinate3D(coords[0], coords[1], coords[2]);
			}
		}
		return c;
	}
}


