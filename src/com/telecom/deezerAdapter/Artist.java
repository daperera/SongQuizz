package com.telecom.deezerAdapter;

import com.telecom.deezerAdapter.visitor.Visitor;

public class Artist implements JsonLeaf {
	private long id;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public void visit(Visitor visitor) {
		visitor.visitArtist(this);
	}
}
