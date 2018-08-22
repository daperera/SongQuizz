package com.telecom.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {

	@FXML public TextField title;
	@FXML public TextField album;
	@FXML public TextField artist;
	@FXML public Button checkButton;

	private ControllerListener listener;

	private final static String correctGuessStyle = "-fx-border-color: green;";
	private final static String incorrectGuessStyle = "-fx-border-color: red;";
	
	public Controller(ControllerListener controllerListener) {
		this.listener = controllerListener;
	}

	@FXML protected void check(ActionEvent event) {
		listener.userEvent();
	}
	
	@FXML
	private void handleOnKeyPressed(KeyEvent event)
	{
	    if(event.getCode().equals(KeyCode.ENTER)) {
	    	listener.userEvent();
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
	
	public void setCheckMode() {
		checkButton.setText("Check");
	}
	
	public void setNextMode() {
		checkButton.setText("Next");
	}
	
}

