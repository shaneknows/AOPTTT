public aspect Referee {
	/**
	 * Game board has been displayed
	 */
	pointcut boardDisplayed() : execution (* Communicator.DisplayGameBoard(*));
		
	/**
	 * Tile has been played 
	 */
	pointcut makeMove(char currentPlayer, String currentInput) : execution (* GameBoard.PlayTile(*, *)) && args(currentPlayer, currentInput);
	
	/**
	 * End game displayed
	 */
	pointcut gameOver() : execution (* *.DisplayStalemate()) || execution(* *.DisplayWinner(*));
	
	/**
	 * Board has been displayed. Need to determine state of the game and prompt for input
	 */
	after() : boardDisplayed() {
		GameBoard gb = GameBoard.getInstance();		
		Players p = Players.getInstance();
		
		// Check win state
		if (gb.DidIWin(p.GetCurrentPlayer())) {
			Communicator.DisplayWinner(p.GetCurrentPlayer());
		}
		else if (gb.DidWeStalemate()) {
			Communicator.DisplayStalemate();
		} else {
			// Update current player and prompt for input
			p.NextPlayer();			
			String currentInput = Communicator.PromptForTileInput(p.GetCurrentPlayer());
			gb.PlayTile(p.GetCurrentPlayer(), currentInput);
		}
	}
	
	/**
	 * Validation after receiving a move, but before actually playing
	 */
	before(char currentPlayer, String currentInput): makeMove(currentPlayer, currentInput) {
		GameBoard gb = GameBoard.getInstance();
		try {
			// Verify the format is exactly 3 characters
			if (currentInput.length() != 3) {
				throw new InvalidTileLocationException("'" + currentInput + "' is not a valid input.");
			}
			
			// Parse value
			String[] inputs = currentInput.split(",");
			int col = Integer.parseInt(String.valueOf(inputs[0].charAt(0)));
			int row = Integer.parseInt(String.valueOf(inputs[1].charAt(0)));
			
			// Check inputs are valid
			if (!(col >= 0 && col < 3) || !(row >= 0 && row < 3)) {
				throw new InvalidTileLocationException("Tile does not exist at location '" + col + ", " + row + "'");
			}
			
			// Verify spot is available on board
			if (gb.GetValueAtTile(col, row) != '_'){
				throw new InvalidTileLocationException("Tile location: '" + currentInput + "' is already taken!");
			}
		}
		// Catch tile specific issues
		catch (InvalidTileLocationException e) {
			Communicator.PrintMessage(e.getMessage());
			currentInput = Communicator.PromptForTileInput(currentPlayer);
			gb.PlayTile(currentPlayer, currentInput);
		}
		// Catch all for invalid inputs
		catch (Exception e) {
			Communicator.PrintMessage("Invalid Input Detected.");
			currentInput = Communicator.PromptForTileInput(currentPlayer);
			gb.PlayTile(currentPlayer, currentInput);
		}
	}
		
	/**
	 * After the tile has been played
	 */
	after(char currentPlayer, String currentInput): makeMove(currentPlayer, currentInput){
		Communicator.DisplayGameBoard(GameBoard.getInstance().Display());
	}
	
	/**
	 * Reset the game
	 */
	after(): gameOver(){
		Communicator.Rematch();
		GameBoard gb = GameBoard.getInstance();
		gb.InitializeTiles();
		Communicator.DisplayGameBoard(gb.Display());
	}
}
