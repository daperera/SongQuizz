package com.telecom.deezerAdapter;

import com.telecom.deezerAdapter.visitor.Visitor;

public interface JsonTreeItem {
	public void visit(Visitor visitor);
}
