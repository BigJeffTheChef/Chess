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
import javafx.stage.Stage;
import view.ViewEnums.Dimensions;
import view.ViewEnums.Palette;

/**
 * Creates a custom stage object configured to be the introduction screen of my Chess program
 * @author Peter Marley
 * @StudentNum 13404067
 * @email pmarley03@qub.ac.uk
 * @GitHub BigJeffTheChef
 *
 */
public class Introduction extends Stage {

	private Scene scene;
	private Parent root;

	private VBox panelTop;
	private Pane panelMid;
	private HBox panelBottom;

	private Background bgImage;

	public Introduction() {
		super();
		this.setBackground("./ChessImage2square.jpeg");
		this.setPanelTop();
		this.setPanelMid();
		this.setPanelBottom();
		this.setRoot();
		this.createMyScene();
		this.setScene(scene);

	}
	
	/**
	 * Create the scene, adding the root, and specifying dimensions
	 */
	private void createMyScene() {
		this.scene = new Scene(root, Dimensions.INTRO_WINDOW.getWidth(), Dimensions.INTRO_WINDOW.getHeight());
	}

	/**
	 * create a Background object for the root
	 * @param file A String representing the filepath relative to project root directory
	 */
	private void setBackground(String file) {
		BackgroundImage bgImage = new BackgroundImage(new Image("file:./" + file),
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				BackgroundSize.DEFAULT);
		this.bgImage = new Background(bgImage);

	}

	/**
	 * Instantiate and configure the top panel of Chess Introduction Stage. Contains program title and my name
	 */
	private void setPanelTop() {
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


		panelTop = new VBox(3);
		panelTop.setAlignment(Pos.CENTER);
		panelTop.setPrefHeight(120);
		panelTop.setPrefWidth(300);
		panelTop.setFillWidth(true);
		panelTop.getChildren().addAll(name[0], name[1], name[2]);
	}

	/**
	 * Instantiate and configure the root VBox for this stage
	 */
	private void setRoot() {
		VBox root = new VBox();
		root.getChildren().addAll(panelTop, panelMid, panelBottom);
		root.setBackground(new Background(new BackgroundFill(Palette.DARK_GREY.get(), CornerRadii.EMPTY, Insets.EMPTY)));
		root.setPrefHeight(Dimensions.INTRO_WINDOW.getHeight());
		root.setPrefWidth(Dimensions.INTRO_WINDOW.getWidth());
		root.setBackground(bgImage);
		this.root = root;
	}


	private void setPanelMid() {
		panelMid = new Pane();
	}

	private void setPanelBottom() {
		panelBottom = new HBox();
	}


}
