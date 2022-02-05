package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class CustomStage extends Stage {
	private VBox root;
	private Scene scene;
	private Region stuff;

	public CustomStage(String fileLocationFXML, String[] stylesheets) throws IOException {
		this.createRoot(fileLocationFXML);
		this.createScene(stylesheets);
		this.defineCloseBehaviour();
		this.setScene(scene);
		stuff = new Region();
	}

	/**
	 * Create root node of this Introduction object
	 * 
	 * @throws IOException
	 */
	private void createRoot(String fileLocationFXML) throws IOException {
		root = FXMLLoader.load(getClass().getResource(fileLocationFXML));
	}

	/**
	 * Create the scene of this Introduction object
	 */
	private void createScene(String[] stylesheets) {
		scene = new Scene(root, 600, 600);
		for (String sheet: stylesheets) {
			scene.getStylesheets().add(getClass().getResource(sheet).toExternalForm());
		}
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
