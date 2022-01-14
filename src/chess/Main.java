package chess;

public class Main {

	public static void main(String[] args) {
		ChessBoard board = new ChessBoard("Team 1", "Team 2");
		board.displayBoard();
		board.testPrintPiece();
	}

}
