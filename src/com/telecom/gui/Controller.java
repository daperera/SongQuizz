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
	
    public Controller(ControllerListener controllerListener) {
    	this.listener = controllerListener;
    	check = true;
    }

	@FXML protected void check(ActionEvent event) {
		if(check) {
			listener.checkEvent(this);
			checkButton.setText("Next");
			check = false;
		}
		else {
			listener.nextEvent(this);
			checkButton.setText("Check");
			check = true;
		}
    }
	
}

