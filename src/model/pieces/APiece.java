package model.pieces;

import model.Enums;
import model.Enums.Team;
import model.Enums.Type;

public abstract class APiece {

	private Team team;
	private Type type;
	// Constructor

	/**
	 * Create a Chess Piece, setting both the Team and the Type. This class is abstract, and is accessed via it's children;
	 * Rook, Knight, Bishop, King, Queen, Pawn.
	 * @param team An enum describing the two teams
	 * @param type An enum describing the 6 different types of Chess Piece
	 * @throws NullPointerException if either argument is null
	 */
	public APiece(Team team, Type type) throws NullPointerException {
		this.setTeam(team);
		this.setType(type);
	}

	// Abstract Methods

	public abstract boolean checkMoveValidity();

	/**
	 * @return the team
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * @param team the team to set
	 * @throws NullPointerException if argument null
	 */
	public void setTeam(Team team) throws NullPointerException {
		if (team == null) {
			throw new NullPointerException("Piece.setTeam(Team) was passed a null argument");
		}
		this.team = team;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 * @throws NullPointerException if argument null
	 */
	public void setType(Type type) {
		if (type == null) {
			throw new NullPointerException("Piece.setType(Type) was passed a null argument");
		}
		this.type = type;
	}

}
