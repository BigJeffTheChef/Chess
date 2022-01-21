package model;

public abstract class Piece {

	// Fields

	private static int whitePieceCounter = 0;
	private static int blackPieceCounter = 0;

	// Constructor

	public Piece(int team) {
		this.addPieceNum(team);
	}

	// Abstract Methods

	public abstract boolean checkMoveValidity();

	// Getters and Setters

	/**
	 * add this piece to the count of this teams piece
	 * @param team
	 */
	private void addPieceNum(int team) {
		if (team == 0) {
			blackPieceCounter++;
		} else {
			whitePieceCounter++;
		}
	}
}
