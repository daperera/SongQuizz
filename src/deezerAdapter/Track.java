package deezerAdapter;
import java.io.IOException;

import com.google.gson.annotations.SerializedName;

import deezerAdapter.visitor.Visitor;
import misc.Utils;

public class Track implements JsonLeaf {

	private long id;
	@SerializedName("preview")
	private String url;

	private String title;
	private Artist artist;
	private Album album;

	public void describe() {
		System.out.println("Title : " + title);
		System.out.println("Artist : " + artist.getName());
		System.out.println("Album : " + album.getTitle());
	}

	public String downloadFromURL() {
		String path = null;
		try {
			// download and save file
			String scheme = Utils.getFileNameExtention(url);
			 path = "data/tmp/" + id + "." + scheme;
			JsonGetter.downloadMp3(url, path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	/*
	@Override
	public List<JsonTreeItem> getChildren() {
		List<JsonTreeItem> children = new ArrayList<JsonTreeItem>();
		children.add(artist);
		children.add(album);
		return children;
	}
	 */

	@Override
	public void visit(Visitor visitor) {
		visitor.visitTrack(this);
	}

}
