package view.intro;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.View;

public class Introduction extends Stage {

	private VBox root;
	private Scene scene;

	public Introduction() throws IOException {
		this.createRoot();
		this.createScene();
		this.defineCloseBehaviour();
		this.setScene(scene);
	}

	/**
	 * Create root node of this Introduction object
	 * 
	 * @throws IOException
	 */
	private void createRoot() throws IOException {
		root = FXMLLoader.load(getClass().getResource("Introduction.fxml"));
	}

	/**
	 * Create the scene of this Introduction object
	 */
	private void createScene() {
		scene = new Scene(root, 600, 600);
		scene.getStylesheets().add(getClass().getResource("../stylesGeneral.css").toExternalForm());
		scene.getStylesheets().add(getClass().getResource("introductionStyles.css").toExternalForm());
	}

	/**
	 * Define close behaviour of this Introduction object
	 */
	private void defineCloseBehaviour() {
		this.setOnCloseRequest(event -> {
			View.quit(this);
			event.consume();
		});
	}
}
