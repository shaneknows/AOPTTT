/**
 * Main class. Causes initial display and the referee takes it from there 
 * @author shane
 *
 */
public class Driver {
	public static void main(String[] args) {
		Communicator.DisplayGameBoard(GameBoard.getInstance().Display());
	}
}
