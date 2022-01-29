package model.pieces;

import model.Enums;
import model.Enums.Team;
import model.Enums.Type;

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
