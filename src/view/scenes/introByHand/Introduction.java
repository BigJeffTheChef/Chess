package view.scenes.introByHand;

import java.awt.Color;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import view.ViewEnums.Dimensions;
import view.ViewEnums.Palette;

public class Introduction extends Scene {

	private VBox panelTop;
	private Pane panelMid;
	private HBox panelBottom;
	
	private BackgroundImage bgImage;

	//private static VBox root = getMyRoot();

	private static VBox getMyRoot() {
		VBox root = new VBox();
		root.getChildren().add(getPanelTop());
		root.setBackground(new Background(new BackgroundFill(Palette.DARK_GREY.get(), CornerRadii.EMPTY, Insets.EMPTY)));
		root.setPrefHeight(Dimensions.INTRO_WINDOW.getHeight());
		root.setPrefWidth(Dimensions.INTRO_WINDOW.getWidth());
		BackgroundImage bgBgImage = new BackgroundImage(bgImage, 
				BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				BackgroundSize.DEFAULT);
		Background bg = new Background(bgBgImage);
		root.setBackground(bg);
		return root;
	}

	public Introduction() {
		super(getMyRoot());
	}

	private static VBox getPanelTop() {
		Label[] name = new Label[] { new Label("Chess!"), new Label("by"), new Label("Peter Marley") };
		for (int i = 0; i < name.length; i++) {
			name[i].setBorder(new Border(
					new BorderStroke[] {
							new BorderStroke(Paint.valueOf("#000000"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1)) }));
			name[i].setMaxWidth(Double.MAX_VALUE);
			name[i].setAlignment(Pos.CENTER);

		}
		
		name[0].setTextFill(Palette.RED.get());
		name[0].setFont(Font.font("Arial", FontWeight.BOLD, 30));
		name[1].setTextFill(Palette.WHITE.get());
		name[1].setFont(Font.font("Arial", FontWeight.BOLD, 20));
		name[2].setTextFill(Palette.WHITE.get());
		name[2].setFont(Font.font("Arial", FontWeight.BOLD, 20));


		VBox panel = new VBox(3);
		panel.setAlignment(Pos.CENTER);
		panel.setPrefHeight(300);
		panel.setPrefWidth(300);
		;
		panel.setFillWidth(true);
		panel.getChildren().addAll(name[0], name[1], name[2]);
		return panel;
	}

	private Pane getPanelMid() {
		return null;
	}

	private HBox getPanelBottom() {
		return null;
	}

}
