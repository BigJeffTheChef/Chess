package model;

import java.util.Hashtable;

import model.Enums.BoardSetup;
import model.Enums.Team;
public class Model {

	// Fields

	private Hashtable<String,Piece> board;

	// Constructor

	public Model() {
		this.setBoard();
	}

	// Getters and Setters

	public void setBoard(BoardSetup setup) {
		board = new Hashtable<String, Piece>(64);
		switch (setup) {
		case NORMAL:
			board.put("D1", new King(1));
			break;
			default:
				//
		}
		// TODO set white pieces
		// TODO set black piece
	}

}
