package model;

public class Enums {
	public enum Team {
		BLACK(0, "Black"),
		WHITE(1, "White");

		private int number;
		private String name;

		private Team(int team, String name) {
			this.number = team;
			this.name = name;
		}

		public int getTeamNumber() {
			return this.number;
		}

		public String getTeamName() {
			return this.name;
		}


	}

	public enum Type {
		PAWN,
		ROOK,
		BISHOP,
		KNIGHT,
		QUEEN,
		KING;
	}

	public enum Layout {
		NORMAL(8),
		TESTING(8);

		private int boardWidth;

		private Layout(int width) {
			this.boardWidth = width;
		}

		public int getWidth() {
			return this.boardWidth;
		}

	}

}
