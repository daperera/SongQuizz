package deezerAdapter.visitor;

import java.util.List;

import deezerAdapter.Album;
import deezerAdapter.AlbumContainer;
import deezerAdapter.Artist;
import deezerAdapter.JsonNode;
import deezerAdapter.JsonTreeItem;
import deezerAdapter.JsonTreeRoot;
import deezerAdapter.Playlist;
import deezerAdapter.PlaylistContainer;
import deezerAdapter.Track;
import deezerAdapter.TrackContainer;

public interface Visitor {
	public default void visitQueryRoot(JsonTreeRoot root) {
		visitChildren(root);
	}

	public default void visitPlaylistContainer(PlaylistContainer playlistContainer) {
		visitChildren(playlistContainer);
	}
	
	public default void visitPlaylist(Playlist playlist) {
		visitChildren(playlist);
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
