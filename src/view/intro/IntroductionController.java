package view.intro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class IntroductionController {
	
	@FXML
	private Button quitButton;
	@FXML
	private GridPane introductionPane;
	Stage stage;
	
	public void quit(ActionEvent e) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Quit");
		alert.setHeaderText("You are about to quit!");
		alert.setContentText("Are you sure?");
		if (alert.showAndWait().get() == ButtonType.OK) {
			stage = (Stage) introductionPane.getScene().getWindow();
			stage.close();
		}
	}
	
	public void options() {
		Stage s = new Stage();
		Scene sc = new Scene(new Group());
		s.show();
	}
	
	public void play() {
		//TODO play button in intro screen
	}
}
