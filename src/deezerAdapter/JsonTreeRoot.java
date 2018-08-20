package deezerAdapter;

import deezerAdapter.visitor.Visitor;

public class JsonTreeRoot implements SingleChildNode {
	
	private AlbumContainer albumContainer;
	
	public AlbumContainer getAlbumContainer() {
		return albumContainer;
	}
	public void setAlbumContainer(AlbumContainer albumContainer) {
		this.albumContainer = albumContainer;
	}

	@Override
	public void visit(Visitor visitor) {
		visitor.visitQueryRoot(this);
	}
	
	@Override
	public JsonNode getOnlyChild() {
		return albumContainer;
	}

}
