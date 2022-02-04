package view.game;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Stage {

	private BorderPane root;
	private Scene scene;
	private Pane center, top, right, bottom, left;

	public Game() {
		this.createRoot();
		this.createScene();
		this.setScene(scene);
	}

	private void createRoot() {
		root = new BorderPane(center, top, right, bottom, left);
	}

	private void createScene() {
		scene = new Scene(root, 800, 800);
	}
}
