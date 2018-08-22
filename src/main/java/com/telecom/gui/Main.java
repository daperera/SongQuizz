package com.telecom.gui;

import org.apache.commons.text.similarity.LevenshteinDistance;

import com.telecom.deezerAdapter.Track;
import com.telecom.game.ControllerListener;
import com.telecom.game.Stats;
import com.telecom.game.TrackFinder;
import com.telecom.game.TrackPlayer;
import com.telecom.game.User;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application implements ControllerListener {
	
	private Controller controller;
	private User user;
	private Stats stats;
	private TrackFinder quizz;
	private Track currentTrack;
	private TrackPlayer player;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Window window = new Window(stage, this);
		controller = window.getController();
		user = new User("");
		stats = new Stats();
		quizz = new TrackFinder();
		player = new TrackPlayer();
		playNextTrack();
	}

	/**
	 * fired when 'check' button clicked
	 */
	@Override
	public void checkEvent() {
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
		controller.guessCorrection(controller.title, title, correctTitle);
		controller.guessCorrection(controller.album, album, correctAlbum);
		controller.guessCorrection(controller.artist, artist, correctArtist);
	}

	/**
	 * fired when 'next' button clicked
	 */
	@Override
	public void nextEvent() {
		controller.reinitFields(); // reinit field content and style
		playNextTrack(); // play next track
	}
	
	private boolean checkCorrectness(String a, String b) {
		LevenshteinDistance distance = new LevenshteinDistance();
		return ((float) distance.apply(a,b) / (float) Math.max(a.length(), b.length())) < 0.4;
	}

	public void playNextTrack() {
		currentTrack = quizz.getNextTrack();
		player.play(currentTrack);
		player.setOnEndOfTrack(() -> {checkEvent();});
	}

}
