package com.telecom.game;
import java.util.List;

import java.util.concurrent.ThreadLocalRandom;

import com.telecom.deezerAdapter.JsonTreeItem;
import com.telecom.deezerAdapter.Track;
import com.telecom.deezerAdapter.visitor.JsonTreeBuilder;
import com.telecom.deezerAdapter.visitor.TrackCollector;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;



public class SongQuizz {

	private List<Track> tracks;
	MediaPlayer player;

	public SongQuizz() {

		// create Json hierarchy from the query to deezer api
		JsonTreeItem tree = new JsonTreeBuilder().build();

		// create a visitor that will collect tracks on this tree
		TrackCollector collector = new TrackCollector();
		tracks = collector.collect(tree);

	}

	public Track playNext() {
		// stop current track
		if(player != null)
			player.stop();

		// select a song at random
		int randomNum = ThreadLocalRandom.current().nextInt(0, tracks.size());
		Track track = tracks.get(randomNum);

		//  stream song from url
		Media media = new Media(track.getUrl());
		player = new MediaPlayer(media); 
		player.play();
		
		
		// download track from URL and save path
		//String path = track.downloadFromURL();

		// play the downloaded file
//		soundToPlay = new SoundJLayer(path);
//		soundToPlay.play();

		// return the track that is playing
		return track;
	}

}
