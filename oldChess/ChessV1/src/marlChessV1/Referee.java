package marlChessV1;

import java.util.Arrays;
import java.util.Scanner;
/**
 * PAWNS
 * TODO PAWN en passant
 * TODO PAWN cannot capture backwards
 * 
 * ROOKS
 * TODO ROOK & KING castling
 * 
 * KING
 * TODO KING not allowed allowed to move into check
 * TODO KING not allowed to move adjacent to enemy king
 * TODO KING check
 * 
 * GAME OVER
 * TODO GAMEOVER checkmate
 * TODO GAMEOVER surrender
 * TODO GAMEOVER draw
 * TODO GAMEOVER stalemate
 * TODO CHECK
 * 
 * @author Peter Marley
 *
 */
public class Referee {
	
	// Test mode settings
	public static final boolean TEST_MODE = false;
	public static final boolean ALLOW_PLAYER_CHANGE = true;

	public static Scanner scanner = new Scanner(System.in);
	public static ChessBoard board = new ChessBoard(TEST_MODE);
	public static char player = 'w';
	public static int[][] memory = new int[][] { { -1, -1 }, { -1, -1 } };
	public static int menuItems = 0;
	public static long chessClockWhite = (TEST_MODE) ? 3600000 : 0;
	public static long chessClockBlack = (TEST_MODE) ? 3600000 : 0;

	/**
	 * Beginning and end of program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		startUserInterface();
		rollCredits();
	}

	//////////////////////////////
	//		PROGRAM CONTROL		//
	//////////////////////////////

	/**
	 * The repeating menu system of the game. Executes the users menu selection.
	 */
	public static void startUserInterface() {
		// set flags
		boolean toContinue = true;
		boolean notifyMoveFailure = false;
		boolean notifyPlayerPieceSelectionFailure = false;

		// main game loop
		while (toContinue) {

			String response = null;
			boolean moveSuccessful = true;
			clearMemory();

			// a player round; get two validated coordinates (while allowing menu selections or "cancel")
			for (int i = 0; i < 2 && toContinue; i++) {
				if (!TEST_MODE) {
					System.out.printf("%n".repeat(20));
				}
				//board.displayBoard(testModeActive);
				displayMenu(i);
				if (notifyPlayerPieceSelectionFailure) {
					System.out.println("Whoops that wasn't one of your pieces. Try another square!");
					notifyPlayerPieceSelectionFailure = false;
				}

				if (i > 0) {
					System.out.printf("%nCurrently selected piece: %s%n", convertArrIndicesToCoords(memory[0]));
				}
				if (notifyMoveFailure) {
					System.out.println("Sorry that move was not valid, please try again!");
					notifyMoveFailure = false;
				}

				// get user response and add time taken to make move to players chess clock
				long timeBeforeAction = System.currentTimeMillis();
				response = scanner.nextLine();
				long timeTaken = System.currentTimeMillis() - timeBeforeAction;
				if (player == 'w') {
					chessClockWhite += timeTaken;
				} else {
					chessClockBlack += timeTaken;
				}

				// cancel move if user request or the user response was invalid
				if (response.equals("cancel") || !validateResponse(response)) {
					i = -1;
					continue;
				}

				if (response.length() == 1) { // if user selected menu
					if (response.equals("1")) {
						board.printCapturedPieces();
						i--;
						continue;
					} else {
						toContinue = !confirmQuit();
						break;
					}
				} else if (response.length() == 2) { // if user selected chessBoard square
					memory[i] = convertCoordsToArrIndices(response);
					if (i == 0) {
						if (!board.isPlayersPiece(memory[i], player)) {
							notifyPlayerPieceSelectionFailure = true;
							i = -1;
						}
					}
				}

			} // end of player round

			// if quit selected from menu exit main loop - returns program execution to main()
			if (!toContinue)
				break;
			// if anything but quit: execute move
			moveSuccessful = board.movePiece(Arrays.copyOf(memory[0], 2), Arrays.copyOf(memory[1], 2), player);

			// if move successful change active player, if unsuccessful give the user another turn
			if (moveSuccessful) {
				if (!TEST_MODE || ALLOW_PLAYER_CHANGE) {
					player = (player == 'w') ? 'b' : 'w';
				}
			} else {
				notifyMoveFailure = true;
			}

		} // end of game loop
	} // end of userInterface()

	/**
	 * Takes a string and calculates if it is either a valid chess square (e.g. A5) or a valid menu selection (range 1 to menuItems)
	 * 
	 * @param input
	 * @return a boolean representing weather the user input was accepted
	 */
	public static boolean validateResponse(String input) {
		String errorMessage = "Sorry \"" + input + "\" was not a recognised input. Please try again!";
		input = input.toUpperCase();
		switch (input.length()) {
		case 1:
			int menuSelection = -1;
			try {
				menuSelection = Integer.valueOf(input);
			} catch (NumberFormatException e) {
				System.out.println(errorMessage);
				return false;
			}
			if (menuSelection >= 1 && menuSelection <= menuItems) {
				return true;
			}
			break;
		case 2:
			input = input.toUpperCase();
			char char0 = input.charAt(0);
			char char1 = input.charAt(1);
			if ((char0 >= 'A' && char0 <= 'H') && (char1 >= '1' && char1 <= '8')) {
				return true;
			}
			break;
		default:
			System.out.println(errorMessage);
		}
		return false;
	}

