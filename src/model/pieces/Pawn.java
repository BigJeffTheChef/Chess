package model.pieces;

import model.Enums;
import model.Enums.Team;
import model.Enums.Type;

public class Pawn extends APiece {

	public Pawn(Team team) {
		super(team, Type.PAWN);
	}

	@Override
	public boolean checkMoveValidity() {
		// TODO checkMoveValidity()
		return false;
	}
}
