package model;

import java.util.ArrayList;
import java.util.Hashtable;

import model.Enums.Layout;
import model.Enums.Team;
import model.pieces.*;

public class Model {

	private Hashtable<String, APiece> board;
	private ArrayList<APiece> captured;

	/**
	 * Instantiates a (chessboard) Model object.
	 * @param layout
	 */
	public Model(Layout layout) {
		if (layout == null) {
			throw new NullPointerException("Model constructor does not accept null arguments");
		}
		this.setBoard(layout);
		this.captured = new ArrayList<APiece>(32);
	}

	/**
	 * Set the chessboard up.
	 * @param setup
	 */
	public void setBoard(Layout setup) {
		board = new Hashtable<String, APiece>(32);
		switch (setup) {
		case NORMAL:
			board.put("A1", new Rook(Team.WHITE));
			board.put("B1", new Knight(Team.WHITE));
			board.put("C1", new Bishop(Team.WHITE));
			board.put("D1", new King(Team.WHITE));
			board.put("E1", new Queen(Team.WHITE));
			board.put("F1", new Bishop(Team.WHITE));
			board.put("G1", new Knight(Team.WHITE));
			board.put("H1", new Rook(Team.WHITE));

			board.put("A2", new Pawn(Team.WHITE));
			board.put("B2", new Pawn(Team.WHITE));
			board.put("C2", new Pawn(Team.WHITE));
			board.put("D2", new Pawn(Team.WHITE));
			board.put("E2", new Pawn(Team.WHITE));
			board.put("F2", new Pawn(Team.WHITE));
			board.put("G2", new Pawn(Team.WHITE));

			board.put("A7", new Pawn(Team.BLACK));
			board.put("B7", new Pawn(Team.BLACK));
			board.put("C7", new Pawn(Team.BLACK));
			board.put("D7", new Pawn(Team.BLACK));
			board.put("E7", new Pawn(Team.BLACK));
			board.put("F7", new Pawn(Team.BLACK));
			board.put("G7", new Pawn(Team.BLACK));

			board.put("A8", new Rook(Team.BLACK));
			board.put("B8", new Knight(Team.BLACK));
			board.put("C8", new Bishop(Team.BLACK));
			board.put("D8", new King(Team.BLACK));
			board.put("E8", new Queen(Team.BLACK));
			board.put("F8", new Bishop(Team.BLACK));
			board.put("G8", new Knight(Team.BLACK));
			board.put("H8", new Rook(Team.BLACK));

			break;
		default:
			throw new NullPointerException("Layout must be set");
		}
	}
}
