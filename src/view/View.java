package view;

import controller.Controller;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Enums.Layout;
import model.Model;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class View extends Application {

//	private static View v;
//	private static Controller c;
//	private static Model m;

	///////////////////////////////////////////////////
	// Application main()							//
	/////////////////////////////////////////////////

	public static void main(String[] args) {
		Controller.initController(args);
	}
	
	///////////////////////////////////////////////////
	// Application launch()							//
	/////////////////////////////////////////////////
	
	public static void launchGUI(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
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
