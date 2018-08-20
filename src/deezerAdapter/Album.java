package deezerAdapter;

import java.util.ArrayList;
import java.util.List;

import deezerAdapter.visitor.Visitor;

public class Album implements JsonNode {
	private long id;
	private String title;
	private String coverUrl;
	private TrackContainer tracks;
	
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
	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	
	@Override
	public List<JsonTreeItem> getChildren() {
		System.out.println("album title : " + title);
		System.out.println("album tracks : " + tracks);
		List<JsonTreeItem> children = new ArrayList<JsonTreeItem>();
		children.add(tracks);
		return children;
	}
	
	@Override
	public void visit(Visitor visitor) {
		visitor.visitAlbum(this);
	}
}
