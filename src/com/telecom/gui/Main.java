package com.telecom.gui;

import org.apache.commons.text.similarity.LevenshteinDistance;

import com.telecom.deezerAdapter.Track;
import com.telecom.game.ControllerListener;
import com.telecom.game.SongQuizz;
import com.telecom.game.Stats;
import com.telecom.game.User;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application implements ControllerListener{
	
	private User user;
	private Stats stats;
	private SongQuizz quizz;
	private Track currentTrack;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		new Window(stage, this);
		user = new User("");
		stats = new Stats();
		quizz = new SongQuizz();
		currentTrack = quizz.playNext();
	}

	@Override
	public void checkEvent(Controller controller) {
		// check guess accuracy
		String title = currentTrack.getTitle();
		String album = currentTrack.getAlbum().getTitle();
		String artist = currentTrack.getArtist().getName();
		boolean correctTitle = checkCorrectness(title, controller.title.getText());
		boolean correctAlbum = checkCorrectness(album, controller.album.getText());
		boolean correctArtist = checkCorrectness(artist, controller.artist.getText());

		// update stats
		if(correctTitle && correctAlbum && correctArtist) {
			stats.correctGuess(user, currentTrack);
		}
			
		
		// prompt result to user
		controller.title.setText(title);
		controller.album.setText(album);
		controller.artist.setText(artist);
		if(correctTitle)
			controller.title.setStyle("-fx-border-color: green;");
		else
			controller.title.setStyle("-fx-border-color: red;");
		if(correctAlbum)
			controller.album.setStyle("-fx-border-color: green;");
		else
			controller.album.setStyle("-fx-border-color: red;");
		if(correctArtist)
			controller.artist.setStyle("-fx-border-color: green;");
		else
			controller.artist.setStyle("-fx-border-color: red;");
			
	}

	@Override
	public void nextEvent(Controller controller) {
		// reinitialize field and style
		controller.title.setText("");
		controller.album.setText("");
		controller.artist.setText("");
		controller.title.setStyle("-fx-border-color: black;");
		controller.album.setStyle("-fx-border-color: black;");
		controller.artist.setStyle("-fx-border-color: black;");
		
		// play next track
		currentTrack = quizz.playNext();
	}
	
	private boolean checkCorrectness(String a, String b) {
		LevenshteinDistance distance = new LevenshteinDistance();
		return ((float) distance.apply(a,b) / (float) Math.max(a.length(), b.length())) < 0.4;
	}
	
}
