package marlChessV1;

public class ChessPiece {

	// static variables
	private static int pieces = 0;

	// instance variables
	private String team;
	private String pieceType;
	private int pieceNum;

	//////////////////////////////
	//		CONSTRUCTORS		//
	//////////////////////////////

	/**
	 * This constructor makes a ChessPiece if all the arguments are accepted, otherwise throws InstantiationError with custom message
	 * 
	 * @param team  <br>
	 *              Accepts: [white, black, w, b] (case insensitive) <br>
	 *              sets to W or B
	 *              <hr>
	 * @param piece <br>
	 *              Accepts:
	 *              <br>
	 *              Pawn: [pa, pawn] sets to: Pawn
	 *              <br>
	 *              Rook: [ro, rook] sets to: Rook
	 *              <br>
	 *              Knight: [kn, knight] sets to: Knight
	 *              <br>
	 *              Bishop: [bi, bishop] sets to: Bishop
	 *              <br>
	 *              King: [ki, king] sets to: King
	 *              <br>
	 *              Queen: [qu, queen] sets to: Queen
	 *              <hr>
	 */
	public ChessPiece(String team, String piece) {
		try {
			this.setTeam(team);
			this.setPiece(piece);
			pieces++;
			this.pieceNum = pieces;
		} catch (InstantiationError e) {
			System.err.println(getFailureMessage());
		}
	}

	//////////////////////////////
	//			UTILITY			//
	//////////////////////////////

	/**
	 * @return returns a String for console printing
	 */
	public String getSuccessMessage() {
		return String.format("%n%n+++ChessPiece %d instantiation successful!%n\t%s", pieces, this.toString());
	}

	public String getFailureMessage() {
		return String.format("%n%n+++ChessPiece %d instantiation FAILED!", pieces);
	}

	@Override
	public String toString() {
		return String.format("ChessPiece [No = \"%s\" team=\"%s\" name=\"%s\"]",
				pieceNum, team, this.getPieceName(false));
	}

	//////////////////////////////
	//	GETTERS AND SETTERS		//
	//////////////////////////////

	/**
	 * @param lowerCase - a boolean to return a normalised lower case String
	 * @return the team "White", "Black", "white" or "black"
	 */
	public String getTeam(boolean lowerCase) {
		return (lowerCase) ? team.toLowerCase() : team;
	}

	/**
	 * Sets the team of the piece.
	 * 
	 * @param team - accepts "white", "w", "black" and "b".
	 * @throws InstantiationError throws if argument not accepted
	 */
	private void setTeam(String team) throws InstantiationError {
		if (team.equalsIgnoreCase("white") || team.equalsIgnoreCase("w")) {
			this.team = "White";
		} else if (team.equalsIgnoreCase("black") || team.equalsIgnoreCase("b")) {
			this.team = "Black";
		} else {
			String errorMsg = String.format("Invalid team specified: \"%s\"", team);
			InstantiationError e = new InstantiationError(errorMsg);
			throw e;
		}
	}

	/**
	 * @param lowerCase - a boolean, returns name as lowercase if true
	 * @return the piece fullName. First letter capitalised, the remaining in lower case e.g. "King", "Rook".
	 */
	public String getPieceName(boolean lowerCase) {
		return (lowerCase) ? pieceType.toLowerCase() : pieceType;
	}

	/**
	 * Set the pieceFullName.
	 * 
	 * @param piece - first two letters or entire name of piece, all lower case e.g. "pa" & "pawn"
	 * @throws InstantiationError throws if argument not accepted
	 */
	private void setPiece(String piece) throws InstantiationError {
		piece = piece.toLowerCase();
		switch (piece) {
		case "pa":
		case "pawn":
			this.pieceType = "Pawn";
			break;
		case "ro":
		case "rook":
			this.pieceType = "Rook";
			break;
		case "kn":
		case "knight":
			this.pieceType = "Knight";
			break;
		case "bi":
		case "bishop":
			this.pieceType = "Bishop";
			break;
		case "ki":
		case "king":
			this.pieceType = "King";
			break;
		case "qu":
		case "queen":
			this.pieceType = "Queen";
			break;
		default:
			String errorMsg = String.format("Invalid piece specified! piece: \"%s\"", piece);
			InstantiationError e = new InstantiationError(errorMsg);
			throw e;
		}
	}

	/**
	 * @return the number of pieces created
	 */
	public int getPieces() {
		return pieces;
	}
} // end of class
