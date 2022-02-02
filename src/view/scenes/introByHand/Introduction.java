package view.scenes.introByHand;

import java.awt.Color;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import view.ViewEnums.Palette;

public class Introduction extends Scene {

	private VBox panelTop;
	private Pane panelMid;
	private HBox panelBottom;

	public Introduction() {
		super(new VBox(getPanelTop()), 600, 600);
		//super.setF//(Palette.DARK_GREY.get());
	}

	private static VBox getPanelTop() {
		Label[] name = new Label[] { new Label("Chess!"),new Label("by"), new Label("Peter Marley") };
		for (int i = 0; i < name.length; i++) {
			name[i].setBorder(new Border(
					new BorderStroke[] {new BorderStroke(Paint.valueOf("#000000"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))}
					));
			name[i].setMaxWidth(Double.MAX_VALUE);
			name[i].setAlignment(Pos.CENTER);
			
		}
		name[0].setTextFill(Palette.RED.asPaint());
		name[1].setTextFill(Palette.WHITE.asPaint());
		name[2].setTextFill(Palette.WHITE.asPaint());
		
		
		VBox panel = new VBox(3);
		panel.setAlignment(Pos.CENTER);
		panel.setPrefHeight(300);
		panel.setPrefWidth(300);;
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
