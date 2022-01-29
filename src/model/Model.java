package model;

import java.util.Hashtable;

import model.Enums.Team;

public class Model {

	// Fields

	private Hashtable<String,Piece>board = new Hashtable<>(64);

	// Constructor

	public Model() {
		this.setBoard();
	}

	// Getters and Setters

	public void setBoard(Team team) {
		if (team == WHITE) {
			
		}
	}

}
