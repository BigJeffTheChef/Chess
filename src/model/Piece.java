package model;

public abstract class Piece {

	// Fields
	
	private static int pieceCounter = 0;
	private int pieceNumber;

	// Constructor
	
	public Piece() {
		this.setPieceNumber();
	}
	
	// Abstract Methods
	
	public abstract boolean checkMoveValidity();
	
	// Getters and Setters
	
	public void setPieceNumber() {
		this.pieceNumber = ++pieceCounter;
	}
	
	public int getPieceNumber() {
		return this.getPieceNumber();
	}
}
