/* Nicole Beilis
 * June 15, 2018.
 * Battleship
 */
import java.util.Random;

public class Battleship {
	
	//TEXT FILE
	//DO FOR LARGER BOATS (ADD PICTURES)
	//add menu screen
	//instructions

	final public static int BOARD_SIZE = 10;
	final public static int BOAT_NUM = 10;
	final public static int GUESSED_FULL_SPACE = 2;
	final public static int EMPTY_SPACE = 0;
	final public static int NOT_GUESSED = 0;
	final public static int GUESSED_EMPTY_SPACE = 1;
	final public static int GUESSED_SPACE = 1;
	final public static int FULL_SPACE = 1;
	
	public static void main(String[] args) {
		
		Board b2D = new Board(2*BOARD_SIZE,BOARD_SIZE); //creates board
		Coordinate loc = null;
		int row =0, col = 0;
		int[][] computerBoat;
		computerBoat = new int [2*BOARD_SIZE][BOARD_SIZE]; //stores computer's boats
		int[][] playerGuess; //tracks player's guesses
		playerGuess = new int [2*BOARD_SIZE][BOARD_SIZE];
		int[][] computerGuess; //tracks computer's guesses
		computerGuess = new int [2*BOARD_SIZE][BOARD_SIZE];
		int[][] playerBoat;
		playerBoat = new int [2*BOARD_SIZE][BOARD_SIZE]; //stores players boats
		playerBoats(playerBoat, loc, row, col, b2D);
		computerBoats(computerBoat, b2D);
		while(gameOver(computerBoat, playerBoat, b2D)==false) {
			b2D.displayMessage("Number of boats left to guess for the player: " +boatsLeftPlayer(computerBoat));
			b2D.displayMessageBelow("Number of boats left to guess for the computer: " + boatsLeftComputer(playerBoat));
			playerTurn(loc, row, col, computerBoat, b2D, playerGuess, playerBoat);
			if (gameOver(computerBoat, playerBoat, b2D)==false) {
				computerTurn(playerBoat, b2D, computerGuess);
			}
		}
		
	}
	
	public static int boatsLeftPlayer (int[][]computerBoat) { //PROBLEM: because they get to go again when they are right, it does not stop right away, need to guess one more time
		int numBoatsLeft = BOAT_NUM;
		for (int i = 0; i<2*BOARD_SIZE; i++) {
			for (int j = 0; j<BOARD_SIZE; j++) {
				if (computerBoat[i][j]==GUESSED_FULL_SPACE) {
					numBoatsLeft--;
				}
			}
		}
		return numBoatsLeft;
	}
	
	public static int boatsLeftComputer (int[][]playerBoat) {
		int numBoatsLeft = BOAT_NUM;
		for (int i = 0; i<2*BOARD_SIZE; i++) {
			for (int j = 0; j<BOARD_SIZE; j++) {
				if (playerBoat[i][j]==GUESSED_FULL_SPACE) {
					numBoatsLeft--;
				}
			}
		}
		return numBoatsLeft;
	}
	
	public static boolean gameOver(int[][]computerBoat, int[][]playerBoat, Board b2D) {
		boolean gameOver = false;
		int playerNum = 0;
		int computerNum = 0;
		for (int i = 0; i<2*BOARD_SIZE; i++) {
			for (int j = 0; j<BOARD_SIZE; j++) {
				if (computerBoat[i][j]==GUESSED_FULL_SPACE) {
					playerNum++;
				}
				if (playerBoat[i][j]==GUESSED_FULL_SPACE) {
					computerNum++;
				}
			}
		}
		if (playerNum==BOAT_NUM) {
			b2D.displayMessageTop("Player wins.");
			gameOver = true;
		}
		if (computerNum==BOAT_NUM) {
			b2D.displayMessageTop("Computer wins.");
			gameOver = true;
		}
		return gameOver;
	}
	
