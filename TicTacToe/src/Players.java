/**
 * Class that stores Players in a game
 * and contains methods related to the player information
 * @author shane
 *
 */
public class Players {
	public char[] PlayersList = new char[]{ 'X', 'O' };
	private int currentPlayer = 0;
	
	private static Players CurrentPlayers = null;
	
	// Get single instance of Players
	public static Players getInstance() {
		if (CurrentPlayers == null)
			CurrentPlayers = new Players();
		return CurrentPlayers;
	}
	
	/**
	 * Retrieve the current player
	 * @return
	 */
	public char GetCurrentPlayer() {
		return GetPlayerSymbol(currentPlayer);
	}

	/**
	 * Retrieve the symbol for the designated player
	 * @param player The Player ID
	 * @return The Player symbol
	 */
	public char GetPlayerSymbol(int player) {
		return PlayersList[player];
	}
	
	/**
	 * Toggle the current player
	 */
	public void NextPlayer() {
		if (currentPlayer == 0) {
			currentPlayer = 1;
		} else {
			currentPlayer = 0;
		}
	}
}
