package model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Board;
import model.Coordinate;
import model.aircraft.Board3D;
import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;
import model.ship.Board2D;
import model.ship.Coordinate2D;

public class  PlayerFilePreTest { 
	
	final static String DIRFILES = "pre-test/files/";
	final int SIZE = 6;
	static String sboard0, sboard1;
	Board board2d, board3d;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		sboard0 = "      \n"
				+ "      \n"
				+ "      \n"
				+ "      \n"
				+ "      \n"
				+ "      "; 
		
		sboard1 = "O    ®\n"
				+ "O    ®\n"
				+ "O    ®\n"
				+ "O    ®\n"
				+ "     ®\n"
				+ "  ΩΩ  "; 
	}
	
	@Before
	public void setUp() throws Exception {
		board2d =  new Board2D(SIZE);
		board3d =  new Board3D(SIZE);
	}

	/* Comprueba que cuando pasamos al constructor de PlayerFile, como BufferedReader un valor
	 * null, lanza la excepción NullPointerException
	 */
	@Test(expected=NullPointerException.class)
	public void testPlayerFileNullPointerException() throws FileNotFoundException {
		new PlayerFile("Saul",null);
	}

	//Comprueba getName.
	@Test
	public void testGetName() throws BattleshipIOException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"CraftEmpty.in");
		System.out.println(ip.getName());
		assertEquals("Saul (PlayerFile)", ip.getName());
	}

	/* Crea un fichero vacío, donde indica la constante DIRFILES, y comprueba que putCrafts(board2d) deja a board2d invariable. 
	 * El atributo sboard0 contiene el tablero vacío*/
	@Test
	public void testPutCraftsEmptyFile() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"CraftEmpty.in");
		try {
			ip.putCrafts(board2d);
			System.out.println(board2d.show(false));
		} catch (InvalidCoordinateException | OccupiedCoordinateException | NextToAnotherCraftException | BattleshipIOException e) {}	
	}
	
	/* Comprueba un fichero que solo contiene puts */
	@Test
	public void testPutCraftsOk1() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsOk1.in");
		ip.putCrafts(board2d);
		assertEquals(sboard1, board2d.show(true));
	}
	
	/* Crea un fichero, donde indica la constante DIRFILES, que contenga varios puts y un endput al final y pruébalo sobre el tablero board2d
	 * Comprueba que los Ship se posicionan correctamente */
	@Test
	public void testPutCraftsOk2() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsOk2endput.in");
		ip.putCrafts(board2d);
		assertEquals(sboard1, board2d.show(true));
		
	
	}
	
	/* Crea un fichero, donde indica la constante DIRFILES, con varios puts, añade luego un endput y otro put más después. Comprueba que no se 
	 * puso el Craft último. Vuelve a comprobar lo mismo con otro fichero, poniendo exit al inicio */
	@Test
	public void testPutCraftsOk3() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsOk2endputretard.in");
		ip.putCrafts(board2d);
		assertEquals(sboard1, board2d.show(true));
	}	
	
	/* Aparece al final el comando shoot sin que aparezca un endput antes*/
	@Test
	public void testPutCraftsWrong1() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsWrong1.in");
		try {
		   ip.putCrafts(board2d);
		   fail("Error: se debió lanzar BattleshipIOException");
		} catch (BattleshipIOException e) {
			assertNotNull(e.getMessage());
			assertTrue (e.getMessage().length()>1);
			assertEquals(sboard1,board2d.show(true));
		}
	}
	
	/* Crea un fichero, donde indica la constante DIRFILES, donde en una 
	 * línea aparezca un Aircraft y una Coordinate3D 
	 * e inténtalo poner en un Board2D. Comprueba que se lanza la 
	 * excepción IllegalArgumentException */
	@Test(expected=IllegalArgumentException.class)
	public void testPutCraftsWrong2() throws BattleshipIOException, 
	InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsWrong2.in");
		Board b = new Board2D(5);
		ip.putCrafts(b);
		
	}
	
	/* Crea un fichero, en el directorio que indica la constante DIRFILES, donde haya una orden incorrecta. Comprueba que putCrafts lanza la excepción 
	 * BattleshipIOExeption */
	@Test(expected=BattleshipIOException.class)
	public void testPutCraftsWrong3() throws BattleshipIOException, InvalidCoordinateException, NextToAnotherCraftException, OccupiedCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsWrong3.in");
		Board b = new Board3D(5);
		ip.putCrafts(b);
	}
	
	
	/* Crea un fichero, en el directorio que indica la constante DIRFILES, con órdenes put, con varios espacios en blanco y tabuladores entre put, orientación y coordenadas
	 * Comprueba que los Craft se han añadido */
	@Test
	public void testPutCraftsWithSpaces() throws BattleshipIOException,  NextToAnotherCraftException, OccupiedCoordinateException, InvalidCoordinateException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShipsOk2.in");
		Board b = new Board2D(6);
		ip.putCrafts(b);
		String s = 	"O    ®\n" + 
					"O    ®\n" + 
					"O    ®\n" + 
					"O    ®\n" + 
					"     ®\n" + 
					"  ΩΩ  ";
		assertEquals(b.show(true), s);
		System.out.println(b.show(true));
			
	}
	
	//TODO
	/* Atendiendo a las especificaciones del enunciado, sigue creando tests comprobando distintas situaciones de error:
	/* Líneas con 4 y 1 coordenadas, ships que colisionan, solapan o quedan juntos, Board null, orden put muy corta, coordenadas no 
	 * numéricas, orientación incorrecta, etc  */
	

	
	//TESTS PARA nextShoot
	
	/* Se crean 5 coordenadas y se guardan en un vector. Esas cinco coordenadas aparecen en el mismo orden en
	 * un fichero con shoot cada una de ellas. Se crea un PlayerFile a partir del fichero y se comprueba con
	 * nextShoot que las coordenadas que devuelve son iguales y en el mismo orden a las del vector.
	 * 
	 */
	@Test
	public void testNextShootOk1() throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException {
		Coordinate[] vc = new Coordinate[5];
		for (int i=0; i<5; i++) vc[i]=new Coordinate2D(i,i+1);
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShootOk1.in");
		Coordinate c;
		int i=0;
		while ((c=ip.nextShoot(board2d))!=null) {
			assertEquals(vc[i],c);
			i++;
		}
	}
	
		
	/*Lee disparos en un fichero vacío.*/
	@Test
	public void testNextShootEmpty() throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShootEmpty.in");
		assertNull(ip.nextShoot(board2d));
	}
	
	
    /* Crea un fichero, en el directorio que indica la constante DIRFILES, con una orden put y una endput. Comprueba que nextShoot lanza en ambos casos la excepción 
     * BattleshipIOException  */
	@Test(expected=BattleshipIOException.class)
	public void testNextShootWrong1() throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"shootWrong1.in");
		ip.nextShoot(new Board2D(5));
		
	}
	
	@Test(expected=BattleshipIOException.class)
	public void testNextShootWrong2() throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"shootWrong2.in");
		ip.nextShoot(new Board2D(5));
	}
	
	
	//TODO
	/* Crea un fichero, en el directorio que indica la constante DIRFILES, donde aparezca una orden incorrecta, una orden sin coordenadas, una con 1 coordenada y 
	 * otra con 4 coordenadas. Comprueba que en todos los casos se lanza la excepción BattleshipIOException 
	 */
	@Test
	public void testNextShootWrong3() throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException {
		IPlayer ip = PlayerFactory.createPlayer("paco", DIRFILES+"shootWrong3.in");
		
		for(int i = 1; i <= 5; i++) {
			try {
				ip.nextShoot(new Board2D(5));
				fail("pocos o demasiados enteros para shoot");
			}
			catch(BattleshipIOException e) {
				
			}
		}
		ip.nextShoot(new Board2D(5));
	}
	
	/* Se pasa un Board null a nextShoot. Debe lanzar NullPointerException */
	@Test(expected=NullPointerException.class)
	public void testNextShootNullPointerException() throws BattleshipIOException, InvalidCoordinateException, CoordinateAlreadyHitException {
		IPlayer ip = PlayerFactory.createPlayer("Saul",DIRFILES+"ShootOk1.in");
        ip.nextShoot(null);
	}
	
}
