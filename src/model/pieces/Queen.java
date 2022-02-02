package model.pieces;

import model.ModelEnums;
import model.ModelEnums.Team;
import model.ModelEnums.Type;

public class Queen extends APiece {

	public Queen(Team team) {
		super(team, Type.KING);
	}

	@Override
	public boolean checkMoveValidity() {
		// TODO checkMoveValidity()
		return false;
	}
}
