package model;

public class King extends Piece {

	// Fields
	
	public static int kingCounter = 0;
	public int kingNumber;
	
	// Constructor
	
	public King() {
		this.setKingNumber();
	}
	
	// Overrides
	
	@Override
	public boolean checkMoveValidity() {
		// TODO Auto-generated method stub
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
