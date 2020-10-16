package model;

import java.util.HashSet;
import java.util.Set;

public class Ship {
	private Coordinate position;
	private Orientation orientation;
	private final static int BOUNDING_SQUARE_SIZE = 5;
	private final static int CRAFT_VALUE = 1;
	private final static int HIT_VALUE = -1;
	private char symbol;
	private String name;
	public Ship(Orientation o, char s, String n) {
		orientation = o; // seleccionando la forma del barco.
		// NORTH => fila 0 de shape
		// EAST => fila 1 de shape
		// SOUTH => fila 2 de shape
		symbol = s;
		name = n;
		position = null;
	}
	public Orientation getOrientation() {
		return orientation;
	}
	public Coordinate getPosition() {
		// codigo uggly!
		if(position == null) {
			return null;
		}
		return position.copy();
	}
	public void setPosition(Coordinate position) {
		this.position = new Coordinate(position);
	}
	public String getName() {
		return name;
	}
	public int [][] getShape() {
		return shape;
	}
	public int getShapeIndex(Coordinate c) {
		return c.get(1)*BOUNDING_SQUARE_SIZE + c.get(0);
	}
	public Set<Coordinate> getAbsolutePositions(Coordinate position){
		Set<Coordinate> cjto = new HashSet<Coordinate>();
		int y, x, indice, orientacion;
		
		// comprobar que el barco este en el tablero.
		if(position != null) {
			orientacion = orientation.ordinal();
			for(y = 0; y < BOUNDING_SQUARE_SIZE; y++) {
				for(x = 0; x < BOUNDING_SQUARE_SIZE; x++) {
					indice = getShapeIndex(new Coordinate(x, y));
					if(shape[orientacion][indice] == CRAFT_VALUE || shape[orientacion][indice] == HIT_VALUE) {
						cjto.add(new Coordinate(x, y).add(position));
					}
				}
			}
		}
		return cjto;
	}
	public char getSymbol() {
		return symbol;
	}
	public Set<Coordinate> getAbsolutePositions(){
		return getAbsolutePositions(position); // llama al de arriba con la posicion actual de barco nazi.
	}
	
	public boolean hit(Coordinate c) {
		boolean tocado = false;
		Coordinate relativa;
		int indice, orientacion;
		
		Set<Coordinate> absolutas = getAbsolutePositions(); // posicones del barco en el tablero
		if(absolutas.contains(c)) { // la posicion del disparo coincide con alguna de esas posiciones.
			relativa = c.subtract(position);
			indice = getShapeIndex(relativa);
			orientacion = orientation.ordinal();
			if(shape[orientacion][indice] == CRAFT_VALUE) {
				shape[orientacion][indice] = HIT_VALUE;
				tocado = true;
			}
		}
		return tocado;
	}
	public boolean is_hit(Coordinate c) {
		boolean tocado = false;
		Coordinate relativa;
		int indice, orientacion;
		
		Set<Coordinate> absolutas = getAbsolutePositions(); // posicones del barco en el tablero
		if(absolutas.contains(c)) { // la posicion del disparo coincide con alguna de esas posiciones.
			relativa = c.subtract(position);
			indice = getShapeIndex(relativa);
			orientacion = orientation.ordinal();
			if(shape[orientacion][indice] == HIT_VALUE) {
				tocado = true;
			}
		}
		return tocado;
	}
	//  
	public boolean isShotDown() {
		boolean esta = false;
		// tiene que haber algun CRAFT para no estar hundido
		// o todas tienes que ser HIT
		int orientacion = orientation.ordinal();
		esta = true;
		for(int i = 0; i < shape[orientacion].length && esta; i++) {
			if(shape[orientacion][i] == CRAFT_VALUE) {
				esta = false;
			}
		}
		/*
		esta = true;
		for(int y = 0; y < BOUNDING_SQUARE_SIZE && esta; y++) {
			for(int x = 0; x < BOUNDING_SQUARE_SIZE && esta; x++) {
				Coordinate c = new Coordinate(x, y); // genera la coordenada
				int indice = getShapeIndex(c); // para luego sacar el indice en el vector... :)
				if(shape[orientacion][indice] == CRAFT_VALUE) {
					esta = false;
				}
			}
		}*/
		return esta;
	}
	public String toString() {
		StringBuilder gonsalico = new StringBuilder();
		int indice, orientacion;
		
		orientacion = orientation.ordinal();
		gonsalico.append(name + " (" + orientation.toString() + ")\n");
		// cabecera---------------------------------------
		gonsalico.append(" ");
		for(int i = 1; i <= BOUNDING_SQUARE_SIZE; i++) {
			gonsalico.append("-");
		}
		gonsalico.append("\n");
		for(int y = 0; y < BOUNDING_SQUARE_SIZE; y++) {
			gonsalico.append("|");
			for(int x = 0; x < BOUNDING_SQUARE_SIZE; x++) {
				indice = getShapeIndex(new Coordinate(x, y));
				if(shape[orientacion][indice] == HIT_VALUE) {
					gonsalico.append(Board.HIT_SYMBOL);
				}
				else {
					if(shape[orientacion][indice] == CRAFT_VALUE) {
						gonsalico.append(symbol);
					}
					else {
						gonsalico.append(Board.WATER_SYMBOL);
					}
				}
			}
			gonsalico.append("|\n");
		}
		gonsalico.append(" ");
		for(int i = 1; i <= BOUNDING_SQUARE_SIZE; i++) {
			gonsalico.append("-");
		}
		//gonsalico.append("\n");
		return gonsalico.toString();
	}
	
	
	private int shape[][] = new int[][] {		//     relativas		position		absolutas		
        { 0, 0, 0, 0, 0,               // NORTH    ·····					
          0, 0, 1, 0, 0,               //          ··#·· (2,1)			 (1,1)			(3,2)
          0, 0, 1, 0, 0,               //          ··#·· (2,2)							(3,3)
          0, 0, 1, 0, 0,               //          ..#.. (2,3)							(3,4)
          0, 0, 0, 0, 0},              //          ·····

        { 0, 0, 0, 0, 0,               // EAST     ·····
          0, 0, 0, 0, 0,               //          ·····
          0, 1, 1, 1, 0,               //          ·###· (1,2)(2,2)(3,2)
          0, 0, 0, 0, 0,               //          ·····
          0, 0, 0, 0, 0},              //          ·····

        { 0, 0, 0, 0, 0,               // SOUTH    ·····
          0, 0, 1, 0, 0,               //          ··#··
          0, 0, 1, 0, 0,               //          ··#··
          0, 0, 1, 0, 0,               //          ..#..
          0, 0, 0, 0, 0},              //          ·····

        { 0, 0, 0, 0, 0,               // WEST     ·····
          0, 0, 0, 0, 0,               //          ·····
          0, 1, 1, 1, 0,               //          ·###·
          0, 0, 0, 0, 0,               //          ·····
          0, 0, 0, 0, 0}};   
}



