package view;

import controller.Controller;
import view.scenes.introduction.Introduction;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;



public class View extends Application {

	///////////////////////////////////////////////////
	// Application main()							//
	/////////////////////////////////////////////////

	public static void main(String[] args) {
		Controller.initController();
		System.out.println("Goodbye! End of main()");
	}
	
	///////////////////////////////////////////////////
	// Application launch()							//
	/////////////////////////////////////////////////
	
	/**
	 * A static method that allows the Controller to control execution.
	 * Controller ensures the Model has been created successfully, or forces
	 * the program to terminate by allowing the program to reach end of main()
	 */
	public static void launchGUI() {
		launch();
	}

	/**
	 * Initialise the GUI
	 */
	@Override
	public void start(Stage introStage) throws Exception {
		System.out.println("start() called");
		introStage = new Introduction();
		introStage.show();

	}
	
	/**
	 * Quit the game with a confirmation alert
	 * @param stage The stage to quit from
	 */
	public void quit(Stage stage) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Quit");
		alert.setHeaderText("You are about to quit!");
		alert.setContentText("Are you sure?");
		if (alert.showAndWait().get() == ButtonType.OK) {
			stage.close();
		}
	}
}
