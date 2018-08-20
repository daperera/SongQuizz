package deezerAdapter.visitor;

import java.io.IOException;

import com.google.gson.Gson;

import deezerAdapter.AlbumContainer;
import deezerAdapter.JsonGetter;
import deezerAdapter.JsonTreeItem;
import deezerAdapter.JsonTreeRoot;

public class JsonTreeBuilder implements Visitor {

	private String url;

	public JsonTreeBuilder(String url) {
		this.url = url;

	}

	// syntaxic sugar
	public JsonTreeItem build() {
		JsonTreeItem root = new JsonTreeRoot();
		root.visit(this);
		return root;
	}
	
	@Override
	public void visitQueryRoot(JsonTreeRoot root) {
		try {
			String response = JsonGetter.get(url);
			AlbumContainer albumContainer = new Gson().fromJson(response, AlbumContainer.class);
			root.setAlbumContainer(albumContainer);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		visitChildren(root);
	}
	
	/*
	@Override
	public void visitPlaylist(Playlist playlist) {
		try {
			String response = JsonGetter.get(playlist.getTracklist());
			TrackContainer tracks = new Gson().fromJson(response, TrackContainer.class);
			playlist.setTracks(tracks);
		} catch (IOException e) { 
			e.printStackTrace();
		}
		visitChildren(playlist);
	}
	*/
}
