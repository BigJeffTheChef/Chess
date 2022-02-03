package view.scenes.introByHand;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Driver extends Application {

	private Introduction i;

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		stage = new Introduction();
		stage.show();

	}

}
