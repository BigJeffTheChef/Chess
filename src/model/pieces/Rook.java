package model.pieces;

import model.Enums;
import model.Enums.Team;
import model.Enums.Type;

public class Rook extends APiece {

	public Rook(Team team) {
		super(team, Type.ROOK);
	}

	@Override
	public boolean checkMoveValidity() {
		// TODO checkMoveValidity()
		return false;
	}
}