	/** Player's turn
	 * Pre:
	 * Post:
	*/
	public static void playerTurn (Coordinate loc, int row, int col, int[][]computerBoat, Board b2D, int[][]playerGuess, int[][]playerBoat) {
		b2D.displayMessageTop("Player's Turn.");
		int clickCheck = 0; //check naming convention
		while (clickCheck==0 && gameOver(computerBoat, playerBoat, b2D)==false) {
			loc = b2D.getClick();
			row = loc.getRow();
			col = loc.getCol();
			if (computerBoat[row][col]==EMPTY_SPACE && row<BOARD_SIZE && playerGuess[row][col]==NOT_GUESSED) {
				b2D.putPeg("white", row, col);
				playerGuess[row][col]=GUESSED_EMPTY_SPACE;
				clickCheck = 1;
			}
			else if (computerBoat[row][col]==FULL_SPACE && row<BOARD_SIZE && playerGuess[row][col]==NOT_GUESSED) {
				b2D.putPeg("red", row, col);
				computerBoat[row][col]=GUESSED_FULL_SPACE;
			}
		}
	}
	
	/** Stores computer's ships
	 * Pre:
	 * Post:
	*/
	public static void computerBoats(int [][]computerBoat, Board b2D){
		b2D.displayMessageTop("Computer is setting up boats.");
		Random r = new Random();
		int num = BOAT_NUM;
		while (num!=0) {
			int i = r.nextInt(BOARD_SIZE);
			int j = r.nextInt(BOARD_SIZE);
			if (!(computerBoat[i][j]==FULL_SPACE)) { 
				computerBoat[i][j]=FULL_SPACE;
				b2D.putPeg("green", i, j); //for debugging, shows where computer's boats are
				num--;
			}
		}
	}
	
	/** Records computer's turn
	 * Pre:
	 * Post:
	*/
	public static void computerTurn(int[][]playerBoat, Board b2D, int[][]computerGuess) {
		b2D.displayMessageTop("Computer's Turn.");
		Random r = new Random();
		int clickCheck =0;
//		try
//		{
//			Thread.sleep (500); //delays computer
//		}
//		catch (InterruptedException e)
//		{
//			;
//		}
		while (clickCheck==0) {
			int i = r.nextInt(BOARD_SIZE);
			int j = r.nextInt(BOARD_SIZE);
			if (playerBoat[i+BOARD_SIZE][j]==EMPTY_SPACE && computerGuess[i+BOARD_SIZE][j]==NOT_GUESSED) {
				b2D.putPeg("white", i+BOARD_SIZE, j);
				computerGuess[i+BOARD_SIZE][j]=GUESSED_EMPTY_SPACE;
				clickCheck=1;
			}
			else if (playerBoat[i+BOARD_SIZE][j]==FULL_SPACE && computerGuess[i+BOARD_SIZE][j]==NOT_GUESSED) {
				b2D.putPeg("red", i+BOARD_SIZE, j);
				playerBoat[i+BOARD_SIZE][j]=GUESSED_FULL_SPACE;
				computerGuess[i+BOARD_SIZE][j]=GUESSED_SPACE;
			}
		}
	}
	
	/** Stores player's boats
	 * Pre:
	 * Post:
	*/
	public static void playerBoats (int[][]playerBoat, Coordinate loc, int row, int col, Board b2D) {
		//need to create sized boats and a certain amount of boats
		int num = BOAT_NUM;
		while (num!=0) {
			b2D.displayMessageTop("Place " + num + " boat(s) in the bottom grid by clicking.");
			loc = b2D.getClick(); 
			row = loc.getRow();
			col = loc.getCol();
			if (row>=BOARD_SIZE && !(playerBoat[row][col]==FULL_SPACE)) { 
				playerBoat[row][col]=FULL_SPACE;
				num--;
				b2D.putPeg("green", row, col);
			}
			if (num==0) {
				b2D.displayMessage("Player's Turn.");
			}
		}
	}
	
}
