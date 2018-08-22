package com.telecom.gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Window {
	
	private Controller controller;
	
	public Window(Stage stage, ControllerListener controllerListener) {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("window.fxml"));
		
		controller = new Controller(controllerListener);
		loader.setController(controller);
		
		Parent root;
		try {
			root = loader.load();
			
			Scene scene = new Scene(root, 300, 275);
			stage.setTitle("FXML Welcome");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Controller getController() {
		return controller;
	}
}
