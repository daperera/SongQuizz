package com.telecom.deezerAdapter;

import com.google.gson.annotations.SerializedName;
import com.telecom.deezerAdapter.visitor.Visitor;

public class Album implements SingleChildNode {
	private long id;
	private String title;
	private TrackContainer tracks;
	
	@SerializedName("tracklist")
	private String tracklistUrl;
	
	@SerializedName("cover")
	private String coverUrl;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public TrackContainer getTracks() {
		return tracks;
	}
	public void setTracks(TrackContainer tracks) {
		this.tracks = tracks;
	}
	public String getTracklistUrl() {
		return tracklistUrl;
	}
	public void setTracklistUrl(String tracklistUrl) {
		this.tracklistUrl = tracklistUrl;
	}
	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	
	@Override
	public void visit(Visitor visitor) {
		visitor.visitAlbum(this);
	}
	@Override
	public JsonTreeItem getOnlyChild() {
		return tracks;
	}
}
