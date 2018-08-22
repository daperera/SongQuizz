package com.telecom.deezerAdapter.visitor;

import java.util.List;

import com.telecom.deezerAdapter.Album;
import com.telecom.deezerAdapter.AlbumContainer;
import com.telecom.deezerAdapter.Artist;
import com.telecom.deezerAdapter.JsonNode;
import com.telecom.deezerAdapter.JsonTreeItem;
import com.telecom.deezerAdapter.JsonTreeRoot;
import com.telecom.deezerAdapter.Track;
import com.telecom.deezerAdapter.TrackContainer;

public interface Visitor {
	public default void visitQueryRoot(JsonTreeRoot root) {
		visitChildren(root);
	}

	public default void visitAlbumContainer(AlbumContainer albumContainer) {
		visitChildren(albumContainer);
	}
	
	public default void visitTrackContainer(TrackContainer trackContainer) {
		visitChildren(trackContainer);
	}
	
	public default void visitAlbum(Album album) {
		visitChildren(album);
	}
	
	public default void visitTrack(Track track) {
		// do nothing
	}
	
	public default void visitArtist(Artist artist) {
		// do nothing
	}
	
	default void visitChildren(JsonNode node) {
		List<JsonTreeItem> children = node.getChildren();
		for(JsonTreeItem child : children) {
			child.visit(this);
		}
	}

	

}
