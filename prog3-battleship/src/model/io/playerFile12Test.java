package model.io;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.StringReader;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Board;
import model.Coordinate;
import model.exceptions.BattleshipException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;
import model.ship.Board2D;

public class playerFile12Test {
	Coordinate cd2, cd3;
	BufferedReader reader ;
	String boardok1 =
			"               \n" + 
			"               \n" + 
			"               \n" + 
			" Ø             \n" + 
			" Ø             \n" + 
			" Ø  ΩΩ         \n" + 
			"               \n" + 
			"               \n" + 
			"               \n" + 
			"               \n" + 
			"               \n" + 
			"               \n" + 
			"               \n" + 
			"               \n" + 
			"               ";
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	@Before
	public void setUp() throws Exception {
		
		
	}	
	@Test
	public void testPutok1() {
		String test = 	
				"put  Cruiser NORTH -1 2\n" + 
				"put Destroyer  EAST 3 3\n" + 
				"endput\n" + 
				"shoot 0 3\n" + 
				"shoot 1 3\n" + 
				"shoot 0  1\n" + 
				"exit";
		reader = new BufferedReader(new StringReader(test));
		Board b = new Board2D(15);
		PlayerFile pf = new PlayerFile("jose", reader);
		try {
			pf.putCrafts(b);
			assertEquals("Board 15; crafts: 2; destroyed: 0", b.toString());
			assertEquals(boardok1, b.show(true));
			System.out.println(b);
			System.out.println("-----------------");
			System.out.println(b.show(true));
			System.out.println("-----------------");
		} catch (InvalidCoordinateException | OccupiedCoordinateException | NextToAnotherCraftException | BattleshipIOException e) {
			e.printStackTrace();
		}
	}
	// faltan argumentos
	@Test(expected=BattleshipIOException.class)
	public void testPutNok1() throws BattleshipException{
		String test = 	
				"put  Cruiser\n" + 
				"shoot 0  1\n" + 
				"exit";
		reader = new BufferedReader(new StringReader(test));
		Board b = new Board2D(10);
		PlayerFile pf = new PlayerFile("jose", reader);
		pf.putCrafts(b);
	}
	// orientacion incorrecta
	@Test(expected=BattleshipIOException.class)
	public void testPutNok2() throws BattleshipException{
		String test = 	
				"put  Cruiser jander 2 2\n" + 
				"shoot 0  1\n" + 
				"exit";
		reader = new BufferedReader(new StringReader(test));
		Board b = new Board2D(10);
		PlayerFile pf = new PlayerFile("jose", reader);
		pf.putCrafts(b);
	}
	// faltar argumentos de coordenada
	@Test(expected=BattleshipIOException.class)
	public void testPutNok3() throws BattleshipException{
		String test = 	
				"put  Cruiser NORTH 2 2\n" + 
				"put Fighter NORTH 1\n" +
				"shoot 0  1\n" + 
				"exit";
		reader = new BufferedReader(new StringReader(test));
		Board b = new Board2D(10);
		PlayerFile pf = new PlayerFile("jose", reader);
		pf.putCrafts(b);
	}
	// las coordenadas no son numeros
	@Test(expected=BattleshipIOException.class)
	public void testPutNok4() throws BattleshipException{
		String test = 	
				"put  Cruiser NORTH 2 2a\n" + 
				"put Fighter NORTH 1 2\n" +
				"shoot 0  1\n" + 
				"exit";
		reader = new BufferedReader(new StringReader(test));
		Board b = new Board2D(10);
		PlayerFile pf = new PlayerFile("jose", reader);
		pf.putCrafts(b);
	}
	// sobran argumentos
	@Test(expected=BattleshipIOException.class)
	public void testPutNok5() throws BattleshipException{
		String test = 	
				"put  Cruiser NORTH 2 2 2 2\n" + 
				"put Fighter NORTH 1\n" +
				"shoot 0  1\n" + 
				"exit";
		reader = new BufferedReader(new StringReader(test));
		Board b = new Board2D(10);
		PlayerFile pf = new PlayerFile("jose", reader);
		pf.putCrafts(b);
	}
	
	
	
	
	
	
}
