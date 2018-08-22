package com.telecom.deezerAdapter;
import java.util.ArrayList;
import java.util.List;

public interface SingleChildNode extends JsonNode {

	public JsonTreeItem getOnlyChild();
	
	@Override
	public default List<JsonTreeItem> getChildren() {
		ArrayList<JsonTreeItem> children = new ArrayList<JsonTreeItem>();
		children.add(getOnlyChild());
		return children;
	}

}