	/**
	 * Clears the static int[][] memory
	 */
	public static void clearMemory() {
		for (int i = 0; i < memory.length; i++) {
			for (int j = 0; j < memory[i].length; j++) {
				memory[i][j] = -1;
			}
		}
	}

	/**
	 * Ask's the user if they are sure they would like to quit
	 * 
	 * @return a boolean - representing their confirmation of quit
	 */
	public static boolean confirmQuit() {
		System.out.println("Are you sure you want to quit? (Y/N)");
		String input = "";
		for (int i = 0; !(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")); i++) {
			if (i != 0) {
				System.out.println("Sorry you must enter \"Y\" or \"N\"");
			}
			input = scanner.nextLine();
		}

		return input.equalsIgnoreCase("Y") ? true : false;
	}

	//////////////////////////////
	//			UTILITY			//
	//////////////////////////////

	/**
	 * Thanks user for playing, and prints my name
	 */
	public static void rollCredits() {
		System.out.println("Thanks for playing!");
		try {
			for (int i = 0; i < 4; i++) {
				Thread.sleep(500);
				System.out.println(".");
			}
		} catch (InterruptedException e) {
		}
		System.out.printf("%n".repeat(50));
		System.out.println("This game brought to you by novice programmer");

		System.out.println(" ______   ______     ______   ______     ______        __    __     ______     ______     __         ______     __  __    ");
		System.out.println("/\\  == \\ /\\  ___\\   /\\__  _\\ /\\  ___\\   /\\  == \\      /\\ \"-./  \\   /\\  __ \\   /\\  == \\   /\\ \\       /\\  ___\\   /\\ \\_\\ \\   ");
		System.out.println("\\ \\  _-/ \\ \\  __\\   \\/_/\\ \\/ \\ \\  __\\   \\ \\  __<      \\ \\ \\-./\\ \\  \\ \\  __ \\  \\ \\  __<   \\ \\ \\____  \\ \\  __\\   \\ \\____ \\");
		System.out.println(" \\ \\_\\    \\ \\_____\\    \\ \\_\\  \\ \\_____\\  \\ \\_\\ \\_\\     \\ \\_\\ \\ \\_\\  \\ \\_\\ \\_\\  \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_____\\  \\/\\_____\\");
		System.out.println("  \\/_/     \\/_____/     \\/_/   \\/_____/   \\/_/ /_/      \\/_/  \\/_/   \\/_/\\/_/   \\/_/ /_/   \\/_____/   \\/_____/   \\/_____/");

	}

	/**
	 * This pauses program until user types Y or y and presses enter
	 */
	public static void promptContinue() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String input = "";
		while (!input.equalsIgnoreCase("Y")) {
			System.out.println("Continue? (Y)");
			input = scanner.nextLine();
		}
	}

	/**
	 * provide a String representation of that square, and return an int[2] representing chessBoard indices
	 * 
	 * @param coordString - A String representing a board square "A1", "H6" etc
	 * @return An int[] holding the row and column of the selection
	 */
	public static int[] convertCoordsToArrIndices(String coordString) {
		coordString = coordString.toUpperCase();
		int row = coordString.charAt(1) - 49; // convert ASCII-int to int-1
		int col = coordString.charAt(0) - 65; // convert ASCII-UppercaseChar to int
		return new int[] { row, col };
	}

	/**
	 * provide an int[2] representing chessBoard indices, and return a String representation of that square
	 * 
	 * @param coordArr - An int[] holding the row and column of the selection
	 * @return A String representing a board square "A1", "H6" etc
	 */
	public static String convertArrIndicesToCoords(int[] coordArr) {
		char letter = (char) (coordArr[1] + 65);
		int number = coordArr[0] + 1;
		return String.format("%c%d", letter, number);
	}

	/**
	 * Displays the ChessBoard lovely little menu
	 * 
	 * @param i - used in logic that decides part of menu's text (0 or 1 only)
	 */
	public static void displayMenu(int i) {
		board.displayBoard(TEST_MODE);
		String divider = "_".repeat(48);
		String currentPlayer = (player == 'w') ? "White" : "Black";
		long seconds = (player == 'w') ? (chessClockWhite / 1000) % 60 : (chessClockBlack / 1000) % 60;
		long minutes = (player == 'w') ? (chessClockWhite / 1000) / 60 : (chessClockBlack / 1000) / 60;
		menuItems = 0;
		System.out.printf("%-48s%n%-48s|%n", divider, "");
		System.out.printf("%-48s|%n", "MENU:");
		System.out.printf("%d%-47s|%n", ++menuItems, ". Check Captured and Lost");
		System.out.printf("%d%-47s|%n", ++menuItems, ". Quit");
		System.out.printf("%-48s|%n", divider);
		System.out.printf("%-48s|%n%-48s|%n", "POSSIBLE ACTIONS:", "");
		System.out.printf("%-48s|%n", "  - Select " + ((i == 0) ? "one of your pieces" : "a destination square") + " (A5, B7 etc)");
		System.out.printf("%-48s|%n", "  - Select a menu item.");
		System.out.printf("%-48s|%n", "  - Cancel your selection by entering \"cancel\"");
		System.out.printf("%-48s|%n", divider);
		System.out.printf("%-48s|%n%-48s|%n", "", "CURRENT PLAYER: " + currentPlayer);
		System.out.printf("%-48s|%n", "  + " + currentPlayer + " chess clock: " + minutes + "m " + seconds + "s... and counting");
		System.out.printf("%-48s|%n", divider);
	}
}