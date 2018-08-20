package deezerAdapter;
import com.google.gson.annotations.SerializedName;

import deezerAdapter.visitor.Visitor;

public class Playlist implements SingleChildNode {

	private TrackContainer tracks;

	@SerializedName("tracklist")
	private String tracklistUrl;

	public String getTracklist() {
		return tracklistUrl;
	}
	public void setTracklist(String tracklist) {
		this.tracklistUrl = tracklist;
	}

	private long id;

	public TrackContainer getTracks() {
		return tracks;
	}
	public void setTracks(TrackContainer tracks) {
		this.tracks = tracks;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public void visit(Visitor visitor) {
		visitor.visitPlaylist(this);
	}
	@Override
	public JsonNode getOnlyChild() {
		return tracks;
	}

/*
	@Override
	public void resolve() {
		
		try {
			if(DEBUG)
				System.out.println("resolving track");
			
			String response = JsonGetter.get(tracklistUrl);
			
			if(DEBUG)
				System.out.println("tracklist of playlist " + id + " " + response);


			tracks = new Gson().fromJson(response, TrackContainer.class);
			
			
			tracks.resolve();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
*/


}
