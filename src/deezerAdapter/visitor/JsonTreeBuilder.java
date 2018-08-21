package deezerAdapter.visitor;

import java.io.IOException;

import com.google.gson.Gson;

import deezerAdapter.Album;
import deezerAdapter.AlbumContainer;
import deezerAdapter.JsonGetter;
import deezerAdapter.JsonTreeItem;
import deezerAdapter.JsonTreeRoot;
import deezerAdapter.Track;
import deezerAdapter.TrackContainer;

public class JsonTreeBuilder implements Visitor {
	
	private Album currentAlbum;

	// syntaxic sugar
	public JsonTreeItem build() {
		JsonTreeItem root = new JsonTreeRoot();
		root.visit(this);
		return root;
	}
	
	@Override
	public void visitQueryRoot(JsonTreeRoot root) {
		try {
			// create url for deezer playlist query
			String url = "https://api.deezer.com/user/1357015064/albums";
			String response = JsonGetter.get(url);
			AlbumContainer albumContainer = new Gson().fromJson(response, AlbumContainer.class);
			root.setAlbumContainer(albumContainer);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		visitChildren(root);
	}
	
	@Override
	public void visitAlbum(Album album) {
		// record current album (to fill the proper child field)
		currentAlbum = album;
		
		// construct children
		try {
			String response = JsonGetter.get(album.getTracklistUrl());
			TrackContainer tracks = new Gson().fromJson(response, TrackContainer.class);
			album.setTracks(tracks);
		} catch (IOException e) { 
			e.printStackTrace();
		}
		
		// visit children
		visitChildren(album);
	}
	
	@Override
	public void visitTrack(Track track) {
		track.setAlbum(currentAlbum);
	}
	
}
