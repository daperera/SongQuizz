package com.telecom.deezerAdapter.visitor;

import java.util.ArrayList;
import java.util.List;

import com.telecom.deezerAdapter.JsonTreeItem;
import com.telecom.deezerAdapter.Track;

public class TrackCollector implements Visitor {
	
	List<Track> tracks;
	
	public List<Track> collect(JsonTreeItem tree) {
		tracks = new ArrayList<Track>();
		tree.visit(this);
		return tracks;
	}
	
	public void visitTrack(Track track) {
		// collect track
		if(!track.getUrl().isEmpty()) // check that the url was correctly filled out
			tracks.add(track);
		//visitChildren(track);
	}
	
}
