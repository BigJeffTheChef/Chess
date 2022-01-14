package marlChessV2;

public class Pawn extends ChessPiece {

	private static int blackPawnCount = 0;
	private static int whitePawnCount = 0;

	private int pieceNum;
	private boolean hasMoved;

	public Pawn(char team) {
		super(team);
		this.setPieceNum();
	}

	@Override
	public boolean movePiece(ChessBoard b, int[] s, int[] d) {
		// declare and initialise flags and vars
		boolean destinationIsEmpty = (b.getSquare(d) == null) ? true : false;
		boolean destinationHasOpponent = false;
		boolean vectorIsValid = false;
		boolean canTakePiece = false;
		int maxMoveDistance = 1;
		int direction = (b.getCurrentPlayer() == 'w') ? 1 : -1;

		// check destination
		if (!destinationIsEmpty) {
			destinationHasOpponent = (b.getSquare(d).getTeam() != b.getCurrentPlayer());
		}

		// check if pawn has not yet moved (meaning it can move 2 spaces)
		if (!hasMoved) {
			maxMoveDistance = 2;
			// check if route is blocked
		}

		if (!destinationHasOpponent) {
			if (b.getSquare(s[0] + direction, s[1]) != null) {
				//TODO check both the intermediate square AND destination square when distance is 2
				return false;
			} else {

			}
		}

		// check vector validity and if source piece can capture destination piece
		if (destinationIsEmpty && s[1] == d[1]) {
			// check if destination is within same row and within movable distance
			if (d[0] == s[0] + direction || d[0] == s[0] + (direction * maxMoveDistance)) {
				vectorIsValid = true;
			}
		} else if (destinationHasOpponent && (s[1] - 1 == d[1] || s[1] + 1 == d[1]) && s[0] + direction == d[0]) {
			// check opponent is one column left or right and one row away (with directionality) - can capture
			vectorIsValid = true;
			canTakePiece = true;
		}

		if (vectorIsValid) {
			hasMoved = true;
			return true;
		}

		return false;
	}

	//////////////////////////////
	//	Get, Set and toString	//
	//////////////////////////////

	private void setPieceNum() {
		this.pieceNum = (this.getTeam() == 'w') ? ++whitePawnCount : ++blackPawnCount;
	}

	public int getPieceNum() {
		return this.pieceNum;
	}

	@Override
	public String toString() {
		String team = (super.getTeam() == 'w') ? "White" : "Black";
		return team + " Pawn " + this.getPieceNum();
	}

}
