package view;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ViewEnums {
	public enum Palette {
		WHITE("#ffffff"),
		BLUE("#5866ef"),
		GREEN("#3da560"),
		YELLOW("#f9a62b"),
		RED("#ec4145"),
		LILAC("#9b84ec"),
		CORAL("#f37b68"),
		TURQUOISE("#49ddc1"),
		GREY("#4f5d7e"),
		PURPLE("#583694"),
		LIGHT_BLUE("#09b0f2"),
		DARK_GREY("#2f3136"),
		NOT_BLACK_YET("#23272a"),
		BLACK("#000000");

		private Paint p;

		private Palette(String p) {
			this.p = Paint.valueOf(p);
		}

		public Paint get() {
			return p;
		}

		public Color asColor() {
			return (Color) p;
		}
	}

	public enum Dimensions {
		INTRO_WINDOW(600, 600);

		private int width, height;

		private Dimensions(int w, int h) {
			this.width = w;
			this.height = h;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}
	}
}
