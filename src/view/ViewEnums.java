package view;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ViewEnums {
	public enum Palette {
		WHITE("#ffffff", 255, 255, 255),
		BLUE("#5866ef", 88, 102, 239),
		GREEN("#3da560", 61, 165, 96),
		YELLOW("#f9a62b", 249, 166, 43),
		RED("#ec4145", 236, 65, 69),
		LILAC("#9b84ec", 155, 132, 236),
		CORAL("#f37b68", 243, 123, 104),
		TURQUOISE("#49ddc1", 73, 221, 193),
		GREY("#4f5d7e", 79, 93, 126),
		PURPLE("#583694", 88, 54, 148),
		LIGHT_BLUE("#09b0f2", 9, 176, 242),
		DARK_GREY("#2f3136", 47, 49, 54),
		NOT_BLACK_YET("#23272a", 35, 39, 42),
		BLACK("#000000", 0, 0, 0);

		private Paint p;
		private Color c;

		private Palette(String p, int r, int g, int b) {
			this.p = Paint.valueOf(p);
			this.c = (Color) this.p;
		}

		public Paint asPaint() {
			return p;
		}

		public Color asColor() {
			return c;
		}
	}
}
