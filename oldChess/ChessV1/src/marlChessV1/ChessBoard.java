package marlChessV1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ChessBoard {

	private ChessPiece[][] chessBoard = new ChessPiece[8][8];
	private ArrayList<ChessPiece> captured = new ArrayList<ChessPiece>(32);
	private int[] lastMovedPiece = new int[] { -10, -10 };

	private static int moves = 1;

	//////////////////////////////
	//		CONSTRUCTORS		//
	//////////////////////////////

	/**
	 * Creates and populates a ChessBoard with all pieces in starting position
	 * 
	 * @param test - a boolean - if true, populate board with test pieces
	 */
	public ChessBoard(boolean test) {
		int rowBlackSpecials = 7;
		int rowBlackPawns = 6;
		int rowWhitePawns = 1;
		int rowWhiteSpecials = 0;
		if (!test) {
			// Set pieces on the board
			for (int i = 0; i < 8; i++) {
				// place pawns
				this.setSquare("b", "pawn", rowBlackPawns, i);
				this.setSquare("w", "pawn", rowWhitePawns, i);
				// place special pieces
				if (i == 0 || i == 7) {
					this.setSquare("b", "rook", rowBlackSpecials, i);
					this.setSquare("w", "rook", rowWhiteSpecials, i);
				} else if (i == 1 || i == 6) {
					this.setSquare("b", "knight", rowBlackSpecials, i);
					this.setSquare("w", "knight", rowWhiteSpecials, i);
				} else if (i == 2 || i == 5) {
					this.setSquare("b", "bishop", rowBlackSpecials, i);
					this.setSquare("w", "bishop", rowWhiteSpecials, i);
				} else if (i == 3) {
					this.setSquare("b", "king", rowBlackSpecials, i);
					this.setSquare("w", "king", rowWhiteSpecials, i);
				} else if (i == 4) {
					this.setSquare("b", "queen", rowBlackSpecials, i);
					this.setSquare("w", "queen", rowWhiteSpecials, i);
				}
			}
		} else {
			// test pieces board
			//this.setSquare(rowWhitePawns + 1, 2, "b", "pawn");
			//			this.setSquare("w", "rook", 0, 0);
			//			this.setSquare("b", "pawn", 5, 3);
			//this.setSquare("w", "knight", 2, 3);
			//			this.setSquare("b", "pawn", 4, 4);
			//			this.setSquare("w", "bishop", 1, 4);
			//this.setSquare("w", "queen", 3, 4);
			//			this.setSquare("b", "bishop", 3, 6);
			//			this.setSquare("w", "rook", 0, 5);
			//			this.setSquare("b", "queen", 0, 3);
			//this.setSquare("w", "pawn", rowWhitePawns + 1, 2);
			//this.setSquare("b", "pawn", rowWhitePawns + 1, 3);
			//this.setSquare("b", "king", rowBlackSpecials, 3);
			//this.setSquare("w", "king", rowWhiteSpecials, 3);
			//this.chessBoard[rowWhitePawns + 1][2] = new ChessPiece("b", "pawn");
			//this.chessBoard[rowWhitePawns + 1][3] = new ChessPiece("w", "pawn");
			// Set pieces on the board
			for (int i = 0; i < 8; i++) {
				// place pawns
				this.setSquare("b", "pawn", 1, 3);
				this.setSquare("w", "pawn", 6, 3);
				// place special pieces
				if (i == 0 || i == 7) {
					this.setSquare("b", "rook", rowBlackSpecials, i);
					this.setSquare("w", "rook", rowWhiteSpecials, i);
				} else if (i == 1 || i == 6) {
					this.setSquare("b", "knight", rowBlackSpecials, i);
					this.setSquare("w", "knight", rowWhiteSpecials, i);
				} else if (i == 2 || i == 5) {
					this.setSquare("b", "bishop", rowBlackSpecials, i);
					this.setSquare("w", "bishop", rowWhiteSpecials, i);
				} else if (i == 3) {
					//this.setSquare("b", "king", rowBlackSpecials, i);
					//this.setSquare("w", "king", rowWhiteSpecials, i);
				} else if (i == 4) {
					this.setSquare("b", "queen", rowBlackSpecials, i);
					this.setSquare("w", "queen", rowWhiteSpecials, i);
				}
			}
		}

	}

	//////////////////////////////
	//	BOARD ADMINISTRATION	//
	//////////////////////////////

	/**
	 * The method that administers the moving of pieces
	 * 
	 * @param s      - the source piece as an int[2]
	 * @param d      - the destination piece as an int[2]
	 * @param player - the player 'w' or 'b'
	 * @return a boolean representing the success or failure of move operation
	 */
	public boolean movePiece(int[] s, int[] d, char player) {
		ChessPiece selectedPiece = this.getSquare(s[0], s[1]);
		boolean isSuccessful = false;
		switch (selectedPiece.getPieceName(true)) {
		case "pawn":
			isSuccessful = movePawn(s, d);
			break;
		case "rook":
			isSuccessful = moveRook(s, d);
			break;
		case "knight":
			isSuccessful = moveKnight(s, d);
			break;
		case "bishop":
			isSuccessful = moveBishop(s, d);
			break;
		case "king":
			isSuccessful = moveKing(s, d);
			break;
		case "queen":
			isSuccessful = moveQueen(s, d);
			break;
		default:
			System.out.println("WHAT IS THIS BULLSHIT FAKE PIECE BRUH?? switch error in ChessBoard.movePiece()");
		}
		if (isSuccessful)
			moves++;
		return isSuccessful;
	}

	/**
	 * moves piece from coordinate s to coordinate d, empties coordinate s and sets lastMovedPiece to coordinate d
	 * <br>
	 * captures the piece at coordinate d if capture argument is true;
	 * 
	 * @param s
	 * @param d
	 * @param capture - a boolean; whether to capture piece at coords d
	 */
	private void executeMove(int[] s, int[] d, boolean capture) {
		if (capture) {
			capturePiece(d);
		}
		setSquare(getSquare(s), d);
		emptySquare(s);
		setLastMovedPiece(d);
		// upgrading pawn?
		if (getSquare(d).getPieceName(true).equals("pawn") && ((Referee.player == 'w' && d[0] == 7) || (Referee.player == 'b' && d[0] == 0))) {
			upgradePawn(d);
		}
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
	
	private void checkForCheck() {
		
	}
	
	/**
	 * Upgrade's the players pawn. Prompting the player to enter "rook", "bishop", "queen" or "night"
	 * @param d
	 */
	@SuppressWarnings("resource")
	private void upgradePawn(int[] d) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("You get to upgrade your Pawn at position " + Referee.convertArrIndicesToCoords(d) + "!");
		System.out.println("You may choose a Queen, Rook, Bishop or Knight");
		String upgrade = "";
		boolean firstSelection = true;
		while (!upgrade.equalsIgnoreCase("queen") && !upgrade.equalsIgnoreCase("rook") && !upgrade.equalsIgnoreCase("bishop") && !upgrade.equalsIgnoreCase("knight")) {
			if (!firstSelection) {
				System.out.println("Sorry that wasn't a valid piece, please try again!");
			}
			upgrade = scanner.nextLine();
			firstSelection = false;
		}
		String player = (Referee.player == 'w') ? "w" : "b";
		setSquare(new ChessPiece(player, upgrade.toLowerCase()), d);

	}

	/**
	 * set a square to null
	 * 
	 * @param row
	 * @param col
	 */
	private void emptySquare(int row, int col) {
		this.chessBoard[row][col] = null;
	}

	/**
	 * set a square to null
	 * 
	 * @param coords - an int[]
	 */
	private void emptySquare(int[] coords) {
		emptySquare(coords[0], coords[1]);
	}

	//////////////////////////////
	//		MOVE RULES			//
	//////////////////////////////

	/**
	 * Move a Knight
	 * 
	 * @param s - the source position as an int[2]
	 * @param d - the destination position as an int[2]
	 * @return a boolean representing the success or failure of move operation
	 */
	private boolean moveKnight(int[] s, int[] d) {

		// initialise absolute vectors
		int vertical = Math.abs(d[0] - s[0]);
		int horizontal = Math.abs(d[1] - s[1]);

		// get player's team
		String team = this.getSquare(s).getTeam(true);

		// validate vectors are acceptable
		if (!((horizontal == 1 && vertical == 2) || (horizontal == 2 && vertical == 1))) {
			return false;
		}

		// check destination square and execute move if square is empty, or contains opponents piece
		if (checkDestinationIsEmpty(d)) {
			executeMove(s, d, false);
		} else if (checkDestinationHasOpponent(d, team)) {
			executeMove(s, d, true);
		} else {
			return false;
		}
		return true;
	}

	/**
	 * Move a Rook.
	 * 
	 * @param s - the source position as an int[2]
	 * @param d - the destination position as an int[2]
	 * @return a boolean representing the success or failure of move operation
	 */
	private boolean moveRook(int[] s, int[] d) {
		boolean destinationIsEmpty = false;
		boolean vertical;
		boolean moveInPositiveDirection = true;

		String team = this.getSquare(s).getTeam(true);

		// check axis of move and vector validity
		if (s[1] == d[1]) {
			vertical = true;
			;
		} else if (s[0] == d[0]) {
			vertical = false;
		} else {
			return false;
		}

		// check directionality of move (+/- relative to ChessBoard index
		if (vertical) {
			if (s[0] - d[0] > 0) {
				moveInPositiveDirection = false;
			}
		} else {
			if (s[1] - d[1] > 0) {
				moveInPositiveDirection = false;
			}
		}

		// check destination
		destinationIsEmpty = checkDestinationIsEmpty(d);
		if (!destinationIsEmpty) {
			if (!checkDestinationHasOpponent(d, team)) {
				return false;
			}
		}

		// check all board squares between s and d
		boolean squareIsEmpty;
		boolean squareHasOpponent;
		int row = s[0];
		int col = s[1];
		for (int i = 0; row < chessBoard.length && row >= 0 && col < chessBoard.length && col >= 0; i++) {
			squareHasOpponent = false; // default flag to false
			if (i != 0) {
				squareIsEmpty = checkDestinationIsEmpty(row, col);
				if (!squareIsEmpty) {
					squareHasOpponent = checkDestinationHasOpponent(row, col, team);
				}

				if (row == d[0] && col == d[1]) { // if on destination piece
					if (squareHasOpponent) {
						executeMove(s, d, true);
						return true;
					} else if (squareIsEmpty) {
						executeMove(s, d, false);
						return true;
					} else {
						return false;
					}
				} else {
					if (!squareIsEmpty) {
						return false;
					}
				}
			}

			if (moveInPositiveDirection && vertical) { // increment/ decrement
				row++;
			} else if (!moveInPositiveDirection && vertical) {
				row--;
			} else if (moveInPositiveDirection && !vertical) {
				col++;
			} else if (!moveInPositiveDirection && !vertical) {
				col--;
			}
		}
		return false;
	}

	/**
	 * Move a Pawn.
	 * 
	 * @param s - the source position as an int[2]
	 * @param d
	 * @return a boolean representing the success or failure of move operation
	 */
	private boolean movePawn(int[] s, int[] d) {
		// declare and initialise flags
		boolean destinationIsEmpty = false;
		boolean destinationHasOpponent = false;
		boolean vectorIsValid = false;
		boolean canTakePiece = false;

		// declare and initialise other variables
		int maxMoveDistance = 1;
		String team = this.getSquare(s[0], s[1]).getTeam(true);
		int direction = (team.equalsIgnoreCase("white")) ? 1 : -1;

		// check destination
		destinationIsEmpty = checkDestinationIsEmpty(d);
		if (!destinationIsEmpty) {
			destinationHasOpponent = checkDestinationHasOpponent(d, team);
		}

		// check if pawn has not yet moved (meaning it can move 2 spaces)
		if ((s[0] == 1 && team.equals("white")) || (s[0] == 6 && team.equals("black"))) {
			maxMoveDistance = 2;
			// check if route is blocked
			if (d[0] >= 2 && this.getSquare(s[0] + direction, s[1]) != null) {
				return false;
			}
		}

		// check vector validity and if source piece can capture destination piece
		if (destinationIsEmpty && s[1] == d[1]) {
			// check if destination is within same row and within movable distance
			if (d[0] == s[0] + direction || d[0] == s[0] + (direction * maxMoveDistance)) {
				vectorIsValid = true;
			}
		} else if (destinationHasOpponent && (s[1] - 1 == d[1] || s[1] + 1 == d[1]) && s[0] + direction == d[0]) {
			// check opponent is one column left or right and one row away (with directionality) - can capture
			vectorIsValid = true;
			canTakePiece = true;
		}

		if (vectorIsValid) {
			if (destinationIsEmpty) {
				executeMove(s, d, false);
				return true;
			} else if (destinationHasOpponent && canTakePiece) {
				executeMove(s, d, true);
				return true;
			}
		}

		return false;
	}

	/**
	 * Move a Bishop.
	 * 
	 * @param s - the source position as an int[2]
	 * @param d - the destination position as an int[2]
	 * @return a boolean representing the success or failure of move operation
	 */
	private boolean moveBishop(int[] s, int[] d) {
		int vectorVert = (d[0] - s[0] > 0) ? 1 : -1;
		int vectorHori = (d[1] - s[1] > 0) ? 1 : -1;
		int distance = Math.abs(d[1] - s[1]); // total distance of move
		String team = getSquare(s).getTeam(true);

		// cannot be a direct vertical or horizontal move
		if (vectorHori == 0 || vectorVert == 0) {
			return false;
		}

		// create cursor on next square
		int[] cursor = Arrays.copyOf(s, s.length);
		cursor[0] += vectorVert;
		cursor[1] += vectorHori;

		// check each square along move vector
		for (int i = 0; i < distance; i++) {
			// set flags
			boolean squareEmpty = (getSquare(cursor) == null) ? true : false;
			boolean squareIsDestination = (d[0] == cursor[0] && d[1] == cursor[1]) ? true : false;
			if (squareIsDestination) { // if square is destination
				boolean squareHasOpponent = (squareEmpty) ? false : checkDestinationHasOpponent(cursor, team);
				if (squareEmpty) {
					// if square is empty then execute move without capture
					executeMove(s, d, false);
					break;
				} else {
					// if square is not empty
					if (squareHasOpponent) {
						// if square is opponent then execute move with capture
						executeMove(s, d, true);
						break;
					} else {
						// if square is not opponent then move failed
						return false;
					}
				}
			} else if (!squareEmpty) { // if square is not destination & square is not empty then move invalid
				return false;

			}
			// move cursor to next square
			cursor[0] += (vectorVert > 0) ? 1 : -1;
			cursor[1] += (vectorHori > 0) ? 1 : -1;
		}
		return true;
	}

	/**
	 * Move a Queen.
	 * 
	 * @param s - the source position as an int[2]
	 * @param d - the destination position as an int[2]
	 * @return a boolean representing the success or failure of move operation
	 */
	private boolean moveQueen(int[] s, int[] d) {
		// set vectors
		int vectorVert = d[0] - s[0];
		int vectorHori = d[1] - s[1];
		if (vectorVert != 0) {
			vectorVert = (d[0] - s[0] > 0) ? 1 : -1;
		}
		if (vectorHori != 0) {
			vectorHori = (d[1] - s[1] > 0) ? 1 : -1;
		}

		String team = getSquare(s).getTeam(true);

		// create cursor on next square
		int[] cursor = Arrays.copyOf(s, s.length);
		cursor[0] += vectorVert;
		cursor[1] += vectorHori;

		// check each square along move vector
		while (true) {
			// set flags
			boolean squareEmpty = (getSquare(cursor) == null) ? true : false;
			boolean squareIsDestination = (d[0] == cursor[0] && d[1] == cursor[1]) ? true : false;
			if (squareIsDestination) { // if square is destination
				boolean squareHasOpponent = (squareEmpty) ? false : checkDestinationHasOpponent(cursor, team);
				if (squareEmpty) {
					// if square is empty then execute move without capture
					executeMove(s, d, false);
					break;
				} else {
					// if square is not empty
					if (squareHasOpponent) {
						// if square is opponent then execute move with capture
						executeMove(s, d, true);
						break;
					} else {
						// if square is not opponent then move failed
						return false;
					}
				}
			} else if (!squareEmpty) { // if square is not destination & square is not empty then move invalid
				return false;

			}
			// move cursor to next square
			cursor[0] += vectorVert;
			cursor[1] += vectorHori;
		}
		return true;
	}

	/**
	 * Move a Queen.
	 * 
	 * @param s - the source position as an int[2]
	 * @param d - the destination position as an int[2]
	 * @return a boolean representing the success or failure of move operation
	 */
	private boolean moveKing(int[] s, int[] d) {
		// set vectors
		int vectorVert = d[0] - s[0];
		int vectorHori = d[1] - s[1];
		if (vectorVert != 0) {
			vectorVert = (d[0] - s[0] > 0) ? 1 : -1;
		}
		if (vectorHori != 0) {
			vectorHori = (d[1] - s[1] > 0) ? 1 : -1;
		}

		String team = getSquare(s).getTeam(true);

		// create cursor on next square
		int[] cursor = Arrays.copyOf(s, s.length);
		cursor[0] += vectorVert;
		cursor[1] += vectorHori;

		// check each square along move vector
		while (true) {
			// set flags
			boolean squareEmpty = (getSquare(cursor) == null) ? true : false;
			boolean squareIsDestination = (d[0] == cursor[0] && d[1] == cursor[1]) ? true : false;
			if (squareIsDestination) { // if square is destination
				boolean squareHasOpponent = (squareEmpty) ? false : checkDestinationHasOpponent(cursor, team);
				if (squareEmpty) {
					// if square is empty then execute move without capture
					executeMove(s, d, false);
					break;
				} else {
					// if square is not empty
					if (squareHasOpponent) {
						// if square is opponent then execute move with capture
						executeMove(s, d, true);
						break;
					} else {
						// if square is not opponent then move failed
						return false;
					}
				}
			} else if (!squareEmpty) { // if square is not destination & square is not empty then move invalid
				return false;

			}
			// move cursor to next square
			cursor[0] += vectorVert;
			cursor[1] += vectorHori;
			break;
		}
		return true;
	}

	//////////////////////////////
	//			UTILITY			//
	//////////////////////////////

	/**
	 * Prints the captured pieces of both teams
	 */
	public void printCapturedPieces() {
		boolean found;
		for (int i = 0; i < 2; i++) {
			found = false;
			System.out.print((i == 0) ? "Black" : "White");
			System.out.println(" has captured:");
			for (int j = 0; j < captured.size(); j++) {
				if (i == 0 && captured.get(j).getTeam(true).equals("white")) {
					System.out.printf("\tWhite %s%n", captured.get(j).getPieceName(false));
					found = true;
				} else if (i == 1 && captured.get(j).getTeam(true).equals("black")) {
					System.out.printf("\tBlack %s%n", captured.get(j).getPieceName(false));
					found = true;
				}
			}
			if (!found) {
				System.out.println("\tNone");
			}
		}
		Referee.promptContinue();
	}

	/**
	 * Checks if the piece in this position is the players piece
	 * 
	 * @param s      - an board position as an int[2]
	 * @param player - the curent player 'w' or 'b'
	 * @return boolean - representing if this is the current players piece
	 */
	public boolean isPlayersPiece(int[] s, char player) {
		if (getSquare(s) == null ||
				!((player == 'w' && getSquare(s).getTeam(true).equals("white")) || (player == 'b' && getSquare(s).getTeam(true).equals("black")))) {
			return false;
		}
		return true;
	}

	/**
	 * Displays the ChessBoard. It notes the squares colour, coordinates, and the piece that is on the square (if any)
	 */
	public void displayBoard(boolean testMode) {
		//String border = "________________________________________________________________________________";
		System.out.println("Move: " + moves);
		String border = "--------------------------------------------------------------------------------";
		for (int i = 7; i >= 0; i--) {
			System.out.println(border);
			for (int k = 0; k < ((testMode) ? 4 : 3); k++) {
				System.out.print("|");
				for (int j = 0; j < 8; j++) {
					if (k == 2) {
						System.out.printf("   %c%s    |", (char) j + 65, i + 1);
						continue;
					}
					if (testMode && k == 3) {
						System.out.printf("   %d,%d   |", i, j);
						continue;
					}
					System.out.print(' ');
					if (chessBoard[i][j] instanceof ChessPiece) {
						String print = null;
						if (i == lastMovedPiece[0] && j == lastMovedPiece[1]) {
							print = String.format("*%-6s*|", (k == 0) ? chessBoard[i][j].getTeam(false) : chessBoard[i][j].getPieceName(false));
						} else {
							print = String.format(" %-6s |", (k == 0) ? chessBoard[i][j].getTeam(false) : chessBoard[i][j].getPieceName(false));
						}
						System.out.print(print);
					} else {
						System.out.printf(" %-6s |", "");
					}
				}
				System.out.println();
			}
		}
		System.out.println(border);

	}

	//////////////////////////////
	//		BOARD CHECKS		//
	//////////////////////////////

	/**
	 * Checks if destination is empty of ChessPiece objects
	 * 
	 * @param d an int[] holding destination coords
	 * @return a boolean - destinationIsEmpty
	 */
	private boolean checkDestinationIsEmpty(int[] d) {
		return checkDestinationIsEmpty(d[0], d[1]);
	}

	/**
	 * Checks if destination is empty of ChessPiece objects
	 * 
	 * @param row
	 * @param col
	 * @return a boolean - destinationIsEmpty
	 */
	private boolean checkDestinationIsEmpty(int row, int col) {
		boolean check = false;
		if (this.getSquare(row, col) == null) {
			// destination is empty
			check = true;
		}
		return check;
	}

	/**
	 * Checks if destination has an opponents piece on it
	 * 
	 * @param row
	 * @param col
	 * @param team - A String, the team; "white" or "black"
	 * @return
	 */
	private boolean checkDestinationHasOpponent(int row, int col, String team) {
		boolean check = false;
		if (!this.getSquare(row, col).getTeam(false).equalsIgnoreCase(team)) {
			// destination has opponents piece in it
			check = true;
		}
		return check;
	}

	/**
	 * Checks if destination has an opponents piece on it
	 * 
	 * @param d    - an int[] holding destination coords
	 * @param team - A String, the team; "white" or "black"
	 * @return
	 */
	private boolean checkDestinationHasOpponent(int[] d, String team) {
		return checkDestinationHasOpponent(d[0], d[1], team);
	}

	//////////////////////////////
	//	GETTERS AND SETTERS		//
	//////////////////////////////

	/**
	 * Set the last moved piece
	 * 
	 * @param coords - an int[2] containing chessBoard coordinates
	 */
	private void setLastMovedPiece(int[] coords) {
		this.lastMovedPiece = coords;
	}

	/**
	 * places a piece on the on chessBoard
	 * 
	 * @param piece - A ChessPiece Object
	 * @param row
	 * @param col
	 */
	private void setSquare(ChessPiece piece, int row, int col) {
		this.chessBoard[row][col] = piece;
	}

	/**
	 * places a piece on the chessBoard
	 * 
	 * @param piece  - A ChessPiece Object
	 * @param coords - an int[2] containing chessBoard coordinates
	 */
	private void setSquare(ChessPiece piece, int[] coords) {
		setSquare(piece, coords[0], coords[1]);
	}

	/**
	 * places a piece on the chessBoard
	 * 
	 * @param Team  - "b" or "w"
	 * @param piece - "pawn", "king", "bishop" etc
	 * @param row
	 * @param col
	 */
	private void setSquare(String team, String piece, int row, int col) {
		this.setSquare(new ChessPiece(team, piece), row, col);
	}

	/**
	 * Retrieve a piece from the board
	 * 
	 * @param row
	 * @param col
	 * @return ChessPiece
	 */
	private ChessPiece getSquare(int row, int col) {
		return this.chessBoard[row][col];
	}

	/**
	 * Retrieve a piece from the board
	 * 
	 * @param coords - an int[]
	 * @return ChessPiece
	 */
	private ChessPiece getSquare(int[] coords) {
		return getSquare(coords[0], coords[1]);
	}
}
