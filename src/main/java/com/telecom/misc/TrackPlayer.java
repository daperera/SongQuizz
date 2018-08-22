package com.telecom.misc;

import com.telecom.deezerAdapter.Track;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class TrackPlayer {
	private MediaPlayer player;

	public void play(Track track) {
		// stop current track
		if(player != null)
			player.stop();

		//  stream new track from url
		Media media = new Media(track.getUrl());
		player = new MediaPlayer(media); 
		player.play();
	}

	public void setOnEndOfTrack(Runnable r) {
		// register listener to the end of the media
		if(player != null)
			player.setOnEndOfMedia(r);
	}
	
}
