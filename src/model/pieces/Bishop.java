package model.pieces;

import model.ModelEnums;
import model.ModelEnums.Team;
import model.ModelEnums.Type;

public class Bishop extends APiece {

	public Bishop(Team team) {
		super(team, Type.KING);
	}

	@Override
	public boolean checkMoveValidity() {
		// TODO checkMoveValidity()
		return false;
	}
}
