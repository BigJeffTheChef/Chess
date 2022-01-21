package marlChessV2;

public abstract class ChessPiece {

	// instance variables
	private char team;

	//////////////////////////////
	//		CONSTRUCTORS		//
	//////////////////////////////

	/**
	 * This constructor makes a ChessPiece if all the arguments are accepted, otherwise throws InstantiationError with custom message
	 * 
	 * @param team "white", "black", "w", "b" (case insensitive)
	 */
	public ChessPiece(char team) throws InstantiationError {
		this.setTeam(team);
	}

	//////////////////////////////
	//			UTILITY			//
	//////////////////////////////

	@Override
	public abstract String toString();

	public abstract boolean movePiece(ChessBoard b, int[] s, int[] d);

	//////////////////////////////
	//	GETTERS AND SETTERS		//
	//////////////////////////////

	/**
	 * @return the team 'w' or 'b'
	 */
	public char getTeam() {
		return this.team;
	}

	/**
	 * Sets the team of the piece.
	 * 
	 * @param team - accepts "white", "w", "black" and "b".
	 * @throws InstantiationError throws if argument not accepted
	 */
	private void setTeam(char team) throws InstantiationError {
		if (team == 'w' || team == 'b') {
			this.team = team;
		} else {
			String errorMsg = String.format("Invalid team specified: \"%s\"", team);
			InstantiationError e = new InstantiationError(errorMsg);
			throw e;
		}
	}

} // end of class
