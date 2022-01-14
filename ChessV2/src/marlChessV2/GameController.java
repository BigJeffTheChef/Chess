package marlChessV2;

import java.util.Scanner;

public class GameController {

	public static final boolean TEST_MODE = true;
	public static char currentPlayer = 'w';
	public static ChessBoard b = new ChessBoard();
	public static int[][] memory = new int[][] { { -1, -1 }, { -1, -1 } };
	public static Scanner scanner = new Scanner(System.in);
	public static boolean moveSuccessful = true;

	public static void main(String[] args) {
		userInterface();
	}

	public static void userInterface() {
		boolean gameOver = false;
		while (!gameOver) {
			b.displayBoard();
			for (int userGo = 0; userGo <= 1 && !gameOver; userGo++) {
				boolean continuePrompting = true;
				String selection = "";
				// ensure selection is within length range
				while (continuePrompting) {
					displayMenuPrompt(userGo);
					if (userGo == 0) {
						clearMemory();
					}
					moveSuccessful = true;
					selection = scanner.nextLine().toUpperCase();
					int length = selection.length();

					if (length < 1 || length > 2) {
						// CANCEL SELECTED OR BAD INPUT

						if (selection.equalsIgnoreCase("cancel")) {
							System.out.println("Cancelling move...");
							userGo = -1;
							continuePrompting = false;
							continue;
						}
						System.out.println("Invalid selection. Please try again!");
						continue;
					} else if (length == 1) {
						// MENU ITEM SELECTED

						int menuItem = selection.charAt(0) - 48;
						switch (menuItem) {
						case 1:
							System.out.println("You selected 1.");
							b.printCapturedPieces(scanner);
							break;
						case 2:
							displayHelp(scanner);
							break;
						case 3:
							System.out.println("You selected 3.");
							boolean quitConfirmed = confirmQuit();
							if (quitConfirmed) {
								gameOver = true;
								continuePrompting = false;
							}
							continue;
						default:
							System.out.println("Invalid menu selection: Please try again!");
							continue;
						}
					} else if (length == 2) {
						// COORD INPUTTED
						//TODO BUSTED LOGIC HERE

						int[] tmp = convertCoordToIndices(selection);
						if (tmp[0] >= 0 && tmp[0] <= 7 && tmp[1] >= 0 && tmp[1] <= 7) {
							// if coord are acceptable

							if (userGo == 0) {
								// IF ON SOURCE SQUARE SELECTION
								if (b.getSquare(tmp) == null) {
									continue;
								} else if (b.getSquare(tmp).getTeam() == b.getCurrentPlayer()) {
									memory[userGo] = tmp;
									continuePrompting = false;
								}
							} else if (userGo == 1) {
								// IF ON DESTINATION SQUARE SELECTION
								if (b.getSquare(tmp) == null || b.getSquare(tmp).getTeam() != b.getCurrentPlayer()) {
									memory[userGo] = tmp;
									continuePrompting = false;
								}

							}
						}

					}
				}

			}
			// execute move, and if successful change player
			if (!gameOver) {
				moveSuccessful = b.executeMove(memory);
				if (moveSuccessful) {
					b.changeCurrentPlayer();
				}
			}
		}
		rollCredits();
	}

	//////////////////////////////
	//			UTILITY			//
	//////////////////////////////

	public static void displayHelp(Scanner scanner) {
		System.out.println("___________________________________");

		System.out.println("The game works as follows:");

		System.out.println("- During your move you may:");
		System.out.println("\t1) Reset your move by entering \"cancel\"");
		System.out.println("\t2) Select a menu item");
		System.out.println("\t3) Continue your round by following the prompt (more below)");

		System.out.println("- You will be prompted for two selections");
		System.out.println("\t1) Select one of your pieces");
		System.out.println("\t2) Select a destination square");

		System.out.println("- If either of the following happens your move will be reset");
		System.out.println("\t1) You select an opponents piece instead of your own");
		System.out.println("\t2) You select an invalid move");

		System.out.println("___________________________________");

		System.out.println("Press Enter to continue...");
		scanner.nextLine();
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
		System.exit(0);
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

	public static void displayMenuOptions() {
		String player = (b.getCurrentPlayer() == 'w') ? "White" : "Black";
		System.out.printf("CURRENT PLAYER: %s%n", player);
		System.out.println("MENU:");
		System.out.println("1. Display Captured Pieces");
		System.out.println("2. Help");
		System.out.println("3. Quit");
	}

	/**
	 * print the menu
	 * 
	 * @param i - 0 or 1. depending on which part of the user's go it is, the menu will be slightly different.
	 */
	public static void displayMenuPrompt(int i) {
		if (i == 1) {
			System.out.println("You have selected (" + convertIndicesToCoord(memory[0]) + ")");
		}
		System.out.println("- Please select " + ((i == 0) ? "one of your pieces" : "a destination square") + " (E.G A1), a menu option or \"cancel\" to start move again:");
		if (!moveSuccessful) {
			String source = convertIndicesToCoord(memory[0]);
			String dest = convertIndicesToCoord(memory[1]);
			System.out.println("*** MOVE (" + source + " to " + dest + ") WAS NOT VALID SORRY! PLEASE TRY AGAIN " + ((b.getCurrentPlayer() == 'w') ? "WHITE" : "BLACK") + " PLAYER ***");
		}
	}

	/**
	 * Convert board coordinates to indices.
	 * 
	 * @param coord (EG A1)
	 * @return An int[2] (EG [0,0])
	 */
	public static int[] convertCoordToIndices(String coord) {
		return new int[] { coord.charAt(1) - 49, coord.charAt(0) - 65 };
	}

	/**
	 * Convert board indices to coordinates.
	 * 
	 * @param indices (EG [0,0])
	 * @return coord (EG A1)
	 */
	public static String convertIndicesToCoord(int[] indices) {
		return String.format("%c%d", (char) indices[1] + 65, indices[0] + 1);
	}
}
