package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Objects;

import model.Enums.Layout;
import model.Enums.StoredStrings;
import model.Enums.Team;
import model.pieces.*;

public class Model {
	//TODO make a singleton?
	private Hashtable<String, APiece> board;
	private ArrayList<APiece> captured;
	private Layout layout;
	private String[] teamNames;

	///////////////////////////////////////////////////
	// CONSTRUCTOR									//
	/////////////////////////////////////////////////

	/**
	 * Instantiates a (chess board) Model object.
	 * 
	 * @param layout
	 */
	public Model(Layout layout) throws InstantiationError {
		String errorMsg = null;
		try {
			this.setLayout(layout);
			this.captured = new ArrayList<APiece>(32);
			this.setTeamNames(StoredStrings.PLAYER_1_NAME.toString(), StoredStrings.PLAYER_2_NAME.toString());
			this.setBoard();
		} catch (NullPointerException e) {
			errorMsg = "NullPointerException: " + e.getMessage();
		} catch (IllegalArgumentException e) {
			errorMsg = "IllegalArgumentException: " + e.getMessage();
		} finally {
			if (errorMsg != null) {
				throw new InstantiationError(String.format("Model was not successully Instantiated:%n\t" + errorMsg));
			}
		}
	}

	///////////////////////////////////////////////////
	// OPERATIONS									//
	/////////////////////////////////////////////////

	/**
	 * Capture a piece at coordinate
	 * 
	 * @param coord
	 * @throws NullPointerException if APiece at coordinate coord is null
	 */
	private void capture(int[] coord) throws NullPointerException {
		APiece capturedPiece = board.get(coord);
		if (capturedPiece == null) {
			throw new NullPointerException("Board Square " + Arrays.toString(coord) + " / " + convertCoords(coord) + "was null!");
		}
		captured.add(capturedPiece);
		board.remove(coord);
	}

	/**
	 * Add APiece to the board with an {@code int[2]} coordinate
	 * 
	 * @param coord An int[2] - board coordinate, e.g. [0,0] - [7,7]
	 * @param piece - an APiece object
	 * @throws IllegalArgumentException if any coord argument element is outside range 0-7 inclusive
	 * @throw NullPointerException if piece or coord arguments are null
	 */
	private void addAPieceToBoard(int[] coord, APiece piece) throws IllegalArgumentException, NullPointerException {
		addAPieceToBoard(convertCoords(coord), piece);
	}

	/**
	 * Add APiece to the board with an {@code int[2]} coordinate
	 * 
	 * @param coord A String - board coordinate eg, "A1" - "H8"
	 * @param piece - an APiece object
	 */
	private void addAPieceToBoard(String coord, APiece piece) throws IllegalArgumentException, NullPointerException {
		//addAPieceToBoard(convertCoords(coord), piece);
		if (coord == null) {
			throw new NullPointerException("Model.addPieceToBoard(String, APiece): String arg may not be null.");
		} else if (piece == null) {
			throw new NullPointerException("Model.addPieceToBoard(String, APiece): APiece arg may not be null.");
		} else if (!isCoordValid(coord)) {
			throw new IllegalArgumentException(
					"Model.addPieceToBoard(String, APiece): String char 0 must only be A-H inclusive and char 1 must only be 1-8 inclusive, but was \""
							+ coord + "\"");
		}
		board.put(coord, piece);
	}

	///////////////////////////////////////////////////
	// UTILITY										//
	/////////////////////////////////////////////////

	/**
	 * Validates that a specified board coordinate String is valid. Char 0 [A-H inclusive], char 1 [1-8 inclusive]
	 * 
	 * @param coord A String
	 * @return A boolean - is this coordinate valid?
	 */
	private static boolean isCoordValid(String coord) {
		if (coord.length() != 2 || coord.charAt(0) < 'A' || coord.charAt(0) > 'H' || coord.charAt(1) < '1' || coord.charAt(1) > '8') {
			return false;
		}
		return true;
	}

	/**
	 * Convert a String representation of a board square to an int[2].<br>
	 * A1 -> [0,0]<br>
	 * C2 -> [1,2]<br>
	 * Returns null if coord in invalid
	 * 
	 * @param s - A string representing a board square A-H / 1-8
	 * @return an int[2] representing the board position, e.g. [0,0], or null if coord is invalid
	 */
	public static int[] convertCoords(String s) {
		s = s.toUpperCase();
		if (isCoordValid(s)) {
			return new int[] { s.charAt(1) - 49, s.charAt(0) - 65 };
		} else {
			return null;
		}
	}

	/**
	 * Convert an int[2] representation of a board square to a String.<br>
	 * [0,0] -> A1<br>
	 * [1,2] -> C2<br>
	 * Returns null if coord in invalid
	 * 
	 * @param c - An int[2] representing a board square [0-7,0-7]
	 * @return A String representing the board position, e.g. A1, or null if coord is invalid
	 */
	public static String convertCoords(int[] c) {
		String conversion = String.format("%c%c", (char) (c[1] + 65), (char) c[0] + 49);
		if (isCoordValid(conversion)) {
			return conversion;
		} else {
			return null;
		}
	}

