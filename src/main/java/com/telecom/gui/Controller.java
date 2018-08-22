package com.telecom.gui;

import com.telecom.game.ControllerListener;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

	@FXML public TextField title;
	@FXML public TextField album;
	@FXML public TextField artist;
	@FXML public Button checkButton;

	private ControllerListener listener;
	private boolean check;

	private final static String correctGuessStyle = "-fx-border-color: green;";
	private final static String incorrectGuessStyle = "-fx-border-color: red;";
	
	public Controller(ControllerListener controllerListener) {
		this.listener = controllerListener;
		check = true;
	}

	@FXML protected void check(ActionEvent event) {
		if(check) {
			listener.checkEvent();
			checkButton.setText("Next");
			check = false;
		}
		else {
			listener.nextEvent();
			checkButton.setText("Check");
			check = true;
		}
	}

	public void guessCorrection(TextField field, String answer, boolean correctGuess) {
		field.setText(answer); // display answer
		field.setDisable(true); // disable modification
		if(correctGuess) {
			field.setStyle(correctGuessStyle);
		}
		else {
			field.setStyle(incorrectGuessStyle);
		}
	}

	public void reinitFields() {
		for(TextField field : new TextField[] {title, album, artist}) {
			field.setText(""); // reinit field
			field.setStyle(null); // reinit style
			field.setDisable(false); // enable modification
		}
		title.requestFocus();
	}
}

