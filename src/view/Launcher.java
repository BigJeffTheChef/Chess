package view;

import controller.Controller;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Enums.Layout;
import model.Model;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Launcher extends Application {

	private static View v;
	private static Controller c;
	private static Model m;

	///////////////////////////////////////////////////
	// Application main()							//
	/////////////////////////////////////////////////

	public static void main(String[] args)  {
		try {
			// Instantiate view
			m = new Model(Layout.NORMAL);
			c = new Controller();
			v = new View();
			launch(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
				v.start(primaryStage);
	}
}
