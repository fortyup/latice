package latice.model;

public class Box {

	public Box(Tile tile, BoxType type) {
		this.tile=tile;
		this.type=type;
	}

	protected Tile tile;
	
	protected BoxType type;

	public BoxType getType() {
		return type;
	}
	
	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}
	
	public boolean isTileAt() {
		if (this.getTile() == Tile.NO)
			return false;
		else
			return true;
	}
	
}
