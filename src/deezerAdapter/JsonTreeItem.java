package deezerAdapter;

import deezerAdapter.visitor.Visitor;

public interface JsonTreeItem {
	public void visit(Visitor visitor);
}
