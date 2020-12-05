package model;

import java.util.Objects;

import model.exceptions.CoordinateAlreadyHitException;
import model.exceptions.InvalidCoordinateException;
import model.exceptions.NextToAnotherCraftException;
import model.exceptions.OccupiedCoordinateException;
import model.exceptions.io.BattleshipIOException;
import model.io.IPlayer;
import model.io.IVisualiser;


/**
 * The Class Game.
 * @auhor Alejandro Seguí Apellániz 48793265F
 * @version 11.0.8
 */
public class Game {
	
	/** The game started. */
	private boolean gameStarted;
	
	/** The next to shoot. */
	private int nextToShoot;
	
	/** The shoot counter. */
	private int shootCounter;
	
	/** The player 2. */
	private IPlayer player1, player2;
	
	/** The board 2. */
	private Board board1, board2;
	
	/**
	 * Instantiates a new game.
	 *
	 * @param b1 the b 1
	 * @param b2 the b 2
	 * @param p1 the p 1
	 * @param p2 the p 2
	 */
	public Game(Board b1, Board b2, IPlayer p1, IPlayer p2) {
		Objects.requireNonNull(b1);
		Objects.requireNonNull(b2);
		Objects.requireNonNull(p1);
		Objects.requireNonNull(p2);
		
		player1 = p1;
		player2 = p2;
		board1 = b1;
		board2 = b2;
		
		nextToShoot = 1;
		shootCounter = 0;
		
		gameStarted = false;
	}
	
	/**
	 * Gets the player 1.
	 *
	 * @return the player 1
	 */
	public IPlayer getPlayer1() {
		return player1;
	}
	
	/**
	 * Gets the player 2.
	 *
	 * @return the player 2
	 */
	public IPlayer getPlayer2() {
		return player2;
	}
	
	/**
	 * Gets the player last shoot.
	 *
	 * @return the player last shoot
	 */
	public IPlayer getPlayerLastShoot() {
		IPlayer ultimo = null;
		
		if(shootCounter != 0) {
			if(nextToShoot == 1) {
				ultimo = player2;
			}
			else {
				ultimo = player1;
			}
		}
		return ultimo;
	}
	
	/**
	 * Gets the board 1.
	 *
	 * @return the board 1
	 */
	public Board getBoard1() {
		return board1;
	}
	
	/**
	 * Gets the board 2.
	 *
	 * @return the board 2
	 */
	public Board getBoard2() {
		return board2;
	}
	
	/**
	 * Start.
	 */
	public void start() {
		//Jugadores ponen barcos el tablero
		//Player1 -> board1
		//player2 -> board2
		
		try {
			player1.putCrafts(board1);
			player2.putCrafts(board2);
			
		}
		catch(InvalidCoordinateException | OccupiedCoordinateException | NextToAnotherCraftException | BattleshipIOException e){
			throw new RuntimeException("Error al colocar los barcos");
		}
		//Inicializamos atributos
		gameStarted = true;
		shootCounter = 0;
		nextToShoot = 1;
	}
	
	/**
	 * Game ended.
	 *
	 * @return true, if successful
	 */
	public boolean gameEnded() {
		boolean end = false;
		if((board1.areAllCraftsDestroyed() || board2.areAllCraftsDestroyed()) && gameStarted == true) {
			end = true;
		}
		
		return end;
	}
	
	/**
	 * Play next.
	 *
	 * @return true, if successful
	 */
	public boolean playNext() {
		Coordinate cord = null;
		boolean lineas = false;
		
		if(nextToShoot == 2) {
			try {
				cord = player2.nextShoot(board1);
				if(cord != null) {
					lineas = true;
				}
				
			}
			catch(CoordinateAlreadyHitException e) { 
				System.out.print("Action by ");
				System.out.println(player2.getName());
				lineas = true;
			}
			catch(BattleshipIOException | InvalidCoordinateException  e) { 
				throw new RuntimeException("Se produce ERROR al lanzar");
			}
		}
		else {
			if(nextToShoot == 1) {
				try {
					cord = player1.nextShoot(board2);
					if(cord != null) {
						lineas = true;
					}
				} 
				catch(CoordinateAlreadyHitException e) {
					System.out.print("Action by ");
					System.out.println(player1.getName());
					lineas = true;
				}
				catch(BattleshipIOException | InvalidCoordinateException e) {
					throw new RuntimeException("Se produce ERROR al lanzar");
				}
			}
		}
		if(lineas == true) {
			shootCounter++;
			if(nextToShoot == 1) {
				nextToShoot = 2;
			}
			else {
				nextToShoot = 1;
			}
		}
		return lineas;
	}
	
	/**
	 * Play game.
	 *
	 * @param visualiser the visualiser
	 */
	public void playGame(IVisualiser visualiser) {
		boolean c = true;
		start();
		visualiser.show();
		do{
			c = playNext();
			if(c == true) {
				visualiser.show();
			}
		}while((c == true) && (gameEnded() == false));
		visualiser.close();
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		String output;
		
		if(gameStarted == false) {
			output = "=== GAME NOT STARTED ===\n";
		}
		else {
			if(gameEnded()== false) {
				output = "=== ONGOING GAME ===\n";
			}
			else {
				output = "=== GAME ENDED ===\n";
			}
		}
		
		output += "==================================\n";
		output += player1.getName();
		output += "\n==================================\n";
		output += board1.show(false);
		output += "\n==================================\n";
		output += player2.getName();
		output += "\n==================================\n";
		output += board2.show(false);
		output += "\n==================================\n";
		output += "Number of shots: " + shootCounter;
		
		
		if(gameEnded() == true) {
			if(board1.areAllCraftsDestroyed()) {
				output += "\n" + player2.getName() ;
			}
			else {
				output += "\n" + player1.getName() ;					
			}
			output += " wins";
		}
		
		return output;
	}
	
}
