package model;

public class King extends Piece {

	// Fields

	public static int kingCounter = 0;
	public int kingNumber;

	// Constructor

	public King(int team) {
		super(team);
		this.setKingNumber();
	}

	// Overrides

	@Override
	public boolean checkMoveValidity() {
		// TODO checkMoveValidity() KING
		return false;
	}

	// Getters and Setters

	public void setKingNumber() {
		this.kingNumber = ++kingCounter;
	}

	public int getKingNumber() {
		return this.kingNumber;
	}

}
