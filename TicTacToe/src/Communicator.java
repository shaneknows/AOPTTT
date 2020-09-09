import java.util.Scanner;

/**
 * Class responsible for I/O elements of the Tic Tac Toe game
 * @author shane
 *
 */
public class Communicator {
	/**
	 * Scanner for I/O
	 */
	private static Scanner in = new Scanner(System.in);
	
	/**
	 * Display the GameBoard
	 * @param display Display string
	 */
	public static void DisplayGameBoard(String display){
		System.out.println(display);
	}

	/**
	 * Prompt user for input and play tile
	 */
	public static String PromptForTileInput(char currentPlayer) {
		System.out.println("Enter a tile location to place an '" + currentPlayer  + "'");
		System.out.println("(Example '0,1' would place the symbol in the middle square in the first column)");
		return in.nextLine();
	}
	
	/**
	 * Display stalemate message
	 */
	public static void DisplayStalemate(){
		System.out.println("STALEMATE! No more available moves.");
	}
	
	/**
	 * Display victory message and the winner
	 * @param winnerSymbol
	 */
	public static void DisplayWinner(char winnerSymbol){
		System.out.println("GAME OVER. " + winnerSymbol + " wins!");
	}
	
	/**
	 * Prompt users for a rematch
	 */
	public static void Rematch() {
		System.out.println("Hit 'Enter' to play again");
		in.nextLine();
	}
	
	/**
	 * Prints any custom message to the UI (such as an exception)
	 * @param message
	 */
	public static void PrintMessage(String message) {
		System.out.println(message);
	}
}
