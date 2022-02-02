package model.pieces;

import model.ModelEnums;
import model.ModelEnums.Team;
import model.ModelEnums.Type;

public class Knight extends APiece {

	public Knight(Team team) {
		super(team, Type.PAWN);
	}

	@Override
	public boolean checkMoveValidity() {
		// TODO checkMoveValidity()
		return false;
	}
}
