package model;

import java.awt.Color;

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
		TESTING(8),
		UNIT_TESTS(8);

		private int boardWidth;

		private Layout(int width) {
			this.boardWidth = width;
		}

		public int getWidth() {
			return this.boardWidth;
		}

	}
	
	public enum Palette {
		WHITE(Color.decode("#ffffff")),
		BLUE(Color.decode("#5866ef")),
		GREEN(Color.decode("3da560")),
		YELLOW(Color.decode("#f9a62b")),
		RED(Color.decode("#ec4145")),
		LILAC(Color.decode("#9b84ec")),
		CORAL(Color.decode("#f37b68")),
		TURQUOISE(Color.decode("49ddc1")),
		GREY(Color.decode("4f5d7e")),
		PURPLE(Color.decode("583694")),
		LIGHT_BLUE(Color.decode("09b0f2"));
		
		private Color color;
		
		private Palette(Color color) {
			this.color = color;
		}
		
		public Color get() {
			return this.color;
		}
		
	}
	
	public enum StoredStrings {
		PLAYER_1_NAME("Black Name Unset"),
		PLAYER_2_NAME("White Name Unset");
		
		private String name;
		
		private StoredStrings(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return this.name;
		}
	}

}