	///////////////////////////////////////////////////
	// GETTERS N SETTERS							//
	/////////////////////////////////////////////////

	/**
	 * @return {@code ArrayList<APiece>} containing all captured APieces
	 */
	public ArrayList<APiece> getCaptured() {
		return this.captured;
	}

	/**
	 * @return A String[2] - The team names
	 */
	public String[] getTeamNames() {
		return this.teamNames;
	}

	/**
	 * Set the team names
	 * 
	 * @param player1Name A String
	 * @param player2Name A String
	 * @throws NullPointerException     if either argument is null
	 * @throws IllegalArgumentException if either argument is a blank String (only white space or empty)
	 */
	public void setTeamNames(String player1Name, String player2Name) throws NullPointerException {
		if (player1Name == null || player2Name == null) {
			throw new NullPointerException("Model.setTeamNames(enum, enum) does not accept null arguments");
		} else if (player1Name.isBlank() || player2Name.isBlank()) {
			throw new IllegalArgumentException("Model.setTeamNames(String, String) does not accept blank String arguments");
		} else {
			this.teamNames = new String[] { player1Name, player2Name };
		}
	}

	/**
	 * @return a Layout. The Layout of this object.
	 */
	public Layout getLayout() {
		return this.layout;
	}

	/**
	 * Set the layout field of this object
	 * 
	 * @param layout
	 * @throws NullPointerException if layout if null
	 */
	private void setLayout(Layout layout) throws NullPointerException {
		if (layout == null) {
			throw new NullPointerException("Model.setLayout(layout) does not accept null arguments");
		}
		this.layout = layout;
	}

	/**
	 * @return a {@code Hashtable<String, APiece>} containing all the {@code APiece}'s positions
	 */
	public Hashtable<String, APiece> getBoard() {
		return this.board;
	}

	/**
	 * Set the chessboard up.
	 * 
	 * @param setup
	 */
	private void setBoard() {
		board = new Hashtable<String, APiece>(32);
		switch (this.layout) {
		case NORMAL:
			addAPieceToBoard("A1", new Rook(Team.WHITE));
			addAPieceToBoard("B1", new Knight(Team.WHITE));
			addAPieceToBoard("C1", new Bishop(Team.WHITE));
			addAPieceToBoard("D1", new King(Team.WHITE));
			addAPieceToBoard("E1", new Queen(Team.WHITE));
			addAPieceToBoard("F1", new Bishop(Team.WHITE));
			addAPieceToBoard("G1", new Knight(Team.WHITE));
			addAPieceToBoard("H1", new Rook(Team.WHITE));

			addAPieceToBoard("A2", new Pawn(Team.WHITE));
			addAPieceToBoard("B2", new Pawn(Team.WHITE));
			addAPieceToBoard("C2", new Pawn(Team.WHITE));
			addAPieceToBoard("D2", new Pawn(Team.WHITE));
			addAPieceToBoard("E2", new Pawn(Team.WHITE));
			addAPieceToBoard("F2", new Pawn(Team.WHITE));
			addAPieceToBoard("G2", new Pawn(Team.WHITE));
			addAPieceToBoard("H2", new Pawn(Team.WHITE));

			addAPieceToBoard("A7", new Pawn(Team.BLACK));
			addAPieceToBoard("B7", new Pawn(Team.BLACK));
			addAPieceToBoard("C7", new Pawn(Team.BLACK));
			addAPieceToBoard("D7", new Pawn(Team.BLACK));
			addAPieceToBoard("E7", new Pawn(Team.BLACK));
			addAPieceToBoard("F7", new Pawn(Team.BLACK));
			addAPieceToBoard("G7", new Pawn(Team.BLACK));
			addAPieceToBoard("H7", new Pawn(Team.BLACK));

			addAPieceToBoard("A8", new Rook(Team.BLACK));
			addAPieceToBoard("B8", new Knight(Team.BLACK));
			addAPieceToBoard("C8", new Bishop(Team.BLACK));
			addAPieceToBoard("D8", new King(Team.BLACK));
			addAPieceToBoard("E8", new Queen(Team.BLACK));
			addAPieceToBoard("F8", new Bishop(Team.BLACK));
			addAPieceToBoard("G8", new Knight(Team.BLACK));
			addAPieceToBoard("H8", new Rook(Team.BLACK));
			break;
		case TESTING:
			addAPieceToBoard(new int[] { -1, 0 }, new Rook(Team.WHITE));
			APiece tp = null;
			int[] ti = null;
			addAPieceToBoard("A1", tp);
			addAPieceToBoard(ti, new Rook(Team.WHITE));
			break;
		case UNIT_TESTS:
			addAPieceToBoard("F6", new Rook(Team.WHITE));
			addAPieceToBoard("D7", new Knight(Team.WHITE));
			break;
		default:
		}
	}

}
