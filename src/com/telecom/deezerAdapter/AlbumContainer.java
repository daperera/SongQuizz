package com.telecom.deezerAdapter;

import java.util.ArrayList;
import java.util.List;

import com.telecom.deezerAdapter.visitor.Visitor;

public class AlbumContainer implements JsonNode {
	
	private List<Album> data;

	public List<Album> getData() {
		return data;
	}
	public void setData(List<Album> data) {
		this.data = data;
	}

	@Override
	public List<JsonTreeItem> getChildren() {
		return new ArrayList<JsonTreeItem>(data);
	}
	@Override
	public void visit(Visitor visitor) {
		visitor.visitAlbumContainer(this);
	}
}
