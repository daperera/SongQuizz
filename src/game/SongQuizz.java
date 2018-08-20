package game;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import deezerAdapter.JsonTreeItem;
import deezerAdapter.Track;
import deezerAdapter.visitor.JsonTreeBuilder;
import deezerAdapter.visitor.TrackCollector;
import misc.SoundJLayer;

public class SongQuizz {

	private List<Track> tracks;
	SoundJLayer soundToPlay;

	public SongQuizz() {
		
		// create Json hierarchy from the query to deezer api
		JsonTreeItem tree = new JsonTreeBuilder().build();

		// create a visitor that will collect tracks on this tree
		TrackCollector collector = new TrackCollector();
		tracks = collector.collect(tree);
		
	}
	
	public Track playNext() {
		// stop current track
		if(soundToPlay != null)
			soundToPlay.stop();
		
		// select a song at random
		int randomNum = ThreadLocalRandom.current().nextInt(0, tracks.size());
		Track track = tracks.get(randomNum);
		
		// download track from URL and save path
		String path = track.downloadFromURL();
		
		// play the downloaded file
		soundToPlay = new SoundJLayer(path);
		soundToPlay.play();
		
		// return the track that is playing
		return track;
	}

}
