package com.telecom.gui;

import java.io.IOException;

import com.telecom.game.ControllerListener;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Window {
	public Window(Stage stage, ControllerListener controllerListener) {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("window.fxml"));
		
		Controller playListViewController = new Controller(controllerListener);
		loader.setController(playListViewController);
		
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
}
