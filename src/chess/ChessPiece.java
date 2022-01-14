package chess;

import java.util.Arrays;

public class ChessPiece {
	private String pieceUnicode;
	private String team;
	private String piece;
	private int[] position;

	//	public ChessPiece() {
	//		this.pieceUnicode = "";
	//		this.piece = "";
	//		this.position = new int[] { 0, 0 };
	//	}

	public ChessPiece(String unicode, String team, String piece) {
		this.pieceUnicode = unicode;
		this.team = team;
		this.piece = piece;
		this.position = new int[] { 0, 0 };
	}

	public ChessPiece(String unicode, String team, String piece, int row, int col) {
		this.pieceUnicode = unicode;
		this.team = team;
		this.piece = piece;
		this.position = new int[] { row, col };
	}

	public String getName() {
		return this.piece;
	}

	public String getChar() {
		return this.pieceUnicode;
	}

	public int[] getPos() {
		return this.position;
	}
	
	public String getTeam() {
		return this.team;
	}
	public String getPiece() {
		return this.piece;
	}
}
