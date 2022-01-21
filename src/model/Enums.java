package model;

public class Enums {
	public enum Team {
		BLACK(0),
		WHITE(1);

		private int teamNumber;

		private Team(int team) {
			this.teamNumber = team;
		}

		public int value() {
			return this.teamNumber;
		}
	}
}
