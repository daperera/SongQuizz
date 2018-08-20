package deezerAdapter.visitor;

import java.io.IOException;

import com.google.gson.Gson;

import deezerAdapter.Album;
import deezerAdapter.AlbumContainer;
import deezerAdapter.JsonGetter;
import deezerAdapter.JsonTreeItem;
import deezerAdapter.JsonTreeRoot;
import deezerAdapter.TrackContainer;

public class JsonTreeBuilder implements Visitor {


	// syntaxic sugar
	public JsonTreeItem build() {
		JsonTreeItem root = new JsonTreeRoot();
		root.visit(this);
		TracksAlbumSetter setter = new TracksAlbumSetter();
		setter.set(root);
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
		// construct children
		try {
			String response = JsonGetter.get(album.getTracklistUrl());
			TrackContainer tracks = new Gson().fromJson(response, TrackContainer.class);
			album.setTracks(tracks);
		} catch (IOException e) { 
			e.printStackTrace();
		}
		visitChildren(album);
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
