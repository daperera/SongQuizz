package deezerAdapter;
import java.util.List;

public interface JsonNode extends JsonTreeItem {
	public List<JsonTreeItem> getChildren();
}
