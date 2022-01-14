package chess;

public class ChessBoard {
	/**
	 * 8x8
	 * 
	 * Piece per side:
	 * Each set consists of 16 pieces: one king, one queen, two rooks, two bishops, two knights, and eight pawns.
	 * 
	 */

	private Object[][] board;
	private String whiteName;
	private String blackName;

	private ChessPiece[] blackPawns;
	private ChessPiece[] blackSpecials;

	private ChessPiece[] whitePawns;
	private ChessPiece[] whiteSpecials;

	// blackKing, blackQueen, blackRook1, blackRook2, blackBishop1, blackBishop2, blackKnight1, blackKnight2;
	//	private ChessPiece whitePawn1, whitePawn2, whitePawn3, whitePawn4, whitePawn5, whitePawn6, whitePawn7, whitePawn8;
	//	private ChessPiece whiteKing, whiteQueen, whiteRook1, whiteRook2, whiteBishop1, whiteBishop2, whiteKnight1,
	//			whiteKnight2;
	//	

	public ChessBoard(String white, String black) {

		this.blackPawns = new ChessPiece[8];
		this.whitePawns = new ChessPiece[8];
		this.whiteSpecials = new ChessPiece[8];
		this.whiteName = white;
		this.blackName = black;

		// populate board with non-null values
		this.board = new Object[8][8];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = (String) "";
			}
		}
		// populate black/whitePawns arrays & place pieces
		for (int i = 0; i < 8; i++) {
			blackPawns[i] = new ChessPiece("\u265F", "Black", "Pawn " + (i + 1));
			board[1][i] = blackPawns[i];
			whitePawns[i] = new ChessPiece("\u2659", "White", "Pawn " + (i + 1));
			board[6][i] = whitePawns[i];
		}

		// populate black/whiteSpecials ChessPiece arrays & place pieces
		blackSpecials = new ChessPiece[] {
				new ChessPiece("\u265C", "Black", "Rook L"),
				new ChessPiece("\u265E", "Black", " Knight L"),
				new ChessPiece("\u265D", "Black", " Bishop L"),
				new ChessPiece("\u265B", "Black", " Queen"),
				new ChessPiece("\u265A", "Black", " King"),
				new ChessPiece("\u265D", "Black", " Bishop R"),
				new ChessPiece("\u265E", "Black", " Knight R"),
				new ChessPiece("\u265C", "Black", " Rook R")
		};
		whiteSpecials = new ChessPiece[] {
				new ChessPiece("\u2656", "White", " Rook L"),
				new ChessPiece("\u2658", "White", " Knight L"),
				new ChessPiece("\u2657", "White", " Bishop L"),
				new ChessPiece("\u2655", "White", " Queen"),
				new ChessPiece("\u2654", "White", " King"),
				new ChessPiece("\u265D", "White", " Bishop R"),
				new ChessPiece("\u2658", "White", " Knight R"),
				new ChessPiece("\u2656", "White", " Rook R")
		};
		for (int i = 0; i < 8; i++) {
			board[0][i] = blackSpecials[i];
			board[7][i] = whiteSpecials[i];
		}
	}

	public void displayBoard() {
		for (int i = 0; i < 8; i++) {
			System.out.println("-------------------------------------------------------------------------------------------------");
			for (int k = 0; k < 2; k++) {
				System.out.print("|");
				for (int j = 0; j < 8; j++) {
					if (board[i][j] instanceof ChessPiece) {
						System.out.printf(" %9s |", (k == 0) ? ((ChessPiece) board[i][j]).getTeam() : ((ChessPiece) board[i][j]).getPiece());
					} else {
						System.out.printf(" %9s |", (String) board[i][j]);
					}

				}
				System.out.println();
			}
			System.out.println();
		}

	}

	public void testPrintPiece() {
		System.out.println(blackPawns[0]);
		System.out.println(blackPawns[7]);
		System.out.println(whitePawns[0]);
		System.out.println(whitePawns[7]);
	}
}
