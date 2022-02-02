package model.pieces;

import model.ModelEnums;
import model.ModelEnums.Team;
import model.ModelEnums.Type;

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
