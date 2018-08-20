package deezerAdapter.visitor;

import deezerAdapter.Album;
import deezerAdapter.JsonTreeItem;
import deezerAdapter.Track;

public class TracksAlbumSetter implements Visitor {
	
	private Album currentAlbum;
	
	public void set(JsonTreeItem tree) {
		tree.visit(this);
	}
	
	@Override
	public void visitAlbum(Album album) {
		currentAlbum = album;
		visitChildren(album);
	}
	
	@Override
	public void visitTrack(Track track) {
		track.setAlbum(currentAlbum);
	}
}
