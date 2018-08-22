package com.telecom.gui;

import com.telecom.deezerAdapter.Track;
import com.telecom.game.Stats;
import com.telecom.game.TrackFinder;
import com.telecom.game.User;
import com.telecom.misc.StringComparator;
import com.telecom.misc.TrackPlayer;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application implements ControllerListener, ModeChangesListener {

	private Controller controller;
	private User user;
	private Stats stats;
	private TrackFinder quizz;
	private Track currentTrack;
	private TrackPlayer player;
	private StringComparator comparator;
	private InterfaceMode mode;

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
		comparator = new StringComparator();
		mode = new InterfaceMode(this);
		player = new TrackPlayer();
		playNextTrack();
	}

	/**
	 * fired when passing to 'Next' Mode
	 */
	@Override
	public void setNextMode() {
		// check guess accuracy
		String title = currentTrack.getTitle();
		String album = currentTrack.getAlbum().getTitle();
		String artist = currentTrack.getArtist().getName();
		boolean correctTitle = comparator.match(title, controller.title.getText());
		boolean correctAlbum = comparator.match(album, controller.album.getText());
		boolean correctArtist = comparator.match(artist, controller.artist.getText());

		// update stats
		if(correctTitle && correctAlbum && correctArtist) {
			stats.correctGuess(user, currentTrack);
		}

		// prompt result to user
		controller.guessCorrection(controller.title, title, correctTitle);
		controller.guessCorrection(controller.album, album, correctAlbum);
		controller.guessCorrection(controller.artist, artist, correctArtist);
		controller.setNextMode();
	}

	/**
	 * fired when 'next' button clicked
	 */
	@Override
	public void setCheckMode() {
		// reinit field content and style
		controller.reinitFields(); 
		controller.setCheckMode();
		playNextTrack(); // play next track
	}
	
	public void userEvent() {
		mode.userEvent();
	}

	public void playNextTrack() {
		currentTrack = quizz.getNextTrack();
		player.play(currentTrack);
		player.setOnEndOfTrack(() -> {mode.endOfTrack();}); // set listener to the end of the current track
	}

}
