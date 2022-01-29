package model;

import java.util.Hashtable;

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
	
	public enum BoardSetup {
		NORMAL;
	}
	
}
