package com.telecom.deezerAdapter;
import java.util.ArrayList;
import java.util.List;

import com.telecom.deezerAdapter.visitor.Visitor;

public class TrackContainer implements JsonNode {
	private List<Track> data;

	public List<Track> getData() {
		return data;
	}
	public void setData(List<Track> data) {
		this.data = data;
	}

	@Override
	public List<JsonTreeItem> getChildren() {
		return new ArrayList<JsonTreeItem>(data);
	}
	@Override
	public void visit(Visitor visitor) {
		visitor.visitTrackContainer(this);
	}
}
