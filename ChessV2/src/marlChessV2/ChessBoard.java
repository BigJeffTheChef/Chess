package marlChessV2;

import java.util.ArrayList;
import java.util.Scanner;

public class ChessBoard {

	private ChessPiece[][] board = new ChessPiece[8][8];
	private char currentPlayer = 'w';
	private int[] lastMovedPiece = new int[] { -1, -1 };
	private ArrayList<ChessPiece> captured = new ArrayList<ChessPiece>(32);

	//////////////////////////////
	//		CONSTRUCTORS		//
	//////////////////////////////

	/**
	 * fully parameterised constructor
	 * 
	 * @param test
	 */
	@SuppressWarnings("unused")
	public ChessBoard() {
		int rowBlackSpecials = 7;
		int rowBlackPawns = 6;
		int rowWhitePawns = 1;
		int rowWhiteSpecials = 0;
		if (!GameController.TEST_MODE) {
			// Set pieces on the board
			for (int i = 0; i < 8; i++) {
				// place pawns
				this.setSquare(new Pawn('b'), rowBlackPawns, i);
				this.setSquare(new Pawn('w'), rowWhitePawns, i);
				// place special pieces
				if (i == 0 || i == 7) {
					//this.setSquare("b", "rook", rowBlackSpecials, i);
					//this.setSquare("w", "rook", rowWhiteSpecials, i);
				} else if (i == 1 || i == 6) {
					//this.setSquare("b", "knight", rowBlackSpecials, i);
					//this.setSquare("w", "knight", rowWhiteSpecials, i);
				} else if (i == 2 || i == 5) {
					//this.setSquare("b", "bishop", rowBlackSpecials, i);
					//this.setSquare("w", "bishop", rowWhiteSpecials, i);
				} else if (i == 3) {
					//this.setSquare("b", "king", rowBlackSpecials, i);
					//this.setSquare("w", "king", rowWhiteSpecials, i);
				} else if (i == 4) {
					//this.setSquare("b", "queen", rowBlackSpecials, i);
					//this.setSquare("w", "queen", rowWhiteSpecials, i);
				}
			}
		} else {
			// test pieces board
			//lastMovedPiece = new int[] { 1, 0 };
			//for (int i = 0; i < 8; i++) {
			// place pawns
			this.setSquare(new Pawn('b'), 4, 0);
			this.setSquare(new Pawn('b'), 4, 1);

			this.setSquare(new Pawn('w'), 3, 0);
			this.setSquare(new Pawn('w'), 3, 1);
			//}
		}

	}

	//////////////////////////////
	//		BOARD METHODS		//
	//////////////////////////////

	public boolean executeMove(int[][] memory) {
		return this.executeMove(memory[0], memory[1]);
	}

	/**
	 * Add a piece to the captured ArrayList
	 * 
	 * @param row
	 * @param col
	 */
	private void capturePiece(int row, int col) {
		this.captured.add(this.getSquare(row, col));
	}

	/**
	 * Add a piece to the captured ArrayList
	 * 
	 * @param coords - an int[]
	 */
	private void capturePiece(int[] coords) {
		capturePiece(coords[0], coords[1]);
	}

	public boolean executeMove(int[] s, int[] d) {
		boolean moveAccepted = getSquare(s).movePiece(this, s, d);
		if (moveAccepted) {
			boolean capture = (getSquare(d) == null) ? false : true;
			if (capture) {
				capturePiece(d);
			}
			setSquare(getSquare(s), d);
			emptySquare(s);
			setLastMovedPiece(d);
			return true;
		}
		return false;
	}

	/**
	 * Set the last moved piece
	 * 
	 * @param coords - an int[2] containing chessBoard coordinates
	 */
	private void setLastMovedPiece(int[] coords) {
		this.lastMovedPiece = coords;
	}

	/**
	 * set a square to null
	 * 
	 * @param row
	 * @param col
	 */
	private void emptySquare(int row, int col) {
		board[row][col] = null;
	}

	/**
	 * set a square to null
	 * 
	 * @param coords - an int[]
	 */
	private void emptySquare(int[] coords) {
		emptySquare(coords[0], coords[1]);
	}

	/**
	 * Displays the ChessBoard. It notes the squares colour, coordinates, and the piece that is on the square (if any)
	 */
	public void displayBoard() {
		//String border = "________________________________________________________________________________";
		System.out.printf("%n".repeat(200));
		String border = (" " + "-".repeat(20)).repeat(8);
		for (int row = 7; row >= 0; row--) {
			System.out.println(border);
			for (int subrow = 0; subrow < 4; subrow++) {
				System.out.print("|");
				for (int col = 0; col < 8; col++) {
					if (subrow == 0) {
						if (lastMovedPiece[0] == row && lastMovedPiece[1] == col) {
							System.out.printf("  %s  |", "LAST MOVED PIECE");
						} else {
							System.out.printf("    %13s   |", "");
						}
						continue;
					} else if (subrow == 1) {
						System.out.printf("    %13s   |", (getSquare(row, col) != null) ? getSquare(row, col) : "");
						continue;
					} else if (subrow == 2) {
						if (GameController.TEST_MODE) {
							System.out.printf("        (%d,%d)       |", row, col);
						} else {
							System.out.printf("    %13s   |", "");
						}
						continue;
					} else if (subrow == 3) {
						System.out.printf("         %c%d         |", (char) col + 65, row + 1);
						continue;
					}
				}
				System.out.println();
			}
		}
		System.out.println(border);
		GameController.displayMenuOptions();
	}

	/**
	 * Prints the captured pieces of both teams
	 */
	public void printCapturedPieces(Scanner scanner) {
		boolean found;
		String border = "===========================";
		System.out.println(border);
		for (int i = 0; i < 2; i++) {
			found = false;
			System.out.print((i == 0) ? "Black" : "White");
			System.out.println(" has captured:");
			for (int j = 0; j < captured.size(); j++) {
				if (i == 0 && captured.get(j).getTeam() == 'w') {
					System.out.printf("\tWhite %s%n", captured.get(j));
					found = true;
				} else if (i == 1 && captured.get(j).getTeam() == 'b') {
					System.out.printf("\tBlack %s%n", captured.get(j));
					found = true;
				}
			}
			if (!found) {
				System.out.println("\tNone");
			}
		}
		System.out.println("Press enter to continue");
		System.out.println(border);
		scanner.nextLine();
	}

	//////////////////////////////
	//		GETTERS N SETTERS	//
	//////////////////////////////

	public ChessPiece[][] getBoard() {
		return this.board;
	}

	/**
	 * places a piece on a square
	 * 
	 * @param c   - A ChessPiece
	 * @param row - An int
	 * @param col - An int
	 */
	public void setSquare(ChessPiece c, int row, int col) {
		board[row][col] = c;
	}

	public void setSquare(ChessPiece c, int[] s) {
		setSquare(c, s[0], s[1]);
		;
	}

	/**
	 * @param row
	 * @param col
	 * @return a ChessPiece from the board
	 */
	public ChessPiece getSquare(int row, int col) {
		return board[row][col];
	}

	/**
	 * 
	 * @param sq An int[] - board coordinates
	 * @return a ChessPiece from the board
	 */
	public ChessPiece getSquare(int[] sq) {
		return getSquare(sq[0], sq[1]);
	}

	/**
	 * Called when players turn is over and next player is up
	 */
	public void changeCurrentPlayer() {
		this.currentPlayer = (this.currentPlayer == 'w') ? 'b' : 'w';
	}

	/**
	 * @return the currentPlayer ('w' or 'b')
	 */
	public char getCurrentPlayer() {
		return this.currentPlayer;
	}
}
