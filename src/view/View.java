package view;

import java.util.Arrays;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.Enums.Layout;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class View extends Application {

	private final String[] args;
	
	public View(String[] args) {
		this.args = args;
		System.out.println("args: " + Arrays.deepToString(args));
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		System.out.println("start() called");
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
