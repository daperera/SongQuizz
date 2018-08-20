package deezerAdapter;
import java.util.ArrayList;
import java.util.List;

import deezerAdapter.visitor.Visitor;

public class PlaylistContainer implements JsonNode {
	private List<Playlist> data;

	public List<Playlist> getData() {
		return data;
	}
	public void setData(List<Playlist> data) {
		this.data = data;
	}
	
	@Override
	public List<JsonTreeItem> getChildren() {
		return new ArrayList<JsonTreeItem>(data);
	}
	@Override
	public void visit(Visitor visitor) {
		visitor.visitPlaylistContainer(this);
	}
}
