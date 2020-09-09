/**
 * Class holding the GameBoad tile array and associated methods
 * @author shane
 *
 */
public class GameBoard {
	private char[][] Tiles;
	private final int GRID_SIZE = 3;
	private char EMPTY_TILE = '_';
	
	private static GameBoard Game = null;
	
	/**
	 * Get single instance of game board
	 * @return The GameBoard
	 */
	public static GameBoard getInstance() {
		if (Game == null)
			Game = new GameBoard();
		return Game;
	}
	
	/**
	 * Clear Tiles values, resetting the game
	 */
	public void InitializeTiles() {
		Tiles = new char[GRID_SIZE][GRID_SIZE];
		for (int i=0; i<GRID_SIZE; i++) {
			for (int j=0;j<GRID_SIZE;j++) {
				Tiles[i][j] = EMPTY_TILE;
			}
		}
	}
	
	/**
	 * Get Gameboard display string
	 * @return Gameboard string for displaying
	 */
	public String Display() {
		String result = "";
		for (int i=0; i<GRID_SIZE; i++) {
			for (int j=0;j<GRID_SIZE;j++) {
				if (j == GRID_SIZE-1) {
					result += Tiles[i][j] + "\n";
				} else {
					result += Tiles[i][j];
				}
			}
		}
		return result;
	}
	
	/**
	 * Set tile in gameboard
	 * @param player The Player Symbol
	 * @param tileLocation The Tile Location (x,y)
	 */
	public void PlayTile(char player, String tileLocation) {
		int col = Character.getNumericValue(tileLocation.charAt(0));
		int row = Character.getNumericValue(tileLocation.charAt(2));
		
		this.Tiles[col][row] = player;
	}
	
	/**
	 * Retrieve the value present at a tile location
	 * @param col The Column ID
	 * @param row The Row ID
	 * @return
	 */
	public char GetValueAtTile(int col, int row) {
		return Tiles[col][row];
	}
	
	/**
	 * Check if there is a valid winner 
	 * @param currentPlayer The current player symbol
	 * @return If win state has been detected
	 */
	public boolean DidIWin(char currentPlayer) {
		for (int index = 0; index < GRID_SIZE; index++) {
			// Check Vertical Wins
			if (Tiles[index][0] == currentPlayer && Tiles[index][0] == Tiles[index][1] && Tiles[index][0] == Tiles[index][2])
				return true;
			
			// Check Horizontal Wins
			if (Tiles[0][index] == currentPlayer && Tiles[0][index] == Tiles[1][index] && Tiles[0][index] == Tiles[2][index])
				return true;
		}
		
		// Check Diagonal Wins
		if (Tiles[1][1] == currentPlayer && 
				(Tiles[0][0] == Tiles[1][1] && Tiles[0][0] == Tiles[2][2]
				|| Tiles[2][0] == Tiles[1][1] && Tiles[2][0] == Tiles[0][2]))
			return true;
		
		// No winner yet
		return false;
	}
	
	/**
	 * Checks for any remaining empty tiles (Call after seeing if we have a winner) 
	 * @return
	 */
	public boolean DidWeStalemate() {
		for (int i=0; i<GRID_SIZE; i++) {
			for (int j=0;j<GRID_SIZE;j++) {
				if (Tiles[i][j] == EMPTY_TILE) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Private Constructor
	 */
	private GameBoard() {
		InitializeTiles();
	}
}
