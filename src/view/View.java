package view;

import controller.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class View extends Application {

	///////////////////////////////////////////////////
	// Application main()							//
	/////////////////////////////////////////////////

	public static void main(String[] args) {
		Controller.initController();
		System.out.println("end of main");
	}
	
	///////////////////////////////////////////////////
	// Application launch()							//
	/////////////////////////////////////////////////
	
	public static void launchGUI() {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		System.out.println("start() called");
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Introduction.fxml"));
			Scene scene = new Scene(root, 600, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
